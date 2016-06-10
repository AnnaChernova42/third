package su.jet.webautotests.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;
import su.jet.webautotests.DataConstants.*;
import su.jet.webautotests.Forms.ActionsOverTransactionsForm;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;
import static su.jet.webautotests.utils.TestLogger.log;

/**
 * Created by ub.kim on 09.03.2016.
 */
public class AmlPage extends BasePage {

    final int timeout = 10000;

    String operationXpath = "*//tbody/tr/td//*[text()='CODE']";
    By informationTab = By.xpath("*//span[text()='Сведения об операции']");
    String baseXpath = ".//div[contains(@id, 'boundlist')]//*[contains(text(),'value')]";
    String statusXpath = ".//div[contains(@id, 'boundlist')]//*[text()='value']";
    String cellInTableByTransactionNumColumn = ".//div[@class='x-grid-item-container']//tbody/tr[td[1]/div[text()='CODE']]//td[NUM]/div";
    String cellInTableByAnyColumn = ".//div[@class='x-grid-item-container']//tbody/tr[td//*[text()='CODE']]";
    String cellsInTable = ".//div[@class='x-grid-item-container']//tbody//td[NUM]/div";
    String contextMenuValue = ".//div[contains(@id,'menuitem')]//span[text()='value']";
    By numberOfRecords = By.xpath(".//div[contains(@id,'grid')]//div/table");
    public String columnNameXPath = ".//div[span//span[text()='value']]";
    By ascendingOrdering = By.xpath(".//div[contains(@id,'menuitem')]//span[text()='Сортировать по возрастанию']");
    By descendingOrdering = By.xpath(".//div[contains(@id,'menuitem')]//span[text()='Сортировать по убыванию']");
    By columnsSelect = By.xpath(".//div[contains(@id, 'menu')]//span[text()='Столбцы']");
    By columnsCheckedItemsSelect = By.xpath(".//div[contains(@id, 'menu')]//div[contains(@class, 'x-box-item x-menu-item-checked')]//a/span");
    By columnsUncheckedItemsSelect = By.xpath(".//div[contains(@id, 'menu')]//div[contains(@class, 'x-menu-item-unchecked')]//a/span");
    By columnsDisabledItemSelect = By.xpath(".//div[contains(@id, 'menu')]//div[contains(@class, 'x-menu-item-disabled')]");

    //region Мониторинг
    By monitoringSelect = By.xpath(".//span[text()='Мониторинг']");
    By listOperations = By.xpath(".//span[text()='Список выявленных операций']");
    public By listOperationsTitlePage = By.xpath(".//div[contains(@id, 'header')]//label[text()='Выявленные операции']");
    By creationOES = By.xpath(".//span[text()='Коды ОПОК']");

    //Строка в нижней части страницы, типа "Загружено операций: ..."
    By operationsDownloadedCount = By.xpath("//div[contains(text(), 'Загружено операций:')]/b[1]");

    String mainStatusXpath = "//div[div/div/div/div[text()='_ID_']]//input[1]";

    public int getCountOfDownloadedOperations(){
        String count = $(operationsDownloadedCount).getText();
        return Integer.parseInt(count);
    }

    @Step("Открыть \"Мониторинг\" и выбрать \"{0}\"")
    public void MonitoringBtn(String value) {
        $(monitoringSelect).click();
        switch (value){
            case "ListOperations":
                $(listOperations).click();
                $(listOperationsTitlePage).waitUntil(visible, timeout);
                break;
            case "CreationOES":
                $(creationOES).click();
                break;
        }
    }
    //endregion

    //region Администрирование
    // Администрирование -> Обязательный контроль -> <...>
    By administrationSelect = By.xpath(".//span[text()='Администрирование']");
    By mandatoryControl = By.xpath(".//span[text()='Обязательный контроль']");
    By codesOPOK = By.xpath(".//span[text()='Коды ОПОК']");
    By codesOPOKTitlePage = By.xpath(".//div[contains(@id, 'title')]/div[text()='Коды ОПОК']");
    By standardTerms = By.xpath(".//span[text()='Стандартные условия выявления']");
    By rulesIdentify = By.xpath(".//span[text()='Правила выявления']");
    By testingRulesIdentify = By.xpath(".//span[text()='Тестирование правил выявления']");

    // Администрирование -> Назначение ответственных -> <...>
    By assignResponsible = By.xpath(".//span[text()='Назначение ответственных']");
    By rulesIdentifyResponsible = By.xpath(".//span[text()='Правила назначения ответственных']");
    By replaceResponsible = By.xpath(".//span[text()='Замещения ответственных']");
    By limitsDestination = By.xpath(".//span[text()='Лимиты назначения']");

