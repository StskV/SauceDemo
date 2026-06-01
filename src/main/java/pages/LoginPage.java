package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        enterCredentials(user, password);
        return new ProductsPage(driver).isPageOpened();
    }

    @Step("Вход в систему с невалидными кредами: логин '{user}', пароль '{pass}'")
    public LoginPage loginWithInvalidCreds(String user, String pass) {
        enterCredentials(user, pass);
        return this;
    }

    public String getErrorMessage() {
        return waitVisible(ERROR_MESSAGE).getText();
    }
}