package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArticlePageObject extends CorePageObject {

    private final By ADD_BOOKMARK = By.id("article_menu_bookmark");
    private final By FRAGMENT_PAGE_COORDINATOR = By.id("fragment_page_coordinator");
    private final By NAVIGATE_UP = By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]");
    private final By TITLE = By.id("title");

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void saveArticleToDefaultSavedList() {
        waitForElementPresentAndClick(ADD_BOOKMARK);
        waitForElementPresent(FRAGMENT_PAGE_COORDINATOR);
    }

    public void returnToPreviousPage() {
        waitForElementPresentAndClick(NAVIGATE_UP);
    }

    public void assertTitleIsPresentNoWait() {
        WebElement title = driver.findElement(TITLE);
        assertTrue(title.isDisplayed(), "Заголовок статьи не обнаружен");
    }
}
