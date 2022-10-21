import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    WebElement searchBar;
    HomePage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }
    void switchToEnglishLang()
    {
        driver.findElement(By.xpath("//*[text()='English']")).click();

    }
    void openGoogle()
    {
        driver.get("https://www.google.com/");

    }
    void enterSearchKeyword()
    {
        searchBar =driver.findElement(By.cssSelector("input[title='Search'"));
        searchBar.clear();
        searchBar.sendKeys("software testing");
    }

    void clickOnSearch()
    {
        searchBar.sendKeys(Keys.ENTER);

    }
}
