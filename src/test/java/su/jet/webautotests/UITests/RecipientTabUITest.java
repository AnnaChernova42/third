package su.jet.webautotests.UITests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.BaseClass;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.Pages.EditPageTabs.RecipientTab;
import su.jet.webautotests.Pages.TransactionEditPage;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

/**
 * Набор тестов для первичной проверки доступности элементов вкладки "Получатель" страницы "Редактирование..."
 * Created by vv.drobot on 18.03.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("Проверить наличие всех полей в детальной информации, вкладка 'Получатель'")
public class RecipientTabUITest extends BaseClass {
    
    private AmlPage amlPage = new AmlPage();

    @Test
    @TestCase(testRailCaseId = "40284")
    public void verifyThatElementsOnRecipientTabVisible(){

        String[] status = new String[]{"Подлежит контролю"};
        String transactionID = amlPage.selectFirstTransactionByStatus(status);
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionID);
        editPage.openRecipientTab();
        $(editPage.recipientTitle).shouldBe(visible);
        /**
         * Блок "Общие сведения об участнике"
         */
        $(RecipientTab.TU3).shouldBe(visible);
        $(RecipientTab.PRU3).shouldBe(visible);
        $(RecipientTab.NAMEU3).shouldBe(visible);
        $(RecipientTab.ND3).shouldBe(visible);
        $(RecipientTab.RG3).shouldBe(visible);
        $(RecipientTab.VP_3).shouldBe(visible);
        $(RecipientTab.CARD_B3).shouldBe(visible);
        $(RecipientTab.GR3).shouldBe(visible);
        $(RecipientTab.BP_3).shouldBe(visible);

        /**
         * Блок "Информация о месте регистрации/жительства"
         */
        $(RecipientTab.KODCR3).shouldBe(visible);
        $(RecipientTab.KODCR3_additional).shouldBe(visible);
        $(RecipientTab.AMR_S3).shouldBe(visible);
        $(RecipientTab.AMR_R3).shouldBe(visible);
        $(RecipientTab.AMR_G3).shouldBe(visible);
        $(RecipientTab.AMR_U3).shouldBe(visible);
        $(RecipientTab.AMR_D3).shouldBe(visible);
        $(RecipientTab.AMR_K3).shouldBe(visible);
        $(RecipientTab.AMR_O3).shouldBe(visible);

        /**
         * Блок "Информация о месте нахождения/пребывания"
         */
        $(RecipientTab.KODCN3).shouldBe(visible);
        $(RecipientTab.KODCN3_additional).shouldBe(visible);
        $(RecipientTab.ADRESS_S3).shouldBe(visible);
        $(RecipientTab.ADRESS_R3).shouldBe(visible);
        $(RecipientTab.ADRESS_G3).shouldBe(visible);
        $(RecipientTab.ADRESS_U3).shouldBe(visible);
        $(RecipientTab.ADRESS_D3).shouldBe(visible);
        $(RecipientTab.ADRESS_K3).shouldBe(visible);
        $(RecipientTab.ADRESS_O3).shouldBe(visible);

        /**
         * Блок "Документ, удостоверяющий личность"
         */
        $(RecipientTab.KD3).shouldBe(visible);
        $(RecipientTab.VD32).shouldBe(visible);
        $(RecipientTab.SD3).shouldBe(visible);
        $(RecipientTab.VD31).shouldBe(visible);
        $(RecipientTab.VD33).shouldBe(visible);

        /**
         * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
         */
        $(RecipientTab.VD34).shouldBe(visible);
        $(RecipientTab.VD36).shouldBe(visible);
        $(RecipientTab.VD35).shouldBe(visible);
        $(RecipientTab.VD37).shouldBe(visible);

        /**
         * Блок "Миграционная карта"
         */
        $(RecipientTab.MC_31).shouldBe(visible);
        $(RecipientTab.MC_32).shouldBe(visible);
        $(RecipientTab.MC_33).shouldBe(visible);

        /**
         * Блок "Банковские идентификационные реквизиты КО, обслуживающей участника"
         */
        $(RecipientTab.NAME_B3).shouldBe(visible);
        $(RecipientTab.BIK_B3).shouldBe(visible);
        $(RecipientTab.KODCN_B3).shouldBe(visible);
        $(RecipientTab.KODCN_B3_additional).shouldBe(visible);
        $(RecipientTab.ACC_COR_B3).shouldBe(visible);
        $(RecipientTab.ACC_B3).shouldBe(visible);

        /**
         * Блок "Банковские идентификационные реквизиты корреспондента"
         */
        $(RecipientTab.NAME_R3).shouldBe(visible);
        $(RecipientTab.BIK_R3).shouldBe(visible);
        $(RecipientTab.KODCN_R3).shouldBe(visible);
        $(RecipientTab.KODCN_R3_additional).shouldBe(visible);

        /**
         * Блок "Данные о банковской карте"
         */
        $(RecipientTab.NAME_IS_B3).shouldBe(visible);
        $(RecipientTab.BIK_IS_B3).shouldBe(visible);

        $(RecipientTab.recipientComment).shouldBe(visible);
    }
}
