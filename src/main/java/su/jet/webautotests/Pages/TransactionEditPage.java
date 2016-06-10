package su.jet.webautotests.Pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;
import su.jet.webautotests.Forms.ActionsOverTransactionsForm;
import su.jet.webautotests.Pages.EditPageTabs.*;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static su.jet.webautotests.utils.TestProperties.prop;
import static su.jet.webautotests.utils.TestLogger.log;

/**
 * Страница "Редактирование ОЭС"
 * Created by vv.drobot on 16.03.2016.
 */
public class TransactionEditPage extends BasePage {

    private final int timeout = Integer.parseInt(prop.getProperty("shortTimeout"));
    public String operationNumXpath= "*//td[text()='Номер операции CODE']";
    private Map<String, String> UIFieldsMap;

    /**
     * Первые три вкладки на странице "Редактирование..."
     */
    By informationTab = By.xpath("//span/span[text()='Сведения об операции']");
    public By infoTitle = By.xpath("//div/div/div/div/div/div[contains(@class, 'x-title-text') and contains(text(), 'Редактирование')]");
    By payerTab = By.xpath("//span/span[text()='Плательщик']");
    public By payerTitle = By.xpath("//div/div/div/div/div/div[contains(@class, 'x-title-text') and contains(text(), 'Редактирование')  and contains(text(), 'Плательщик')]");
    By recipientTab = By.xpath("//span/span[text()='Получатель']");
    public By recipientTitle = By.xpath("*//div/div/div/div/div/div[contains(@class, 'x-title-text') and contains(text(), 'Редактирование')  and contains(text(), 'Получатель')]");
    By guarantorTab = By.xpath("//span/span[text()='От имени и по поруч.']");
    public By guarantorTitle = By.xpath("//div/div/div/div/div/div[contains(@class, 'x-title-text') and contains(text(), 'Редактирование')  and contains(text(), 'От имени и по поруч.')]");
    By payerAgentTab = By.xpath("//span/span[text()='Предст. плат-ка']");
    public By payerAgentTitle = By.xpath("//div/div/div/div/div/div[contains(@class, 'x-title-text') and contains(text(), 'Редактирование')  and contains(text(), 'Предст. плат-ка')]");
    By recipientAgentTab = By.xpath("//span/span[text()='Предст. получ-ля']");
    public By recipientAgentTitle = By.xpath("*//div/div/div/div/div/div[contains(@class, 'x-title-text') and contains(text(), 'Редактирование')  and contains(text(), 'Предст. получ-ля')]");
    public static By exit = By.xpath("//span[text() = 'Закрыть']");
    By applyButton = By.xpath("//span[text() = 'Применить']");

    @Step("Нажать кнопку 'Применить'")
    public void clickApplyButton(){
        $(applyButton).click();
    }

    @Step("Открыть вкладку 'Сведения об организации'")
    public void openInformationTab(){
        $(informationTab).click();
        $(OperationInfoTab.DATA).waitUntil(Condition.visible, timeout);
    }

    @Step("Открыть вкладку 'Плательщик'")
    public void openPayerTab(){
        $(payerTab).click();
        $(PayerTab.TU0).waitUntil(Condition.visible, timeout);
    }

    @Step("Открыть вкладку 'Получатель'")
    public void openRecipientTab(){
        $(recipientTab).click();
        $(RecipientTab.TU3).waitUntil(Condition.visible, timeout);
    }

    @Step("Открыть вкладку 'От имени и по поруч.'")
    public void openGuarantorTab(){
        $(guarantorTab).click();
        $(GuarantorTab.TU4).waitUntil(Condition.visible, timeout);
    }

    @Step("Открыть вкладку 'Предст. плат-ка'")
    public void openPayerAgentTab(){
        $(payerAgentTab).click();
        $(PayerAgentTab.TU1).waitUntil(Condition.visible, timeout);
    }

    @Step("Открыть вкладку 'Предст. получ-ля'")
    public void openRecipientAgentTab(){
        $(recipientAgentTab).click();
        $(RecipientAgentTab.TU2).waitUntil(Condition.visible, timeout);
    }

    @Step("Закрыть редактирование транзакции")
    public AmlPage exitTransaction() {
        $(exit).click();
        waitLoadingProcess();
        return page(AmlPage.class);
    }

