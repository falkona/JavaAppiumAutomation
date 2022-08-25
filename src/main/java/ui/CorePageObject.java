package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class CorePageObject {

    protected AppiumDriver driver;
    private static final int DEFAULT_TIMEOUT_IN_SECONDS = 5;

    protected CorePageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    protected void assertElementHasText(MobileElement element, String expectedValue, String errorMessage) {
        assertEquals(expectedValue, element.getText(), errorMessage);
    }

    protected WebElement waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT_IN_SECONDS);
        wait.withMessage("Элемент не найден: " + by.toString());

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void swipeLeft(MobileElement element, String errorMessage, int timeOfSwipe) {
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

    protected boolean waitForElementNotPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT_IN_SECONDS);
        wait.withMessage("Элемент найден: " + by.toString());
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}
