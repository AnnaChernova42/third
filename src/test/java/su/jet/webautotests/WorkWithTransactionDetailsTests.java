package su.jet.webautotests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.Forms.ActionsOverTransactionsForm;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.Pages.EditPageTabs.*;
import su.jet.webautotests.Pages.TransactionEditPage;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

/** Группа сценариев "Работа с деталями операции"
 * Created by vv.drobot on 12.04.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
public class WorkWithTransactionDetailsTests extends BaseClass {

    AmlPage amlPage = new AmlPage();
    String testComment = "Hello world! It's test comment.";


    @Test
    @TestCase(testRailCaseId = "34462")
    @Title("Изменение основного кода операции одним из способов: в списке выявленных операций (инлайн-редактирование), окно 'Действия'")
    public void changingBasicOperationCode(){
        String[] status = new String[]{DataConstants.TransactionStatus.AdditionalAnalysis.toString()};
        String transactionID = amlPage.selectFirstTransactionByStatus(status);

        amlPage.setMainTransactionStatusByInLine(transactionID, "5005");
        amlPage.verifyMainTransactionStatus(transactionID, "5005");
        amlPage.setMainTransactionStatusByInLine(transactionID, "4004");
        amlPage.verifyMainTransactionStatus(transactionID, "4004");

        ActionsOverTransactionsForm actionsOverTransactionsForm = amlPage.clickOnActionsBtnFromToolbar();
        String selectedMainCode = actionsOverTransactionsForm.changeMainCode();
        amlPage.verifyMainTransactionStatus(transactionID, selectedMainCode.split(" - ")[0]);
    }

    @Test
    @TestCase(testRailCaseId = "34463")
    @Title("Ввод комментария")
    public void commentInput(){
        String[] status = new String[]{DataConstants.TransactionStatus.BeControlled.toString()};
        String transactionID = amlPage.selectFirstTransactionByStatus(status);
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionID);
        editPage.openInformationTab();
        OperationInfoTab.inputComment(testComment);
        editPage.clickApplyButton();

        amlPage = editPage.exitTransaction();

        editPage = amlPage.openTransactionByClickToEnterButton(transactionID);
        editPage.openInformationTab();
        OperationInfoTab.verifyComment(testComment);
    }
}
