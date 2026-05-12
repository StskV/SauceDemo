package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test = title]");
    private final By CART = By.cssSelector("[data-test = shopping-cart-link]");
    private final By CART_BADGE = By.cssSelector("[data-test = shopping-cart-badge]");
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final String REMOVE_FROM_CART_PATTERN =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Remove']";
    private final String PRODUCT_CONTAINER =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']";
    private final By PRODUCT_PRICE = By.className("inventory_item_price");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
    }

    public void removeFromCart(String product) {
        driver.findElement(By.xpath(String.format(REMOVE_FROM_CART_PATTERN, product))).click();
    }

    public void clickCart() {
        driver.findElement(CART).click();
    }

    public boolean isCartBadgeDisplayed() {
        return !driver.findElements(CART_BADGE).isEmpty();
    }

    public String getCartBadgeCount() {
        return driver.findElement(CART_BADGE).getText();
    }

    public String getProductPrice(String productName) {
        WebElement product = driver.findElement(By.xpath(String.format(PRODUCT_CONTAINER, productName)));
        return product.findElement(PRODUCT_PRICE).getText();
    }
}