    // Администрирование -> ОЭС -> <...>
    By oes = By.xpath(".//span[text()='ОЭС']");
    By infoAboutKO = By.xpath(".//span[text()='Информация о КО']");
    By ko_ParticipantsOfOperations = By.xpath(".//span[text()='КО участники операций']");
    By checkRules = By.xpath(".//span[text()='Правила проверки']");

    @Step("Открыть \"Администрация\" и выбрать \"{0}\"")
    public void AdministrationBtn(String value) {
        $(administrationSelect).click();
        switch (value){
            case "MandatoryControl-CodesOPOK":
                $(mandatoryControl).hover();
                $(codesOPOK).hover().click();
                $(codesOPOKTitlePage).waitUntil(visible, timeout);
                break;
            case "MandatoryControl-StandardTerms":
                $(mandatoryControl).hover();
                $(standardTerms).hover().click();
                break;
            case "MandatoryControl-RulesIdentify":
                $(mandatoryControl).hover();
                $(codesOPOK).hover();
                $(rulesIdentify).hover().click();
                break;
            case "MandatoryControl-TestingRulesIdentify":
                $(mandatoryControl).hover();
                $(codesOPOK).hover();
                $(testingRulesIdentify).hover().click();
                break;
            case "AssignResponsible-RulesIdentifyResponsible":
                $(assignResponsible).hover();
                $(rulesIdentifyResponsible).hover().click();
                break;
            case "AssignResponsible-ReplaceResponsible":
                $(assignResponsible).hover();
                $(rulesIdentifyResponsible).hover();
                $(replaceResponsible).hover().click();
                break;
            case "AssignResponsible-LimitsDestination":
                $(assignResponsible).hover();
                $(rulesIdentifyResponsible).hover();
                $(limitsDestination).hover().click();
                break;
            case "OES-InfoAboutKO":
                $(oes).hover();
                $(infoAboutKO).hover().click();
                break;
            case "OES-KO_ParticipantsOfOperations":
                $(oes).hover();
                $(ko_ParticipantsOfOperations).hover().click();
                break;
            case "OES-CheckRules":
                $(oes).hover();
                $(checkRules).hover().click();
                break;
        }
    }
    //endregion

    //region Тулбар "Выявленные операции"
    By beControlledBtn = By.xpath(".//span[text()='Подлежит ']");
    By cancelIncorrectTokenBtn = By.xpath(".//span[text()='Отменить (неверная ']");
    By cancelBtn = By.xpath(".//span[text()='тменить']");
    By attachBtn = By.xpath(".//span[text()='Прикрепить']");
    By showList = By.xpath(".//div[contains(@id, 'buttongroup')]//input");
    By clearShowList = By.xpath(".//div[contains(@id, 'buttongroup')]//div[contains(@id,'trigger-clear')]");
    public By showBtn = By.xpath(".//span[text()='Показать']");
    By loadNextPageBtn = By.xpath(".//span[text()='>>']");
    By loadRemainingPagesBtn = By.xpath(".//span[text()='>>|']");
    By detailsBtn = By.xpath(".//span[text()='Детали']");
    By confirmLoadRemainingPagesForm = By.xpath(".//div[contains(@id, 'messagebox') and contains(text(), 'Будет выполнена загрузка большого количества операций')]");

    @Step("Кликнуть на кнопку \"Подлежит контролю\"")
    public void clickOnBeControlledBtn() {
        $(beControlledBtn).click();
    }

    @Step("Кликнуть на кнопку \"Отменить (неверная лексема)\"")
    public void clickOnCancelIncorrectTokenBtn() {
        $(cancelIncorrectTokenBtn).click();
    }

    @Step("Кликнуть на кнопку \"Отменить\"")
    public void clickOnCancelBtn() {
        $(cancelBtn).click();
    }

    @Step("Кликнуть на кнопку \"На отправку\"")
    public AmlPage clickOnToSendBtnFromToolbar() {
        clickOnToSendBtn(0);
        return this;
    }

    @Step("Кликнуть на кнопку \"Действия\"")
    public ActionsOverTransactionsForm clickOnActionsBtnFromToolbar() {
        clickOnActionsBtn(0);
        return page(ActionsOverTransactionsForm.class);
    }

    @Step("Кликнуть на кнопку \"Прикрепить\"")
    public void clickOnAttachBtn() {
        $(attachBtn).click();
    }

    @Step("Кликнуть на кнопку \"Экспорт\"")
    public AmlPage clickOnExportBtnFromToolbar() {
        clickOnExportBtn(0);
        return this;
    }

