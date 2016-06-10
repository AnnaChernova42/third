package su.jet.webautotests.UITests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.BaseClass;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.Pages.EditPageTabs.PayerTab;
import su.jet.webautotests.Pages.TransactionEditPage;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

/**
 * Набор тестов для первичной проверки доступности элементов вкладки "Плательщик" страницы "Редактирование..."
 * Created by vv.drobot on 18.03.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("Проверить наличие всех полей в детальной информации, вкладка 'Плательщик'")
public class PayerTabUITest extends BaseClass {

    private AmlPage amlPage = new AmlPage();

    @Test
    @TestCase(testRailCaseId = "40283")
    public void verifyThatElementsOnPayerTabVisible(){
        String[] status = new String[]{"Подлежит контролю"};
        String transactionID = amlPage.selectFirstTransactionByStatus(status);
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionID);
        editPage.openPayerTab();
        $(editPage.payerTitle).shouldBe(visible);

        /**
         * Блок "Общие сведения об участнике"
         */
        $(PayerTab.TU0).shouldBe(visible);
        $(PayerTab.PRU0).shouldBe(visible);
        $(PayerTab.NAMEU0).shouldBe(visible);
        $(PayerTab.ND0).shouldBe(visible);
        $(PayerTab.RG0).shouldBe(visible);
        $(PayerTab.VP_0).shouldBe(visible);
        $(PayerTab.CARD_B0).shouldBe(visible);
        $(PayerTab.GR0).shouldBe(visible);
        $(PayerTab.BP_0).shouldBe(visible);

        /**
         * Блок "Информация о месте регистрации/жительства"
         */
        $(PayerTab.KODCR0).shouldBe(visible);
        $(PayerTab.KODCR0_additional).shouldBe(visible);
        $(PayerTab.AMR_S0).shouldBe(visible);
        $(PayerTab.AMR_R0).shouldBe(visible);
        $(PayerTab.AMR_G0).shouldBe(visible);
        $(PayerTab.AMR_U0).shouldBe(visible);
        $(PayerTab.AMR_D0).shouldBe(visible);
        $(PayerTab.AMR_K0).shouldBe(visible);
        $(PayerTab.AMR_O0).shouldBe(visible);

        /**
         * Блок "Информация о месте нахождения/пребывания"
         */
        $(PayerTab.KODCN0).shouldBe(visible);
        $(PayerTab.KODCN0_additional).shouldBe(visible);
        $(PayerTab.ADRESS_S0).shouldBe(visible);
        $(PayerTab.ADRESS_R0).shouldBe(visible);
        $(PayerTab.ADRESS_G0).shouldBe(visible);
        $(PayerTab.ADRESS_U0).shouldBe(visible);
        $(PayerTab.ADRESS_D0).shouldBe(visible);
        $(PayerTab.ADRESS_K0).shouldBe(visible);
        $(PayerTab.ADRESS_O0).shouldBe(visible);

        /**
         * Блок "Документ, удостоверяющий личность"
         */
        $(PayerTab.KD0).shouldBe(visible);
        $(PayerTab.VD02).shouldBe(visible);
        $(PayerTab.SD0).shouldBe(visible);
        $(PayerTab.VD01).shouldBe(visible);
        $(PayerTab.VD03).shouldBe(visible);

        /**
         * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
         */
        $(PayerTab.VD04).shouldBe(visible);
        $(PayerTab.VD06).shouldBe(visible);
        $(PayerTab.VD05).shouldBe(visible);
        $(PayerTab.VD07).shouldBe(visible);

        /**
         * Блок "Миграционная карта"
         */
        $(PayerTab.MC_01).shouldBe(visible);
        $(PayerTab.MC_02).shouldBe(visible);
        $(PayerTab.MC_03).shouldBe(visible);

        /**
         * Блок "Банковские идентификационные реквизиты КО, обслуживающей участника"
         */
        $(PayerTab.NAME_B0).shouldBe(visible);
        $(PayerTab.BIK_B0).shouldBe(visible);
        $(PayerTab.KODCN_B0).shouldBe(visible);
        $(PayerTab.KODCN_B0_additional).shouldBe(visible);
        $(PayerTab.ACC_COR_B0).shouldBe(visible);
        $(PayerTab.ACC_B0).shouldBe(visible);

        /**
         * Блок "Банковские идентификационные реквизиты корреспондента"
         */
        $(PayerTab.NAME_R0).shouldBe(visible);
        $(PayerTab.BIK_R0).shouldBe(visible);
        $(PayerTab.KODCN_R0).shouldBe(visible);
        $(PayerTab.KODCN_R0_additional).shouldBe(visible);

        /**
         * Блок "Данные о банковской карте"
         */
        $(PayerTab.NAME_IS_B0).shouldBe(visible);
        $(PayerTab.BIK_IS_B0).shouldBe(visible);

        $(PayerTab.payerComment).shouldBe(visible);
    }
}
