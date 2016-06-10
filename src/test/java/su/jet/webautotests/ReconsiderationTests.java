package su.jet.webautotests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.Forms.ActionsOverTransactionsForm;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.DataConstants.*;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;
import static su.jet.webautotests.utils.TestLogger.log;

/**
 * Created by ub.kim on 08.04.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("Повторное рассмотрение")
public class ReconsiderationTests extends BaseClass {

    AmlPage amlPage = new AmlPage();
    ActionsOverTransactionsForm actionsOverTransactionsForm;
    String transactionId;

    @Test
    @TestCase(testRailCaseId = "34484")
    public void FromSentToReconsideration(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.Sent.toString(), MessageStatus.Sent.toString());
        log.info(transactionId);
        actionsOverTransactionsForm = amlPage.clickOnActionsBtnFromToolbar();
        actionsOverTransactionsForm.actionSelection(SelectAction.Reconsideration.name());
        amlPage.clearStatusFld()
                .findTransactionByNumber(transactionId);

        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + TransactionStatus.SentReconsideration + "\")",
                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(TransactionStatus.SentReconsideration.toString()));
    }

    @Test
    @TestCase(testRailCaseId = "34485")
    public void FroReconsiderationToReconsideration(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.Reconsideration.toString(), MessageStatus.ControlIsPassed.toString());
        log.info(transactionId);
        actionsOverTransactionsForm = amlPage.clickOnActionsBtnFromToolbar();

        assertThat("Действие \"Повторное рассмотрение\" отображается для операции, которая уже находится в этом статусе",
                $(actionsOverTransactionsForm.reconsiderationRbtn), is(not(displayed())));
    }

    @Test
    @TestCase(testRailCaseId = "34487")
    public void FromBeControlledToReconsideration(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.BeControlled.toString(), MessageStatus.ControlIsPassed.toString());
        log.info(transactionId);
        actionsOverTransactionsForm = amlPage.clickOnActionsBtnFromToolbar();
        actionsOverTransactionsForm.actionSelection(SelectAction.Reconsideration.name());
        amlPage.clearStatusFld()
                .findTransactionByNumber(transactionId);

        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + TransactionStatus.Reconsideration + "\")",
                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(TransactionStatus.Reconsideration.toString()));
    }

}
