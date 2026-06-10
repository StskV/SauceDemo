package tests;

import io.qameta.allure.testng.AllureTestNg;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import steps.CartStep;
import steps.PurchaseStep;
import utils.PropertyReader;
import java.util.HashMap;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    protected WebDriver driver;
    protected CartStep cartStep;
    protected PurchaseStep purchaseStep;
    protected String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod(
            alwaysRun = true,
            description = "Настройка драйвера"
    )
    public void setUp(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--headless");

            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            driver = new EdgeDriver(options);
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
        }

        context.setAttribute("driver", driver);

        cartStep = new CartStep(driver);
        purchaseStep = new PurchaseStep(driver);
    }

    @AfterMethod(
            alwaysRun = true,
            description = "Закрытие браузера"
    )
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}