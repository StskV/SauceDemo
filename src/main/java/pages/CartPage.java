package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final String REMOVE_BUTTON_PATTERN = "//*[text()='%s']//ancestor::*[contains(@class,'cart_item')]//button[text()='Remove']";
    private final By PRODUCT_CONTAINER = By.className("cart_item");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPage open() {
        driver.get(BASE_URL + "cart.html");
        return isPageOpened();
    }

    @Override
    public CartPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
        return this;
    }

    public int getProductsCount() {
        return driver.findElements(PRODUCT_CONTAINER).size();
    }

    public CartPage removeProduct(String product) {
        click(By.xpath(String.format(REMOVE_BUTTON_PATTERN, product)));
        return this;
    }

    public CheckoutPage clickCheckout() {
        click(CHECKOUT_BUTTON);
        return new CheckoutPage(driver).isPageOpened();
    }

    public ProductsPage clickContinueShopping() {
        click(CONTINUE_SHOPPING_BUTTON);
        return new ProductsPage(driver).isPageOpened();
    }
}