package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
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
        log.info("Открытие страницы Cart Page");
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

    @Step("Удаление продукта '{product}' из корзины")
    public CartPage removeProduct(String product) {
        log.info("Удаление продукта '{}' из корзины", product);
        click(By.xpath(String.format(REMOVE_BUTTON_PATTERN, product)));
        return this;
    }

    @Step("Переход на страницу Checkout: Your Information")
    public CheckoutPage clickCheckout() {
        log.info("Переход на страницу Checkout: Your Information со страницы Cart Page");
        click(CHECKOUT_BUTTON);
        return new CheckoutPage(driver).isPageOpened();
    }

    @Step("Переход на страницу Products")
    public ProductsPage clickContinueShopping() {
        log.info("Возвращение на Products Page со страницы Checkout");
        click(CONTINUE_SHOPPING_BUTTON);
        return new ProductsPage(driver).isPageOpened();
    }
}