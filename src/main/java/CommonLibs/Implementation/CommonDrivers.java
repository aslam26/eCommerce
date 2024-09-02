package CommonLibs.Implementation;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CommonDrivers {


    private WebDriver driver;
    int pageLoadTime;
    int elmentDisplayTime;


    public WebDriver getDriver() {
        return driver;
    }

    public void setPageLoadTime(int pageLoadTime) {
        this.pageLoadTime = pageLoadTime;
    }

    public void setElmentDisplayTime(int elmentDisplayTime) {
        this.elmentDisplayTime = elmentDisplayTime;
    }

    public CommonDrivers(String browserType) throws Exception {

        pageLoadTime = 60;
        elmentDisplayTime = 20;

       if(browserType.equalsIgnoreCase("chrome")){
           //The primary feature of Selenium Manager is called automated driver management,
           //Selenium Manager automatically discovers, downloads, and caches the browsers driven with Selenium (Chrome, Firefox, and Edge) when these browsers are not installed in the local system.
            WebDriverManager.chromedriver().setup();
            this.driver=new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("chromeHeadless")) {
           WebDriverManager.chromedriver().setup();
           ChromeOptions option=new ChromeOptions();
           option.addArguments("--headless");
           option.addArguments("--window-size=1920,1080"); // Set a fixed window size
           option.addArguments("--disable-gpu"); // Applicable to Chrome, may not be needed for other browsers
           option.addArguments("--disable-extensions");
           option.addArguments("--no-sandbox");
           option.addArguments("--disable-dev-shm-usage");
           this.driver=new ChromeDriver(option);
       } else if (browserType.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            this.driver=new EdgeDriver();
        } else if(browserType.equalsIgnoreCase("edgeHeadless")){
           WebDriverManager.edgedriver().setup();
           EdgeOptions options=new EdgeOptions();
           options.addArguments("--headless");
           options.addArguments("--disable-gpu");
          // options.addArguments("--window-size=1920,1080");
           this.driver=new EdgeDriver(options);
       } else if (browserType.equalsIgnoreCase("FirefoxHeadless")) {
           WebDriverManager.firefoxdriver().setup();
           FirefoxOptions option=new FirefoxOptions();
           option.addArguments("---headless");
           option.addArguments("--disable-gpu");
           option.addArguments("--window-size=1920,1080");
           this.driver=new FirefoxDriver(option);
       } else {
            throw new Exception("Invalid Browser: "+browserType);
        }


        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    public void navigateToURl(String url){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTime));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(elmentDisplayTime));
        driver.get(url);
    }


    public void closeAllBrowser(){
       if(driver != null) {
           driver.quit();
       }
    }



}
