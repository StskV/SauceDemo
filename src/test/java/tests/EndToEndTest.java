package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EndToEndTest extends BaseTest {

    private final String PRODUCT_NAME = "Sauce Labs Backpack";
    private final String EXPECTED_MESSAGE = "Thank you for your order!";

    @Test
    public void checkSuccessfulFullPurchaseFlow() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String expectedPrice = productsPage.getProductPrice(PRODUCT_NAME);
        productsPage.addToCart(PRODUCT_NAME);
        productsPage.clickCart();
        softAssert.assertEquals(PRODUCT_NAME, cartPage.getProductName(), "Product name does not match on Cart page");
        softAssert.assertEquals(expectedPrice, cartPage.getProductPrice(), "Product price does not match on Cart page");
        cartPage.clickCheckout();
        checkoutPage.fillYourInformation("John", "Smith", "12345");
        checkoutPage.clickContinue();
        softAssert.assertEquals(PRODUCT_NAME, checkoutPage.getProductName(), "Product name does not match on Checkout page");
        softAssert.assertEquals(expectedPrice, checkoutPage.getProductPrice(), "Product price does not match on Checkout page");
        checkoutPage.clickFinish();
        softAssert.assertEquals(checkoutPage.getSuccessOrderMessage(), EXPECTED_MESSAGE, "End to end test failed");
        softAssert.assertAll();
    }
}
