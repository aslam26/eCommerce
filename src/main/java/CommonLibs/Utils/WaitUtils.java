package CommonLibs.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver,int timeOut){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));

    }

    public void waitUntilElementVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilTextVisible(WebElement element, String text){
        wait.until(ExpectedConditions.textToBePresentInElement(element,text));

    }

    public void waitUntilIsClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitUntilTextInElement(WebElement element, String textstart){
        wait.until(ExpectedConditions.textToBePresentInElement(element,textstart));
    }


}
