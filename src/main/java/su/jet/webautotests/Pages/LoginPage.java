package su.jet.webautotests.Pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static su.jet.webautotests.utils.TestLogger.log;

/**
 * Created by ub.kim on 09.03.2016.
 */
public class LoginPage {

    By userNameInput = By.xpath(".//*[@id='passwordcontents']//input[@type='text']");
    By passwordInput = By.xpath(".//*[@id='passwordcontents']//input[@type='password']");
    By enterBtn = By.xpath(".//*[@id='passwordcontents']//a[@class='btn']");


    public JetCompliancePage login(String login, String password){
        switchTo().window("Login | Jet Compliance");
        log.info("Login in system with login \"" + login + "\" and password \"" + password + "\"");
        $(userNameInput).setValue(login);
        $(passwordInput).setValue(password);
        $(enterBtn).click();
        sleep(1500);
        return page(JetCompliancePage.class);
    }
}
