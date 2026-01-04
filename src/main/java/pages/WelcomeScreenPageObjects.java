package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.containers.IWelcomeScreenContainer;
import utils.ActionEngine;

public class WelcomeScreenPageObjects extends ActionEngine implements IWelcomeScreenContainer {

    // private locators
    private final By bossWallahHeading = AppiumBy.accessibilityId("Welcome to Boss Wallah");
    private final By startBusiness = AppiumBy.accessibilityId("Start a Business\\nExplore business ideas, get a persaonalized business plan, and launch your business.");
    private final By exploreGovernmentBusiness = AppiumBy.accessibilityId("Explore Government Schemes\\nDiscover funding and schemes that you may be eligible for.");
    private final By exploreFreeBasicBusiness = AppiumBy.accessibilityId("Explore freely with basic access");
    private final By confirmButton = AppiumBy.accessibilityId("Yes");
    private final By declineButton = AppiumBy.accessibilityId("No");

    // public page objects
    @Override
    public void selectStartBusinessButton() {
        custom_click(startBusiness);
    }

    @Override
    public void selectExploreGovernmentSchemesButton() {
        custom_click(exploreGovernmentBusiness);
    }

    @Override
    public void selectExploreFreeBasicAccessButton() {
        custom_click(exploreFreeBasicBusiness);
    }

    @Override
    public void selectConfirmBasicAccessButton() {
        custom_click(confirmButton);
    }

    @Override
    public void selectDeclineBasicAccessButton() {
        custom_click(declineButton);
    }

    @Override
    public boolean isWelcomeToBossWallahDisplayed() {
        return custom_isDisplayed(bossWallahHeading);
    }

    @Override
    public boolean isStartBusinessButtonVisible() {
        return custom_isDisplayed(startBusiness);
    }

    @Override
    public boolean isExploreGovernmentSchemesButtonVisible() {
        return custom_isDisplayed(exploreGovernmentBusiness);
    }

    @Override
    public boolean isExploreFreeBasicAccessButtonVisible() {
        return custom_isDisplayed(exploreFreeBasicBusiness);
    }

    @Override
    public boolean isConfirmBasicAccessButtonVisible() {
        return custom_isDisplayed(confirmButton);
    }

    @Override
    public boolean isDeclineBasicAccessButtonVisible() {
        return custom_isDisplayed(declineButton);
    }
}
