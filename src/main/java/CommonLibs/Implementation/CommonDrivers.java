package CommonLibs.Implementation;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class CommonDrivers {


    private WebDriver driver;
    int pageLoadTime;
    int elmentDisplayTime;
    String currentWorkingDir;



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
        currentWorkingDir = System.getProperty("user.dir");

      /* if (browserType.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    currentWorkingDir + "/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.verboseLogging", "true");
            this.driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver",
                    currentWorkingDir + "/drivers/msedgedriver.exe");
            System.setProperty("webdriver.edge.verboseLogging","true");
            this.driver = new EdgeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.firefox.driver",
                    currentWorkingDir + "/drivers/geckodriver.exe");
            this.driver=new FirefoxDriver();
        } else
            throw new Exception("invalid browser: " + browserType);*/

       if(browserType.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            this.driver=new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("chromeHeadless")) {
           WebDriverManager.chromedriver().setup();
           ChromeOptions option=new ChromeOptions();
           option.addArguments("--headless");
           option.addArguments("--disable-gpu");
           option.addArguments("--window-size=1920,1000");
           driver=new ChromeDriver(option);
       } else if (browserType.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            this.driver=new EdgeDriver();
        }else {
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
