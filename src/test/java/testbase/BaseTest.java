package testbase;

import AppiumManager.AppiumHybridDriverInitializer;
import factories.DriverFactory;
import factories.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.CommonUtils;
import utils.EPlatformType;

public abstract class BaseTest {
    protected PageFactory pageFactory;
    private CommonUtils utils;
    private AppiumDriver appiumFactoryDriver;

    @BeforeClass(alwaysRun = true)
    public void startUp(ITestContext context) {
        String platformName = (String) context.getAttribute("platformName");
        AppiumHybridDriverInitializer initializer = new AppiumHybridDriverInitializer(EPlatformType.getPlatformType(platformName));
        initializer.initializeHybridAppiumDriver();
        pageFactory = new PageFactory();
        utils = new CommonUtils();
        String packageName = utils.readPropertiesFile().getProperty("packageName");
        appiumFactoryDriver = DriverFactory.getDriver();
        ((InteractsWithApps) appiumFactoryDriver).activateApp(packageName);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        pageFactory.clearInstances();
        utils = null;
        pageFactory = null;
        appiumFactoryDriver = null;
        DriverFactory.clearAll();
    }

}
