package pages.containers;

public interface IWelcomeScreenContainer {

    // select buttons
    void selectStartBusinessButton();
    void selectExploreGovernmentSchemesButton();
    void selectExploreFreeBasicAccessButton();
    void selectConfirmBasicAccessButton();
    void selectDeclineBasicAccessButton();

    // display texts
    boolean isWelcomeToBossWallahDisplayed();
    boolean isStartBusinessButtonVisible();
    boolean isExploreGovernmentSchemesButtonVisible();
    boolean isExploreFreeBasicAccessButtonVisible();
    boolean isConfirmBasicAccessButtonVisible();
    boolean isDeclineBasicAccessButtonVisible();
}
