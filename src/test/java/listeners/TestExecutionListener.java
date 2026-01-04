package listeners;

import factories.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.CommonUtils;
import java.io.File;
import java.io.IOException;

public class TestExecutionListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test success.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failure.");
        AppiumDriver driver = DriverFactory.getDriver();
        try {
            FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir")+"/screenshots/"+result.getMethod().getMethodName()+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        CommonUtils commonUtils = new CommonUtils();
        String platformName = System.getProperty("platformName") != null ? System.getProperty("platformName") : commonUtils.readPropertiesFile().getProperty("platformName");
        DriverFactory.setPlatformName(platformName);
        context.setAttribute("platformName", platformName);
        System.out.println("Test started.");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test finished.");
    }
}