    @Step("Получить карты всех полей данных из UI")
    public Map<String, String> getTransactionFields(){
        String tabName;
        UIFieldsMap = new HashMap<>();
        //region Маппинг вкладки "Сведения об операции"
        openInformationTab();
        tabName = "OperationInfoTab";
        putElementToMap("creditOrganization", tabName);
        $(OperationInfoTab.creditOrganizationButton).click();
        putElementToMap("NUMBF_S", tabName);
        putElementToMap("REGN", tabName);
        putElementToMap("BIK_S", tabName);
        putElementToMap("ND_KO", tabName);
        putElementToMap("KTU_S", tabName);
        putElementToMap("TEL", tabName);
        $(OperationInfoTab.branchButton).click();
        putElementToMap("BRANCH", tabName);
        putElementToMap("NUMBF_SS", tabName);
        putElementToMap("BIK_SS", tabName);
        putElementToMap("KTU_SS", tabName);
        putElementToMap("DATA", tabName);
        putElementToMap("VO", tabName);
        putElementToMap("DOP_V", tabName);
        putElementToMap("PRIZ_SD", tabName);
        putElementToMap("B_PAYER", tabName);
        putElementToMap("B_RECIP", tabName);
        putElementToMap("PART", tabName);
        putElementToMap("PRIZ6001", tabName);
        putElementToMap("DATE_S", tabName);
        putElementToMap("TERROR", tabName);
        putElementToMap("NUM_PAY_D", tabName);
        putElementToMap("SUME", tabName);
        putElementToMap("SUM", tabName);
        putElementToMap("SUM_CON", tabName);
        putElementToMap("PRIM_1", tabName);
        putElementToMap("DATE_PAY_D", tabName);
        putElementToMap("METAL", tabName);
        putElementToMap("CURREN", tabName);
        putElementToMap("CURREN_CON", tabName);
        putElementToMap("DESCR_1", tabName);
        putElementToMap("MANUAL_CMNT", tabName);
        //endregion
        //region Маппинг вкладки "Плательщик"
        openPayerTab();
        tabName = "PayerTab";
        putElementToMap("TU0", tabName);
        putElementToMap("PRU0", tabName);
        putElementToMap("NAMEU0", tabName);
        putElementToMap("ND0", tabName);
        putElementToMap("RG0", tabName);
        putElementToMap("VP_0", tabName);
        putElementToMap("CARD_B0", tabName);
        putElementToMap("GR0", tabName);
        putElementToMap("BP_0", tabName);
        putElementToMap("KODCR0", tabName);
        putElementToMap("KODCR0_additional", tabName);
        putElementToMap("AMR_S0", tabName);
        putElementToMap("AMR_R0", tabName);
        putElementToMap("AMR_G0", tabName);
        putElementToMap("AMR_U0", tabName);
        putElementToMap("AMR_D0", tabName);
        putElementToMap("AMR_K0", tabName);
        putElementToMap("AMR_O0", tabName);
        putElementToMap("KODCN0", tabName);
        putElementToMap("KODCN0_additional", tabName);
        putElementToMap("ADRESS_S0", tabName);
        putElementToMap("ADRESS_R0", tabName);
        putElementToMap("ADRESS_G0", tabName);
        putElementToMap("ADRESS_U0", tabName);
        putElementToMap("ADRESS_D0", tabName);
        putElementToMap("ADRESS_K0", tabName);
        putElementToMap("ADRESS_O0", tabName);
        putElementToMap("KD0", tabName);
        putElementToMap("VD02", tabName);
        putElementToMap("SD0", tabName);
        putElementToMap("VD01", tabName);
        putElementToMap("VD03", tabName);
        putElementToMap("VD04", tabName);
        putElementToMap("VD06", tabName);
        putElementToMap("VD05", tabName);
        putElementToMap("VD07", tabName);
        putElementToMap("MC_01", tabName);
        putElementToMap("MC_02", tabName);
        putElementToMap("MC_03", tabName);
        putElementToMap("NAME_B0", tabName);
        putElementToMap("BIK_B0", tabName);
        putElementToMap("KODCN_B0", tabName);
        putElementToMap("KODCN_B0_additional", tabName);
        putElementToMap("ACC_COR_B0", tabName);
        putElementToMap("ACC_B0", tabName);
        putElementToMap("NAME_R0", tabName);
        putElementToMap("BIK_R0", tabName);
        putElementToMap("KODCN_R0", tabName);
        putElementToMap("KODCN_R0_additional", tabName);
        putElementToMap("NAME_IS_B0", tabName);
        putElementToMap("BIK_IS_B0", tabName);
        putElementToMap("payerComment", tabName);
        //endregion
        //region Маппинг вкладки "Получатель"
        openRecipientTab();
        tabName = "RecipientTab";
        putElementToMap("TU3", tabName);
        putElementToMap("PRU3", tabName);
        putElementToMap("NAMEU3", tabName);
        putElementToMap("ND3", tabName);
        putElementToMap("RG3", tabName);
        putElementToMap("VP_3", tabName);
        putElementToMap("CARD_B3", tabName);
        putElementToMap("GR3", tabName);
        putElementToMap("BP_3", tabName);
        putElementToMap("KODCR3", tabName);
        putElementToMap("KODCR3_additional", tabName);
        putElementToMap("AMR_S3", tabName);
        putElementToMap("AMR_R3", tabName);
        putElementToMap("AMR_G3", tabName);
        putElementToMap("AMR_U3", tabName);
        putElementToMap("AMR_D3", tabName);
        putElementToMap("AMR_K3", tabName);
        putElementToMap("AMR_O3", tabName);
        putElementToMap("KODCN3", tabName);
        putElementToMap("KODCN3_additional", tabName);
        putElementToMap("ADRESS_S3", tabName);
        putElementToMap("ADRESS_R3", tabName);
        putElementToMap("ADRESS_G3", tabName);
        putElementToMap("ADRESS_U3", tabName);
        putElementToMap("ADRESS_D3", tabName);
        putElementToMap("ADRESS_K3", tabName);
        putElementToMap("ADRESS_O3", tabName);
        putElementToMap("KD3", tabName);
        putElementToMap("VD32", tabName);
        putElementToMap("SD3", tabName);
        putElementToMap("VD31", tabName);
        putElementToMap("VD33", tabName);
        putElementToMap("VD34", tabName);
        putElementToMap("VD36", tabName);
        putElementToMap("VD35", tabName);
        putElementToMap("VD37", tabName);
        putElementToMap("MC_31", tabName);
        putElementToMap("MC_32", tabName);
        putElementToMap("MC_33", tabName);
        putElementToMap("NAME_B3", tabName);
        putElementToMap("BIK_B3", tabName);
        putElementToMap("KODCN_B3", tabName);
        putElementToMap("KODCN_B3_additional", tabName);
        putElementToMap("ACC_COR_B3", tabName);
        putElementToMap("ACC_B3", tabName);
        putElementToMap("NAME_R3", tabName);
        putElementToMap("BIK_R3", tabName);
        putElementToMap("KODCN_R3", tabName);
        putElementToMap("KODCN_R3_additional", tabName);
        putElementToMap("NAME_IS_B3", tabName);
        putElementToMap("BIK_IS_B3", tabName);
        putElementToMap("recipientComment", tabName);
        //endregion
        //region Маппинг вкладки "От имени и по поруч."
        openGuarantorTab();
        tabName = "GuarantorTab";
        putElementToMap("TU4", tabName);
        putElementToMap("PRU4", tabName);
        putElementToMap("NAMEU4", tabName);
        putElementToMap("ND4", tabName);
        putElementToMap("RG4", tabName);
        putElementToMap("GR4", tabName);
        putElementToMap("BP_4", tabName);
        putElementToMap("KODCR4", tabName);
        putElementToMap("KODCR4_additional", tabName);
        putElementToMap("AMR_S4", tabName);
        putElementToMap("AMR_R4", tabName);
        putElementToMap("AMR_G4", tabName);
        putElementToMap("AMR_U4", tabName);
        putElementToMap("AMR_D4", tabName);
        putElementToMap("AMR_K4", tabName);
        putElementToMap("AMR_O4", tabName);
        putElementToMap("KODCN4", tabName);
        putElementToMap("KODCN4_additional", tabName);
        putElementToMap("ADRESS_S4", tabName);
        putElementToMap("ADRESS_R4", tabName);
        putElementToMap("ADRESS_G4", tabName);
        putElementToMap("ADRESS_U4", tabName);
        putElementToMap("ADRESS_D4", tabName);
        putElementToMap("ADRESS_K4", tabName);
        putElementToMap("ADRESS_O4", tabName);
        putElementToMap("KD4", tabName);
        putElementToMap("VD42", tabName);
        putElementToMap("SD4", tabName);
        putElementToMap("VD41", tabName);
        putElementToMap("VD43", tabName);
        putElementToMap("VD44", tabName);
        putElementToMap("VD46", tabName);
        putElementToMap("VD45", tabName);
        putElementToMap("VD47", tabName);
        putElementToMap("MC_41", tabName);
        putElementToMap("MC_42", tabName);
        putElementToMap("MC_43", tabName);
        putElementToMap("guarantorComment", tabName);
        //endregion
        //region Маппинг вкладки "Предст. плат-ка"
        openPayerAgentTab();
        tabName = "PayerAgentTab";
        putElementToMap("TU1", tabName);
        putElementToMap("PRU1", tabName);
        putElementToMap("NAMEU1", tabName);
        putElementToMap("ND1", tabName);
        putElementToMap("RG1", tabName);
        putElementToMap("GR1", tabName);
        putElementToMap("BP_1", tabName);
        putElementToMap("KODCR1", tabName);
        putElementToMap("KODCR1_additional", tabName);
        putElementToMap("AMR_S1", tabName);
        putElementToMap("AMR_R1", tabName);
        putElementToMap("AMR_G1", tabName);
        putElementToMap("AMR_U1", tabName);
        putElementToMap("AMR_D1", tabName);
        putElementToMap("AMR_K1", tabName);
        putElementToMap("AMR_O1", tabName);
        putElementToMap("KODCN1", tabName);
        putElementToMap("KODCN1_additional", tabName);
        putElementToMap("ADRESS_S1", tabName);
        putElementToMap("ADRESS_R1", tabName);
        putElementToMap("ADRESS_G1", tabName);
        putElementToMap("ADRESS_U1", tabName);
        putElementToMap("ADRESS_D1", tabName);
        putElementToMap("ADRESS_K1", tabName);
        putElementToMap("ADRESS_O1", tabName);
        putElementToMap("KD1", tabName);
        putElementToMap("VD12", tabName);
        putElementToMap("SD1", tabName);
        putElementToMap("VD11", tabName);
        putElementToMap("VD13", tabName);
        putElementToMap("VD14", tabName);
        putElementToMap("VD16", tabName);
        putElementToMap("VD15", tabName);
        putElementToMap("VD17", tabName);
        putElementToMap("MC_11", tabName);
        putElementToMap("MC_12", tabName);
        putElementToMap("MC_13", tabName);
        putElementToMap("payerAgentComment", tabName);
        //endregion
        //region Маппинг вкладки "Предст. получ-ля"
        openRecipientAgentTab();
        tabName = "RecipientAgentTab";
        putElementToMap("TU2", tabName);
        putElementToMap("PRU2", tabName);
        putElementToMap("NAMEU2", tabName);
        putElementToMap("ND2", tabName);
        putElementToMap("RG2", tabName);
        putElementToMap("GR2", tabName);
        putElementToMap("BP_2", tabName);
        putElementToMap("KODCR2", tabName);
        putElementToMap("KODCR2_additional", tabName);
        putElementToMap("AMR_S2", tabName);
        putElementToMap("AMR_R2", tabName);
        putElementToMap("AMR_G2", tabName);
        putElementToMap("AMR_U2", tabName);
        putElementToMap("AMR_D2", tabName);
        putElementToMap("AMR_K2", tabName);
        putElementToMap("AMR_O2", tabName);
        putElementToMap("KODCN2", tabName);
        putElementToMap("KODCN2_additional", tabName);
        putElementToMap("ADRESS_S2", tabName);
        putElementToMap("ADRESS_R2", tabName);
        putElementToMap("ADRESS_G2", tabName);
        putElementToMap("ADRESS_U2", tabName);
        putElementToMap("ADRESS_D2", tabName);
        putElementToMap("ADRESS_K2", tabName);
        putElementToMap("ADRESS_O2", tabName);
        putElementToMap("KD2", tabName);
        putElementToMap("VD22", tabName);
        putElementToMap("SD2", tabName);
        putElementToMap("VD21", tabName);
        putElementToMap("VD23", tabName);
        putElementToMap("VD24", tabName);
        putElementToMap("VD26", tabName);
        putElementToMap("VD25", tabName);
        putElementToMap("VD27", tabName);
        putElementToMap("MC_21", tabName);
        putElementToMap("MC_22", tabName);
        putElementToMap("MC_23", tabName);
        putElementToMap("recipientAgentComment", tabName);
        //endregion
        return UIFieldsMap;
    }

    private void putElementToMap(String elementName, String tabName){
        try{
            Class cl = Class.forName("su.jet.webautotests.Pages.EditPageTabs." + tabName);
            By element = (By)cl.getDeclaredField(elementName).get(this);
            if($(element).exists()){
                UIFieldsMap.put(elementName, $(element).getValue());
            }else {log.warn("Элемент " + elementName + " не найден на вкладке " + tabName + " в UI.");}
        }catch (Exception e){
            log.error(e.getStackTrace());
        }
    }

    @Step("Кликнуть на кнопку \"На отправку\"")
    public TransactionEditPage clickOnToSendBtnFromToolbar() {
        clickOnToSendBtn(0);
        return this;
    }

    @Step("Кликнуть на кнопку \"Действия\"")
    public ActionsOverTransactionsForm clickOnActionsBtnFromTransactionEditPage() {
        clickOnActionsBtn(1);
        return page(ActionsOverTransactionsForm.class);
    }

    @Step("Кликнуть на кнопку \"Экспорт\"")
    public TransactionEditPage clickOnExportBtnFromToolbar() {
        clickOnExportBtn(1);
        return this;
    }
}
