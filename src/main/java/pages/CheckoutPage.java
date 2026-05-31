package pages;

import dto.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        driver.get(BASE_URL + "checkout-step-one.html");
        return isPageOpened();
    }

    @Override
    public CheckoutPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONTINUE_BUTTON));
        return this;
    }

    public CheckoutPage fillYourInformation(Customer customer) {
        type(FIRST_NAME_FIELD, customer.getFirstName());
        type(LAST_NAME_FIELD, customer.getLastName());
        type(ZIP_FIELD, customer.getZipCode());
        return this;
    }

    public CheckoutPage clickContinue() {
        click(CONTINUE_BUTTON);
        return this;
    }

    public CheckoutPage clickFinish() {
        click(FINISH_BUTTON);
        return this;
    }

    public String getProductName() {
        return waitVisible(PRODUCT_NAME).getText();
    }

    public String getSuccessOrderMessage() {
        return waitVisible(SUCCESS_ORDER_MESSAGE).getText();
    }
}