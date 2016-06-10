package su.jet.webautotests;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import su.jet.webautotests.Pages.AmlPage;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
@Listeners(su.jet.webautotests.utils.Listener.class)
public class ExampleTest extends BaseClass {
	
	private String operationNumber = "2061";
	AmlPage amlPage = new AmlPage();

	@Test
	//@TestCase(testRailCaseId = "26636")
	public void TestAMLExample() {
		//amlPage.clickOnDetailsBtn();
//		$(amlPage.getOperation(operationNumber)).shouldHave(text(String.valueOf(operationNumber)));
//		$(amlPage.getOperationStatusByTransactionNumber(operationNumber)).shouldHave(text("!Отправлена (повторное рассмотрение)"));
//		$(amlPage.getOperationType(operationNumber)).shouldHave(text("1001"));
//		$(amlPage.getAdditionalOperationType(operationNumber)).shouldHave(text(""));
//		$(amlPage.getStatusMessage(operationNumber)).shouldHave(text("Отправлено"));
//		$(amlPage.getSumInRub(operationNumber)).shouldHave(text("2001200,00"));
//		$(amlPage.getSumInCurrency(operationNumber)).shouldHave(text("2001200,00"));
//		$(amlPage.getCurrency(operationNumber)).shouldHave(text("RUR"));
//		$(amlPage.getOperationDate(operationNumber)).shouldHave(text("20.03.2014"));
//		$(amlPage.getProcessTo(operationNumber)).shouldHave(text("27.11.2015"));
//		$(amlPage.getResponsible(operationNumber)).shouldHave(text("PETROV"));
//		$(amlPage.getComment(operationNumber)).shouldHave(text("test pk (22.03.2016 16:01:27 OFMSUPERVISOR)"));
//		$(amlPage.getCashOrClearing(operationNumber)).shouldHave(text("нал"));
//		$(amlPage.getAttachment(operationNumber)).shouldHave(text(""));
//		$$(By.xpath(".//div[@class='x-grid-item-container']//tbody/tr/td[5]/div")).shouldHave(texts("Отправлено"));
	}
}
