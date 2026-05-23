package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    @Step("Заполнение формы 'Checkout: Your Information'  именем '{firstName}', фамилией '{lastName}' и zip кодом '{zip}'")
    public void fillYourInformation(String firstName, String lastName, String zip) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(ZIP_FIELD).sendKeys(zip);
    }

    @Step("Переход на страницу 'Checkout: Overview")
    public void clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    @Step("Переход на страницу Checkout: Complete!")
    public void clickFinish() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public String getProductName() {
        return driver.findElement(PRODUCT_NAME).getText();
    }

    public String getProductPrice() {
        return driver.findElement(PRODUCT_PRICE).getText();
    }

    public String getSuccessOrderMessage() {
        return driver.findElement(SUCCESS_ORDER_MESSAGE).getText();
    }
}
