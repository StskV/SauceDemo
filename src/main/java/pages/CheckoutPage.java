package pages;

import dto.Customer;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutPage extends BasePage {

    private final By FIRST_NAME_FIELD = By.id("first-name");
    private final By LAST_NAME_FIELD = By.id("last-name");
    private final By ZIP_FIELD = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.id("continue");
    private final By FINISH_BUTTON = By.id("finish");
    private final By SUCCESS_ORDER_MESSAGE = By.className("complete-header");
    private final By PRODUCT_NAME = By.className("inventory_item_name");
    private final By PRODUCT_PRICE = By.className("inventory_item_price");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutPage open() {
        log.info("Открытие страницы Checkout Page");
        driver.get(BASE_URL + "checkout-step-one.html");
        return isPageOpened();
    }

    @Override
    public CheckoutPage isPageOpened() {
        wait.until(ExpectedConditions.urlContains("checkout"));
        return this;
    }

    @Step("Заполнение формы 'Checkout: Your Information' именем '{customer.firstName}', фамилией '{customer.lastName}' и zip кодом '{customer.zipCode}'")
    public CheckoutPage fillYourInformation(Customer customer) {
        log.info("Заполнение формы 'Checkout: Your Information' именем '{}', фамилией '{}' и zip кодом '{}'", customer.getFirstName(), customer.getLastName(), customer.getZipCode());
        type(FIRST_NAME_FIELD, customer.getFirstName());
        type(LAST_NAME_FIELD, customer.getLastName());
        type(ZIP_FIELD, customer.getZipCode());
        return this;
    }

    @Step("Переход на страницу 'Checkout: Overview'")
    public CheckoutPage clickContinue() {
        log.info("Переход на страницу 'Checkout: Overview");
        click(CONTINUE_BUTTON);
        return this;
    }

    @Step("Переход на страницу Checkout: Complete!")
    public CheckoutPage clickFinish() {
        log.info("Переход на страницу Checkout: Complete!");
        click(FINISH_BUTTON);
        return this;
    }

    public String getProductName() {
        return waitVisible(PRODUCT_NAME).getText();
    }

    public String getProductPrice() {
        return waitVisible(PRODUCT_PRICE).getText();
    }

    public String getSuccessOrderMessage() {
        return waitVisible(SUCCESS_ORDER_MESSAGE).getText();
    }
}