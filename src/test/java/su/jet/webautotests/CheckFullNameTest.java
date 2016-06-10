package su.jet.webautotests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.Pages.EditPageTabs.OperationInfoTab;
import su.jet.webautotests.Pages.EditPageTabs.PayerTab;
import su.jet.webautotests.Pages.TransactionEditPage;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.yandex.qatools.matchers.webdriver.AttributeMatcher.value;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;
import static su.jet.webautotests.utils.TestProperties.prop;

/**
 * Created by ub.kim on 22.03.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("Вход")
public class CheckFullNameTest extends BaseClass {

    private String operNumber = prop.getProperty("testTransaction");
    private String DATA = "20.03.2014";
    private String VO = "1001";
    private String DOP_V = "0";
    private String NAMEU0 = "ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ \"ДИЗАЙН И\"";
    private String ND0 = "7714852807";
    private String RG0 = "1117746794087";
    private AmlPage amlPage = new AmlPage();

    @Test
    @TestCase(testRailCaseId = "27889")
    @Title("Проверка ФИО")
    public void CheckFullNameTest(){
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(operNumber);
        editPage.openInformationTab();
        assertThat("Фактическое значение поля \"Дата операции\" не соответствует ожидаемому значению - \"" + DATA + "\"", $(OperationInfoTab.DATA), value(DATA));
        assertThat("Фактическое значение поля \"Код вида операции\" не соответствует ожидаемому значению - \"" + VO + "\"", $(OperationInfoTab.VO), value(VO));
        assertThat("Фактическое значение поля \"Доп. коды вида операции\" не соответствует ожидаемому значению - \"" + DOP_V + "\"", $(OperationInfoTab.DOP_V), value(DOP_V));
        editPage.openPayerTab();
        assertThat("Фактическое значение поля \"Наименование ЮЛ или ФИО ФЛ (NAMEU0)\" не соответствует ожидаемому значению - \"" + NAMEU0 + "\"", $(PayerTab.NAMEU0), value(NAMEU0));
        assertThat("Фактическое значение поля \"ИНН (ND0)\" не соответствует ожидаемому значению - \"" + ND0 + "\"", $(PayerTab.ND0), value(ND0));
        assertThat("Фактическое значение поля \"Рег. номер (RG0)\" не соответствует ожидаемому значению - \"" + RG0 + "\"", $(PayerTab.RG0), value(RG0));
    }

    @Test
    @TestCase(testRailCaseId = "26636")
    @Title("Вход в АМЛ")
    public void EntranceToAml(){
        assertThat("Заглавная страница \"AML\" не загрузилась или загрузилась не корректно", $(amlPage.listOperationsTitlePage), is(displayed()));
    }
}
