package su.jet.webautotests.Pages.EditPageTabs;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;
import su.jet.webautotests.Pages.TransactionEditPage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс содержит маппинг элементов вкладки "Сведения об операции"
 * Created by vv.drobot on 17.03.2016.
 */
public class OperationInfoTab extends TransactionEditPage {

    /**
     * Блок "Кредитная организация"
     */
    public static By creditOrganization = By.xpath("//div[div/div/div/div/b[text()='Кредитная организация']]/div/div/div/div/input");
    public static By creditOrganizationButton = By.xpath("//div[div/div/div/div/b[text()='Кредитная организация']]/a");
    public static By NUMBF_S = By.xpath("//div[label/span[contains(text(), '(NUMBF_S)')]]//input");
    public static By REGN = By.xpath("//div[label/span[contains(text(), '(REGN)')]]//input");
    public static By BIK_S = By.xpath("//div[label/span[contains(text(), '(BIK_S)')]]//input");
    public static By ND_KO = By.xpath("//div[label/span[contains(text(), '(ND_KO)')]]//input");
    public static By KTU_S = By.xpath("//div[label/span[contains(text(), '(KTU_S)')]]//input");
    public static By TEL = By.xpath("//div[label/span[contains(text(), '(TEL)')]]//input");

    /**
     * Блок "Филиал КО"
     */
    public static By branchButton = By.xpath("//div[div/div/div/div/b[text()='Филиал КО']]//a");
    public static By BRANCH = By.xpath("//div[label[contains(text(), '(BRANCH)')]]/input");
    public static By NUMBF_SS = By.xpath("//div[label/span[contains(text(), '(NUMBF_SS)')]]//input");
    public static By BIK_SS = By.xpath("//div[label/span[contains(text(), '(BIK_SS)')]]//input");
    public static By KTU_SS = By.xpath("//div[label/span[contains(text(), '(KTU_SS)')]]//input");

    /**
     * Блок "Признаки операции"
     */
    public static By DATA = By.xpath("//div[label/span[contains(text(), '(DATA)')]]//input");
    public static By VO = By.xpath("//div[label/span[contains(text(), '(VO)')]]//input");
    public static By DOP_V = By.xpath("//div[label/span[contains(text(), '(DOP_V)')]]//input");
    public static By PRIZ_SD = By.xpath("//div[label/span[contains(text(), '(PRIZ_SD)')]]//input");
    public static By B_PAYER = By.xpath("//div[label/span[contains(text(), '(B_PAYER)')]]//input");
    public static By B_RECIP = By.xpath("//div[label/span[contains(text(), '(B_RECIP)')]]//input");
    public static By PART = By.xpath("//div[label/span[contains(text(), '(PART)')]]//input");
    public static By PRIZ6001 = By.xpath("//div[label/span[contains(text(), '(PRIZ6001)')]]//input");
    public static By DATE_S = By.xpath("//div[label/span[contains(text(), '(DATE_S)')]]//input");
    public static By TERROR = By.xpath("//div[label/span[contains(text(), '(TERROR)')]]//input");

    /**
     * Блок "Реквизиты платежного документа"
     */
    public static By NUM_PAY_D = By.xpath("//div[label/span[contains(text(), '(NUM_PAY_D)')]]//input");
    public static By SUME = By.xpath("//div[label/span[contains(text(), '(SUME)')]]//input");
    public static By SUM = By.xpath("//div[label/span[contains(text(), '(SUM)')]]//input");
    public static By SUM_CON = By.xpath("//div[label/span[contains(text(), '(SUM_CON)')]]//input");
    public static By PRIM_1 = By.xpath("//div[label/span[contains(text(), '(PRIM_1)')]]//textarea");
    public static By DATE_PAY_D = By.xpath("//div[label/span[contains(text(), '(DATE_PAY_D)')]]//input");
    public static By METAL = By.xpath("//div[label/span[contains(text(), '(METAL)')]]//input");
    public static By CURREN = By.xpath("//div[label/span[contains(text(), '(CURREN)')]]//input");
    public static By CURREN_CON = By.xpath("//div[label/span[contains(text(), '(CURREN_CON)')]]//input");

    /**
     * Блок "Дополнительная информация о сведениях"
     */
    public static By DESCR_1 = By.xpath("//div[label/span[contains(text(), '(DESCR_1)')]]//textarea");
    public static By MANUAL_CMNT = By.xpath("//div[div/label/span/b[contains(text(), 'Комментарий к сообщению')]]//textarea");

    @Step("Ввести комментарий \"{0}\" на вкладке 'Сведения об операции'")
    public static void inputComment(String comment){
        $(MANUAL_CMNT).setValue(comment);
    }

    @Step("Проверить что в поле 'Комментарий' присутствует текст \"{0}\" на вкладке 'Сведения об операции'")
    public static void verifyComment(String expectedComment){
        String actualComment = $(OperationInfoTab.MANUAL_CMNT).getValue();
        Assert.assertTrue(actualComment.equals(expectedComment));
    }
}
