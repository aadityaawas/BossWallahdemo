package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.containers.ILoginContainer;
import utils.ActionEngine;

public class LoginPageObjects extends ActionEngine implements ILoginContainer {

    // private locators
    By mobileNumberButton = AppiumBy.accessibilityId("Via Mobile Number");
    By emailButton = AppiumBy.accessibilityId("Via Email ID");
    By loginLaterButton = AppiumBy.accessibilityId("I'll login later");
    By enterEmailField = isAndroid()
            ? AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
            : AppiumBy.iOSClassChain("some_id");
    By enterMobileNumberField = isAndroid()
            ? AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
            : AppiumBy.iOSClassChain("some_id");
    By getOTPField = AppiumBy.accessibilityId("Get OTP");
    By verifyOTPField = AppiumBy.accessibilityId("Verify");

    // public page objects
    @Override
    public void enterMobileNumber(String mobileNumber) {
        custom_sendKeys(enterMobileNumberField, mobileNumber);
    }

    @Override
    public void enterOTP(String otp) {
    }

    @Override
    public void enterEmail(String email) {
        custom_sendKeys(enterEmailField, email);
    }

    @Override
    public void selectLoginViaMobileNumber() {
        custom_click(mobileNumberButton);
    }

    @Override
    public void selectLoginViaEmailID() {
        custom_click(emailButton);
    }

    @Override
    public void selectLoginLater() {
        custom_click(loginLaterButton);
    }

    @Override
    public void selectGetOTP() {
        custom_click(getOTPField);
    }

    @Override
    public void selectVerifyPassword() {
        custom_click(verifyOTPField);
    }
}
