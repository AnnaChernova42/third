package su.jet.webautotests.Pages.EditPageTabs;

import org.openqa.selenium.By;
import su.jet.webautotests.Pages.TransactionEditPage;

/**
 * Класс содержит маппинг элементов вкладки "От имени и по поруч."
 * Created by vv.drobot on 18.03.2016.
 */
public class GuarantorTab extends TransactionEditPage {

    /**
     * Блок "Общие сведения об участнике"
     */
    public static By TU4 = By.xpath("//div[label/span[contains(text(), '(TU4)')]]//input");
    public static By PRU4 = By.xpath("//div[label/span[contains(text(), '(PRU4)')]]//input");
    public static By NAMEU4 = By.xpath("//div[label/span[contains(text(), '(NAMEU4)')]]//input");
    public static By ND4 = By.xpath("//div[label/span[contains(text(), '(ND4)')]]//input");
    public static By RG4 = By.xpath("//div[label/span[contains(text(), '(RG4)')]]//input");
    public static By GR4 = By.xpath("//div[label/span[contains(text(), '(GR4)')]]//input");
    public static By BP_4 = By.xpath("//div[label/span[contains(text(), '(BP_4)')]]//input");

    /**
     * Блок "Информация о месте регистрации/жительства"
     */
    public static By KODCR4 = By.xpath("//div[label/span[contains(text(), '(KODCR4)')]]//input");
    public static By KODCR4_additional = By.xpath("//div[div/div/label/span[contains(text(), '(KODCR4)')]]/div/div[3]//input");
    public static By AMR_S4 = By.xpath("//div[label/span[contains(text(), '(AMR_S4)')]]//input");
    public static By AMR_R4 = By.xpath("//div[label/span[contains(text(), '(AMR_R4)')]]//input");
    public static By AMR_G4 = By.xpath("//div[label/span[contains(text(), '(AMR_G4)')]]//input");
    public static By AMR_U4 = By.xpath("//div[label/span[contains(text(), '(AMR_U4)')]]//input");
    public static By AMR_D4 = By.xpath("//div[label/span[contains(text(), '(AMR_D4)')]]//input");
    public static By AMR_K4 = By.xpath("//div[label/span[contains(text(), '(AMR_K4)')]]//input");
    public static By AMR_O4 = By.xpath("//div[label/span[contains(text(), '(AMR_O4)')]]//input");

    /**
     * Блок "Информация о месте нахождения/пребывания"
     */
    public static By KODCN4 = By.xpath("//div[label/span[contains(text(), '(KODCN4)')]]//input");
    public static By KODCN4_additional = By.xpath("//div[div/div/label/span[contains(text(), '(KODCN4)')]]/div/div[3]//input");
    public static By ADRESS_S4 = By.xpath("//div[label/span[contains(text(), '(ADRESS_S4)')]]//input");
    public static By ADRESS_R4 = By.xpath("//div[label/span[contains(text(), '(ADRESS_R4)')]]//input");
    public static By ADRESS_G4 = By.xpath("//div[label/span[contains(text(), '(ADRESS_G4)')]]//input");
    public static By ADRESS_U4 = By.xpath("//div[label/span[contains(text(), '(ADRESS_U4)')]]//input");
    public static By ADRESS_D4 = By.xpath("//div[label/span[contains(text(), '(ADRESS_D4)')]]//input");
    public static By ADRESS_K4 = By.xpath("//div[label/span[contains(text(), '(ADRESS_K4)')]]//input");
    public static By ADRESS_O4 = By.xpath("//div[label/span[contains(text(), '(ADRESS_O4)')]]//input");

    /**
     * Блок "Документ, удостоверяющий личность"
     */
    public static By KD4 = By.xpath("//div[label/span[contains(text(), '(KD4)')]]//input");
    public static By VD42 = By.xpath("//div[label/span[contains(text(), '(VD42)')]]//input");
    public static By SD4 = By.xpath("//div[label/span[contains(text(), '(SD4)')]]//input");
    public static By VD41 = By.xpath("//div[label/span[contains(text(), '(VD41)')]]//input");
    public static By VD43 = By.xpath("//div[label/span[contains(text(), '(VD43)')]]//input");

    /**
     * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
     */
    public static By VD44 = By.xpath("//div[label/span[contains(text(), '(VD44)')]]//input");
    public static By VD46 = By.xpath("//div[label/span[contains(text(), '(VD46)')]]//input");
    public static By VD45 = By.xpath("//div[label/span[contains(text(), '(VD45)')]]//input");
    public static By VD47 = By.xpath("//div[label/span[contains(text(), '(VD47)')]]//input");

    /**
     * Блок "Миграционная карта"
     */
    public static By MC_41 = By.xpath("//div[label/span[contains(text(), '(MC_41)')]]//input");
    public static By MC_42 = By.xpath("//div[label/span[contains(text(), '(MC_42)')]]//input");
    public static By MC_43 = By.xpath("//div[label/span[contains(text(), '(MC_43)')]]//input");

    public static By guarantorComment = By.xpath("//div[div/label/span/b[contains(text(), 'Примечание')]]//textarea");
}
