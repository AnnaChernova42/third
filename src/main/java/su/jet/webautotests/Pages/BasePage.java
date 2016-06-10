package su.jet.webautotests.Pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static su.jet.webautotests.utils.TestProperties.prop;
import static su.jet.webautotests.utils.TestLogger.log;

/**
 * Created by ub.kim on 23.03.2016.
 */
public class BasePage {

    protected int longTimeout = Integer.parseInt(prop.getProperty("longTimeout"));
    protected By loadingIcon = By.xpath(".//div[contains(text(), 'Загрузка...')]");
    protected By processingIcon = By.xpath(".//div[contains(text(), 'Обработка...')]");
    private By status = By.xpath("//div[contains(text(), 'Загружено операций:')]");
    private By toSendBtn = By.xpath(".//span[text()='На отправку']");
    private By actionsBtn = By.xpath(".//span[text()='Действия']");
    private By exportBtn = By.xpath(".//span[text()='Экспорт']");
    protected By confirmLoadRemainingPagesForm = By.xpath(".//div[contains(@id, 'messagebox') and contains(text(), 'Действие: \"Отправить в Комиту\" успешно выполнено для операции')]");
    protected By confirmMessageBoxBtn = By.xpath(".//div[contains(@id,'messagebox')]//span[contains(text(), 'OK')]");
    public static By cellarBody = By.xpath(".//div[contains(@id, 'app-monitoring-alerts-bottompanel') and contains(@id, 'body')]");

    protected void waitLoadingProcess() {
        $(status).waitUntil(visible, longTimeout);
        $(processingIcon).waitUntil(not(visible), longTimeout);
        $(loadingIcon).waitUntil(not(visible), longTimeout);
    }

    protected void clickOnToSendBtn(int index) {
        $$(toSendBtn).get(index).click();
        $(processingIcon).waitUntil(not(visible), longTimeout);
        $(loadingIcon).waitUntil(not(visible), longTimeout);
    }

    protected void clickOnActionsBtn(int index) {
        $$(actionsBtn).get(index).click();
    }

    protected void clickOnExportBtn(int index) {
        $$(exportBtn).get(index).click();
        $(confirmLoadRemainingPagesForm).waitUntil(appear, longTimeout);
        $(confirmMessageBoxBtn).click();
        $(processingIcon).waitUntil(not(visible), longTimeout);
        $(loadingIcon).waitUntil(not(visible), longTimeout);
    }
}
