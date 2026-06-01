package tests;

import dto.Customer;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckoutPage;

public class EndToEndTest extends BaseTest {

    private final String PRODUCT_NAME = "Sauce Labs Backpack";
    private final String EXPECTED_MESSAGE = "Thank you for your order!";

    @Test(
            description = "End to end проверка успешной покупки",
            testName = "Успешная покупка",
            groups = "smoke",
            priority = 1
    )
    @Owner("Satsiuk Viktoriya")
    @Epic("Sauce Demo 1")
    @Feature("Order Checkout")
    @Story("End-to-End purchase flow")
    @Severity(SeverityLevel.CRITICAL)
    public void checkSuccessfulFullPurchaseFlow() {
        SoftAssert softAssert = new SoftAssert();
        Customer customer = Customer.builder()
                .firstName("John")
                .lastName("Smith")
                .zipCode("12345")
                .build();
        CheckoutPage checkoutPage = purchaseStep.buyProduct(PRODUCT_NAME, customer);
        softAssert.assertEquals(checkoutPage.getProductName(), PRODUCT_NAME, "Product name does not match on Checkout page");
        checkoutPage.clickFinish();
        softAssert.assertEquals(checkoutPage.getSuccessOrderMessage(), EXPECTED_MESSAGE, "End to end test failed");
        softAssert.assertAll();
    }
}
