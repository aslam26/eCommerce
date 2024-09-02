package com.Luma.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.rmi.server.ExportException;

/**
 * Create by Aslam Mujawar on 11/08/24
 */

public class ProductListPage extends BasePage{

    Actions actions;
    public ProductListPage(WebDriver driver) {
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
    WebElement addCartButton1;

    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    public WebElement successMessage;
    //div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']

    @FindBy(xpath = "(//a[normalize-space(text())='Montana Wind Jacket'])[2]")
    WebElement getProductName;

    @FindBy(xpath = "//span[@class='counter-number']")
    WebElement countNumber;

    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    public WebElement chooseOptionMessage;

    @FindBy(xpath = "(//div[@id='option-label-size-143-item-168'])[3]")
    WebElement oneMoreJacket;

    @FindBy(css = "li:nth-child(3) div button")
    WebElement addCartButton2;

    @FindBy(css="div#option-label-size-143-item-169")
    WebElement sizeXL;

    @FindBy(css="div#option-label-color-93-item-53")
    WebElement greenColor;

    @FindBy(css="input#qty")
    WebElement addQuantity;

    @FindBy(css="button#product-addtocart-button")
    WebElement productDetailPageAddToCartButton;

    @FindBy(xpath = "//span[@data-ui-id='page-title-wrapper']")
    WebElement titleOfProduct;


    public void navigateToProductPage(){

        actions.moveToElement(MenTab)
                .moveToElement(TopMenu)
                .moveToElement(JacketMenu)
                .perform();
        JacketMenu.click();
    }

    public void setAddtoCart(){
        MontanaWindJacketSize.click();
        MontanaWindJacketColour.click();
        actions.moveToElement(MontanaWindJacketColour).perform();
        addCartButton1.click();
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
            throw new NoSuchElementException("Unexpected ariaChecked attribute: "+ariaChecked);
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
            throw new NoSuchElementException("Unexpected ariaChecked attribute: "+ariaChecked);
        }
    }

    public void AddToCart() {
        actions.moveToElement(MontanaWindJacketColour).perform();
        addCartButton1.click();
    }

    public String getSuccessMessage(){
        return successMessage.getText();
    }

    public String myCartCount(){
        return countNumber.getText();
    }

    public void dontSelectAnyOptionsUnderProduct(){
        actions.moveToElement(oneMoreJacket).perform();
        addCartButton2.click();
    }

    public String optionSelectMessage(){
        return chooseOptionMessage.getText();
    }

    public boolean sizeSelectionAtProduct() throws Exception {
        sizeXL.click();
        String ariaChecked= sizeXL.getAttribute("aria-checked");
        if(ariaChecked.equals("true")){
            return true;
        }else if(ariaChecked.equals("false")){
            return false;
        }else {
            throw new Exception("unknown");
        }
    }

    public boolean colorSelectionAtProduct() throws ExportException {
        greenColor.click();
        String ariaChecked=greenColor.getAttribute("aria-checked");
        if(ariaChecked.equals("true")){
            return true;
        } else if (ariaChecked.equals("false")) {
            return false;
        }else {
            throw new ExportException("unknown");
        }
    }

    public void setQuantity(String Qty){
        addQuantity.clear();
        addQuantity.sendKeys(Qty);
    }

    public void clickAddToCartButton(){
        productDetailPageAddToCartButton.click();
    }

    public String getTitleOfProduct(){
        return titleOfProduct.getText();
    }

}
