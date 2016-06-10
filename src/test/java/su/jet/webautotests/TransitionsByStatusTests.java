package su.jet.webautotests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.Forms.ActionsOverTransactionsForm;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by ub.kim on 29.03.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("Переходы статусов алертов")
public class TransitionsByStatusTests extends BaseClass {

    String [] statusValues = {"Подлежит контролю", "Новый ответственный"};
    String transactionStatus = "Новый ответственный";
    AmlPage amlPage = new AmlPage();
    ActionsOverTransactionsForm actionsOverTransactionsForm;
    List<String> transactionIds;
    String transactionId, selectedResponsible;

    @Test
    @TestCase(testRailCaseId = "26911")
    public void NewResponsible(){
        transactionId = amlPage.selectFirstTransactionByStatus(statusValues);
        actionsOverTransactionsForm = amlPage.clickOnActionsBtnFromToolbar();
        selectedResponsible = actionsOverTransactionsForm.changeResponsibleOnFirstItemFromList();
        amlPage.clearStatusFld()
                .findTransactionByNumber(transactionId);

        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + transactionStatus + "\")",
                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(transactionStatus));
        assertThat("Поле \"Ответственный\" имеет фактическое значение отличное от ожидаемого (\"" + selectedResponsible + "\")",
                selectedResponsible, containsString(amlPage.getResponsible(transactionId)));
    }

    @Test
    //@TestCase(testRailCaseId = "26911")
    public void NewResponsibleSupervisorMass(){
        transactionIds = amlPage.selectMultipleTransactionsByStatus(statusValues);
        actionsOverTransactionsForm = amlPage.clickOnActionsBtnFromToolbar();
        selectedResponsible = actionsOverTransactionsForm.changeResponsibleOnFirstItemFromList();
//        amlPage.clearStatusFld()
//                .findTransactionByNumber(transactionId);
//
//        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + transactionStatus + "\")",
//                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(transactionStatus));
//        assertThat("Поле \"Ответственный\" имеет фактическое значение отличное от ожидаемого (\"" + selectedResponsible + "\")",
//                selectedResponsible, containsString(amlPage.getResponsible(transactionId)));
    }
}
