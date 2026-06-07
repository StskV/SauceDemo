package steps;

import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.ProductsPage;

@Log4j2
@RequiredArgsConstructor
public class CartStep {
    private final WebDriver driver;

    @Step("Добавление товаров в корзину: {products}")
    public CartPage addProductsToCart(String... products) {
        ProductsPage productsPage =
                new ProductsPage(driver)
                        .open()
                        .isPageOpened();
        for (String product : products) {
            log.info("Добавление товара: '{}'", product);
            productsPage.addToCart(product);
        }
        return productsPage.clickCart();
    }
}