package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.locator.AndroidBy;
import ui.locator.Locator;

import java.util.List;

public class SavedArticlesPageObject extends CorePageObject {

    private final Locator ARTICLE_ITEM = new Locator(new AndroidBy(By.id("page_list_item_title")));
    private List searchResultList;

    public SavedArticlesPageObject(AppiumDriver driver) {
        super(driver);
    }

    //for Android only
    public void removeArticleFromList(int articleNumber) {
        waitForElementPresent(ARTICLE_ITEM);
        By by = getByFromLocator(ARTICLE_ITEM);
        searchResultList = driver.findElements(by);
        WebElement article = (WebElement) searchResultList.get(articleNumber);
        swipeLeft(article, "Не удалось выполнить свайп влево", 300);
    }

}
