package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.locator.AndroidBy;
import ui.locator.IOSBy;
import ui.locator.Locator;

public class OnboardingPageObject extends CorePageObject {

    private final Locator SKIP = new Locator(new IOSBy(By.xpath("//XCUIElementTypeButton[@name=\"Skip\"]")),
                                             new AndroidBy(By.id("fragment_onboarding_skip_button")));
    private final Locator SEARCH = new Locator(new IOSBy(By.id("Search Wikipedia")), new AndroidBy(By.id("search_container")));

    public OnboardingPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void skipWikipediaOnboarding() {
        try {
            WebElement skipButton = waitForElementPresent(SKIP);
            skipButton.click();
            waitForElementPresent(SEARCH);
        } catch (Exception e) {
            System.out.println("Кнопка Skip (онбординг) не обнаружена");
        }
    }

}
