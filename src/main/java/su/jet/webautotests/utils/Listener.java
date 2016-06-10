package su.jet.webautotests.utils;

import org.apache.log4j.FileAppender;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import com.google.common.io.Files;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static su.jet.webautotests.utils.TestLogger.log;

/**
 * Created by ub.kim on 01.04.2016.
 */
public class Listener extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult tr) {
        log.info("Test " + tr.getName() + " started....");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {

        log.info("Test '" + tr.getName() + "' PASSED");
        // This will print the class name in which the method is present
        log.info(tr.getTestClass());
        System.out.println(".....");

        try {
            saveLogAttachment();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult tr) {

        log.error("Test '" + tr.getName() + "' FAILED");
        System.out.println(".....");
        try {
            createAttachment();
            saveLogAttachment();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log.info("Test '" + tr.getName() + "' SKIPPED");
        System.out.println(".....");
    }

	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] createAttachment() throws IOException {
		return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
	}

    @Attachment(value = "Log file", type = "log")
    public byte[] saveLogAttachment() throws URISyntaxException, IOException {
        return getLog();
    }

    private byte[] getLog() throws IOException, URISyntaxException {
        FileAppender fa = (FileAppender)TestLogger.log.getAppender("log");
        File log = new File(fa.getFile());
        return Files.toByteArray(log);
    }
}