    @Step("Выбрать фильтр  \"{0}\"")
    public AmlPage clickOnShowList(String value) {
        $(showList).click();
        $(By.xpath(baseXpath.replace("value", value))).click();
        return this;
    }

    @Step("Очистить фильтр")
    public AmlPage clearShowList() {
        $(clearShowList).click();
        return this;
    }

    @Step("Нажать на кнопку \"Показать\"")
    public AmlPage clickOnShowBtn() {
        $(showBtn).click();
        waitLoadingProcess();
        return this;
    }

    @Step("Нажать на кнопку \"Загрузить следующую страницу\"")
    public void clickOnLoadNextPageBtn() {
        $(loadNextPageBtn).click();
        waitLoadingProcess();
    }

    @Step("Нажать на кнопку \"Загрузить оставшиеся страницы\"")
    public void clickOnLoadRemainingPagesBtn() {
        $(loadRemainingPagesBtn).click();
        if ($(confirmLoadRemainingPagesForm).exists())
            $(confirmMessageBoxBtn).click();
        waitLoadingProcess();
    }

    @Step("Кликнуть на кнопку \"Детали\"")
    public AmlPage clickOnDetailsBtn() {
        if(!$(cellarBody).isDisplayed())
        $(detailsBtn).click();
        $(cellarBody).shouldBe(visible);
        return this;
    }

    //endregion

    //region Тулбар строки состояния
    By toolbarNumberOfTransactions = By.xpath(".//div[contains(@id,'toolbar')]//div[@class='counter' and contains(text(), 'Загружено')]");

    @Step("Получить число загруженных операций из строки состояния")
    public String getTextLoadedTrsnsactions(){
        return $(toolbarNumberOfTransactions).text();
    }

    //endregion

    //region Выявленные операции - Фильтры

    By showFilters = By.xpath(".//span[text()='Показать фильтры']");
    By allowFilters = By.xpath(".//span[text()='Учитывать фильтры']");
    /**
     * Вид операции
     */
    By codeFld = By.xpath(".//fieldset[legend/div[text()='Вид операции']]//div[label/span[text()='Код:']]//input");
    By searchInFld = By.xpath(".//fieldset[legend/div[text()='Вид операции']]//div[label/span[text()='искать в:']]//input");

    /**
     * Выявленная операция
     */
    By numberFld = By.xpath(".//fieldset[legend/div[text()='Выявленная операция']]//div[label/span[text()='Номер:']]//input");
    By responsibleFld = By.xpath(".//fieldset[legend/div[text()='Выявленная операция']]//div[label/span[text()='Ответственный:']]//input");
    By statusFld = By.xpath(".//fieldset[legend/div[text()='Выявленная операция']]//div[label/span[text()='Статус:']]//input");
    By clearStatusFld = By.xpath(".//fieldset[legend/div[text()='Выявленная операция']]//div[label/span[text()='Статус:']]//div[contains(@id, 'clear')]");

    /**
     * Сумма операции
     */
    By sumFromFld = By.xpath(".//fieldset[legend/div[text()='Сумма операции']]//div[label/span[text()='от:']]//input");
    By sumToFld = By.xpath(".//fieldset[legend/div[text()='Сумма операции']]//div[label/span[text()='до:']]//input");

    /**
     * Дата операции
     */
    By dateFromFld = By.xpath(".//fieldset[legend/div[text()='Дата операции']]//div[label/span[text()='от:']]//input");
    By dateToFld = By.xpath(".//fieldset[legend/div[text()='Дата операции']]//div[label/span[text()='до:']]//input");

    /**
     * Подразделение
     */
    By tbFld = By.xpath(".//fieldset[legend/div[text()='Подразделение']]//div[label/span[text()='ТБ:']]//input");
    By osbFld = By.xpath(".//fieldset[legend/div[text()='Подразделение']]//div[label/span[text()='ОСБ:']]//input");


    /**
     * Чекбоксы
     */
    By showOldOperations = By.xpath(".//div[label/span[text()='Показывать давно обработанные операции:']]//input");

    @Step("Кликнуть на кнопку \"Показать фильтры\"")
    public AmlPage clickOnShowFilters() {
        if (!$(By.xpath(".//a[contains(@class, 'pressed')]")).$(showFilters).isDisplayed())
        $(showFilters).click();
        return this;
    }

    @Step("Кликнуть на кнопку \"Учитывать фильтры\"")
    public AmlPage clickOnAllowFilters() {
        $(allowFilters).click();
        return this;
    }

    @Step("Выставить значение \"{0}\" в фильтре \"Код\"")
    public AmlPage setCodeFld(String value) {
        $(codeFld).setValue(value);
        $(By.xpath(baseXpath.replace("value", value))).click();
        return this;
    }

