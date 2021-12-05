package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SavedArticlesPageObject extends CorePageObject {

    private final By ARTICLE_ITEM = By.id("page_list_item_title");
    private List searchResultList;

    public SavedArticlesPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void removeArticleFromList(int articleNumber) {
        waitForElementPresent(ARTICLE_ITEM);
        searchResultList = driver.findElements(ARTICLE_ITEM);
        WebElement article = (WebElement) searchResultList.get(articleNumber);
        swipeLeft(article, "Не удалось выполнить свайп влево", 300);
    }
}
