package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import ui.locator.AndroidBy;
import ui.locator.Locator;

public class SavedListsPageObject extends CorePageObject {

    private final Locator LIST_TITLE = new Locator(new AndroidBy(By.id("item_title")));

    public SavedListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    //for Android only
    public SavedArticlesPageObject openListOfSavedArticles() {
        waitForElementPresentAndClick(LIST_TITLE);

        return new SavedArticlesPageObject(driver);
    }

}
