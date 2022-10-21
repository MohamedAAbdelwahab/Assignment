import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {
    WebDriver driver;
    public SearchPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="result-stats")
    WebElement searchResults;
    @FindBy(css="a[aria-label='Page 2']")
    WebElement page2;
    @FindBy(css="a[aria-label='Page 3']")
    WebElement page3;
    @FindBy(css="input[value='software testing']")
    WebElement searchBar;
    List<WebElement> searchSuggestion;
    public boolean getResultNumbersElement()
    {
        return searchResults.isDisplayed();
    }
    public void NavigateToPage2()
    {
        page2.click();
    }
    public void NavigateToPage3()
    {
        page3.click();
    }
    public void scrollToSearchSuggestion()
    {
        searchSuggestion=driver.findElements(By.xpath("//*[@id='bres']/div/div/div/div/div[5]"));
        scrollToElementUsingJs(searchSuggestion.get(0));

    }
    public void scrollToElementUsingJs(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);

    }
    public boolean isSearchSuggestionVisible()
    {
        return searchSuggestion.size()>0;
    }
    public boolean isNumberOfResultsEqual()
    {
        String searchResultsTextInPage2=searchResults.getText();
        String numberOfResultsSecondPage=searchResultsTextInPage2.substring(16,searchResultsTextInPage2.indexOf("results"));
        scrollToElementUsingJs(page3);
        NavigateToPage3();
        String searchResultsTextInPage3=searchResults.getText();
        String numberOfResultsThirdPage=searchResultsTextInPage3.substring(16,searchResultsTextInPage3.indexOf("results"));
        return numberOfResultsSecondPage.equals(numberOfResultsThirdPage);
    }
    void clearSearchBar()
    {
        searchBar.clear();
    }
    void searchNewKeyword(String str)
    {
        searchBar.clear();
        searchBar.sendKeys(str);
        searchBar.sendKeys(Keys.ENTER);

    }
}
