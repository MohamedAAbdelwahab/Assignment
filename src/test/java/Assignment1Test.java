import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Assignment1Test extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    @Test(priority = 1,description = ("Opening google.com and searching for 'Software Testing '"))
     public  void openGoogleAndSearch()  {
        HomePage page=new HomePage(driver);
        page.openGoogle();
        try
        {
            page.switchToEnglishLang();
        }
        catch(Exception e)
        {
            logger.log(Level.FINE,"No Element Or the page is already in english language");
        }
        page.enterSearchKeyword();
        page.clickOnSearch();

    }
    @Test(priority = 2,description = "Searching for 'Software Testing new' ")
    public void removeKeywordAndSearch()
    {
        SearchPage searchPage=new SearchPage(driver);
        searchPage.clearSearchBar();
        searchPage.searchNewKeyword("Software Testing new");
        Assert.assertTrue(searchPage.getResultNumbersElement()); //Assert that number of results exist on UI
        searchPage.NavigateToPage2(); //Scroll down and go to the next page
        softAssert.assertTrue(searchPage.isNumberOfResultsEqual(),"Number of results on page 2 not equal on page 3"); //Validate if the number of results on page 2 is equal to page 3 or not
        searchPage.scrollToSearchSuggestion();
        Assert.assertTrue(searchPage.isSearchSuggestionVisible()); //Validate there are different search suggestions displayed at the end of the page
    }

}
