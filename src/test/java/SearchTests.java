import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.MainPageObject;
import ui.OnboardingPageObject;
import ui.SearchPageObject;

public class SearchTests extends CoreTestCase {
    private String searchLine = "Harry Potter";

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();

        OnboardingPageObject onboardingPage = new OnboardingPageObject(driver);
        onboardingPage.skipWikipediaOnboarding();
    }

    @AfterEach
    @Override
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void searchWikipediaTest() {
        MainPageObject mainPage = new MainPageObject(driver);
        mainPage.assertSearchFieldIsPresent();
    }

    @Test
    public void cancelSearchTest() {
        MainPageObject mainPage = new MainPageObject(driver);
        SearchPageObject searchPage = mainPage.initSearch();
        searchPage.sendKeysToSearch(searchLine);
        searchPage.assertSearchHasResults();
        searchPage.cancelSearch();
        searchPage.assertSearchIsEmpty();
    }

}
