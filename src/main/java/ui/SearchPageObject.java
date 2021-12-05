package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject  extends CorePageObject {

    private final By SEARCH_FIELD_BY = By.id("search_src_text");
    private final By SEARCH_RESULTS_LIST = By.id("search_results_list");
    private final By SEARCH_CLOSE_BUTTON = By.id("search_close_btn");
    private final By SEARCH_EMPTY_LIST = By.id("search_empty_container");
    private final By ARTICLE_TITLE = By.id("page_list_item_title");
    private final By QUIT_SEARCH_BUTTON = By.className("android.widget.ImageButton");

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void sendKeysToSearch(String keys) {
        waitForElementPresentAndSendKeys(SEARCH_FIELD_BY, keys);
    }

    public void assertSearchHasResults() {
        waitForElementPresent(SEARCH_RESULTS_LIST);
    }

    public void assertSearchIsEmpty() {
        waitForElementPresent(SEARCH_EMPTY_LIST);
    }

    public void cancelSearch() {
        WebElement searchCloseButton = waitForElementPresent(SEARCH_CLOSE_BUTTON);
        searchCloseButton.click();
    }

    public ArticlePageObject openArticleFromSearch(int articleNumber) {
        List searchResultList;

        waitForElementPresent(ARTICLE_TITLE);
        searchResultList = driver.findElements(ARTICLE_TITLE);
        WebElement article = (WebElement) searchResultList.get(articleNumber);
        article.click();

        return new ArticlePageObject(driver);
    }

    public void quitSearch() {
        waitForElementPresentAndClick(QUIT_SEARCH_BUTTON);
    }
}
