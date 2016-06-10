package su.jet.webautotests.Pages.EditPageTabs;

import org.openqa.selenium.By;
import su.jet.webautotests.Pages.TransactionEditPage;

/**
 * Класс содержит маппинг элементов вкладки "Получатель"
 * Created by vv.drobot on 18.03.2016.
 */
public class RecipientTab extends TransactionEditPage {

    /**
     * Блок "Общие сведения об участнике"
     */
    public static By TU3 = By.xpath("//div[label/span[contains(text(), '(TU3)')]]//input");
    public static By PRU3 = By.xpath("//div[label/span[contains(text(), '(PRU3)')]]//input");
    public static By NAMEU3 = By.xpath("//div[label/span[contains(text(), '(NAMEU3)')]]//input");
    public static By ND3 = By.xpath("//div[label/span[contains(text(), '(ND3)')]]//input");
    public static By RG3 = By.xpath("//div[label/span[contains(text(), '(RG3)')]]//input");
    public static By VP_3 = By.xpath("//div[label/span[contains(text(), '(VP_3)')]]//input");
    public static By CARD_B3 = By.xpath("//div[label/span[contains(text(), '(CARD_B3)')]]//input");
    public static By GR3 = By.xpath("//div[label/span[contains(text(), '(GR3)')]]//input");
    public static By BP_3 = By.xpath("//div[label/span[contains(text(), '(BP_3)')]]//input");

    /**
     * Блок "Информация о месте регистрации/жительства"
     */
    public static By KODCR3 = By.xpath("//div[label/span[contains(text(), '(KODCR3)')]]//input");
    public static By KODCR3_additional = By.xpath("//div[div/div/label/span[contains(text(), '(KODCR3)')]]/div/div[3]/div[1]//input");
    public static By AMR_S3 = By.xpath("//div[label/span[contains(text(), '(AMR_S3)')]]//input");
    public static By AMR_R3 = By.xpath("//div[label/span[contains(text(), '(AMR_R3)')]]//input");
    public static By AMR_G3 = By.xpath("//div[label/span[contains(text(), '(AMR_G3)')]]//input");
    public static By AMR_U3 = By.xpath("//div[label/span[contains(text(), '(AMR_U3)')]]//input");
    public static By AMR_D3 = By.xpath("//div[label/span[contains(text(), '(AMR_D3)')]]//input");
    public static By AMR_K3 = By.xpath("//div[label/span[contains(text(), '(AMR_K3)')]]//input");
    public static By AMR_O3 = By.xpath("//div[label/span[contains(text(), '(AMR_O3)')]]//input");

    /**
     * Блок "Информация о месте нахождения/пребывания"
     */
    public static By KODCN3 = By.xpath("//div[label/span[contains(text(), '(KODCN3)')]]//input");
    public static By KODCN3_additional = By.xpath("//div[div/div/label/span[contains(text(), '(KODCN3)')]]/div/div[3]//input");
    public static By ADRESS_S3 = By.xpath("//div[label/span[contains(text(), '(ADRESS_S3)')]]//input");
    public static By ADRESS_R3 = By.xpath("//div[label/span[contains(text(), '(ADRESS_R3)')]]//input");
    public static By ADRESS_G3 = By.xpath("//div[label/span[contains(text(), '(ADRESS_G3)')]]//input");
    public static By ADRESS_U3 = By.xpath("//div[label/span[contains(text(), '(ADRESS_U3)')]]//input");
    public static By ADRESS_D3 = By.xpath("//div[label/span[contains(text(), '(ADRESS_D3)')]]//input");
    public static By ADRESS_K3 = By.xpath("//div[label/span[contains(text(), '(ADRESS_K3)')]]//input");
    public static By ADRESS_O3 = By.xpath("//div[label/span[contains(text(), '(ADRESS_O3)')]]//input");

    /**
     * Блок "Документ, удостоверяющий личность"
     */
    public static By KD3 = By.xpath("//div[label/span[contains(text(), '(KD3)')]]//input");
    public static By VD32 = By.xpath("//div[label/span[contains(text(), '(VD32)')]]//input");
    public static By SD3 = By.xpath("//div[label/span[contains(text(), '(SD3)')]]//input");
    public static By VD31 = By.xpath("//div[label/span[contains(text(), '(VD31)')]]//input");
    public static By VD33 = By.xpath("//div[label/span[contains(text(), '(VD33)')]]//input");

    /**
     * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
     */
    public static By VD34 = By.xpath("//div[label/span[contains(text(), '(VD34)')]]//input");
    public static By VD36 = By.xpath("//div[label/span[contains(text(), '(VD36)')]]//input");
    public static By VD35 = By.xpath("//div[label/span[contains(text(), '(VD35)')]]//input");
    public static By VD37 = By.xpath("//div[label/span[contains(text(), '(VD37)')]]//input");

    /**
     * Блок "Миграционная карта"
     */
    public static By MC_31 = By.xpath("//div[label/span[contains(text(), '(MC_31)')]]//input");
    public static By MC_32 = By.xpath("//div[label/span[contains(text(), '(MC_32)')]]//input");
    public static By MC_33 = By.xpath("//div[label/span[contains(text(), '(MC_33)')]]//input");

    /**
     * Блок "Банковские идентификационные реквизиты КО, обслуживающей участника"
     */
    public static By NAME_B3 = By.xpath("//div[label/span[contains(text(), '(NAME_B3)')]]//input");
    public static By BIK_B3 = By.xpath("//div[label/span[contains(text(), '(BIK_B3)')]]//input");
    public static By KODCN_B3 = By.xpath("//div[label/span[contains(text(), '(KODCN_B3)')]]//input");
    public static By KODCN_B3_additional = By.xpath("//div[div/div/label/span[contains(text(), '(KODCN_B3)')]]/div/div[4]//input");
    public static By ACC_COR_B3 = By.xpath("//div[label/span[contains(text(), '(ACC_COR_B3)')]]//input");
    public static By ACC_B3 = By.xpath("//div[label/span[contains(text(), '(ACC_B3)')]]//input");

    /**
     * Блок "Банковские идентификационные реквизиты корреспондента"
     */
    public static By NAME_R3 = By.xpath("//div[label/span[contains(text(), '(NAME_R3)')]]//input");
    public static By BIK_R3 = By.xpath("//div[label/span[contains(text(), '(BIK_R3)')]]//input");
    public static By KODCN_R3 = By.xpath("//div[label/span[contains(text(), '(KODCN_R3)')]]//input");
    public static By KODCN_R3_additional = By.xpath("//div[div/div/label/span[contains(text(), '(KODCN_R3)')]]/div/div[4]//input");

    /**
     * Блок "Данные о банковской карте"
     */
    public static By NAME_IS_B3 = By.xpath("//div[label/span[contains(text(), '(NAME_IS_B3)')]]//input");
    public static By BIK_IS_B3 = By.xpath("//div[label/span[contains(text(), '(BIK_IS_B3)')]]//input");

    public static By recipientComment = By.xpath("//div[div/label/span/b[contains(text(), 'Примечание')]]//textarea");

}
