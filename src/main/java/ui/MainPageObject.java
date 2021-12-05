package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPageObject extends CorePageObject {

    private final By SEARCH_FIELD = By.xpath("//*[contains(@text, 'Search Wikipedia')]");
    private final By SAVED_ITEMS = By.xpath("//android.widget.FrameLayout[@content-desc=\"Saved\"]");

    public MainPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void assertSearchFieldIsPresent() {
        assertElementHasText(SEARCH_FIELD, "Search Wikipedia", "Не найден текст: 'Search " +
                "Wikipedia'");
    }

    public SearchPageObject initSearch() {
        WebElement searchField = waitForElementPresent(SEARCH_FIELD);
        searchField.click();

        return new SearchPageObject(driver);
    }

    public SavedListsPageObject openSavedLists() {
        waitForElementPresentAndClick(SAVED_ITEMS);

        return new SavedListsPageObject(driver);
    }
}
