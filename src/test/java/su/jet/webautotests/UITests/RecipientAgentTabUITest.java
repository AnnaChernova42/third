package su.jet.webautotests.UITests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.BaseClass;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.Pages.EditPageTabs.RecipientAgentTab;
import su.jet.webautotests.Pages.TransactionEditPage;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Набор тестов для первичной проверки доступности элементов вкладки "Представитель получателя" страницы "Редактирование..."
 * Created by vv.drobot on 18.03.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("Проверить наличие всех полей в детальной информации, вкладка 'Представитель получателя'")
public class RecipientAgentTabUITest extends BaseClass {

    private AmlPage amlPage = new AmlPage();

    @Test
    @TestCase(testRailCaseId = "40287")
    public void verifyThatElementsOnRecipientAgentTabVisible(){
        String[] status = new String[]{"Подлежит контролю"};
        String transactionID = amlPage.selectFirstTransactionByStatus(status);
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionID);
        editPage.openRecipientAgentTab();
        $(editPage.recipientAgentTitle).shouldBe(visible);

        /**
         * Блок "Общие сведения об участнике"
         */
        $(RecipientAgentTab.TU2).shouldBe(visible);
        $(RecipientAgentTab.PRU2).shouldBe(visible);
        $(RecipientAgentTab.NAMEU2).shouldBe(visible);
        $(RecipientAgentTab.ND2).shouldBe(visible);
        $(RecipientAgentTab.RG2).shouldBe(visible);
        $(RecipientAgentTab.GR2).shouldBe(visible);
        $(RecipientAgentTab.BP_2).shouldBe(visible);

        /**
         * Блок "Информация о месте регистрации/жительства"
         */
        $(RecipientAgentTab.KODCR2).shouldBe(visible);
        $(RecipientAgentTab.KODCR2_additional).shouldBe(visible);
        $(RecipientAgentTab.AMR_S2).shouldBe(visible);
        $(RecipientAgentTab.AMR_R2).shouldBe(visible);
        $(RecipientAgentTab.AMR_G2).shouldBe(visible);
        $(RecipientAgentTab.AMR_U2).shouldBe(visible);
        $(RecipientAgentTab.AMR_D2).shouldBe(visible);
        $(RecipientAgentTab.AMR_K2).shouldBe(visible);
        $(RecipientAgentTab.AMR_O2).shouldBe(visible);

        /**
         * Блок "Информация о месте нахождения/пребывания"
         */
        $(RecipientAgentTab.KODCN2).shouldBe(visible);
        $(RecipientAgentTab.KODCN2_additional).shouldBe(visible);
        $(RecipientAgentTab.ADRESS_S2).shouldBe(visible);
        $(RecipientAgentTab.ADRESS_R2).shouldBe(visible);
        $(RecipientAgentTab.ADRESS_G2).shouldBe(visible);
        $(RecipientAgentTab.ADRESS_U2).shouldBe(visible);
        $(RecipientAgentTab.ADRESS_D2).shouldBe(visible);
        $(RecipientAgentTab.ADRESS_K2).shouldBe(visible);
        $(RecipientAgentTab.ADRESS_O2).shouldBe(visible);

        /**
         * Блок "Документ, удостоверяющий личность"
         */
        $(RecipientAgentTab.KD2).shouldBe(visible);
        $(RecipientAgentTab.VD22).shouldBe(visible);
        $(RecipientAgentTab.SD2).shouldBe(visible);
        $(RecipientAgentTab.VD21).shouldBe(visible);
        $(RecipientAgentTab.VD23).shouldBe(visible);

        /**
         * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
         */
        $(RecipientAgentTab.VD22).shouldBe(visible);
        $(RecipientAgentTab.VD26).shouldBe(visible);
        $(RecipientAgentTab.VD25).shouldBe(visible);
        $(RecipientAgentTab.VD27).shouldBe(visible);

        /**
         * Блок "Миграционная карта"
         */
        $(RecipientAgentTab.MC_21).shouldBe(visible);
        $(RecipientAgentTab.MC_22).shouldBe(visible);
        $(RecipientAgentTab.MC_23).shouldBe(visible);

        $(RecipientAgentTab.recipientAgentComment).shouldBe(visible);
    }
}
