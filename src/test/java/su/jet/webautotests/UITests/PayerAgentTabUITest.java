package su.jet.webautotests.UITests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.BaseClass;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.Pages.EditPageTabs.PayerAgentTab;
import su.jet.webautotests.Pages.TransactionEditPage;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Набор тестов для первичной проверки доступности элементов вкладки "Представитель плательщика" страницы "Редактирование..."
 * Created by vv.drobot on 18.03.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("Проверить наличие всех полей в детальной информации, вкладка 'Представитель плательщика'")
public class PayerAgentTabUITest extends BaseClass {

    private AmlPage amlPage = new AmlPage();

    @Test
    @TestCase(testRailCaseId = "40286")
    public void verifyThatElementsOnPayerAgentTabVisible(){
        String[] status = new String[]{"Подлежит контролю"};
        String transactionID = amlPage.selectFirstTransactionByStatus(status);
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionID);
        editPage.openPayerAgentTab();
        $(editPage.payerAgentTitle).shouldBe(visible);

        /**
         * Блок "Общие сведения об участнике"
         */
        $(PayerAgentTab.TU1).shouldBe(visible);
        $(PayerAgentTab.PRU1).shouldBe(visible);
        $(PayerAgentTab.NAMEU1).shouldBe(visible);
        $(PayerAgentTab.ND1).shouldBe(visible);
        $(PayerAgentTab.RG1).shouldBe(visible);
        $(PayerAgentTab.GR1).shouldBe(visible);
        $(PayerAgentTab.BP_1).shouldBe(visible);

        /**
         * Блок "Информация о месте регистрации/жительства"
         */
        $(PayerAgentTab.KODCR1).shouldBe(visible);
        $(PayerAgentTab.KODCR1_additional).shouldBe(visible);
        $(PayerAgentTab.AMR_S1).shouldBe(visible);
        $(PayerAgentTab.AMR_R1).shouldBe(visible);
        $(PayerAgentTab.AMR_G1).shouldBe(visible);
        $(PayerAgentTab.AMR_U1).shouldBe(visible);
        $(PayerAgentTab.AMR_D1).shouldBe(visible);
        $(PayerAgentTab.AMR_K1).shouldBe(visible);
        $(PayerAgentTab.AMR_O1).shouldBe(visible);

        /**
         * Блок "Информация о месте нахождения/пребывания"
         */
        $(PayerAgentTab.KODCN1).shouldBe(visible);
        $(PayerAgentTab.KODCN1_additional).shouldBe(visible);
        $(PayerAgentTab.ADRESS_S1).shouldBe(visible);
        $(PayerAgentTab.ADRESS_R1).shouldBe(visible);
        $(PayerAgentTab.ADRESS_G1).shouldBe(visible);
        $(PayerAgentTab.ADRESS_U1).shouldBe(visible);
        $(PayerAgentTab.ADRESS_D1).shouldBe(visible);
        $(PayerAgentTab.ADRESS_K1).shouldBe(visible);
        $(PayerAgentTab.ADRESS_O1).shouldBe(visible);

        /**
         * Блок "Документ, удостоверяющий личность"
         */
        $(PayerAgentTab.KD1).shouldBe(visible);
        $(PayerAgentTab.VD12).shouldBe(visible);
        $(PayerAgentTab.SD1).shouldBe(visible);
        $(PayerAgentTab.VD11).shouldBe(visible);
        $(PayerAgentTab.VD13).shouldBe(visible);

        /**
         * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
         */
        $(PayerAgentTab.VD11).shouldBe(visible);
        $(PayerAgentTab.VD16).shouldBe(visible);
        $(PayerAgentTab.VD15).shouldBe(visible);
        $(PayerAgentTab.VD17).shouldBe(visible);

        /**
         * Блок "Миграционная карта"
         */
        $(PayerAgentTab.MC_11).shouldBe(visible);
        $(PayerAgentTab.MC_12).shouldBe(visible);
        $(PayerAgentTab.MC_13).shouldBe(visible);

        $(PayerAgentTab.payerAgentComment).shouldBe(visible);
    }
}
