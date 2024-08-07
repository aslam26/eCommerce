package CommonLibs.Implementation;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

        pageLoadTime=60;
        elmentDisplayTime=20;
        currentWorkingDir=System.getProperty("user.dir");

        if(browserType.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",
                    currentWorkingDir+"/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.verboseLogging", "true");
            this.driver=new ChromeDriver();
        }else{
            throw new Exception("invalid browser: " + browserType);
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
