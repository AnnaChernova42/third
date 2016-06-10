package su.jet.webautotests.Pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Condition.*;

import static su.jet.webautotests.utils.TestProperties.prop;

/**
 * Created by ub.kim on 09.03.2016.
 */
public class JetCompliancePage extends BasePage {

    By jetAMLelement = By.xpath(".//div[@id='infoDomCont']//a[@title='Jet AML System']");

    public AmlPage openJetAML(){
        switchTo().window("Приложения Jet Compliance");
        switchTo().frame(1);
        $(jetAMLelement).waitUntil(appear, 3000).click();
        System.out.println("Open JetAML");
        switchTo().window("AML");
        waitLoadingProcess();
        return page(AmlPage.class);
    }
}
