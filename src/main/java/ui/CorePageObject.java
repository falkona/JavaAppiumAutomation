package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.locator.Locator;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class CorePageObject {

    protected AppiumDriver driver;
    private static final int DEFAULT_TIMEOUT_IN_SECONDS = 5;

    protected CorePageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    protected void assertElementHasText(Locator locator, String expectedValue, String errorMessage) {
        WebElement element = waitForElementPresent(locator);
        assertEquals(expectedValue, element.getText(), errorMessage);
    }

    protected WebElement waitForElementPresent(Locator locator) {
        By by = getByFromLocator(locator);
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT_IN_SECONDS);
        wait.withMessage("Элемент не найден: " + by.toString());

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void waitForElementPresentAndClick(Locator locator) {
        WebElement element = waitForElementPresent(locator);
        element.click();
    }

    protected void waitForElementPresentAndSendKeys(Locator locator, String keys) {
        WebElement element = waitForElementPresent(locator);
        element.sendKeys(keys);
    }

    private void swipeLeft(Locator locator, String errorMessage, int timeOfSwipe) {
        WebElement element = waitForElementPresent(locator);
        swipeLeft(element, errorMessage, timeOfSwipe);
    }

    protected void swipeLeft(WebElement element, String errorMessage, int timeOfSwipe) {
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

    protected By getByFromLocator(Locator locator) {
        String platform = System.getenv("PLATFORM");
        By by = null;
        if (platform.equals("Android")) {
            by = locator.getAndroidBy().getBy();
        } else if (platform.equals("iOS")) {
            by = locator.getiOSBy().getBy();
        }
        return by;
    }

    protected boolean waitForElementNotPresent(Locator locator) {
        By by = getByFromLocator(locator);
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT_IN_SECONDS);
        wait.withMessage("Элемент найден: " + by.toString());
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}