    @Step("Выставить значение \"{0}\" в фильтре \"Искать в\"")
    public AmlPage setSearchInFld(String value) {
        $(searchInFld).click();
        $(By.xpath(baseXpath.replace("value", value))).click();
        return this;
    }

    @Step("Выставить значение \"{0}\" в фильтре \"Номер\"")
    public void setNumberFld(String value) {
        $(numberFld).setValue(value);
    }

    @Step("Выставить значение \"{0}\" в фильтре \"Ответственный\"")
    public AmlPage setResponsibleFld(String value) {
        $(responsibleFld).click();
        $(By.xpath(baseXpath.replace("value", value))).click();
        return this;
    }

    @Step("Выставить значения \"{0}\" в фильтре \"Статус\"")
    public AmlPage setStatusFld(String[] values) {
        $(statusFld).click();
        for (String value:values) {
            $(By.xpath(statusXpath.replace("value", value))).waitUntil(visible, timeout);
            if($(By.xpath(statusXpath.replace("value", value))).isDisplayed()) {
                $(By.xpath(statusXpath.replace("value", value))).click();
            }
            else{
                log.error("No such status " + value);
                continue;
            }
        }
        return this;
    }

    @Step("Выставить значение \"{0}\" в фильтре \"Статус\"")
    public AmlPage setStatusFld(String value) {
        $(statusFld).click();
        $(By.xpath(statusXpath.replace("value", value))).waitUntil(visible, timeout);
        if($(By.xpath(statusXpath.replace("value", value))).exists()) {
            $(By.xpath(statusXpath.replace("value", value))).click();
        }
            else{
                log.error("No such status " + value);
            }
        return this;
    }

    @Step("Очистить фильтр \"Статус\"")
    public AmlPage clearStatusFld() {
        $(clearStatusFld).click();
        return this;
    }

    @Step("Выставить значение \"{0}\" в фильтре \"Сумма операции от\"")
    public AmlPage setSumFromFld(String value) {
        $(sumFromFld).setValue(value);
        return this;
    }

    @Step("Выставить значение \"{0}\" в фильтре \"Сумма операции до\"")
    public AmlPage setSumFromTo(String value) {
        $(sumToFld).setValue(value);
        return this;
    }

    @Step("Выставить значение \"{0}\" в фильтре \"Дата операции от\"")
    public AmlPage setDateFromFld(String value) {
        $(dateFromFld).setValue(value);
        return this;
    }

    @Step("Выставить значение \"{0}\" в фильтре \"Дата операции до\"")
    public AmlPage setDateToFld(String value) {
        $(dateToFld).setValue(value);
        return this;
    }

    @Step("Выставить значение \"{0}\" в фильтре \"Подразделение ТБ\"")
    public AmlPage setTbFld(List<String> values) {
        $(tbFld).click();
        for (String value:values) {
            $(By.xpath(baseXpath.replace("value", value))).click();
        }
        $(tbFld).pressEscape();
        return this;
    }

    @Step("Выставить значение \"{0}\" в фильтре \"Подразделение ОСБ\"")
    public AmlPage setOsbFld(String value) {
        $(osbFld).click();
        $(By.xpath(baseXpath.replace("value", value))).scrollTo().click();
        $(osbFld).pressEscape();
        return this;
    }

    @Step("Отметить чекбокс \"Показать давно обработанные операции\"")
    public void setChkbxShowOldOperations() {
        if (!$(showOldOperations).isSelected()){
            $(showOldOperations).click();
        }
    }

    //endregion

    //region Выявленные операции - Работа с таблицей

    private By transactionNumbers = By.xpath(".//*/tbody/tr/td[1]/div");

    @Step("Получить значение из таблицы колонки \"Номер операции\" операцию \"{0}\"")
    public SelenideElement getOperation(String num){
        return $(By.xpath(operationXpath.replace("CODE", String.valueOf(num))));
    }

    @Step("Получить все значения из таблицы колонки \"Номер операции\"")
    public List<String> getAllOperations(){
        List<String> list = new ArrayList<>();
        for (String transaction: $$(transactionNumbers).getTexts()) {
            list.add(transaction);
        }
        return list;
    }

