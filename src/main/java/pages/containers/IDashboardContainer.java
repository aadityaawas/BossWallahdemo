package pages.containers;

public interface IDashboardContainer {

    // select main buttons
    void selectHomeButton();
    void selectCoursesButton();
    void selectExploreButton();
    void selectExpertButton();
    void selectAskBBAIButton();
    void selectProfileButton();

    // display button
    boolean isHomeButtonSelected();
    boolean isCoursesButtonSelected();
    boolean isExploreButtonSelected();
    boolean isExpertButtonSelected();
    boolean isAskBBAIButtonSelected();
    boolean isProfileButtonSelected();
}
