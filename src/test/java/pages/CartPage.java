package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final String REMOVE_BUTTON_PATTERN =
            "//*[text()='%s']//ancestor::*[contains(@class,'cart_item')]//button[text()='Remove']";
    private final By PRODUCT_CONTAINER = By.className("cart_item");
    private final By PRODUCT_NAME = By.className("inventory_item_name");
    private final By PRODUCT_PRICE = By.className("inventory_item_price");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void removeProduct(String product) {
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON_PATTERN, product))).click();
    }

    public int getProductsCount() {
        return driver.findElements(PRODUCT_CONTAINER).size();
    }

    public String getProductName() {
        return driver.findElement(PRODUCT_NAME).getText();
    }

    public String getProductPrice() {
        return driver.findElement(PRODUCT_PRICE).getText();
    }
}
