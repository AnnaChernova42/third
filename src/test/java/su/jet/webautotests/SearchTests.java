package su.jet.webautotests;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.Pages.TransactionEditPage;
import su.jet.webautotests.utils.Helpers;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;
import su.jet.webautotests.DataConstants.*;

import java.text.Collator;
import java.text.ParseException;
import java.util.*;

import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotEquals;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;

/**
 * Created by ub.kim on 23.03.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("ЭФ списка оповещений")
public class SearchTests extends BaseClass {

    //region Test data
    String codeId = "1001";
    String searchInValue = "Основной вид оп.";
    String responsibleValue = "Контролер";
    String[] statusValues = {"Отправлена"};
    String sumOperationFromValue = "680000.01";
    String sumOperationToValue = "700000.51";
    String dateOperationFromValue = "02.04.2014"; //Helpers.getDate(-5);
    String dateOperationToValue = "05.03.2014"; //Helpers.getDate(-3);
    List<String> tbValues = Arrays.asList("16", "18", "38");
    String osbValue = "ЦАРИЦЫНСКОЕ ОСБ 7978";
    String[] filter = {"2. Требующие обработки", "Просроченные", "Экспорт в Комиту"};
    String transactionId;
    AmlPage amlPage = new AmlPage();
    Collator russianCollator = Collator.getInstance(new Locale("ru", "RU"));
    Comparator comparator = Collections.reverseOrder();
    Comparator comparator2 = Collections.reverseOrder(russianCollator);
    List<String> currentDescending, expectedDescending, currentAscending, expectedAscending, uncheckedColumns, checkedColumns;
    List<Float> currentAscending2, expectedAscending2, currentDescending2, expectedDescending2;
    List<Date> currentAscending3, expectedAscending3, currentDescending3, expectedDescending3;

    //endregion

    @Test
    @TestCase(testRailCaseId = "38676")
    @Title("Отображение подвала")
    public void DisplayingOfDetails(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(DataConstants.TransactionStatus.BeControlled.toString(), DataConstants.MessageStatus.ControlIsPassed.toString());
        TransactionEditPage editPage = amlPage.clickOnDetailsBtn()
                .openTransactionByClickToEnterButton(transactionId);
        assertThat("Подвал отображается!", $(editPage.cellarBody), is(not(displayed())));
        editPage.exitTransaction();
        assertThat("Подвал не отображается!", $(amlPage.cellarBody), is(displayed()));
        amlPage.AdministrationBtn("MandatoryControl-CodesOPOK");
        assertThat("Подвал отображается!", $(amlPage.cellarBody), is(not(displayed())));
        amlPage.MonitoringBtn("ListOperations");
        assertThat("Подвал не отображается!", $(amlPage.cellarBody), is(displayed()));
    }

    @Test
    @TestCase(testRailCaseId = "26323")
    public void SearchTest() throws ParseException {
        amlPage.clickOnShowList(filter[1])
                .clickOnShowBtn();
        assertThat("В таблице не найдены операции до текущей даты", amlPage.getAllProcessTo().listIterator().next().before(Helpers.getCurrentDate()), is(true));

        amlPage.clearShowList()
                .clickOnShowBtn();
        assertThat("В таблице не найдены операции до текущей даты и после текущей даты", amlPage.getAllProcessTo().listIterator().next().before(Helpers.getCurrentDate()) && amlPage.getAllProcessTo().listIterator().next().after(Helpers.getCurrentDate()), is(true));

        amlPage.clickOnShowList(filter[2])
                .clickOnShowBtn();
        assertThat("В таблице не найдены операции со статусом 'Ожидает отправки'", amlPage.getAllOperationStatus(), hasItems("Ожидает отправки"));
    }


    @Test
    @TestCase(testRailCaseId = "26322")
    public void AdvancedSearchTest(){
        amlPage.clickOnShowFilters()
                .setCodeFld(codeId)
                .setSearchInFld(searchInValue)
                .setResponsibleFld(responsibleValue)
                .setStatusFld(statusValues)
                .setSumFromFld(sumOperationFromValue)
                .setSumFromTo(sumOperationToValue)
                .setDateFromFld(dateOperationFromValue)
                .setDateToFld(dateOperationToValue)
                .setTbFld(tbValues)
                .clickOnShowBtn();

        assertThat("В таблице содержатся 0 или более 1 записей", amlPage.getCountOfRecords(), hasSize(1));
        amlPage.clickOnAllowFilters()
                .clickOnShowBtn();
        assertThat("В таблице содержатся 0 записей", amlPage.getCountOfRecords(), hasSize(greaterThan(1)));
    }

    @Test
    @TestCase(testRailCaseId = "26329")
    public void PaginationTest() {
        amlPage.clickOnShowList(filter[0])
                .clickOnShowBtn();
        HashSet<String> numberOfTransactions = amlPage.getNumberOfLoadedTransactions();
        assertThat("Количество загруженных операций в \"Загружено операций\" отличается от ожидаемого", amlPage.getTextLoadedTrsnsactions(), containsString("Загружено операций: " + numberOfTransactions.size()));

        amlPage.clickOnLoadNextPageBtn();
        HashSet<String> numberOfTransactionsAfterNextLoadedPage = amlPage.getNumberOfLoadedTransactions();
        numberOfTransactions.addAll(numberOfTransactionsAfterNextLoadedPage);
        assertNotEquals(numberOfTransactions.size(), numberOfTransactions.addAll(numberOfTransactionsAfterNextLoadedPage), "Количество загруженных операций совпадает");
        assertThat("Количество загруженных операций в \"Загружено операций\" отличается от ожидаемого", amlPage.getTextLoadedTrsnsactions(), containsString("Загружено операций: " + numberOfTransactions.size()));

        amlPage.clickOnLoadRemainingPagesBtn();
        HashSet<String> numberOfTransactionsAfterRemainingLoadedPages = amlPage.getNumberOfLoadedTransactions();
        numberOfTransactions.addAll(numberOfTransactionsAfterRemainingLoadedPages);
        assertNotEquals(numberOfTransactionsAfterNextLoadedPage.size(), numberOfTransactions.addAll(numberOfTransactionsAfterRemainingLoadedPages), "Количество загруженных операций совпадает");
        assertThat("Количество загруженных операций в \"Загружено операций\" отличается от ожидаемого", amlPage.getTextLoadedTrsnsactions(), containsString("Загружено операций: " + numberOfTransactions.size()));
    }

    @Test
    //@TestCase(testRailCaseId = "26328")
    public void Sorting(){
        /**
         * Колонка "Номер операции"
         */
        amlPage.ascendingOrdering(SelectColumn.TransactionNumber.toString());
        currentAscending = new ArrayList<>(amlPage.getAllOperations());
        expectedAscending = new ArrayList<>(amlPage.getAllOperations());
        Collections.sort(expectedAscending);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.TransactionNumber.toString() + "\" не соответствует ожидаемому результату", currentAscending, equalTo(expectedAscending));
        currentAscending.clear();
        expectedAscending.clear();

        amlPage.descendingOrdering(SelectColumn.TransactionNumber.toString());
        currentDescending = new ArrayList<>(amlPage.getAllOperations());
        expectedDescending = new ArrayList<>(amlPage.getAllOperations());
        Collections.sort(expectedDescending, comparator);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.TransactionNumber.toString() + "\"  не соответствует ожидаемому результату", currentDescending, equalTo(expectedDescending));
        currentDescending.clear();
        expectedDescending.clear();


        /**
         * Колонка "Статус операции"
         */
        amlPage.ascendingOrdering(SelectColumn.TransactionStatus.toString());
        currentAscending = new ArrayList<>(amlPage.getAllOperationStatus());
        expectedAscending = new ArrayList<>(amlPage.getAllOperationStatus());
        Collections.sort(expectedAscending);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.TransactionStatus.toString() + "\" не соответствует ожидаемому результату", currentAscending, equalTo(expectedAscending));
        currentAscending.clear();
        expectedAscending.clear();

        amlPage.descendingOrdering(SelectColumn.TransactionStatus.toString());
        currentDescending = new ArrayList<>(amlPage.getAllOperationStatus());
        expectedDescending = new ArrayList<>(amlPage.getAllOperationStatus());
        Collections.sort(expectedDescending, comparator2);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.TransactionStatus.toString() + "\"  не соответствует ожидаемому результату", currentDescending, equalTo(expectedDescending));
        currentDescending.clear();
        expectedDescending.clear();

        /**
         * Колонка "Вид операции"
         */
        amlPage.ascendingOrdering(SelectColumn.TransactionType.toString());
        currentAscending = new ArrayList<>(amlPage.getAllOperationType());
        expectedAscending = new ArrayList<>(amlPage.getAllOperationType());
        Collections.sort(expectedAscending);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.TransactionType.toString() + "\" не соответствует ожидаемому результату", currentAscending, equalTo(expectedAscending));
        currentAscending.clear();
        expectedAscending.clear();

        amlPage.descendingOrdering(SelectColumn.TransactionType.toString());
        currentDescending = new ArrayList<>(amlPage.getAllOperationType());
        expectedDescending = new ArrayList<>(amlPage.getAllOperationType());
        Collections.sort(expectedDescending, comparator);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.TransactionType.toString() + "\"  не соответствует ожидаемому результату", currentDescending, equalTo(expectedDescending));
        currentDescending.clear();
        expectedDescending.clear();

        /**
         * Колонка "Дополнительный вид операции"
         */
        amlPage.ascendingOrdering(SelectColumn.AdditionalTransactionType.toString());
        currentAscending = new ArrayList<>(amlPage.getAllAdditionalOperationType());
        expectedAscending = new ArrayList<>(amlPage.getAllAdditionalOperationType());
        Collections.sort(expectedAscending);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.AdditionalTransactionType.toString() + "\" не соответствует ожидаемому результату", currentAscending, equalTo(expectedAscending));
        currentAscending.clear();
        expectedAscending.clear();

        amlPage.descendingOrdering(SelectColumn.AdditionalTransactionType.toString());
        currentDescending = new ArrayList<>(amlPage.getAllAdditionalOperationType());
        expectedDescending = new ArrayList<>(amlPage.getAllAdditionalOperationType());
        Collections.sort(expectedDescending, comparator);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.AdditionalTransactionType.toString() + "\"  не соответствует ожидаемому результату", currentDescending, equalTo(expectedDescending));
        currentDescending.clear();
        expectedDescending.clear();


