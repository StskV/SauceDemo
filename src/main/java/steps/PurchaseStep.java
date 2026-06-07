package steps;

import dto.Customer;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;
import pages.ProductsPage;

@Log4j2
@RequiredArgsConstructor
public class PurchaseStep {
    private final WebDriver driver;

    @Step("Покупка товара '{product}'")
    public CheckoutPage buyProduct(String product, Customer customer) {
        log.info("Покупка товара '{}'", product);
        return new ProductsPage(driver)
                .open()
                .isPageOpened()
                .addToCart(product)
                .clickCart()
                .clickCheckout()
                .fillYourInformation(customer)
                .clickContinue();
    }
}