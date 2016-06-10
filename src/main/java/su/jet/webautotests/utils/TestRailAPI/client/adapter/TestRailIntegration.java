package su.jet.webautotests.utils.TestRailAPI.client.adapter;

import su.jet.webautotests.utils.TestRailAPI.client.APIClient;
import su.jet.webautotests.utils.TestRailAPI.client.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.PropertyProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestRailIntegration {

    private static String base_url = PropertyProvider.getInstance().getTEST_RAIL_BASE_URI();
    private static String user = PropertyProvider.getInstance().getTEST_RAIL_LOGIN();
    private static String password = PropertyProvider.getInstance().getTEST_RAIL_PASSWORD();
    public static APIClient client = new APIClient(base_url);

    private static Map restMap;
    private static TestRailIntegration instance;

    public TestRailIntegration() throws IOException, APIException {
        restMap = runTestRailBuilder();
    }

    public enum uri {
        GET_PROJECTS("get_projects"),
        ADD_SUITE("add_suite/%s"),
        GET_SUITE("get_suites/%s"),
        GET_PLANS("get_plans/%s"),
        ADD_PlAN_ENTRY("add_plan_entry/%s"),
        GET_PlAN_ENTRY("get_plan_entry/%s"),
        ADD_RESULT_FOR_TEST("add_result_for_case/%s/%s"),
        ADD_RESULT_FOR_CASE("add_result_for_case/%s/%s"),
        GET_TEST_CASES("get_tests/%s/");

        private final String value;

        uri(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    /**
     * Метод сетит ранее объявленые данные авториазции
     */
    public static void setAuthCredits() {
        client.setUser(user);
        client.setPassword(password);
    }

    /**
     * Метод ищет project по названию и возвращает его id
     *
     * @return
     * @throws IOException
     * @throws APIException
     */
    public String getProjectId() throws IOException, APIException {
        String projectName = PropertyProvider.getInstance().getTEST_RAIL_PRJ_NAME();
        String ProjectId = "";
        setAuthCredits();
        JSONArray arr = (JSONArray) client.sendGet(uri.GET_PROJECTS.toString());

        for (int i = 0; i < arr.size(); i++) {
            JSONObject jo = (JSONObject) arr.get(i);
            String name = jo.get("name").toString();
            if (name.equals(projectName)) {
                ProjectId = jo.get("id").toString();
            }
        }
        return ProjectId;
    }

    /**
     * Возвращает id тестплана с названием "Autotests Plan" (default)
     *
     * @return id
     * @throws IOException
     * @throws APIException
     */
    public String getTestPlanId() throws IOException, APIException {
        String testPlanName = PropertyProvider.getInstance().getTEST_RAIL_TP_NAME();
        String testPlanId = "";
        setAuthCredits();
        JSONArray arr = (JSONArray) client.sendGet(String.format(String.valueOf(uri.GET_PLANS), getProjectId()));

        for (int i = 0; i < arr.size(); i++) {
            JSONObject jo = (JSONObject) arr.get(i);
            String name = jo.get("name").toString();
            if (name.equals(testPlanName)) {
                testPlanId = jo.get("id").toString();
            }
        }
        return testPlanId;
    }

    /**
     * Создаем run в указанном test plan
     *
     * @return testRunId
     * @throws IOException
     * @throws APIException
     */
    public String addTestRun() throws IOException, APIException {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy kk:mm");
        String testRunName = "Autotest run " + format.format(d);
        String testRunId = "";
        Map<String, String> data = new HashMap<String, String>();
        data.put("suite_id", getTestSuteId());
        data.put("name", testRunName);
        data.put("description", "This plan was created by autotests " + Clock.systemUTC().instant());
        setAuthCredits();
        JSONObject obj = (JSONObject) client.sendPost(String.format(String.valueOf(uri.ADD_PlAN_ENTRY), getTestPlanId()), data);
        JSONArray arr = (JSONArray) obj.get("runs");

        for (int i = 0; i < arr.size(); i++) {
            JSONObject jo = (JSONObject) arr.get(i);
            testRunId = jo.get("id").toString();
        }
        return testRunId;
    }

    /**
     * Создаем test suite для test run'а
     *
     * @return Возвращает id созданного test suite
     * @throws IOException
     * @throws APIException
     */
    public String creatingTestSute() throws IOException, APIException {
        String testSuiteId = "";
        setAuthCredits();
        Map<String, String> data = new HashMap<String, String>();
        data.put("name", "Autotests Suite");
        data.put("description", "Default Suite for autotests. On it base will make test run");

        JSONArray arr = (JSONArray) client.sendPost(String.format(String.valueOf(uri.ADD_SUITE), getProjectId()), data);
        for (int i = 0; i < arr.size(); i++) {
            JSONObject jo = (JSONObject) arr.get(i);
            String name = jo.get("name").toString();
            if (name.equals(data.get("name"))) {
                testSuiteId = jo.get("id").toString();
            }
        }
        return testSuiteId;
    }

    /**
     * Ищем созданый сьют для автотестов с именем Autotests Suite (default)
     *
     * @return Возвращает id созданного test suite
     * @throws IOException
     * @throws APIException
     */
    public String getTestSuteId() throws IOException, APIException {
        String testSuiteName = PropertyProvider.getInstance().getTEST_RAIL_TS_NAME();
        String testSuiteId = "";
        setAuthCredits();

        JSONArray arr = (JSONArray) client.sendGet(String.format(String.valueOf(uri.GET_SUITE), getProjectId()));
        for (int i = 0; i < arr.size(); i++) {
            JSONObject jo = (JSONObject) arr.get(i);
            String name = jo.get("name").toString();
            if (name.equals(testSuiteName)) {
                testSuiteId = jo.get("id").toString();
            }
        }
        return testSuiteId;
    }

    /**
     * Ищем все тесткейсы в указанном тест плане
     *
     * @return коллекцию key, value из id кейса и его названия
     * @throws IOException
     * @throws APIException
     */
    public Map getTestCasesMap() throws IOException, APIException {
        Map map = new HashMap<>();
        setAuthCredits();
        JSONArray arr = (JSONArray) client.sendGet(String.format(String.valueOf(uri.GET_TEST_CASES), restMap.get("testRunId")));
        for (int i = 0; i < arr.size(); i++) {
            JSONObject jo = (JSONObject) arr.get(i);
            map.put(jo.get("title"), jo.get("id"));
        }
        return map;
    }

    /**
     * Деплоим результат
     *
     * @throws IOException
     * @throws APIException
     */
    public static void addTestResult(Map map) throws IOException, APIException {

        setAuthCredits();
        Map<String, String> data = new HashMap<String, String>();
        data.put("status_id", String.valueOf(map.get("result")));
        client.sendPost(String.format(String.valueOf(uri.ADD_RESULT_FOR_CASE), restMap.get("testRunId"), map.get("testRailCaseId")), data);

    }

    public Map runTestRailBuilder() throws IOException, APIException {
        Map map = new HashMap<>();
        map.put("projectId", getProjectId());
        map.put("testPlanId", getTestPlanId());
        map.put("testSuiteId", getTestSuteId());
        map.put("testRunId", addTestRun());
        return map;
    }

    public static TestRailIntegration initTestRail() throws IOException, APIException {
        TestRailIntegration localInstase = instance;

        if (localInstase == null) {
            synchronized (TestRailIntegration.class) {
                localInstase = instance;
                if (localInstase == null) {
                    instance = localInstase = new TestRailIntegration();
                }
            }
        }

        return localInstase;
    }

}
