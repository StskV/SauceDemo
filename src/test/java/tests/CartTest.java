package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void checkEmptyCart() {
        loginPage.open();
        loginPage.successfulLogin();
        productsPage.clickCart();
        Assert.assertEquals(cartPage.getProductsCount(), 0, "Empty cart test failed");
    }

    @Test
    public void checkAddTwoProductsToCart() {
        loginPage.open();
        loginPage.successfulLogin();
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.clickCart();
        softAssert.assertEquals(cartPage.getProductsCount(), 1, "Adding 1 product test failed");
        cartPage.clickContinueShopping();
        productsPage.addToCart("Sauce Labs Onesie");
        productsPage.clickCart();
        softAssert.assertEquals(cartPage.getProductsCount(), 2, "Adding 2 products test failed");
        softAssert.assertAll();
    }

    @Test
    public void checkRemoveProductsFromCart() {
        loginPage.open();
        loginPage.successfulLogin();
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");
        productsPage.clickCart();
        cartPage.removeProduct("Sauce Labs Backpack");
        cartPage.removeProduct("Test.allTheThings() T-Shirt (Red)");
        Assert.assertEquals(cartPage.getProductsCount(), 0, "Remove products from cart test failed");
    }
}