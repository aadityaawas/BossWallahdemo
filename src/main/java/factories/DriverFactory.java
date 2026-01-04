package factories;

import io.appium.java_client.AppiumDriver;

import java.util.Objects;

public class DriverFactory {
    // Creating ThreadLocal objects for parallel executions
    private static final ThreadLocal<AppiumDriver> appiumThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> platformNameThreadLocal = new ThreadLocal<>();

    // private constructor for Singleton Pattern
    private DriverFactory() {
        throw new UnsupportedOperationException("DriverFactory is a utility class");
    }

    // Getter methods for platformName and appium driver initialization
    public static AppiumDriver getDriver() {
        AppiumDriver driver = appiumThreadLocal.get();
        if (Objects.isNull(driver)) {
            throw new  IllegalStateException("Driver is not initialized");
        }
        return driver;
    }

    public static String getPlatformName() {
        String platformName = platformNameThreadLocal.get();
        if (Objects.isNull(platformName)) {
            throw new  IllegalStateException("Platform name is not initialized");
        } return  platformName;
    }

    // Setter methods for platformName and appium driver fetching
    public static void setDriver(AppiumDriver driver) {
        Objects.requireNonNull(driver, "Driver cannot be null");
        appiumThreadLocal.set(driver);
    }

    public static void setPlatformName(String platformName) {
        Objects.requireNonNull(platformName, "Platform name cannot be null");
        platformNameThreadLocal.set(platformName);
    }

    // Clearing platform and Appium driver after execution to prevent memory leakage.
    public static void quitDriver() {
        AppiumDriver driver = appiumThreadLocal.get();
        if (driver != null) {
            driver.quit();
            appiumThreadLocal.remove();
        }
    }

    public static void clearPlatform() {
        platformNameThreadLocal.remove();
    }

    public static void clearAll() {
        quitDriver();
        clearPlatform();
    }
}
