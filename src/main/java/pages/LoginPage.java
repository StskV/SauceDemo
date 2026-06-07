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
    private final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Login Page")
    @Override
    public LoginPage open() {
        log.info("Открытие страницы Login Page");
        driver.get(BASE_URL);
        return isPageOpened();
    }

    @Override
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
            return this;
        } catch (Exception e) {
            log.error("Login page not loaded", e);
            throw new IllegalStateException("Login page not loaded", e);
        }
    }

    private void enterCredentials(String user, String password) {
        type(USERNAME_FIELD, user);
        type(PASSWORD_FIELD, password);
        click(LOGIN_BUTTON);
    }

    @Step("Успешная авторизация пользователя '{username}'")
    public ProductsPage loginWithValidCreds(String user, String password) {
        log.info("Успешная авторизация пользователя '{}'", user);
        enterCredentials(user, password);
        return new ProductsPage(driver).isPageOpened();
    }

    @Step("Неуспешная авторизация пользователя '{username}'")
    public LoginPage loginWithInvalidCreds(String user, String password) {
        log.info("Неуспешная авторизация пользователя '{}'", user);
        enterCredentials(user, password);
        return this;
    }

    public String getErrorMessage() {
        return waitVisible(ERROR_MESSAGE).getText();
    }
}