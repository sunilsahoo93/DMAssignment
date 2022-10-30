package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class InitializationHooks {

    public static Scenario sc;

    public static WebDriver driver;

    @Before
    public static void setBrowserDriver(Scenario scenario) {
        if(scenario.getSourceTagNames().contains("@API")){
            return;
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void closeBrowser(Scenario scenario) {
        if(scenario.getSourceTagNames().contains("@API")){
            return;
        }
        try {
            driver.close();
            System.out.println("Website closed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
