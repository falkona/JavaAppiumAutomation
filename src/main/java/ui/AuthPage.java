package ui;

import java.util.List;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;

@Getter
public class AuthPage extends CorePageObject {

    @AndroidFindBy(id = "login_button")
    private MobileElement loginButton;

    @AndroidFindBy(id = "logo")
    private MobileElement logo;

    @AndroidFindBy(id = "email_or_phone")
    private MobileElement emailOrPhone;

    @AndroidFindBy(id = "vk_password")
    private MobileElement vkPassword;

    @AndroidFindBy(id = "continue_btn")
    private MobileElement continueButton;

    @AndroidFindBy(id = "login_error_title")
    private MobileElement loginErrorTitle;

    @AndroidFindBy(id = "login_error_subtitle")
    private MobileElement loginErrorSubtitle;

    public AuthPage() {
        super();
    }

    public void initSingIn() {
        loginButton.click();
    }

    public void confirmPageLoad() {
        confirmPageLoad(List.of(logo));
    }

    public void fillLogoPass(String login, String password) {
        emailOrPhone.sendKeys(login);
        vkPassword.sendKeys(password);
    }

    public void submitLogin() {
        continueButton.click();
    }

}
