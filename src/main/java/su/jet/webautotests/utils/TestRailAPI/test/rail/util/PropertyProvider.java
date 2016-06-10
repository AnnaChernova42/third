package su.jet.webautotests.utils.TestRailAPI.test.rail.util;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyProvider {

    public static Logger log = Logger.getLogger(PropertyProvider.class.getName());
    private static PropertyProvider instance;

    private Properties properties;

    //from properties file
    private final String TEST_RAIL_PRJ_NAME = "tr.prj.name";
    private final String TEST_RAIL_TP_NAME = "tr.tp.name";
    private final String TEST_RAIL_TS_NAME = "tr.ts.name";
    private final String TEST_RAIL_BASE_URI = "tr.base.uri";
    private final String TEST_RAIL_LOGIN = "tr.login";
    private final String TEST_RAIL_PASSWORD = "tr.password";

    //default
    private final String DEFAULT_TEST_RAIL_PRJ_NAME = "Test Project";
    private final String DEFAULT_TEST_RAIL_TP_NAME = "Autotests Plan";
    private final String DEFAULT_TEST_RAIL_TS_NAME = "Autotests Suite";
    private final String DEFAULT_TEST_RAIL_BASE_URI = "http://blabla.com";
    private final String DEFAULT_TEST_RAIL_LOGIN = "root";
    private final String DEFAULT_TEST_RAIL_PASSWORD = "q1";


    private final String SYS_ENV_PREFIX = "tr";

    private PropertyProvider() {
        properties = new Properties();
        loadFromFile("aml_autotests.properties");
        loadFromSysEnv();
    }

    public Properties getProperties() {
        return properties;
    }

    private void loadFromFile(String file) {
        try {
            properties.load(new InputStreamReader(new FileInputStream(file), "windows-1251"));
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to read properties from file " + file);
        }
        log.info("Loaded " + properties.size() + " properties from file " + file);
    }

    private boolean checkSysVarProperty(String propertyName, String name, String value) {
        String dottedName = name.replaceAll("_", ".");
        if (dottedName.equals(SYS_ENV_PREFIX + "." + propertyName)) {
            properties.setProperty(propertyName, value);
            return true;
        }
        return false;
    }

    private void loadFromSysEnv() {
        String[] propertyNames = new String[]{
                TEST_RAIL_PRJ_NAME,
                TEST_RAIL_TP_NAME,
                TEST_RAIL_TS_NAME
        };
        int count = 0;
        Map<String, String> variables = System.getenv();
        for (Map.Entry<String, String> variable : variables.entrySet()) {
            if (variable.getKey().startsWith(SYS_ENV_PREFIX)) {
                for (String propertyName : propertyNames) {
                    if (checkSysVarProperty(propertyName, variable.getKey(), variable.getValue())) {
                        count++;
                    }
                }
            }
        }
        log.info("Loaded " + count + " properties from system environment");
    }

    public static PropertyProvider getInstance() {
        if (instance == null) {
            instance = new PropertyProvider();
        }
        return instance;
    }

    public String getTEST_RAIL_PRJ_NAME() {
        return properties.getProperty(TEST_RAIL_PRJ_NAME, DEFAULT_TEST_RAIL_PRJ_NAME);
    }

    public String getTEST_RAIL_TP_NAME() {
        return properties.getProperty(TEST_RAIL_TP_NAME, DEFAULT_TEST_RAIL_TP_NAME);
    }

    public String getTEST_RAIL_TS_NAME() {
        return properties.getProperty(TEST_RAIL_TS_NAME, DEFAULT_TEST_RAIL_TS_NAME);
    }

    public String getTEST_RAIL_BASE_URI() {
        return properties.getProperty(TEST_RAIL_BASE_URI, DEFAULT_TEST_RAIL_BASE_URI);
    }

    public String getTEST_RAIL_LOGIN() {
        return properties.getProperty(TEST_RAIL_LOGIN, DEFAULT_TEST_RAIL_LOGIN);
    }

    public String getTEST_RAIL_PASSWORD() {
        return properties.getProperty(TEST_RAIL_PASSWORD, DEFAULT_TEST_RAIL_PASSWORD);
    }
}
