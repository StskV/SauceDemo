package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Epic("Sauce Demo 1")
@Feature("Products")
public class ProductsTest extends BaseTest {

    @Test(
            description = "Проверка добавления товара в корзину со страницы Products",
            testName = "Добавление товара в корзину со страницы Products",
            groups = "smoke"
    )
    @Owner("Satsiuk Viktoriya")
    @Story("Adding products")
    @Severity(SeverityLevel.CRITICAL)
    public void checkAddProductFromProductsPage() {
        ProductsPage productsPage = new LoginPage(driver)
                .open()
                .loginWithValidCreds(user, password);
        productsPage.addToCart("Sauce Labs Backpack");
        assertEquals(
                productsPage.getCartBadgeCount(),
                "1",
                "Add product to cart test failed"
        );
    }

    @Test(
            description = "Проверка удаления товара из корзины со страницы Products",
            testName = "Удаление товаров из корзины со страницы Products",
            groups = "smoke"
    )
    @Owner("Satsiuk Viktoriya")
    @Story("Remove products")
    @Severity(SeverityLevel.CRITICAL)
    public void checkRemoveProductFromProductsPage() {
        ProductsPage productsPage = new LoginPage(driver)
                .open()
                .loginWithValidCreds(user, password)
                .addToCart("Sauce Labs Bike Light")
                .removeFromCart("Sauce Labs Bike Light");
        assertFalse(
                productsPage.isCartBadgeDisplayed(),
                "Remove product from cart test failed"
        );
    }
}