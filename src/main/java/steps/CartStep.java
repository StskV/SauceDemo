package steps;

import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CartStep {
    private final WebDriver driver;

    public CartStep(WebDriver driver) {
        this.driver = driver;
    }

    public CartPage addProductsToCart(String... products) {
        ProductsPage productsPage = new LoginPage(driver)
                .open()
                .loginWithValidCreds("standard_user", "secret_sauce");
        for (String product : products) {
            productsPage.addToCart(product);
        }
        return productsPage.clickCart();
    }
}