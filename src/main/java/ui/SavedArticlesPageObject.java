package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.locator.AndroidBy;
import ui.locator.IOSBy;
import ui.locator.Locator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SavedArticlesPageObject extends CorePageObject {

    private Locator ARTICLE_ITEM = new Locator(new AndroidBy(By.id("page_list_item_title")));
    private final Locator CLOSE_SYNC_ALERT = new Locator(new IOSBy(By.id("Close")));
    private final Locator SWIPE_ACTION_DELETE = new Locator(new IOSBy(By.id("swipe action delete")));

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

    //for iOS only
    public void removeArticleFromList(String articleTitle) {
        ARTICLE_ITEM.setiOSBy(new IOSBy(By.id(articleTitle)));
        waitForElementPresent(ARTICLE_ITEM);
        WebElement article = driver.findElement(ARTICLE_ITEM.getiOSBy().getBy());
        swipeLeft(article, "Не удалось выполнить свайп влево", 300);
        waitForElementPresentAndClick(SWIPE_ACTION_DELETE);
        waitForElementNotPresent(ARTICLE_ITEM);
    }

    public void skipSyncSavedArticles() {
        try {
            waitForElementPresentAndClick(CLOSE_SYNC_ALERT);
        } catch (Exception e) {
            System.out.println("Не обнаружен алерт 'Sync your saved articles?'");
        }
    }

    public void checkArticlesPresent(List<String> articleTitles) {
        for (String articleTitle : articleTitles) {
            ARTICLE_ITEM.setiOSBy(new IOSBy(By.id(articleTitle)));
            waitForElementPresent(ARTICLE_ITEM);
        }
    }

    public void checkArticlesDeleted(List<String> articleTitles) {
        for (String articleTitle : articleTitles) {
            ARTICLE_ITEM.setiOSBy(new IOSBy(By.id(articleTitle)));
            waitForElementPresent(ARTICLE_ITEM);
        }
    }

}
