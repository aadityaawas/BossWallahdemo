package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.containers.IDashboardContainer;
import utils.ActionEngine;

public class DashboardPageObjects extends ActionEngine implements IDashboardContainer {

    // private locators
    private final By homeButton = AppiumBy.accessibilityId("Home\nTab 1 of 5");
    private final By courseButton = AppiumBy.accessibilityId("Courses\nTab 2 of 5");
    private final By exploreButon = AppiumBy.accessibilityId("Explore\nTab 3 of 5");
    private final By expertsButton = AppiumBy.accessibilityId("Experts\nTab 4 of 5");
    private final By askBBAIButton = AppiumBy.accessibilityId("Ask BB AI\nTab 5 of 5");
    private final By profileButton = isAndroid()
            ? AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)")
            : AppiumBy.iOSClassChain("some_id");

    // public page object methods
    @Override
    public void selectHomeButton() {
        custom_click(homeButton);
    }

    @Override
    public void selectCoursesButton() {
        custom_click(courseButton);
    }

    @Override
    public void selectExploreButton() {
        custom_click(exploreButon);
    }

    @Override
    public void selectExpertButton() {
        custom_click(expertsButton);
    }

    @Override
    public void selectAskBBAIButton() {
        custom_click(askBBAIButton);
    }

    @Override
    public void selectProfileButton() {
        custom_click(profileButton);
    }

    @Override
    public boolean isHomeButtonSelected() {
        return custom_isDisplayed(homeButton);
    }

    @Override
    public boolean isCoursesButtonSelected() {
        return custom_isDisplayed(courseButton);
    }

    @Override
    public boolean isExploreButtonSelected() {
        return custom_isDisplayed(exploreButon);
    }

    @Override
    public boolean isExpertButtonSelected() {
        return custom_isDisplayed(expertsButton);
    }

    @Override
    public boolean isAskBBAIButtonSelected() {
        return custom_isDisplayed(askBBAIButton);
    }

    @Override
    public boolean isProfileButtonSelected() {
        return custom_isDisplayed(profileButton);
    }
}
