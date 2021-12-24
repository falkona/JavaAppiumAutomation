package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.locator.AndroidBy;
import ui.locator.IOSBy;
import ui.locator.Locator;

public class MainPageObject extends CorePageObject {

    private final Locator SEARCH = new Locator(new IOSBy(By.id("Search Wikipedia")),
                                               new AndroidBy(By.xpath("//*[contains(@text, 'Search Wikipedia')]")));
    private final Locator SAVED_ITEMS = new Locator(new IOSBy(By.id("Saved")),
                                                    new AndroidBy(By.xpath("//android.widget.FrameLayout[@content-desc=\"Saved" + "\"]")));

    public MainPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void assertSearchFieldIsPresent() {
        assertElementHasText(SEARCH, "Search Wikipedia", "Не найден текст: 'Search " +
                "Wikipedia'");
    }

    public SearchPageObject initSearch() {
        WebElement searchField = waitForElementPresent(SEARCH);
        searchField.click();

        return new SearchPageObject(driver);
    }

    public SavedListsPageObject openSavedLists() {
        waitForElementPresentAndClick(SAVED_ITEMS);
        return new SavedListsPageObject(driver);
    }

    public SavedArticlesPageObject openSavedArticles() {
        waitForElementPresentAndClick(SAVED_ITEMS);
        return new SavedArticlesPageObject(driver);
    }
}
