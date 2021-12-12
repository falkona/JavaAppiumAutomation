package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.locator.AndroidBy;
import ui.locator.IOSBy;
import ui.locator.Locator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArticlePageObject extends CorePageObject {

    private final Locator ADD_BOOKMARK = new Locator(new IOSBy(By.id("Save for later")), new AndroidBy(By.id("article_menu_bookmark")));
    private final Locator FRAGMENT_PAGE_COORDINATOR = new Locator(new AndroidBy(By.id("fragment_page_coordinator")));
    private final Locator NAVIGATE_UP = new Locator(new IOSBy(By.id("Back")),
                                                    new AndroidBy(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")));
    private final Locator TITLE = new Locator(new AndroidBy(By.id("title")));

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
        By by = getByFromLocator(TITLE);
        WebElement title = driver.findElement(by);
        assertTrue(title.isDisplayed(), "Заголовок статьи не обнаружен");
    }

}
