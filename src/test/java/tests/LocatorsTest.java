package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.LoginPage;

@Test(
        enabled = false
)
public class LocatorsTest extends BaseTest {
    public void checkLocators() {
        new LoginPage(driver)
                .open()
                .loginWithValidCreds(user, password);
        driver.findElement(By.className("submit-button")).click();
        driver.findElement(By.tagName("footer"));
        driver.findElement(By.linkText("Sauce Labs Backpack"));
        driver.findElement(By.partialLinkText("Bike Light"));
        driver.findElement(By.xpath("//*[@id='react-burger-menu-btn']"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));
        driver.findElement(By.xpath("//span[contains(@data-test,'title')]"));
        driver.findElement(By.xpath("//div[contains(text(),'Onesie')]"));
        driver.findElement(By.xpath("//*[text()='Products']//ancestor::div[@class='header_secondary_container']"));
        driver.findElement(By.xpath("//a[@id='item_4_img_link']//descendant::img"));
        driver.findElement(By.xpath("//*[text()='Swag Labs']//following::button[@id='react-burger-menu-btn']"));
        driver.findElement(By.xpath("//*[text()='2026']//parent::div"));
        driver.findElement(By.xpath("//div[@class='inventory_item_price']/preceding::button"));
        driver.findElement(By.xpath("//span[@class='title' and @data-test='title']"));
        driver.findElement(By.cssSelector(".app_logo"));
        driver.findElement(By.cssSelector(".btn.btn_primary"));
        driver.findElement(By.cssSelector(".footer .social_twitter"));
        driver.findElement(By.cssSelector("#item_4_title_link"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("div.header_secondary_container"));
        driver.findElement(By.cssSelector("[data-test=\"add-to-cart-sauce-labs-backpack\"]"));
        driver.findElement(By.cssSelector("[class~='btn']"));
        driver.findElement(By.cssSelector("[data-test|=social]"));
        driver.findElement(By.cssSelector("[data-test^=shopping]"));
        driver.findElement(By.cssSelector("[data-test$=price]"));
        driver.findElement(By.cssSelector("[data-test*=\"facebook\"]"));
    }
}
