package tests.auth;

import org.junit.jupiter.api.Test;
import tests.CoreTestCase;
import ui.AuthPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizationTests extends CoreTestCase {

    private final String LOGIN = "ddnos@mail.ru";
    private final String PASSWORD = "Qwerty";

    @Test
    public void authBadCredentialsTest() {
        setUp();
        AuthPage authPage = new AuthPage();
        authPage.confirmPageLoad();
        authPage.initSingIn();
        authPage.fillLogoPass(LOGIN, PASSWORD);
        authPage.submitLogin();
        assertEquals("Incorrect login or password", authPage.getLoginErrorTitle().getText(), "Заголовок сообщения об ошибке " +
                "отличается от" +
                " ожидаемого");
        assertEquals("Make sure your login information is correct. Trouble signing in?", authPage.getLoginErrorSubtitle().getText(),
                     "Подзаголовок сообщения об ошибке отличается от ожидаемого");
        assertEquals(LOGIN, authPage.getEmailOrPhone().getText(), "Значение поля логин отличается от ожидаемого");
    }

}
