package su.jet.webautotests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.Pages.TransactionEditPage;
import su.jet.webautotests.utils.ExcelReader;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import java.util.ArrayList;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;
import static su.jet.webautotests.utils.TestLogger.log;

/**
 * Тест проверяет соответствие данных представленных в UI, в "Редактировании транзакции", тестовым данным из excel-файла
 * Created by vv.drobot on 22.03.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("ЭФ сообщения")
public class VerifyFieldsByExcelFileTest extends BaseClass {

    private AmlPage amlPage = new AmlPage();

    @Test
    @TestCase(testRailCaseId = "26629")
    public void VerifyFieldsByExcelFile(){
        Map<String, String> excelMap, UIMap;
        ExcelReader reader = new ExcelReader();
        TransactionEditPage editPage;

        ArrayList<String> transactionList = reader.getTransactionList();
        int errorCount = 0;

        //for(String transactionID : transactionList){
        for(int i = 0; i < transactionList.size(); i++){
            int transactionErrorCount = 0;
            //excelMap = reader.getTransactionFields(transactionID);
            excelMap = reader.getTransactionFields(i);
            String transactionID = transactionList.get(i);
            editPage = amlPage.openTransactionByDoubleClick(transactionID);
            if(editPage == null){
                if(amlPage.getCountOfDownloadedOperations() == 0){
                    //amlPage.setNumberFld("");
                    //$(amlPage.showBtn).click();
                    //amlPage.clickOnShowFilters();
                    log.error("Транзакция № " + transactionID + " не найдена.");
                }else{
                    log.error("Транзакция № " + transactionID + " не найдена, или истек период ожидания.");
                    after();
                    before();
                    amlPage = new AmlPage();
                }
            }else{
                log.info("Составляем карту значений полей данных транзакции с ID " + transactionID + " из UI...");
                UIMap = editPage.getTransactionFields();

                for(Map.Entry<String, String> entry : excelMap.entrySet()) {
                    String key = entry.getKey();
                    String expectedValue = entry.getValue();
                    try{
                        String actualValue = UIMap.get(key);
                        if(actualValue.equals("")) actualValue = "0";
                        if(!actualValue.equals(expectedValue)){
                            log.error("Ожидаемое значение поля " + key + " = " + expectedValue + ", но фактическое = " + actualValue + ".");
                            ++errorCount;
                            ++transactionErrorCount;
                        }
                    }catch(Exception e){
                        log.error("Поле " + key + " не найдено в UI.");
                    }
                }
                if(transactionErrorCount == 0) {
                    log.info("Транзакция " + transactionID + " проверена. Расхождений в значениях полей данных не обнаружено.");
                }else {
                    log.error("Транзакция " + transactionID + " проверена. Обнаружено " + transactionErrorCount + " расхождений в значениях полей данных.");
                }
                amlPage = editPage.exitTransaction();
            }
        }
        if(errorCount == 0) {
            log.info("------------------------------------------------------------------");
            log.info("DONE! Проверено " + transactionList.size() + " транзакций. Расхождений в значениях полей данных не обнаружено.");
        } else {
            log.info("------------------------------------------------------------------");
            log.error("FAIL! Проверено " + transactionList.size() + " транзакций. Обнаружено " + errorCount + " расхождений в значениях полей данных.");
        }

//        Map data = new HashMap();
//        FileAppender fa = (FileAppender)log.getAppender("log");
//        File logFile = new File(fa.getFile());
//        try {
//            data.put("testComment", new FileOutputStream(logFile));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            TestRailIntegration.client.sendPost("add_result_for_case/1/1", data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (APIException e) {
//            e.printStackTrace();
//        }

        assertTrue(errorCount == 0);
    }
}
