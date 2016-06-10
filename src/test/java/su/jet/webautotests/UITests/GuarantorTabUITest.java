package su.jet.webautotests.UITests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.BaseClass;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.Pages.EditPageTabs.GuarantorTab;
import su.jet.webautotests.Pages.TransactionEditPage;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Набор тестов для первичной проверки доступности элементов вкладки "От имени и по поруч." страницы "Редактирование..."
 * Created by vv.drobot on 18.03.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("Проверить наличие всех полей в детальной информации, вкладка 'От имени и по поруч.'")
public class GuarantorTabUITest extends BaseClass {

    private AmlPage amlPage = new AmlPage();

    @Test
    @TestCase(testRailCaseId = "40285")
    public void verifyThatElementsOnGuarantorTabVisible(){
        String[] status = new String[]{"Подлежит контролю"};
        String transactionID = amlPage.selectFirstTransactionByStatus(status);
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionID);
        editPage.openGuarantorTab();
        $(editPage.guarantorTitle).shouldBe(visible);

        /**
         * Блок "Общие сведения об участнике"
         */
        $(GuarantorTab.TU4).shouldBe(visible);
        $(GuarantorTab.PRU4).shouldBe(visible);
        $(GuarantorTab.NAMEU4).shouldBe(visible);
        $(GuarantorTab.ND4).shouldBe(visible);
        $(GuarantorTab.RG4).shouldBe(visible);
        $(GuarantorTab.GR4).shouldBe(visible);
        $(GuarantorTab.BP_4).shouldBe(visible);

        /**
         * Блок "Информация о месте регистрации/жительства"
         */
        $(GuarantorTab.KODCR4).shouldBe(visible);
        $(GuarantorTab.KODCR4_additional).shouldBe(visible);
        $(GuarantorTab.AMR_S4).shouldBe(visible);
        $(GuarantorTab.AMR_R4).shouldBe(visible);
        $(GuarantorTab.AMR_G4).shouldBe(visible);
        $(GuarantorTab.AMR_U4).shouldBe(visible);
        $(GuarantorTab.AMR_D4).shouldBe(visible);
        $(GuarantorTab.AMR_K4).shouldBe(visible);
        $(GuarantorTab.AMR_O4).shouldBe(visible);

        /**
         * Блок "Информация о месте нахождения/пребывания"
         */
        $(GuarantorTab.KODCN4).shouldBe(visible);
        $(GuarantorTab.KODCN4_additional).shouldBe(visible);
        $(GuarantorTab.ADRESS_S4).shouldBe(visible);
        $(GuarantorTab.ADRESS_R4).shouldBe(visible);
        $(GuarantorTab.ADRESS_G4).shouldBe(visible);
        $(GuarantorTab.ADRESS_U4).shouldBe(visible);
        $(GuarantorTab.ADRESS_D4).shouldBe(visible);
        $(GuarantorTab.ADRESS_K4).shouldBe(visible);
        $(GuarantorTab.ADRESS_O4).shouldBe(visible);

        /**
         * Блок "Документ, удостоверяющий личность"
         */
        $(GuarantorTab.KD4).shouldBe(visible);
        $(GuarantorTab.VD42).shouldBe(visible);
        $(GuarantorTab.SD4).shouldBe(visible);
        $(GuarantorTab.VD41).shouldBe(visible);
        $(GuarantorTab.VD43).shouldBe(visible);

        /**
         * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
         */
        $(GuarantorTab.VD44).shouldBe(visible);
        $(GuarantorTab.VD46).shouldBe(visible);
        $(GuarantorTab.VD45).shouldBe(visible);
        $(GuarantorTab.VD47).shouldBe(visible);

        /**
         * Блок "Миграционная карта"
         */
        $(GuarantorTab.MC_41).shouldBe(visible);
        $(GuarantorTab.MC_42).shouldBe(visible);
        $(GuarantorTab.MC_43).shouldBe(visible);

        $(GuarantorTab.guarantorComment).shouldBe(visible);
    }
}
