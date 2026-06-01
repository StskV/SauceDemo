package steps;

import dto.Customer;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;
import pages.LoginPage;

public class PurchaseStep {
    private final WebDriver driver;

    public PurchaseStep(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage buyProduct(String product, Customer customer) {
        return new LoginPage(driver)
                .open()
                .loginWithValidCreds("standard_user", "secret_sauce")
                .addToCart(product)
                .clickCart()
                .clickCheckout()
                .fillYourInformation(customer)
                .clickContinue();
    }
}