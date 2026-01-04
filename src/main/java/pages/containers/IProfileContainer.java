package pages.containers;

public interface IProfileContainer {
    // enter details in fields

    // select main profile buttons
    void selectProfilePictureButton();
    void selectHistoryButton();
    void selectWatchListButton();
    void selectJoinAsExpertButton();
    void selectAppSettings();
    void selectUnlockPrimeAccessButton();
    void selectContactSupportButton();
    void selectFAQButton();
    void selectAboutBossWallahButton();
    void selectTermsAndConditionsButton();

    // select edit profile buttons
    void selectEditProfileButton();
    void selectEditProfilePictureButton();
    void selectTakePhotoButton();
    void selectFromGalleryButton();
    void selectUploadButton();
    void selectCameraShutterButton();
    void selectConfirmPhotoButton();
    void selectDeclinePhotoButton();

    // select app settings buttons
    void selectFeedbackButton();
    void selectDataPrivacyButton();
    void selectLogoutButton();
    void selectCancelButton();
    void selectConfirmLogoutButton();

    // element is displayed buttons
    boolean isSuccessfullyPhotoUploaded();

}
