package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test='title']");
    private final By CART = By.cssSelector("[data-test='shopping-cart-link']");
    private final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");
    private final String ADD_TO_CART_PATTERN = "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final String REMOVE_FROM_CART_PATTERN = "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Remove']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductsPage open() {
        driver.get(BASE_URL + "inventory.html");
        return isPageOpened();
    }

    @Override
    public ProductsPage isPageOpened() {
        waitVisible(TITLE);
        return this;
    }

    private By addToCartButton(String product) {
        return By.xpath(String.format(ADD_TO_CART_PATTERN, product));
    }

    private By removeButton(String product) {
        return By.xpath(String.format(REMOVE_FROM_CART_PATTERN, product));
    }

    @Step("Добавление в корзину товара с именем: '{product}'")
    public ProductsPage addToCart(String product) {
        click(addToCartButton(product));
        return this;
    }

    @Step("Удаление из корзины товара с именем '{product}'")
    public ProductsPage removeFromCart(String product) {
        click(removeButton(product));
        return this;
    }

    @Step("Переход на страницу корзины")
    public CartPage clickCart() {
        click(CART);
        return new CartPage(driver).isPageOpened();
    }

    public boolean isCartBadgeDisplayed() {
        return !driver.findElements(CART_BADGE).isEmpty();
    }

    public String getCartBadgeCount() {
        return waitVisible(CART_BADGE).getText();
    }

    public String getTitle() {
        return waitVisible(TITLE).getText();
    }
}