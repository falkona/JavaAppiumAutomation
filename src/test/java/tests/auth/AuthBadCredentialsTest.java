package tests.auth;

import org.junit.jupiter.api.Test;
import tests.CoreTestCase;
import ui.AuthPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthBadCredentialsTest extends CoreTestCase {

    private final String LOGIN = "dasha.nos@vk.com";
    private final String PASSWORD = "Qwerty!1";
    private final String LOGIN_ERROR_TITLE_TEXT = "Incorrect login or password";
    private final String LOGIN_ERROR_SUBTITLE_TEXT = "Make sure your login information is correct. Trouble signing in?";

    @Test
    public void authBadCredentialsTest() {
        setUp();
        AuthPage authPage = new AuthPage();
        authPage.confirmPageLoad();
        authPage.initSingIn();
        authPage.fillLogoPass(LOGIN, PASSWORD);
        authPage.submitLogin();
        assertEquals(LOGIN_ERROR_TITLE_TEXT, authPage.getLoginErrorTitle().getText(), "Заголовок сообщения об ошибке отличается от ожидаемого");
        assertEquals(LOGIN_ERROR_SUBTITLE_TEXT, authPage.getLoginErrorSubtitle().getText(), "Текст сообщения об ошибке отличается от " +
                "ожидаемого");
        assertEquals(LOGIN, authPage.getEmailOrPhone().getText(), "Значение поля 'Логин' ожидаемого");
        assertNotNull(authPage.getVkPassword().getText(), "Значение поля 'Пароль' ожидаемого");
    }

}
