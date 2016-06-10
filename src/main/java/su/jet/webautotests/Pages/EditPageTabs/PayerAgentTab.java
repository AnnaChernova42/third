package su.jet.webautotests.Pages.EditPageTabs;

import org.openqa.selenium.By;
import su.jet.webautotests.Pages.TransactionEditPage;

/**
 * Класс содержит маппинг элементов вкладки "Представитель плательщика"
 * Created by vv.drobot on 18.03.2016.
 */
public class PayerAgentTab extends TransactionEditPage {

    /**
     * Блок "Общие сведения об участнике"
     */
    public static By TU1 = By.xpath("//div[label/span[contains(text(), '(TU1)')]]//input");
    public static By PRU1 = By.xpath("//div[label/span[contains(text(), '(PRU1)')]]//input");
    public static By NAMEU1 = By.xpath("//div[label/span[contains(text(), '(NAMEU1)')]]//input");
    public static By ND1 = By.xpath("//div[label/span[contains(text(), '(ND1)')]]//input");
    public static By RG1 = By.xpath("//div[label/span[contains(text(), '(RG1)')]]//input");
    public static By GR1 = By.xpath("//div[label/span[contains(text(), '(GR1)')]]//input");
    public static By BP_1 = By.xpath("//div[label/span[contains(text(), '(BP_1)')]]//input");

    /**
     * Блок "Информация о месте регистрации/жительства"
     */
    public static By KODCR1 = By.xpath("//div[label/span[contains(text(), '(KODCR1)')]]//input");
    public static By KODCR1_additional = By.xpath("//div[div/div/label/span[contains(text(), '(KODCR1)')]]/div/div[3]//input");
    public static By AMR_S1 = By.xpath("//div[label/span[contains(text(), '(AMR_S1)')]]//input");
    public static By AMR_R1 = By.xpath("//div[label/span[contains(text(), '(AMR_R1)')]]//input");
    public static By AMR_G1 = By.xpath("//div[label/span[contains(text(), '(AMR_G1)')]]//input");
    public static By AMR_U1 = By.xpath("//div[label/span[contains(text(), '(AMR_U1)')]]//input");
    public static By AMR_D1 = By.xpath("//div[label/span[contains(text(), '(AMR_D1)')]]//input");
    public static By AMR_K1 = By.xpath("//div[label/span[contains(text(), '(AMR_K1)')]]//input");
    public static By AMR_O1 = By.xpath("//div[label/span[contains(text(), '(AMR_O1)')]]//input");

    /**
     * Блок "Информация о месте нахождения/пребывания"
     */
    public static By KODCN1 = By.xpath("//div[label/span[contains(text(), '(KODCN1)')]]//input");
    public static By KODCN1_additional = By.xpath("//div[div/div/label/span[contains(text(), '(KODCN1)')]]/div/div[3]//input");
    public static By ADRESS_S1 = By.xpath("//div[label/span[contains(text(), '(ADRESS_S1)')]]//input");
    public static By ADRESS_R1 = By.xpath("//div[label/span[contains(text(), '(ADRESS_R1)')]]//input");
    public static By ADRESS_G1 = By.xpath("//div[label/span[contains(text(), '(ADRESS_G1)')]]//input");
    public static By ADRESS_U1 = By.xpath("//div[label/span[contains(text(), '(ADRESS_U1)')]]//input");
    public static By ADRESS_D1 = By.xpath("//div[label/span[contains(text(), '(ADRESS_D1)')]]//input");
    public static By ADRESS_K1 = By.xpath("//div[label/span[contains(text(), '(ADRESS_K1)')]]//input");
    public static By ADRESS_O1 = By.xpath("//div[label/span[contains(text(), '(ADRESS_O1)')]]//input");

    /**
     * Блок "Документ, удостоверяющий личность"
     */
    public static By KD1 = By.xpath("//div[label/span[contains(text(), '(KD1)')]]//input");
    public static By VD12 = By.xpath("//div[label/span[contains(text(), '(VD12)')]]//input");
    public static By SD1 = By.xpath("//div[label/span[contains(text(), '(SD1)')]]//input");
    public static By VD11 = By.xpath("//div[label/span[contains(text(), '(VD11)')]]//input");
    public static By VD13 = By.xpath("//div[label/span[contains(text(), '(VD13)')]]//input");

    /**
     * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
     */
    public static By VD14 = By.xpath("//div[label/span[contains(text(), '(VD14)')]]//input");
    public static By VD16 = By.xpath("//div[label/span[contains(text(), '(VD16)')]]//input");
    public static By VD15 = By.xpath("//div[label/span[contains(text(), '(VD15)')]]//input");
    public static By VD17 = By.xpath("//div[label/span[contains(text(), '(VD17)')]]//input");

    /**
     * Блок "Миграционная карта"
     */
    public static By MC_11 = By.xpath("//div[label/span[contains(text(), '(MC_11)')]]//input");
    public static By MC_12 = By.xpath("//div[label/span[contains(text(), '(MC_12)')]]//input");
    public static By MC_13 = By.xpath("//div[label/span[contains(text(), '(MC_13)')]]//input");

    public static By payerAgentComment = By.xpath("//div[div/label/span/b[contains(text(), 'Примечание')]]//textarea");

}
