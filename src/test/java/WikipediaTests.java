import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WikipediaTests {

    private AppiumDriver driver;
    private int defaultTimeoutInSeconds = 5;

    @BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("deviceName", "abm_design");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/darianos/Documents/JavaAppiumAutomation/apks/wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        skipWikipediaOnboarding();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchWikipediaTest() {
        assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Search Wikipedia", "Не найден текст: 'Search " +
                "Wikipedia'");
    }

    private void assertElementHasText(By by, String expectedValue, String errorMessage) {
        WebElement element = waitForElementPresent(by);
        assertEquals(expectedValue, element.getText(), errorMessage);
    }

    private WebElement waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeoutInSeconds);
        wait.withMessage("Элемент не найден: " + by.toString());

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void skipWikipediaOnboarding() {
        WebElement skipButton = waitForElementPresent(By.id("fragment_onboarding_skip_button"));
        skipButton.click();
        waitForElementPresent(By.id("search_container"));
    }

}