    @Step("Получить значение из таблицы колонки \"Статус операции\" для операции \"{0}\"")
    public String getOperationStatusByTransactionNumber(String num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(2)))).getText();
    }

    @Step("Получить все статусы \"{0}\" из таблицы колонки \"Статус операции\"")
    public ElementsCollection getSpecificStatus(String status){
        return $$(By.xpath(cellInTableByAnyColumn.replace("CODE", String.valueOf(status))));
    }

    @Step("Получить все значения из таблицы колонки \"Статус операции\"")
    public List<String> getAllOperationStatus(){
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(2))));
        List<String> all = new ArrayList<>();
        for (String element : elements.getTexts()){
            all.add(element);
        }
        return all;
    }

    @Step("Получить значение из таблицы колонки \"Вид операции\" для операции \"{0}\"")
    public SelenideElement getOperationType(String num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", num).replace("NUM", String.valueOf(3))));
    }

    @Step("Получить все значения из таблицы колонки \"Вид операции\"")
    public List<String> getAllOperationType(){
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(3))));
        List<String> all = new ArrayList<>();
        for (String element : elements.getTexts()){
            all.add(element);
        }
        return all;
    }

    @Step("Получить значение из таблицы колонки \"Дополнительный вид операции\" для операции \"{0}\"")
    public SelenideElement getAdditionalOperationType(int num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(4))));
    }

    @Step("Получить все значения из таблицы колонки \"Дополнительный вид операции\"")
    public List<String> getAllAdditionalOperationType(){
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(4))));
        List<String> all = new ArrayList<>();
        for (String element : elements.getTexts()){
            all.add(element);
        }
        return all;
    }

    @Step("Получить значение из таблицы колонки \"Статус сообщения\" для операции \"{0}\"")
    public String getStatusMessage(String num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(5)))).getText();
    }

    @Step("Получить все значения из таблицы колонки \"Статус сообщения\"")
    public List<String> getAllStatusMessage(){
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(5))));
        List<String> all = new ArrayList<>();
        for (String element : elements.getTexts()){
            all.add(element);
        }
        return all;
    }

    @Step("Получить все статусы \"{0}\" из таблицы колонки \"Статус сообщения\"")
    public ElementsCollection getSpecificMessageStatus(String status){
        return $$(By.xpath(cellInTableByAnyColumn.replace("CODE", String.valueOf(status))));
    }

    @Step("Получить значение из таблицы колонки \"Сумма в рублях\" для операции \"{0}\"")
    public SelenideElement getSumInRub(int num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(6))));
    }

    @Step("Получить все значения из таблицы колонки \"Сумма в рублях\"")
    public List<Float> getAllSumInRub(){
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(6))));
        List<Float> all = new ArrayList<>();
        for (String element : elements.getTexts()){
            all.add(Float.valueOf(element.replace(",", ".")));
        }
        return all;
    }

    @Step("Получить значение из таблицы колонки \"Сумма в валюте\" для операции \"{0}\"")
    public SelenideElement getSumInCurrency(int num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(7))));
    }

    @Step("Получить все значения из таблицы колонки \"Сумма в валюте\"")
    public List<Float> getAllSumInCurrency(){
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(7))));
        List<Float> all = new ArrayList<>();
        for (String element : elements.getTexts()){
            all.add(Float.valueOf(element.replace(",", ".")));
        }
        return all;
    }

    @Step("Получить значение из таблицы колонки \"Валюта\" для операции \"{0}\"")
    public SelenideElement getCurrency(int num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(8))));
    }

    @Step("Получить все значения из таблицы колонки \"Валюта\"")
    public List<String> getAllCurrency(){
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(8))));
        List<String> all = new ArrayList<>();
        for (String element : elements.getTexts()){
            all.add(element);
        }
        return all;
    }

    @Step("Получить значение из таблицы колонки \"Дата операции\" для операции \"{0}\"")
    public SelenideElement getOperationDate(int num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(9))));
    }

    @Step("Получить все значения из таблицы колонки \"Дата операции\"")
    public List<Date> getAllOperationDate() throws ParseException {
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(9))));
        List<Date> all = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        for (String element : elements.getTexts()){
            Date date = sdf.parse(element);
            all.add(date);
        }
        return all;
    }

    @Step("Получить значение из таблицы колонки \"Обработать к...\" для операции \"{0}\"")
    public SelenideElement getProcessTo(int num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(10))));
    }

    @Step("Получить все значения из таблицы колонки \"Обработать к...\"")
    public List<Date> getAllProcessTo() throws ParseException {
        List<Date> all = new ArrayList<Date>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(10))));
        for (String element : elements.getTexts()){
            Date date = sdf.parse(element);
            all.add(date);
        }
        return all;
    }

