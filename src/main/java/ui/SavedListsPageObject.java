package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SavedListsPageObject extends CorePageObject {

    private final By LIST_TITLE = By.id("item_title");

    public SavedListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public SavedArticlesPageObject openListOfSavedArticles() {
        waitForElementPresentAndClick(LIST_TITLE);

        return new SavedArticlesPageObject(driver);
    }

}
