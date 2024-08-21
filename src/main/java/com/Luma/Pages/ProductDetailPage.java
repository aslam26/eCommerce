package com.Luma.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Create by Aslam Mujawar on 11/08/24
 */

public class ProductDetailPage extends BasePage{

    Actions actions;
    public ProductDetailPage(WebDriver driver) {
        super(driver);
        actions=new Actions(driver);
    }


    @FindBy(css="a#ui-id-5")
    WebElement MenTab;

    @FindBy(css="a#ui-id-17")
    WebElement TopMenu;

    @FindBy(css="a#ui-id-19")
    WebElement JacketMenu;

    @FindBy(css=":nth-child(3) strong.modes-mode.active.mode-list")
    WebElement listView;

    @FindBy(xpath = "(//div[@id='option-label-size-143-item-168'])[2]")
    WebElement MontanaWindJacketSize;

    @FindBy(xpath = "(//div[@id='option-label-color-93-item-49'])[2]")
    WebElement MontanaWindJacketColour;

    @FindBy(css = "li:nth-child(2) div button")
    WebElement addCartButton;

    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    public WebElement successMessage;

    @FindBy(xpath = "(//a[normalize-space(text())='Montana Wind Jacket'])[2]")
    WebElement getProductName;



    // WebElement montanaWindJacket=driver.findElement(By.cssSelector("li:nth-child(2) div > strong"));

    //WebElement taurusJacket=driver.findElement(RelativeLocator.with(By.cssSelector("ol > li div > strong > a")).below(montanaWindJacket));

  /*  private WebElement getMontanaWindJacket() {
        return driver.findElement(By.cssSelector("li:nth-child(2) div > strong"));
    }

    private WebElement getTaurusJacket() {
        return driver.findElement(RelativeLocator.with(By.cssSelector("ol > li div > strong > a")).below(getMontanaWindJacket()));
    }*/


    public void navigateToProductPage(){

        actions.moveToElement(MenTab)
                .moveToElement(TopMenu)
                .moveToElement(JacketMenu)
                .perform();
        JacketMenu.click();
    }


    public String getSelectProductName(){
        return getProductName.getText();
    }


    public boolean sizeSelection() throws Exception {
        MontanaWindJacketSize.click();
        String ariaChecked=MontanaWindJacketSize.getAttribute("aria-checked");
        if(ariaChecked.equals("true")){
            return true;
        } else if (ariaChecked.equals("false")) {
            return false;
        }else {
            throw new Exception("unknown");
        }
    }

    public boolean colorSelection() throws Exception {
        MontanaWindJacketColour.click();
        String ariaChecked=MontanaWindJacketColour.getAttribute("aria-checked");
        if(ariaChecked.equals("true")){
            return true;
        }else if (ariaChecked.equals("false")){
            return false;
        }else{
            throw new Exception("Unknown");
        }
    }

    public void AddToCart() {
        actions.moveToElement(MontanaWindJacketColour).perform();
        addCartButton.click();
    }

    public String getSuccessMessage(){
        return successMessage.getText();
    }

}
