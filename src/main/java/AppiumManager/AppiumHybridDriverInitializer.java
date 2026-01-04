package AppiumManager;

import factories.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TimeoutException;
import utils.EPlatformType;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class AppiumHybridDriverInitializer {
    // This is used for checking the current context of the application like Native or Web view during parallel execution in flutter and native apps.
    // private static final ThreadLocal<String> activeContext = new ThreadLocal<>();
    private static final String APPIUM_SERVER_URL = "http://localhost:4723";
    private final EPlatformType platformName;

    static URL appiumServer;
    // dynamically connecting the execution the running appium server
    static {
        try {
            appiumServer = URI.create(APPIUM_SERVER_URL).toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public AppiumHybridDriverInitializer(EPlatformType platformName) {
        this.platformName = platformName;
        DriverFactory.setPlatformName(platformName.name());
    }

    // Initializing the Appium Driver for Android and iOS Driver class
    public void initializeHybridAppiumDriver(){
        try {
            AppiumDriver localAppiumDriver = getAppiumPlatformDriver();
            localAppiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            DriverFactory.setDriver(localAppiumDriver);
        } catch (SessionNotCreatedException | AppiumServerHasNotBeenStartedLocallyException | TimeoutException exception) {
            throw new RuntimeException("Failed to start Appium session", exception);
        }
    }

    // To be implemented for flutter if required
    public void switchContextToFlutter() {
    }

    // To be implemented for switching back to native
    public void switchContextToNative() {}

    // Helper method getting desired appium driver
    private @NonNull AppiumDriver getAppiumPlatformDriver() {
        AppiumDriver localAppiumDriver;

        switch (platformName) {
            case ANDROID -> {
                UiAutomator2Options options = getUiAutomator2Options();
                localAppiumDriver = new AndroidDriver(appiumServer, options);
            }
            case IOS -> {
                XCUITestOptions options = getXcuiTestOptions();
                localAppiumDriver = new IOSDriver(appiumServer, options);
            }
            default -> throw new RuntimeException("Illegal platform is provided");
        }
        return localAppiumDriver;
    }

    // Android related capabilities helper
    private static @NonNull UiAutomator2Options getUiAutomator2Options() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Narzo");
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        return options;
    }

    // iOS related capabilities helper
    private static @NonNull XCUITestOptions getXcuiTestOptions() {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setAutomationName("XCUITest");
        options.setDeviceName("iPhone 14");
        options.setPlatformVersion("18.5");
        options.setUdid("0000008110-0000E211C3CEA201E");
        options.setCapability("appium:xcodeOrgId", "45YTHJ9QT2");
        options.setCapability("appium:xcodeSigningId", "iPhone Developer");
        return options;
    }
}
