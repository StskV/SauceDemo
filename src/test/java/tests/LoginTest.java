package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(
            description = "Проверка логина с позитивными кредами",
            testName = "Логин с позитивным паролем",
            groups = "smoke",
            invocationCount = 1,
            threadPoolSize = 1
    )
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(),
                "Products",
                "Login failed");
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
    public void checkLoginWithNegativeCredentials(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                errorMessage,
                "Check login with negative creds failed");
    }
}