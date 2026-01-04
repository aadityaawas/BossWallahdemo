package testcases;

import factories.DriverFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.containers.IDashboardContainer;
import pages.containers.ILoginContainer;
import pages.containers.IProfileContainer;
import pages.containers.IWelcomeScreenContainer;
import testbase.BaseTest;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;

public class LoginTestCase extends BaseTest {

    // private page object variables to minimize the memory leakage.
    private ILoginContainer loginPageObjects;
    private IDashboardContainer dashboardPageObjects;
    private IProfileContainer profilePageObjects;
    private IWelcomeScreenContainer welcomeScreenPageObjects;

    // private @BeforeClass so that we can do some class level setups too
    @BeforeClass
    private void setupPage() {
        loginPageObjects = pageFactory.getPageObject(ILoginContainer.class);
        welcomeScreenPageObjects = pageFactory.getPageObject(IWelcomeScreenContainer.class);
        dashboardPageObjects = pageFactory.getPageObject(IDashboardContainer.class);
        profilePageObjects = pageFactory.getPageObject(IProfileContainer.class);
    }

    // private @AfterClass so that we can do some class level teardown too
    @AfterClass
    private void tearPage() {
        pageFactory.clearInstances();
    }

    // This method is used to return back to the starting point0 which is common for all the test cases.
    // This will provide the zero dependency of one test case over another.
    @BeforeMethod
    private void returnBack(Method method) throws InterruptedException {
        // We are assuming max 5 times we need to go back to the return back to the point0;
        int maxBackPress = 10;

        // Getting all methods with annotations
        Test test = method.getAnnotation(Test.class);

        // Skipping the @BeforeMethod logic of returning back for group "SKIP_RETURN_BACK"
        if (test != null && Arrays.asList(test.groups()).contains("SKIP_RETURN_BACK")) {
            return;
        }

        // Main logic for returning back the desired locator
        while (maxBackPress-- > 0 && !isHomeVisible()) {
            if (DriverFactory.getDriver() instanceof AndroidDriver androidDriver) {
                androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
            }
            // For UI rendering and stabilization
            Thread.sleep(500);
        }

        // Confirming the contents of Home page is loaded as locator values might alter
        if (DriverFactory.getDriver() instanceof AndroidDriver androidDriver) {
            androidDriver.findElement(AppiumBy.accessibilityId("Home\nTab 1 of 5")).click();
        }
    }

    // helper method to iterate the loop accordingly
    private boolean isHomeVisible() {
        if (DriverFactory.getDriver() instanceof AndroidDriver androidDriver) {
            androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            try {
                return !androidDriver.findElements(AppiumBy.accessibilityId("Home\nTab 1 of 5")).isEmpty();
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    // Test case for successful login via mobile number.
    // For ease, I will be entering the OTP manually as in my simulator SIM is not present
    @Test(priority = 1, groups = "SKIP_RETURN_BACK")
    public void loginSuccess() throws InterruptedException {
        Assert.assertTrue(welcomeScreenPageObjects.isWelcomeToBossWallahDisplayed());
        welcomeScreenPageObjects.selectExploreFreeBasicAccessButton();
        welcomeScreenPageObjects.selectConfirmBasicAccessButton();
        loginPageObjects.selectLoginViaMobileNumber();
        loginPageObjects.enterMobileNumber("98765XXXXX"); // dummy phone number.
        loginPageObjects.selectGetOTP();
        Thread.sleep(10000);
//        Entering OTP manually for demo
//        loginPageObjects.enterOTP("1234");
        Assert.assertTrue(dashboardPageObjects.isHomeButtonSelected());
    }

    // Test case for successfully changing the profile picture.
    @Test(priority = 2)
    public void changeProfilePicture() {
        dashboardPageObjects.selectProfileButton();
        profilePageObjects.selectEditProfileButton();
        profilePageObjects.selectEditProfilePictureButton();
        profilePageObjects.selectTakePhotoButton();
        profilePageObjects.selectUploadButton();
        profilePageObjects.selectCameraShutterButton();
        profilePageObjects.selectConfirmPhotoButton();
        Assert.assertTrue(profilePageObjects.isSuccessfullyPhotoUploaded());
    }

    // Test case for successful logout from the app.
    @Test(priority = 3)
    public void logoutSuccess() {
        dashboardPageObjects.selectProfileButton();
        profilePageObjects.selectAppSettings();
        profilePageObjects.selectLogoutButton();
        profilePageObjects.selectConfirmLogoutButton();
        Assert.assertTrue(welcomeScreenPageObjects.isWelcomeToBossWallahDisplayed());
    }

}
