import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest {
    Logger logger=Logger.getLogger("Logger for assignment");

    WebDriver driver;
    Utils util=new Utils();

    @AfterClass
    public void cleanUP()
    {
        driver.close(); //Close the browser window
    }

    @BeforeClass
    public void setup()
    {
        driver=util.setup();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        logger.setLevel(Level.FINE);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    }

}
