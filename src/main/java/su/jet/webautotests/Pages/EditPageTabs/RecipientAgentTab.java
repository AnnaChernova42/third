package su.jet.webautotests.Pages.EditPageTabs;

import org.openqa.selenium.By;
import su.jet.webautotests.Pages.TransactionEditPage;

/**
 * Класс содержит маппинг элементов вкладки "Представитель получателя"
 * Created by vv.drobot on 18.03.2016.
 */
public class RecipientAgentTab extends TransactionEditPage {

    /**
     * Блок "Общие сведения об участнике"
     */
    public static By TU2 = By.xpath("//div[label/span[contains(text(), '(TU2)')]]//input");
    public static By PRU2 = By.xpath("//div[label/span[contains(text(), '(PRU2)')]]//input");
    public static By NAMEU2 = By.xpath("//div[label/span[contains(text(), '(NAMEU2)')]]//input");
    public static By ND2 = By.xpath("//div[label/span[contains(text(), '(ND2)')]]//input");
    public static By RG2 = By.xpath("//div[label/span[contains(text(), '(RG2)')]]//input");
    public static By GR2 = By.xpath("//div[label/span[contains(text(), '(GR2)')]]//input");
    public static By BP_2 = By.xpath("//div[label/span[contains(text(), '(BP_2)')]]//input");

    /**
     * Блок "Информация о месте регистрации/жительства"
     */
    public static By KODCR2 = By.xpath("//div[label/span[contains(text(), '(KODCR2)')]]//input");
    public static By KODCR2_additional = By.xpath("//div[div/div/label/span[contains(text(), '(KODCR2)')]]/div/div[3]//input");
    public static By AMR_S2 = By.xpath("//div[label/span[contains(text(), '(AMR_S2)')]]//input");
    public static By AMR_R2 = By.xpath("//div[label/span[contains(text(), '(AMR_R2)')]]//input");
    public static By AMR_G2 = By.xpath("//div[label/span[contains(text(), '(AMR_G2)')]]//input");
    public static By AMR_U2 = By.xpath("//div[label/span[contains(text(), '(AMR_U2)')]]//input");
    public static By AMR_D2 = By.xpath("//div[label/span[contains(text(), '(AMR_D2)')]]//input");
    public static By AMR_K2 = By.xpath("//div[label/span[contains(text(), '(AMR_K2)')]]//input");
    public static By AMR_O2 = By.xpath("//div[label/span[contains(text(), '(AMR_O2)')]]//input");

    /**
     * Блок "Информация о месте нахождения/пребывания"
     */
    public static By KODCN2 = By.xpath("//div[label/span[contains(text(), '(KODCN2)')]]//input");
    public static By KODCN2_additional = By.xpath("//div[div/div/label/span[contains(text(), '(KODCN2)')]]/div/div[3]//input");
    public static By ADRESS_S2 = By.xpath("//div[label/span[contains(text(), '(ADRESS_S2)')]]//input");
    public static By ADRESS_R2 = By.xpath("//div[label/span[contains(text(), '(ADRESS_R2)')]]//input");
    public static By ADRESS_G2 = By.xpath("//div[label/span[contains(text(), '(ADRESS_G2)')]]//input");
    public static By ADRESS_U2 = By.xpath("//div[label/span[contains(text(), '(ADRESS_U2)')]]//input");
    public static By ADRESS_D2 = By.xpath("//div[label/span[contains(text(), '(ADRESS_D2)')]]//input");
    public static By ADRESS_K2 = By.xpath("//div[label/span[contains(text(), '(ADRESS_K2)')]]//input");
    public static By ADRESS_O2 = By.xpath("//div[label/span[contains(text(), '(ADRESS_O2)')]]//input");

    /**
     * Блок "Документ, удостоверяющий личность"
     */
    public static By KD2 = By.xpath("//div[label/span[contains(text(), '(KD2)')]]//input");
    public static By VD22 = By.xpath("//div[label/span[contains(text(), '(VD22)')]]//input");
    public static By SD2 = By.xpath("//div[label/span[contains(text(), '(SD2)')]]//input");
    public static By VD21 = By.xpath("//div[label/span[contains(text(), '(VD21)')]]//input");
    public static By VD23 = By.xpath("//div[label/span[contains(text(), '(VD23)')]]//input");

    /**
     * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
     */
    public static By VD24 = By.xpath("//div[label/span[contains(text(), '(VD24)')]]//input");
    public static By VD26 = By.xpath("//div[label/span[contains(text(), '(VD26)')]]//input");
    public static By VD25 = By.xpath("//div[label/span[contains(text(), '(VD25)')]]//input");
    public static By VD27 = By.xpath("//div[label/span[contains(text(), '(VD27)')]]//input");

    /**
     * Блок "Миграционная карта"
     */
    public static By MC_21 = By.xpath("//div[label/span[contains(text(), '(MC_21)')]]//input");
    public static By MC_22 = By.xpath("//div[label/span[contains(text(), '(MC_22)')]]//input");
    public static By MC_23 = By.xpath("//div[label/span[contains(text(), '(MC_23)')]]//input");
    public static By recipientAgentComment = By.xpath("//div[div/label/span/b[contains(text(), 'Примечание')]]//textarea");
}
