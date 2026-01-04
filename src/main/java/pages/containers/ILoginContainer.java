package pages.containers;

public interface ILoginContainer {

    // enter details in fields
    void enterMobileNumber(String mobileNumber);
    void enterOTP(String otp);
    void enterEmail(String email);

    // select buttons
    void selectLoginViaMobileNumber();
    void selectLoginViaEmailID();
    void selectLoginLater();
    void selectGetOTP();
    void selectVerifyPassword();


}
