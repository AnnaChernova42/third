package su.jet.webautotests.UITests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.BaseClass;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.Pages.EditPageTabs.OperationInfoTab;
import su.jet.webautotests.Pages.TransactionEditPage;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

/**
 * Набор тестов для первичной проверки доступности элементов вкладки "Сведения об операции" страницы "Редактирование..."
 * Created by vv.drobot on 18.03.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("Проверить наличие всех полей в детальной информации, вкладка 'Сведения об операции'")
public class OperationInfoTabUITest extends BaseClass {

    private AmlPage amlPage = new AmlPage();

    @Test
    @TestCase(testRailCaseId = "38840")
    public void verifyThatElementsOnOperationInfoTabVisible(){
        String[] status = new String[]{"Подлежит контролю"};
        String transactionID = amlPage.selectFirstTransactionByStatus(status);
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionID);
        editPage.openInformationTab();
        $(OperationInfoTab.DATA).shouldBe(visible);

        /**
         * Блок "Кредитная организация"
         */
        $(OperationInfoTab.creditOrganization).shouldBe(visible);
        $(OperationInfoTab.creditOrganizationButton).click();
        $(OperationInfoTab.NUMBF_S).shouldBe(visible);
        $(OperationInfoTab.REGN).shouldBe(visible);
        $(OperationInfoTab.BIK_S).shouldBe(visible);
        $(OperationInfoTab.ND_KO).shouldBe(visible);
        $(OperationInfoTab.KTU_S).shouldBe(visible);
        $(OperationInfoTab.TEL).shouldBe(visible);

        /**
         * Блок "Филиал КО"
         */
        $(OperationInfoTab.branchButton).click();
        $(OperationInfoTab.BRANCH).shouldBe(visible);
        $(OperationInfoTab.NUMBF_SS).shouldBe(visible);
        $(OperationInfoTab.BIK_SS).shouldBe(visible);
        $(OperationInfoTab.KTU_SS).shouldBe(visible);

        /**
         * Блок "Признаки операции"
         */
        $(OperationInfoTab.DATA).shouldBe(visible);
        $(OperationInfoTab.VO).shouldBe(visible);
        $(OperationInfoTab.DOP_V).shouldBe(visible);
        $(OperationInfoTab.PRIZ_SD).shouldBe(visible);
        $(OperationInfoTab.B_PAYER).shouldBe(visible);
        $(OperationInfoTab.B_RECIP).shouldBe(visible);
        $(OperationInfoTab.PART).shouldBe(visible);
        $(OperationInfoTab.PRIZ6001).shouldBe(visible);
        $(OperationInfoTab.DATE_S).shouldBe(visible);
        $(OperationInfoTab.TERROR).shouldBe(visible);

        /**
         * Блок "Реквизиты платежного документа"
         */
        $(OperationInfoTab.NUM_PAY_D).shouldBe(visible);
        $(OperationInfoTab.SUME).shouldBe(visible);
        $(OperationInfoTab.SUM).shouldBe(visible);
        $(OperationInfoTab.SUM_CON).shouldBe(visible);
        $(OperationInfoTab.PRIM_1).shouldBe(visible);
        $(OperationInfoTab.DATE_PAY_D).shouldBe(visible);
        $(OperationInfoTab.METAL).shouldBe(visible);
        $(OperationInfoTab.CURREN).shouldBe(visible);
        $(OperationInfoTab.CURREN_CON).shouldBe(visible);

        /**
         * Блок "Дополнительная информация о сведениях"
         */
        $(OperationInfoTab.DESCR_1).shouldBe(visible);
        $(OperationInfoTab.MANUAL_CMNT).shouldBe(visible);
    }
}
