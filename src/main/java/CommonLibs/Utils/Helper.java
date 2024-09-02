package CommonLibs.Utils;

import com.Luma.Pages.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Helper extends BasePage {

    public Helper(WebDriver driver) {
        super(driver);
    }
    WebDriver driver;

    @FindBy(xpath = "//div[@class='ea-content']")
    WebElement alertPop;
    @FindBy(xpath = "//div[@class='ea-stickybox-hide']")
    WebElement closeAdv;

    public void closePopUp(){
       if(alertPop.isDisplayed()){
           closeAdv.click();
       }

    }

    public static void enterText (final WebElement element, final String text) {
        element.click ();
        element.clear ();
        element.sendKeys (text);
    }

    public  void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
