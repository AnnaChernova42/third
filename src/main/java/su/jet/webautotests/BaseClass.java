package su.jet.webautotests;

import com.codeborne.selenide.Configuration;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import ru.yandex.qatools.allure.annotations.Step;
import su.jet.webautotests.Pages.JetCompliancePage;
import su.jet.webautotests.Pages.LoginPage;
import su.jet.webautotests.utils.TestLogger;
import su.jet.webautotests.utils.TestRailAPI.client.APIException;
import su.jet.webautotests.utils.TestRailAPI.client.adapter.TestRailIntegration;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static su.jet.webautotests.utils.TestProperties.prop;
import static su.jet.webautotests.utils.TestRailAPI.client.adapter.TestRailIntegration.initTestRail;
import static su.jet.webautotests.utils.TestLogger.log;

public class BaseClass {

	private String testStand = prop.getProperty("testStand");
	final int timeout = Integer.parseInt(prop.getProperty("shortTimeout"));
	private String loginName = prop.getProperty("login");
	private String password = prop.getProperty("password");

	@BeforeMethod(alwaysRun = true)
	@Step("Запустить браузер Internet Explorer и открыть страницу")
	public void before() {
		TestLogger.initLogger();
		try {
			initTestRail();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
		log.info("*******************");
		log.info("launching IE browser");
 		System.setProperty("webdriver.ie.driver", ".\\webdriver\\IEDriverServer.exe");
		System.setProperty("selenide.browser", "ie");
		clearBrowserCache();
		Configuration.pageLoadStrategy="eager";
		log.info("Go to the url " + testStand);
		/*
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		try {
			WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
			driver.get("http://www.google.com");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}*/
		open(testStand);
		loginToAml();
	}

	@Step("Войти в Jet AML")
	public void loginToAml(){
		LoginPage loginPage = new LoginPage();
		JetCompliancePage jetCompliancePage = loginPage.login(loginName, password);
		jetCompliancePage.openJetAML();
	}

	@AfterMethod(alwaysRun = true)
	public void getStatusAndAnnotation(ITestResult result) throws NoSuchMethodException, IOException, APIException {
		java.util.Map map = new HashMap<>();
		//Ищем необходимый метод
		Method m = getClass().getMethod(result.getName());
		//Определяем какую именно аннотацию из метода используем
		TestCase annotation = m.getAnnotation(TestCase.class);
		try {
			//получаем аннотациию testRailCaseId и кладем ее в коллекцию с соответствующим ключем
			map.put("testRailCaseId",annotation.testRailCaseId());
		}catch (NullPointerException e){}
		if (result.isSuccess()) {
			//Если результат прохождения теста положительный, то помещаем статус код в коллекцию с соответствующим ключем
			map.put("result", 1);
		} else {
			//Если результат прохождения теста отрицательный, то помещаем статус код в коллекцию с соответствующим ключем
			map.put("result", 5);
		}
		//Проверяем если аннотация testRailDeployEnable равна true и коллекция содержит в себе значения по ключу testRailCaseId отличные от пустых или нулевых, то деплоим результаты
		try {
		if(annotation.testRailDeployEnable() && !map.get("testRailCaseId").equals(null) && !map.get("testRailCaseId").toString().isEmpty())
		{
			TestRailIntegration.addTestResult(map);
		}
		}catch (NullPointerException e){
		System.out.println("Deploying result was canceled, because test has annotation \"testRailDeployEnable: false\" or \"testRailCaseId\" has no value");}
	}

	@AfterMethod(alwaysRun = true)
	@Step("Закрыть браузер")
	public void after(){
		close();
	}
}
