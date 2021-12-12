import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ArticlePageObject;
import ui.MainPageObject;
import ui.OnboardingPageObject;
import ui.SavedArticlesPageObject;
import ui.SavedListsPageObject;
import ui.SearchPageObject;

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
