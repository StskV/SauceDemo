package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(
            description = "Проверка логина с позитивными кредами",
            testName = "Логин с позитивным паролем",
            groups = "smoke",
            invocationCount = 1,
            threadPoolSize = 1
    )
    @Owner("Satsiuk Viktoriya")
    @Epic("Sauce Demo 1")
    @Feature("Login")
    @Story("Login with positive credentials")
    @Description("Проверка логина с позитивными кредами")
    @Severity(SeverityLevel.CRITICAL)
    @Flaky
    @Link(name = "Аналитика", url = "https://www.saucedemo.com/")
    @TmsLink("SD-T01")
    @Issue("BUG-01")
    public void checkLoginWithPositiveCred() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.open()
                        .loginWithValidCreds(user, password);
        assertEquals(productsPage.getTitle(),
                "Products",
                "Check login with positive creds failed"
        );
    }

    @DataProvider(name = "Параметризированный тест для негативного логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(
            dataProvider = "Параметризированный тест для негативного логина",
            description = "Проверка логина с негативными кредами",
            testName = "Логин с негативными кредами",
            groups = "regression"
    )
    @Owner("Satsiuk Viktoriya")
    @Epic("Sauce Demo 1")
    @Feature("Login")
    @Story("Login with negative credentials")
    @Severity(SeverityLevel.NORMAL)
    public void checkLoginWithNegativeCredentials(
            String user,
            String password,
            String errorMessage
    ) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open()
                .loginWithInvalidCreds(user, password);
        assertEquals(loginPage.getErrorMessage(),
                errorMessage,
                "Check login with negative creds failed"
        );
    }
}