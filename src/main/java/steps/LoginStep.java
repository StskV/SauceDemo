package steps;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginStep {

    private final LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
    }

    public ProductsPage loginAsStandardUser() {
        return loginPage.open()
                .loginWithValidCreds("standard_user", "secret_sauce");
    }
}