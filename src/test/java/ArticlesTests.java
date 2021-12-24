import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ArticlePageObject;
import ui.MainPageObject;
import ui.OnboardingPageObject;
import ui.SavedArticlesPageObject;
import ui.SavedListsPageObject;
import ui.SearchPageObject;

import java.util.ArrayList;
import java.util.List;

public class ArticlesTests extends CoreTestCase {
    private String searchLine = "Harry Potter";

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();

        OnboardingPageObject onboardingPage = new OnboardingPageObject(driver);
        onboardingPage.skipWikipediaOnboarding();
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void saveTwoArticlesTest() {
        if (driver instanceof AndroidDriver) {
            MainPageObject mainPage = new MainPageObject(driver);
            SearchPageObject searchPage = mainPage.initSearch();
            searchPage.sendKeysToSearch(searchLine);
            searchPage.assertSearchHasResults();
            for (int i = 0; i < 2; i++) {
                ArticlePageObject articlePage = searchPage.openArticleFromSearch(i);
                articlePage.saveArticleToDefaultSavedList();
                articlePage.returnToPreviousPage();
            }
            searchPage.quitSearch();
            SavedListsPageObject savedLists = mainPage.openSavedLists();
            SavedArticlesPageObject savedArticlesPage = savedLists.openListOfSavedArticles();
            savedArticlesPage.removeArticleFromList(0);
        } else if (driver instanceof IOSDriver) {
            MainPageObject mainPage = new MainPageObject(driver);
            SearchPageObject searchPage = mainPage.initSearch();
            searchPage.setIOSArticleTitle(searchLine);
            searchPage.sendKeysToSearch(searchLine);
            searchPage.assertSearchHasResults();
            List<String> savedArticles = new ArrayList();
            for (int i = 0; i < 2; i++) {
                ArticlePageObject articlePage = searchPage.openArticleFromSearch(i);
                articlePage.saveArticleToDefaultSavedList();
                savedArticles.add(articlePage.getArticleTitle());
                articlePage.returnToPreviousPage();
            }
            searchPage.quitSearch();
            SavedArticlesPageObject savedArticlesPage = mainPage.openSavedArticles();
            savedArticlesPage.skipSyncSavedArticles();
            savedArticlesPage.removeArticleFromList(savedArticles.get(0));
            savedArticles.remove(0);
            savedArticlesPage.checkArticlesPresent(savedArticles);
        }
    }

    @Test
    public void titleIsPresentTest() {
        MainPageObject mainPage = new MainPageObject(driver);
        SearchPageObject searchPage = mainPage.initSearch();
        searchPage.sendKeysToSearch(searchLine);
        ArticlePageObject articlePage = searchPage.openArticleFromSearch(0);
        articlePage.assertTitleIsPresentNoWait();
    }

}
