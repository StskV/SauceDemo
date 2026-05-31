package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    @Test(
            description = "Проверка отображения пустой корзины",
            testName = "Отображение пустой корзины",
            groups = "regression"
    )
    public void checkEmptyCart() {
        ProductsPage products = loginStep.loginAsStandardUser();
        CartPage cart = products.clickCart();
        assertEquals(cart.getProductsCount(), 0, "Empty cart test failed");
    }

    @Test(
            description = "Проверка корзины с двумя товарами",
            testName = "Корзина с двумя товарами",
            groups = "smoke"
    )
    public void checkAddTwoProductsToCart() {
        SoftAssert softAssert = new SoftAssert();
        ProductsPage products = loginStep.loginAsStandardUser();
        CartPage cart = products
                .addToCart("Sauce Labs Bolt T-Shirt")
                .clickCart();
        softAssert.assertEquals(cart.getProductsCount(), 1, "Adding 1 product test failed");
        cart = cart
                .clickContinueShopping()
                .addToCart("Sauce Labs Onesie")
                .clickCart();
        softAssert.assertEquals(cart.getProductsCount(), 2, "Adding 2 products test failed");
        softAssert.assertAll();
    }

    @Test(
            description = "Проверка удаления товаров из корзины",
            testName = "Удаление товаров из корзины",
            groups = "smoke"
    )
    public void checkRemoveProductsFromCart() {
        CartPage cart = cartStep.addProductsToCart(
                        "Sauce Labs Backpack",
                        "Test.allTheThings() T-Shirt (Red)"
                )
                .removeProduct("Sauce Labs Backpack")
                .removeProduct("Test.allTheThings() T-Shirt (Red)");
        assertEquals(cart.getProductsCount(), 0, "Remove products from cart test failed");
    }
}