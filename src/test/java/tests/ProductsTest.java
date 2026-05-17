package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class ProductsTest extends BaseTest {

    @Test(
            description = "Проверка добавления товара в корзину со страницы Products",
            testName = "Добавление товара в корзину со страницы Products",
            groups = "smoke"
    )
    public void checkAddProductFromProductsPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        assertEquals(productsPage.getCartBadgeCount(), "1", "Add product to cart test failed");
    }

    @Test(
            description = "Проверка удаления товара из корзины со страницы Products",
            testName = "Удаление товаров из корзины со страницы Products",
            groups = "smoke"
    )
    public void checkRemoveProductFromProductsPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.removeFromCart("Sauce Labs Bike Light");
        assertFalse(productsPage.isCartBadgeDisplayed(), "Remove product from cart test failed");
    }
}
