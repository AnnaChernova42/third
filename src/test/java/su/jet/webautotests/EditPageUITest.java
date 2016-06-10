package su.jet.webautotests;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import su.jet.webautotests.Pages.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import static su.jet.webautotests.utils.TestProperties.prop;

/**
 * Набор тестов для первичной проверки доступности элементов страницы "Редактирование..."
 * Created by vv.drobot on 17.03.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
public class EditPageUITest extends BaseClass {

    private String operNumber = prop.getProperty("testTransaction");
    private AmlPage amlPage = new AmlPage();

    @Test
    public void isEditPageOpen() {
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(operNumber);
        $(By.xpath(editPage.operationNumXpath.replace("CODE", String.valueOf(operNumber)))).shouldBe(visible);
    }
}