//        /**
//         * Статус сообщения
//         */
//        amlPage.ascendingOrdering(SelectColumn.StatusMessage.toString());
//        currentAscending = new ArrayList<>(amlPage.getAllStatusMessage());
//        expectedAscending = new ArrayList<>(amlPage.getAllStatusMessage());
//        Collections.sort(expectedAscending);
//        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.StatusMessage.toString() + "\" не соответствует ожидаемому результату", currentAscending, equalTo(expectedAscending));
//        currentAscending.clear();
//        expectedAscending.clear();
//
//        amlPage.descendingOrdering(SelectColumn.StatusMessage.toString());
//        currentDescending = new ArrayList<>(amlPage.getAllStatusMessage());
//        expectedDescending = new ArrayList<>(amlPage.getAllStatusMessage());
//
//        Collections.sort(expectedDescending, comparator2);
//        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.StatusMessage.toString() + "\" не соответствует ожидаемому результату", currentDescending, equalTo(expectedDescending));
//        currentDescending.clear();
//        expectedDescending.clear();

        /**
         * Сумма в рублях
         */
        amlPage.ascendingOrdering(SelectColumn.SumInRub.toString());
        currentAscending2 = new ArrayList<>(amlPage.getAllSumInRub());
        expectedAscending2 = new ArrayList<>(amlPage.getAllSumInRub());
        Collections.sort(expectedAscending2);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.SumInRub.toString() + "\" не соответствует ожидаемому результату", currentAscending2, equalTo(expectedAscending2));
        currentAscending2.clear();
        expectedAscending2.clear();

        amlPage.descendingOrdering(SelectColumn.SumInRub.toString());
        currentDescending2 = new ArrayList<>(amlPage.getAllSumInRub());
        expectedDescending2 = new ArrayList<>(amlPage.getAllSumInRub());
        Collections.sort(expectedDescending2, comparator);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.SumInRub.toString() + "\"  не соответствует ожидаемому результату", currentDescending2, equalTo(expectedDescending2));
        currentDescending2.clear();
        expectedDescending2.clear();

        /**
         * Сумма в валюте
         */
        amlPage.ascendingOrdering(SelectColumn.SumInCurrency.toString());
        currentAscending2 = new ArrayList<>(amlPage.getAllSumInCurrency());
        expectedAscending2 = new ArrayList<>(amlPage.getAllSumInCurrency());
        Collections.sort(expectedAscending2);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.SumInCurrency.toString() + "\" не соответствует ожидаемому результату", currentAscending2, equalTo(expectedAscending2));
        currentAscending2.clear();
        expectedAscending2.clear();

        amlPage.descendingOrdering(SelectColumn.SumInCurrency.toString());
        currentDescending2 = new ArrayList<>(amlPage.getAllSumInCurrency());
        expectedDescending2 = new ArrayList<>(amlPage.getAllSumInCurrency());
        Collections.sort(expectedDescending2, comparator);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.SumInCurrency.toString() + "\"  не соответствует ожидаемому результату", currentDescending2, equalTo(expectedDescending2));
        currentDescending2.clear();
        expectedDescending2.clear();

        /**
         * Валюта
         */
        amlPage.ascendingOrdering(SelectColumn.Currency.toString());
        currentAscending = new ArrayList<>(amlPage.getAllCurrency());
        expectedAscending = new ArrayList<>(amlPage.getAllCurrency());
        Collections.sort(expectedAscending);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.Currency.toString() + "\" не соответствует ожидаемому результату", currentAscending, equalTo(expectedAscending));
        currentAscending.clear();
        expectedAscending.clear();

        amlPage.descendingOrdering(SelectColumn.Currency.toString());
        currentDescending = new ArrayList<>(amlPage.getAllCurrency());
        expectedDescending = new ArrayList<>(amlPage.getAllCurrency());
        Collections.sort(expectedDescending, comparator);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.Currency.toString() + "\"  не соответствует ожидаемому результату", currentDescending, equalTo(expectedDescending));
        currentDescending.clear();
        expectedDescending.clear();

        /**
         * Дата операции
         */
        amlPage.ascendingOrdering(SelectColumn.TransactionDate.toString());
        try {
            currentAscending3 = new ArrayList<>(amlPage.getAllOperationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            expectedAscending3 = new ArrayList<>(amlPage.getAllOperationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Collections.sort(expectedAscending3);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.TransactionDate.toString() + "\" не соответствует ожидаемому результату", currentAscending3, equalTo(expectedAscending3));
        currentAscending3.clear();
        expectedAscending3.clear();

        amlPage.descendingOrdering(SelectColumn.TransactionDate.toString());
        try {
            currentDescending3 = new ArrayList<>(amlPage.getAllOperationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            expectedDescending3 = new ArrayList<>(amlPage.getAllOperationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Collections.sort(expectedDescending3, comparator);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.TransactionDate.toString() + "\"  не соответствует ожидаемому результату", currentDescending3, equalTo(expectedDescending3));
        currentDescending3.clear();
        expectedDescending3.clear();


        /**
         * Обработать к...
         */
        amlPage.ascendingOrdering(SelectColumn.ProcessTo.toString());
        try {
            currentAscending3 = new ArrayList<>(amlPage.getAllProcessTo());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            expectedAscending3 = new ArrayList<>(amlPage.getAllProcessTo());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Collections.sort(expectedAscending3);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.ProcessTo.toString() + "\" не соответствует ожидаемому результату", currentAscending3, equalTo(expectedAscending3));
        currentAscending3.clear();
        expectedAscending3.clear();

        amlPage.descendingOrdering(SelectColumn.ProcessTo.toString());
        try {
            currentDescending3 = new ArrayList<>(amlPage.getAllProcessTo());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            expectedDescending3 = new ArrayList<>(amlPage.getAllProcessTo());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Collections.sort(expectedDescending3, comparator);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.ProcessTo.toString() + "\"  не соответствует ожидаемому результату", currentDescending3, equalTo(expectedDescending3));
        currentDescending3.clear();
        expectedDescending3.clear();


        /**
         * Ответственный
         */
        amlPage.ascendingOrdering(SelectColumn.Responsible.toString());
        currentAscending = new ArrayList<>(amlPage.getAllResponsible());
        expectedAscending = new ArrayList<>(amlPage.getAllResponsible());
        Collections.sort(expectedAscending);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.Responsible.toString() + "\" не соответствует ожидаемому результату", currentAscending, equalTo(expectedAscending));
        currentAscending.clear();
        expectedAscending.clear();

        amlPage.descendingOrdering(SelectColumn.Responsible.toString());
        currentDescending = new ArrayList<>(amlPage.getAllResponsible());
        expectedDescending = new ArrayList<>(amlPage.getAllResponsible());
        Collections.sort(expectedDescending, comparator);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.Responsible.toString() + "\"  не соответствует ожидаемому результату", currentDescending, equalTo(expectedDescending));
        currentDescending.clear();
        expectedDescending.clear();


        /**
         * Комментарий
         */
        amlPage.ascendingOrdering(SelectColumn.Comment.toString());
        currentAscending = new ArrayList<>(amlPage.getAllComment());
        expectedAscending = new ArrayList<>(amlPage.getAllComment());
        Collections.sort(expectedAscending);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.Comment.toString() + "\" не соответствует ожидаемому результату", currentAscending, equalTo(expectedAscending));
        currentAscending.clear();
        expectedAscending.clear();

        amlPage.descendingOrdering(SelectColumn.Comment.toString());
        currentDescending = new ArrayList<>(amlPage.getAllComment());
        expectedDescending = new ArrayList<>(amlPage.getAllComment());
        Collections.sort(expectedDescending, comparator);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.Comment.toString() + "\"  не соответствует ожидаемому результату", currentDescending, equalTo(expectedDescending));
        currentDescending.clear();
        expectedDescending.clear();

        /**
         * Нал/Бнал
         */
        amlPage.ascendingOrdering(SelectColumn.CashOrClearing.toString());
        currentAscending = new ArrayList<>(amlPage.getAllCashOrClearing());
        expectedAscending = new ArrayList<>(amlPage.getAllCashOrClearing());
        Collections.sort(expectedAscending);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.CashOrClearing.toString() + "\" не соответствует ожидаемому результату", currentAscending, equalTo(expectedAscending));
        currentAscending.clear();
        expectedAscending.clear();

        amlPage.descendingOrdering(SelectColumn.CashOrClearing.toString());
        currentDescending = new ArrayList<>(amlPage.getAllCashOrClearing());
        expectedDescending = new ArrayList<>(amlPage.getAllCashOrClearing());
        Collections.sort(expectedDescending, comparator2);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.CashOrClearing.toString() + "\"  не соответствует ожидаемому результату", currentDescending, equalTo(expectedDescending));
        currentDescending.clear();
        expectedDescending.clear();

        /**
         * Приложение
         */
        amlPage.ascendingOrdering(SelectColumn.Attachment.toString());
        currentAscending = new ArrayList<>(amlPage.getAllAttachment());
        expectedAscending = new ArrayList<>(amlPage.getAllAttachment());
        Collections.sort(expectedAscending);
        assertThat("Сортировка по возрастанию для колонки \"" + SelectColumn.Attachment.toString() + "\" не соответствует ожидаемому результату", currentAscending, equalTo(expectedAscending));
        currentAscending.clear();
        expectedAscending.clear();

        amlPage.descendingOrdering(SelectColumn.Attachment.toString());
        currentDescending = new ArrayList<>(amlPage.getAllAttachment());
        expectedDescending = new ArrayList<>(amlPage.getAllAttachment());
        Collections.sort(expectedDescending, comparator);
        assertThat("Сортировка по убыванию для колонки \"" + SelectColumn.Attachment.toString() + "\"  не соответствует ожидаемому результату", currentDescending, equalTo(expectedDescending));
        currentDescending.clear();
        expectedDescending.clear();
    }

    @Test
    @TestCase(testRailCaseId = "34447")
    @Title("Изменение состава столбцов")
    public void ChangingCompositionOfColumns(){
        uncheckedColumns = amlPage.hideAllColumns(SelectColumn.TransactionNumber.toString());
        assertThat("Колонка \"" + SelectColumn.TransactionNumber.toString() + "\" не отображается в таблице", $(By.xpath(amlPage.columnNameXPath.replace("value", SelectColumn.TransactionNumber.toString()))), is(displayed()));
        amlPage.verifyThatHidedColumnsAreNotVisible(uncheckedColumns);
        checkedColumns = amlPage.DisplayAllColumns(SelectColumn.TransactionNumber.toString());
        assertThat("Колонка \"" + SelectColumn.TransactionNumber.toString() + "\" не отображается в таблице", $(By.xpath(amlPage.columnNameXPath.replace("value", SelectColumn.TransactionNumber.toString()))), is(displayed()));
        amlPage.verifyThatCheckedColumnsAreVisible(checkedColumns);
    }

    @Test
    //@TestCase(testRailCaseId = "34488")
    @Title("Открытие ЭФ \"Детали операции\" по Enter (для 6 статусов)")
    public void OpeningTransactionEditPageByEnterButton(){
        String[] status = new String[]{"Подлежит контролю"};
        String transactionID = amlPage.selectFirstTransactionByStatus(status);
        TransactionEditPage editPage = amlPage.openTransactionByClickToEnterButton(transactionID);
    }

    @Test
    //@TestCase(testRailCaseId = "34488")
    @Title("Открытие ЭФ \"Детали операции\" по двойному пробелу (для 6 статусов)")
    public void OpeningTransactionEditPageByDoubleSpace(){
        String[] status = new String[]{"Подлежит контролю"};
        String transactionID = amlPage.selectFirstTransactionByStatus(status);
        TransactionEditPage editPage = amlPage.openTransactionByDoubleSpace(transactionID);
    }

    @Test
    //@TestCase(testRailCaseId = "34488")
    @Title("Открытие ЭФ \"Детали операции\" по контекстному меню (для 6 статусов)")
    public void OpeningTransactionEditPageByContextMenu(){
        String[] status = new String[]{"Подлежит контролю"};
        String transactionID = amlPage.selectFirstTransactionByStatus(status);
        TransactionEditPage editPage = amlPage.openTransactionByContextMenu(transactionID, SelectContextMenu.MessageDetails.toString());
    }
}