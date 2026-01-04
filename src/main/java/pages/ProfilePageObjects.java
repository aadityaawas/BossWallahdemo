package pages;

import factories.DriverFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import pages.containers.IProfileContainer;
import utils.ActionEngine;

public class ProfilePageObjects extends ActionEngine implements IProfileContainer {

    // private locators
    private final By profilePictureButton = isAndroid()
            ? AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)")
            : AppiumBy.iOSClassChain("some_id");
    private final By historyButton = AppiumBy.accessibilityId("History");
    private final By watchListButton = AppiumBy.accessibilityId("Watchlist");
    private final By joinAsExpertButton = AppiumBy.accessibilityId("Join as an Expert");
    private final By appSettingsButton = AppiumBy.accessibilityId("App Settings");
    private final By primeAccessButton = AppiumBy.accessibilityId("Unlock Prime Access");
    private final By contactSupportButton = AppiumBy.accessibilityId("Contact Support");
    private final By faqButton = AppiumBy.accessibilityId("FAQ");
    private final By aboutBossWallahButton = AppiumBy.accessibilityId("About Boss Wallah");
    private final By termsAndConditionsButton = AppiumBy.accessibilityId("Terms & Conditions");

    // Edit profile locators
    private final By editProfilePictureButton = isAndroid()
            ? AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)")
            : AppiumBy.iOSClassChain("some_id");
    private final By takePhotoButton = AppiumBy.accessibilityId("Take photo");
    private final By chooseFromGalleryButton = AppiumBy.accessibilityId("Choose from gallery");
    private final By uploadButton = AppiumBy.accessibilityId("Upload");
    private final By confirmPhotoButton = AppiumBy.accessibilityId("Crop");
    private final By declinePhotoButton = AppiumBy.accessibilityId("Navigate up");
    private final By confirmationMessageText = AppiumBy.accessibilityId("Profile Image Updated Successfully");

    // App Setting locators
    private final By feedbackButton = AppiumBy.accessibilityId("Feedback");
    private final By dataPrivacyButton = AppiumBy.accessibilityId("Data privacy");
    private final By logoutButton = AppiumBy.accessibilityId("Logout");
    private final By cancelButton = AppiumBy.accessibilityId("Cancel");
    private final By confirmLogoutButton = isAndroid()
            ? AppiumBy.androidUIAutomator("new UiSelector().description(\"Logout\").instance(1)")
            : AppiumBy.iOSClassChain("some_id");

    // public page objects
    @Override
    public void selectProfilePictureButton() {
        custom_click(profilePictureButton);
    }

    @Override
    public void selectHistoryButton() {
        custom_click(historyButton);
    }

    @Override
    public void selectWatchListButton() {
        custom_click(watchListButton);
    }

    @Override
    public void selectJoinAsExpertButton() {
        custom_click(joinAsExpertButton);
    }

    @Override
    public void selectAppSettings() {
        custom_click(appSettingsButton);
    }

    @Override
    public void selectUnlockPrimeAccessButton() {
        custom_click(primeAccessButton);
    }

    @Override
    public void selectContactSupportButton() {
        custom_click(contactSupportButton);
    }

    @Override
    public void selectFAQButton() {
        custom_click(faqButton);
    }

    @Override
    public void selectAboutBossWallahButton() {
        custom_click(aboutBossWallahButton);
    }

    @Override
    public void selectTermsAndConditionsButton() {
        custom_click(termsAndConditionsButton);
    }

    @Override
    public void selectEditProfileButton() {
        custom_click(profilePictureButton);
    }

    @Override
    public void selectEditProfilePictureButton() {
        custom_click(editProfilePictureButton);
    }

    @Override
    public void selectTakePhotoButton() {
        custom_click(takePhotoButton);
    }

    @Override
    public void selectFromGalleryButton() {
        custom_click(chooseFromGalleryButton);
    }

    @Override
    public void selectUploadButton() {
        custom_click(uploadButton);
    }

    @Override
    public void selectCameraShutterButton() {
        if (DriverFactory.getDriver() instanceof AndroidDriver androidDriver) {
            cameraShutterAction(androidDriver);
        } else if  (DriverFactory.getDriver() instanceof IOSDriver iosDriver) {
            // To be implemented
        }

    }

    private void cameraShutterAction(AndroidDriver androidDriver) {
        By cameraShutterButton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(7)");
//        This code works fine when native camera is invoked in the app, but it looks like the app team has created their own camera.
//        Ignoring this code and commenting this logic.
//        wait.until(ExpectedConditions.visibilityOfElementLocated(cameraShutterButton));
//        androidDriver.pressKey(new KeyEvent(AndroidKey.CAMERA));
        custom_click(cameraShutterButton);
    }

    @Override
    public void selectConfirmPhotoButton() {
        custom_click(confirmPhotoButton);
    }

    @Override
    public void selectDeclinePhotoButton() {
        custom_click(declinePhotoButton);
    }

    @Override
    public void selectFeedbackButton() {
        custom_click(feedbackButton);
    }

    @Override
    public void selectDataPrivacyButton() {
        custom_click(dataPrivacyButton);
    }

    @Override
    public void selectLogoutButton() {
        custom_click(logoutButton);
    }

    @Override
    public void selectCancelButton() {
        custom_click(cancelButton);
    }

    @Override
    public void selectConfirmLogoutButton() {
        custom_click(confirmLogoutButton);
    }

    @Override
    public boolean isSuccessfullyPhotoUploaded() {
        return custom_isDisplayed(confirmationMessageText);
    }
}
