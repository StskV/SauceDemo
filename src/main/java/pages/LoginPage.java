package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {
    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы LoginPage")
    @Override
    public LoginPage open() {
        log.info("Открытие страницы Login Page с URL: {}", BASE_URL);
        driver.get(BASE_URL);
        return isPageOpened();
    }

    @Override
    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    private void enterCredentials(String user, String pass) {
        type(USERNAME_FIELD, user);
        type(PASSWORD_FIELD, pass);
        click(LOGIN_BUTTON);
    }

    @Step("Вход в систему с валидными кредами: логин '{user}', пароль '{password}'")
    public ProductsPage loginWithValidCreds(String user, String password) {
        log.info("логин с валидными данными пользователя: логин '{}', пароль '{}'", user, password);
        enterCredentials(user, password);
        return new ProductsPage(driver).isPageOpened();
    }

    @Step("Вход в систему с невалидными кредами: логин '{user}', пароль '{pass}'")
    public LoginPage loginWithInvalidCreds(String user, String password) {
        log.info("логин с невалидными данными пользователя: логин '{}', пароль '{}'", user, password);
        enterCredentials(user, password);
        return this;
    }

    public String getErrorMessage() {
        String error = waitVisible(ERROR_MESSAGE).getText();
        log.info("Получен текст ошибки на Login Page: '{}'", error);
        return error;
    }
}