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
    String email;
    String referenceNumber;
    int index;
    @BeforeClass
    public WebDriver setup()
    {
        String userdir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", userdir + File.separator + "chromedriver.exe");

        return driver=new ChromeDriver();
    }
    public void scrollUntil(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
    }
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
    public void waitUntil(WebDriver driver, By element){
        WebDriverWait wait=new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

    }
}
