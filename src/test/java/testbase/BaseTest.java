package testbase;

import AppiumManager.AppiumHybridDriverInitializer;
import factories.DriverFactory;
import factories.PageFactory;
import io.appium.java_client.InteractsWithApps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.EPlatformType;

public abstract class BaseTest {
    protected PageFactory pageFactory;

    @BeforeClass(alwaysRun = true)
    public void startUp() {
        String platformName = System.getProperty("platformName") != null ? System.getProperty("platformName") : "android";
        DriverFactory.setPlatformName(platformName);
        AppiumHybridDriverInitializer initializer = new AppiumHybridDriverInitializer(EPlatformType.getPlatformType(platformName));
        initializer.initializeHybridAppiumDriver();
        pageFactory = new PageFactory();
        ((InteractsWithApps) DriverFactory.getDriver()).activateApp("com.wealthdoctor");

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        pageFactory.clearInstances();
        pageFactory = null;
        DriverFactory.clearAll();
    }

}
