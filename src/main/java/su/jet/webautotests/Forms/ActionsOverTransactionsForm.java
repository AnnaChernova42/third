package su.jet.webautotests.Forms;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;
import su.jet.webautotests.Pages.BasePage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static su.jet.webautotests.utils.TestLogger.log;

/**
 * Created by ub.kim on 30.03.2016.
 */
public class ActionsOverTransactionsForm extends BasePage{

    By actionsOverTransactionsForm = By.xpath(".//div[contains(@id, 'app-alerts-alertactionform-form')]//fieldset");
    By selectItemFromCombo = By.xpath(".//div[contains(@id, 'boundlist') and @tabindex='-1']//ul[contains(@id, 'boundlist')]/div[text()]");
    By performBtn = By.xpath(".//span[contains(text(), 'Выполнить')]");

    //region Редактирование операций
    By changeResponsibleCbx = By.xpath(".//fieldset[legend/div[text()='Редактирование операций']]//div[label[text()='Изменить ответственного']]//input");
    By responsibleCombo = By.xpath(".//fieldset[legend/div[text()='Редактирование операций']]//div[label/span[text()='Ответственный:']]//input");
    By changeMainCode = By.xpath("//div[label[text()='Изменить код(ы) вида операции']]/input");
    By mainCodeCombo = By.xpath("//div[label/span[text()='Основной код:']]/div[1]/div/div[2]");

    @Step("Изменить основной код на первый в списке")
    public String changeMainCode(){
        try{
            $(changeMainCode).click();
            $(mainCodeCombo).click();
            String firstCode = $$(selectItemFromCombo).get(0).getText();
            $$(selectItemFromCombo).get(0).click();
            $(performBtn).click();
            return firstCode;
        }catch (Exception e){
            log.error(e.getStackTrace());
            throw e;
        }
    }

    @Step("Изменить ответственного на \"{0}\"")
    public String changeResponsibleOnFirstItemFromList(){
        $(changeResponsibleCbx).click();
        $(responsibleCombo).click();
        String firstResponsible = $$(selectItemFromCombo).get(0).getText();
        $$(selectItemFromCombo).get(0).click();
        $(performBtn).click();
        return firstResponsible;
    }
    //endregion

    //region Выбор действия
    private By transferToSendRbtn = By.xpath(".//fieldset[legend/div[text()='Выбор действия']]//div[label[text()='Передать на отправку']]//input");
    private By sentToCommitRbtn = By.xpath(".//fieldset[legend/div[text()='Выбор действия']]//div[label[text()='Отправить в Комиту']]//input");
    public By reconsiderationRbtn = By.xpath(".//fieldset[legend/div[text()='Выбор действия']]//div[label[text()='Повторное рассмотрение']]//input");

    @Step("В окне \"Действия над операциями\" выбрать действие \"{0}\"")
    public void actionSelection(String action) {
        switch(action){
            case "TransferToSend":
                $(transferToSendRbtn).click();
                $(performBtn).click();
                $(processingIcon).waitUntil(not(visible), longTimeout);
                $(loadingIcon).waitUntil(not(visible), longTimeout);
                break;

            case "SentToCommit":
                $(sentToCommitRbtn).click();
                $(performBtn).click();
                $(confirmLoadRemainingPagesForm).waitUntil(appear, longTimeout);
                $(confirmMessageBoxBtn).click();
                break;

            case "Reconsideration":
                $(reconsiderationRbtn).click();
                $(performBtn).click();
                $(processingIcon).waitUntil(not(visible), longTimeout);
                $(loadingIcon).waitUntil(not(visible), longTimeout);
                break;
        }
    }
    //endregion
}
