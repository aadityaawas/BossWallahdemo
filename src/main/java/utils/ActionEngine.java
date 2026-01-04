package utils;

import factories.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// As this the parent class and no other class can become of this class
public abstract class ActionEngine {
    private final AppiumDriver driver = DriverFactory.getDriver();
    protected final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    // for clicking the web elements
    public void custom_click(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    // for sending the text in the text field
    public void custom_sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    // for displaying the elements with custom timings
    public boolean custom_isDisplayed(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            driver.findElement(locator).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;}
    }

    // for detecting the platform as Android
    public boolean isAndroid() {
        return driver instanceof AndroidDriver;
    }

    // for detecting the platform as IOS
    public boolean isIOS() {
        return driver instanceof IOSDriver;
    }

}
