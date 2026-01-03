package testcases;

import factories.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashboardPageObjects;
import pages.LoginPageObjects;
import pages.ProfilePageObjects;
import pages.WelcomeScreenPageObjects;
import pages.containers.IDashboardContainer;
import pages.containers.ILoginContainer;
import pages.containers.IProfileContainer;
import pages.containers.IWelcomeScreenContainer;

public class LoginTestCases {

    private LoginPageObjects loginPageObjects;
    private DashboardPageObjects dashboardPageObjects;
    private ProfilePageObjects profilePageObjects;
    private WelcomeScreenPageObjects welcomeScreenPageObjects;

    @BeforeClass
    private void setupPage() {
        PageFactory pageFactory = new PageFactory();
        loginPageObjects = (LoginPageObjects) pageFactory.getPageObject(ILoginContainer.class);
        welcomeScreenPageObjects = (WelcomeScreenPageObjects) pageFactory.getPageObject(IWelcomeScreenContainer.class);
        dashboardPageObjects = (DashboardPageObjects) pageFactory.getPageObject(IDashboardContainer.class);
        profilePageObjects = (ProfilePageObjects) pageFactory.getPageObject(IProfileContainer.class);
        System.out.println("Login Page Objects Created");
    }

    @AfterClass
    private void tearDown() {
        loginPageObjects = null;
        dashboardPageObjects = null;
        profilePageObjects = null;
        welcomeScreenPageObjects = null;
        System.out.println("Login Page Objects Deleted");
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
