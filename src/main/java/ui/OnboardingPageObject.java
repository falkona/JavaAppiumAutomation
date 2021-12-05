package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OnboardingPageObject extends CorePageObject {

    public OnboardingPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void skipWikipediaOnboarding() {
        try {
            WebElement skipButton = waitForElementPresent(By.id("fragment_onboarding_skip_button"));
            skipButton.click();
            waitForElementPresent(By.id("search_container"));
        } catch (Exception e) {
            System.out.println("Кнопка Skip (онбординг) не обнаружена");
        }
    }

}
