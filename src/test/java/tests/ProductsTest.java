package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest{

    @Test
    public void checkAddProductsFromProductsPage() {
        loginPage.open();
        loginPage.successfulLogin();
        productsPage.addToCart("Sauce Labs Backpack");
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1", "Add product to cart test failed");
    }

    @Test
    public void checkRemoveProductFromProductsPage() {
        loginPage.open();
        loginPage.successfulLogin();
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.removeFromCart("Sauce Labs Bike Light");
        Assert.assertFalse(productsPage.isCartBadgeDisplayed(), "Remove product from cart test failed");
    }
}
