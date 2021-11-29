import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WikipediaTests {

    private AppiumDriver driver;
    private int defaultTimeoutInSeconds = 5;
    private By searchFieldMainPageBy = By.xpath("//*[contains(@text, 'Search Wikipedia')]");
    private By searchFieldBy = By.id("search_src_text");
    private String searchLine = "Harry Potter";

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
        assertElementHasText(searchFieldMainPageBy, "Search Wikipedia", "Не найден текст: 'Search " +
                "Wikipedia'");
    }

    @Test
    public void cancelSearchTest() {
        WebElement searchMainPage = waitForElementPresent(searchFieldMainPageBy);
        searchMainPage.click();
        WebElement search = waitForElementPresent(searchFieldBy);
        search.sendKeys(searchLine);
        WebElement searchResult = waitForElementPresent(By.id("search_results_list"));
        WebElement searchCloseButton = waitForElementPresent(By.id("search_close_btn"));
        searchCloseButton.click();
        WebElement searchEmptyContainer = waitForElementPresent(By.id("search_empty_container"));
    }

    @Test
    public void saveTwoArticlesTest() {
        List searchResultList;

        waitForElementPresentAndClick(searchFieldMainPageBy);
        waitForElementPresentAndSendKeys(searchFieldBy, searchLine);
        for (int i = 0; i < 2; i++) {
            waitForElementPresent(By.id("page_list_item_title"));
            searchResultList = driver.findElements(By.id("page_list_item_title"));
            WebElement article = (WebElement) searchResultList.get(i);
            article.click();
            waitForElementPresentAndClick(By.id("article_menu_bookmark"));
            waitForElementPresent(By.id("fragment_page_coordinator"));
            waitForElementPresentAndClick(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));
        }
        waitForElementPresentAndClick(By.className("android.widget.ImageButton"));
        waitForElementPresentAndClick(By.xpath("//android.widget.FrameLayout[@content-desc=\"Saved\"]"));
        waitForElementPresentAndClick(By.id("item_title"));
        waitForElementPresent(By.id("page_list_item_title"));
        searchResultList = driver.findElements(By.id("page_list_item_title"));
        WebElement article = (WebElement) searchResultList.get(0);
        swipeLeft(article, "Не удалось выполнить свайп влево", 300);
    }

    @Test
    public void titleIsPresentTest() {
        waitForElementPresentAndClick(searchFieldMainPageBy);
        waitForElementPresentAndSendKeys(searchFieldBy, searchLine);
        waitForElementPresentAndClick(By.id("page_list_item_title"));
        WebElement title = driver.findElement(By.id("title"));
        assertTrue(title.isDisplayed(), "Заголовок статьи не обнаружен");
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

    private void waitForElementPresentAndClick(By by) {
        WebElement element = waitForElementPresent(by);
        element.click();
    }

    private void waitForElementPresentAndSendKeys(By by, String keys) {
        WebElement element = waitForElementPresent(by);
        element.sendKeys(keys);
    }

    private void swipeLeft(By by, String errorMessage, int timeOfSwipe) {
        WebElement element = waitForElementPresent(by);
        swipeLeft(element, errorMessage, timeOfSwipe);
    }

    private void swipeLeft(WebElement element, String errorMessage, int timeOfSwipe) {
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(rightX, middleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(leftX, middleY))
                .release().perform();
    }
}
