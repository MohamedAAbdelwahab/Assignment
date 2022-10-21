import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.sql.Driver;
import java.util.Random;

public class Utils {
    WebDriver driver;
    //the variables below are never used
    String email;
    String referenceNumber;
    int index;
    @BeforeClass
    public WebDriver setup()
    {
        //naming should be "userDir".. also you are
        String userdir = System.getProperty("user.dir");
        // Good job using the File.separator :)
        System.setProperty("webdriver.chrome.driver", userdir + File.separator + "chromedriver.exe");

        return driver=new ChromeDriver();
    }
    //scroll until what? .. use "scrollToElementUsingJs" instead to make it descriptive
    public void scrollUntil(WebElement element)
    {
        //never use driver actions in test classes
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
    }
    //This method is never used.. you can also make it a lot easier using regex or simply appending time to a static mail prefix
    public String randomEmailGenerator() { //copied from https://stackoverflow.com/questions/45841500/generate-random-emails
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
    //use "waitForElementToBeVisible" instead to make it descriptive
    //Also the method "visibilityOfElementLocated" from selenium has issues itself so just stay away from it
    //this method is never used
    public void waitUntil(WebDriver driver, By element){
        WebDriverWait wait=new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

    }
}