//    @Step("Получить все значения из таблицы колонки \"Обработать к...\"")
//    public List<String> getAllProcessTo(){
//        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(10))));
//        List<String> all = new ArrayList<>();
//        for (String element : elements.getTexts()){
//            all.add(element);
//        }
//        return all;
//    }

    @Step("Получить значение из таблицы колонки \"Ответственный\" для операции \"{0}\"")
    public String getResponsible(String num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(11)))).getText();
    }

    @Step("Получить все значения из таблицы колонки \"Ответственный\"")
    public List<String> getAllResponsible(){
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(11))));
        List<String> all = new ArrayList<>();
        for (String element : elements.getTexts()){
            all.add(element);
        }
        return all;
    }

    @Step("Получить значение из таблицы колонки \"Комментарий\" для операции \"{0}\"")
    public SelenideElement getComment(int num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(12))));
    }

    @Step("Получить все значения из таблицы колонки \"Комментарий\"")
    public List<String> getAllComment(){
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(12))));
        List<String> all = new ArrayList<>();
        for (String element : elements.getTexts()){
            all.add(element);
        }
        return all;
    }

    @Step("Получить значение из таблицы колонки \"Нал/Бнал\" для операции \"{0}\"")
    public SelenideElement getCashOrClearing(int num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(13))));
    }

    @Step("Получить все значения из таблицы колонки \"Нал/Бнал\"")
    public List<String> getAllCashOrClearing(){
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(13))));
        List<String> all = new ArrayList<>();
        for (String element : elements.getTexts()){
            all.add(element);
        }
        return all;
    }

    @Step("Получить значение из таблицы колонки \"Приложение\" для операции \"{0}\"")
    public SelenideElement getAttachment(int num){
        return $(By.xpath(cellInTableByTransactionNumColumn.replace("CODE", String.valueOf(num)).replace("NUM", String.valueOf(14))));
    }

    @Step("Получить все значения из таблицы колонки \"Приложение\"")
    public List<String> getAllAttachment(){
        ElementsCollection elements = $$(By.xpath(cellsInTable.replace("NUM", String.valueOf(14))));
        List<String> all = new ArrayList<>();
        for (String element : elements.getTexts()){
            all.add(element);
        }
        return all;
    }

    @Step("Получить число операций в таблице")
    public ElementsCollection getCountOfRecords(){
        return $$(numberOfRecords);
    }

    @Step("Подсчитать все загруженные операции на странице")
    public HashSet<String> getNumberOfLoadedTransactions(){
        HashSet<String> myHashSet = new HashSet<>();
        int numberOfTransactions1, numberOfTransactions2;

        if ($$(numberOfRecords).size() > 0)

            do {
                ElementsCollection elements1 = $$(numberOfRecords);
                for (String element : elements1.getTexts()) {
                    myHashSet.add(element);
                }
                numberOfTransactions1 = myHashSet.size();
                elements1.last().scrollTo().click();
                sleep(1000);

                ElementsCollection elements2 = $$(numberOfRecords);
                for (String element : elements2.getTexts()) {
                    myHashSet.add(element);
                }
                numberOfTransactions2 = myHashSet.size();
                elements2.last().scrollTo().click();
                sleep(1000);
            }
            while (numberOfTransactions1 != numberOfTransactions2);
        return myHashSet;
    }

    //endregion

    @Step("Найти номер операции \"{0}\" и открыть по двойному клику")
    public TransactionEditPage openTransactionByDoubleClick(String operationNumber) {
        By transactionElement = By.xpath(operationXpath.replace("CODE", operationNumber));
        if(!$(transactionElement).isDisplayed()) {
            if(!$(numberFld).isDisplayed()){
                clickOnShowFilters();
            }
            $(numberFld).waitUntil(visible, timeout);
            setNumberFld(operationNumber);
            clickOnShowBtn();
        }
        if(!$(transactionElement).isDisplayed()){
            log.error("Transaction " + operationNumber + " isn't found");
            return null;
        } else {
            $(transactionElement).waitUntil(visible, timeout).doubleClick();
            $(informationTab).waitUntil(visible, timeout);
        }
        return page(TransactionEditPage.class);
    }

    @Step("Найти номер операции \"{0}\" и открыть по кнопке Enter")
    public TransactionEditPage openTransactionByClickToEnterButton(String operationNumber) {
        By transactionElement = By.xpath(operationXpath.replace("CODE", operationNumber));
        if(!$(transactionElement).isDisplayed()) {
            if(!$(numberFld).isDisplayed()){
                clickOnShowFilters();
            }
            $(numberFld).waitUntil(visible, timeout);
            setNumberFld(operationNumber);
            clickOnShowBtn();
        }
        if(!$(transactionElement).isDisplayed()){
            log.error("Transaction " + operationNumber + " isn't found");
            return null;
        } else {
            $(transactionElement).waitUntil(visible, timeout).pressEnter();
            $(informationTab).waitUntil(visible, timeout);
        }
        return page(TransactionEditPage.class);
    }

    @Step("Выделить операцию \"{0}\"")
    public AmlPage findTransactionByNumber(String operationNumber){
        if(!getOperation(operationNumber).isDisplayed()){
            clickOnShowFilters();
            $(numberFld).waitUntil(visible, timeout);
            setNumberFld(operationNumber);
            clearShowList();
            clickOnShowBtn();
        }
        if(getOperation(operationNumber).isDisplayed())
            getOperation(operationNumber).click();
        else log.error("Transaction " + operationNumber + " isn't found");
        return this;
    }

    @Step("Выделить операцию \"{0}\"")
    public String selectFirstTransactionByStatus(String[] status) {
        String transactionCode = null;
        if (getSpecificStatus(String.valueOf(status)).size() == 0) {
            clickOnShowFilters();
            setStatusFld(status);
            clickOnShowBtn();
            transactionCode = getSpecificStatus(status[0]).get(0).$(By.xpath("td[1]/div")).getText();
            getSpecificStatus(status[0]).get(0).click();
            return transactionCode;
        }

        else if (getSpecificStatus(status[0]).size() > 0){
            transactionCode = getSpecificStatus(status[0]).get(0).$(By.xpath("td[1]/div")).getText();
            getSpecificStatus(status[0]).get(0).click();
            return transactionCode;
        }
        else log.error("Transaction with status " + status + " isn't found!");
        return transactionCode;
    }

    @Step("Выделить операции \"{0}\"")
    public List<String> selectMultipleTransactionsByStatus(String[] status) {
        List<String> link = new ArrayList<>();
        clickOnShowFilters();
        setStatusFld(status);
        clickOnShowBtn();

        //actions().click(getSpecificStatus(status[0]).get(0)).build().perform();
        if (getSpecificStatus(status[0]).size() != 0 || getSpecificStatus(status[1]).size() != 0) {
            for (int i = 0; i < 2; i++) {
                //actions().clickAndHold(getSpecificStatus(status[i]).get(0)).build().perform();
                actions().click(getSpecificStatus(status[0]).get(0))
                        .keyDown(getSpecificStatus(status[1]).get(0), Keys.LEFT_CONTROL).build().perform();
                link.add(getSpecificStatus(status[i]).get(0).$(By.xpath("td[1]/div")).getText());
            }
            log.info(link);
            return link;
        }

        else log.error("Transaction with status " + status + " isn't found!");
        return link;
    }

    @Step("Выбрать первую операцию со статусом операции \"{0}\" и статусом сообщения \"{1}\"")
    public String selectFirstTransactionByMessageStatus(String statusValue, String messageStatus) {
        String transactionCode = null;
        clickOnShowFilters();
        setStatusFld(statusValue);
        clearShowList();
        clickOnShowBtn();
        if (getSpecificMessageStatus(String.valueOf(messageStatus)).size() > 0) {
            transactionCode = getSpecificMessageStatus(messageStatus).get(0).$(By.xpath("td[1]/div")).getText();
            getSpecificMessageStatus(messageStatus).get(0).click();
            return transactionCode;
        }
        else log.error("Transaction with message status " + messageStatus + " isn't found!");
        return transactionCode;
    }

    @Step("Подвести курсор мыши к стобцу \"{0}\" и упорядочить по убыванию")
    public void descendingOrdering(String columnName) {
        $(By.xpath(columnNameXPath.replace("value", columnName))).hover().$(By.tagName("div")).click();
        $(descendingOrdering).click();
    }

    @Step("Подвести курсор мыши к стобцу \"{0}\" и упорядочить по возрастанию")
    public void ascendingOrdering(String columnName) {
        $(By.xpath(columnNameXPath.replace("value", columnName))).hover().$(By.tagName("div")).click();
        $(ascendingOrdering).click();
    }

    @Step("Для транзакции \"{0}\" установить основной код операции \"{1}\" через инлайн-редактирование.")
    public void setMainTransactionStatusByInLine(String transactionID, String status){
        log.info("For transaction " + transactionID + " setting main code = " + status);
        try{
            waitLoadingProcess();
            SelenideElement element = getOperationType(transactionID);
            element.doubleClick();
            By inputEl = By.xpath(mainStatusXpath.replace("_ID_", transactionID));
            $(inputEl).click();
            $(inputEl).clear();
            $(inputEl).setValue(status);
            $(inputEl).pressEnter();
            $(inputEl).pressEnter();
        }catch (Exception e){
            log.error(e.getStackTrace());
            throw e;
        }

    }

    @Step("Проверить, что для транзакции \"{0}\" основной код равен \"{1}\"")
    public void verifyMainTransactionStatus(String transactionID, String status) {
        waitLoadingProcess();
        SelenideElement element = getOperationType(transactionID);
        String actualStatus = element.getText();
        log.info("For transaction " + transactionID + " expected code = " + status + ", but actual = " + actualStatus);
        Assert.assertTrue(actualStatus.equals(status));
    }

    @Step("Подвести курсор мыши к столбцу \"{0}\" и в меню \"Столбцы\" у всех элементов снять все чекбоксы")
    public List<String> hideAllColumns(String columnName) {
        $(By.xpath(columnNameXPath.replace("value", columnName))).hover().$(By.tagName("div")).click();
        $(columnsSelect).hover();
        sleep(1000);
        ElementsCollection items = $$(columnsCheckedItemsSelect);
        List<String> listOfUncheckedColumns = new ArrayList<>();
        while(items.size() > 1){
            if(!items.get(1).getText().contains(SelectColumn.TransactionNumber.toString())) {
                listOfUncheckedColumns.add(items.get(1).getText());
                items.get(1).click();
            }
            if($(columnsDisabledItemSelect).exists())
                break;
        }
        $(showBtn).pressEscape().pressEscape();
        return listOfUncheckedColumns;
    }

    @Step("Проверить, что столбцы \"{0}\" не отображаются в таблице")
    public void verifyThatHidedColumnsAreNotVisible(List<String> columnsList){
        for (String column:columnsList) {
            assertThat("Колонка \"" + column + "\" отображается в таблице", $(By.xpath(columnNameXPath.replace("value", column))), is(not(displayed())));
        }
    }

    @Step("Подвести курсор мыши к столбцу \"{0}\" и в меню \"Столбцы\" выделить столбцы по-умолчанию")
    public List<String> DisplayAllColumns(String columnName) {
        $(By.xpath(columnNameXPath.replace("value", columnName))).hover().$(By.tagName("div")).click();
        $(columnsSelect).hover();
        sleep(1000);
        ElementsCollection items = $$(columnsUncheckedItemsSelect);
        List<String> listOfCheckedColumns = new ArrayList<>();
        while(items.size() > 0){
            if(!items.get(0).getText().contains(SelectColumn.TransactionNumber.toString())) {
                listOfCheckedColumns.add(items.get(0).getText());
                items.get(0).click();
            }
            if($$(columnsUncheckedItemsSelect).size() == 7)
                break;
        }
        $(columnsDisabledItemSelect).shouldNotBe(exist);
        $(showBtn).pressEscape().pressEscape();
        return listOfCheckedColumns;
    }

    @Step("Проверить, что столбцы \"{0}\" отображаются в таблице")
    public void verifyThatCheckedColumnsAreVisible(List<String> columnsList) {
        for (String column:columnsList) {
            assertThat("Колонка \"" + column + "\" не отображается в таблице", $(By.xpath(columnNameXPath.replace("value", column))), is(displayed()));
        }
    }

    public TransactionEditPage openTransactionByDoubleSpace(String operationNumber) {
        By transactionElement = By.xpath(operationXpath.replace("CODE", operationNumber));
        if(!$(transactionElement).isDisplayed()) {
            if(!$(numberFld).isDisplayed()){
                clickOnShowFilters();
            }
            $(numberFld).waitUntil(visible, timeout);
            setNumberFld(operationNumber);
            clickOnShowBtn();
        }
        if(!$(transactionElement).isDisplayed()){
            log.error("Transaction " + operationNumber + " isn't found");
            return null;
        } else {
            $(transactionElement).waitUntil(visible, timeout);
            actions().click($(transactionElement)).build().perform();
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            $(informationTab).waitUntil(visible, timeout);
        }
        return page(TransactionEditPage.class);
    }

    public TransactionEditPage openTransactionByContextMenu(String operationNumber, String contextValue) {
        By transactionElement = By.xpath(operationXpath.replace("CODE", operationNumber));
        if(!$(transactionElement).isDisplayed()) {
            if(!$(numberFld).isDisplayed()){
                clickOnShowFilters();
            }
            $(numberFld).waitUntil(visible, timeout);
            setNumberFld(operationNumber);
            clickOnShowBtn();
        }
        if(!$(transactionElement).isDisplayed()){
            log.error("Transaction " + operationNumber + " isn't found");
            return null;
        } else {
            $(transactionElement).waitUntil(visible, timeout);
            $(transactionElement).contextClick();
            $(By.xpath(contextMenuValue.replace("value", contextValue))).click();
            $(informationTab).waitUntil(visible, timeout);
        }
        return page(TransactionEditPage.class);
    }
}
