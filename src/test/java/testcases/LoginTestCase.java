package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.containers.IDashboardContainer;
import pages.containers.ILoginContainer;
import pages.containers.IProfileContainer;
import pages.containers.IWelcomeScreenContainer;
import testbase.BaseTest;

public class LoginTestCase extends BaseTest {

    private ILoginContainer loginPageObjects;
    private IDashboardContainer dashboardPageObjects;
    private IProfileContainer profilePageObjects;
    private IWelcomeScreenContainer welcomeScreenPageObjects;

    @BeforeClass
    private void setupPage() {
        loginPageObjects = pageFactory.getPageObject(ILoginContainer.class);
        welcomeScreenPageObjects = pageFactory.getPageObject(IWelcomeScreenContainer.class);
        dashboardPageObjects = pageFactory.getPageObject(IDashboardContainer.class);
        profilePageObjects = pageFactory.getPageObject(IProfileContainer.class);
    }

    @AfterClass
    private void tearPage() {
        pageFactory.clearInstances();
    }

    @Test(priority = 1)
    public void loginSuccess() {
        welcomeScreenPageObjects.selectExploreFreeBasicAccessButton();
        welcomeScreenPageObjects.selectConfirmBasicAccessButton();
        loginPageObjects.selectLoginViaMobileNumber();
        loginPageObjects.enterMobileNumber("9767298202");
        loginPageObjects.enterOTP("1234");
        loginPageObjects.selectVerifyPassword();
//        Assert.assertTrue(dashboardPageObjects.isHomeButtonSelected());
    }

    @Test(priority = 2)
    public void changeProfilePicture() {
        dashboardPageObjects.selectProfileButton();
        profilePageObjects.selectEditProfileButton();
        profilePageObjects.selectTakePhotoButton();
        profilePageObjects.selectUploadButton();
        profilePageObjects.selectCameraShutterButton();
        profilePageObjects.selectConfirmPhotoButton();
//        Assert.assertEquals("___________________","Profile image Updated Successfully");
    }

    @Test(priority = 3)
    public void logoutSuccess() {
        dashboardPageObjects.selectProfileButton();
        profilePageObjects.selectAppSettings();
        profilePageObjects.selectLogoutButton();
    }

}
