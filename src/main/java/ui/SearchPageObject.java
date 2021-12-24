package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.locator.AndroidBy;
import ui.locator.IOSBy;
import ui.locator.Locator;

import java.util.List;

public class SearchPageObject  extends CorePageObject {

    private final Locator SEARCH_FIELD = new Locator(new IOSBy(By.id("Search Wikipedia")), new AndroidBy(By.id("search_src_text")));
    private final Locator SEARCH_RESULTS_LIST = new Locator(new AndroidBy(By.id("search_results_list")));
    private final Locator SEARCH_CLOSE_BUTTON = new Locator(new IOSBy(By.xpath("//XCUIElementTypeButton[@name=\"Cancel\"]")),
                                                            new AndroidBy(By.id("search_close_btn")));
    private final Locator SEARCH_EMPTY_LIST = new Locator(new AndroidBy(By.id("search_empty_container")));
    private final Locator QUIT_SEARCH_BUTTON = new Locator(new IOSBy(By.xpath("//XCUIElementTypeStaticText[@name=\"Cancel\"]")),
                                                           new AndroidBy(By.className("android.widget.ImageButton")));
    private Locator ARTICLE_TITLE = new Locator(new AndroidBy(By.id("page_list_item_title")));

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void sendKeysToSearch(String keys) {
        waitForElementPresentAndSendKeys(SEARCH_FIELD, keys);
    }

    public void assertSearchHasResults() {
        if (driver instanceof AndroidDriver) {
            waitForElementPresent(SEARCH_RESULTS_LIST);
        } else if (driver instanceof IOSDriver) {
            waitForElementPresent(ARTICLE_TITLE);
        }
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
        By by = getByFromLocator(ARTICLE_TITLE);
        searchResultList = driver.findElements(by);
        WebElement article = (WebElement) searchResultList.get(articleNumber);
        article.click();
        ArticlePageObject articlePage = new ArticlePageObject(driver);
        if (driver instanceof IOSDriver) {
            articlePage.setArticleTitle(article.getText());
        }
        return articlePage;
    }

    public void quitSearch() {
        waitForElementPresentAndClick(QUIT_SEARCH_BUTTON);
    }

    public void setIOSArticleTitle(String searchString) {
        ARTICLE_TITLE.setiOSBy(new IOSBy(By.xpath("//XCUIElementTypeStaticText[contains(@label, '" + searchString + "')]")));
    }
}
