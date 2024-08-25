package com.Luma.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class MiniCartPage extends BasePage{

    public MiniCartPage(WebDriver driver) {
        super(driver);
    }

    //locators
    @FindBy(xpath = "//div[@class='minicart-wrapper']")
    public WebElement cartButton;

    @FindBy (xpath="//a[@class='action showcart active']")
     public WebElement miniCartDialog;

    @FindBy(xpath = "//a[@class='action showcart']")
    public WebElement miniCartDialogClosed;

    @FindBy(xpath = "//a[@class='action edit']")
    WebElement editItem;

    @FindBy(xpath = "//span[normalize-space(text())='View and Edit Cart']")
    WebElement viewAndEditCartLink;

    @FindBy(css="button#top-cart-btn-checkout")
    public WebElement topCartProceedToCheckOutButton;

    @FindBy(css = "button#btn-minicart-close")
    WebElement minCartCloseButton;

    @FindBy(xpath = "(//a[@class='action delete' and @title='Remove item'])[1]")
    public WebElement removeItemButton;

    @FindBy(css="button#product-updatecart-button")
    public WebElement updateCartButton;

    @FindBy(xpath = "//span[normalize-space(text())='Shopping Cart']")
    WebElement shoppingCarTitleOfPage;

    @FindBy(xpath = "//div[normalize-space(text())='Shipping Address']")
    public WebElement shippingAddressTitleOfPage;

    @FindBy(xpath = "//button[@class='action-primary action-accept']")
    public WebElement modelOkButton;

    @FindBy(xpath = "//button[@class='action-secondary action-dismiss']")
    WebElement modelCancelButton;

    @FindBy(xpath = "//p[normalize-space(text())='You have no items in your shopping cart.']")
    WebElement itemDeletedMessage;

    @FindBy(css = "button#btn-minicart-close")
    WebElement alertCloseButton;

    @FindBy(xpath = "//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front mage-dropdown-dialog']")
    WebElement alertPopupClosed;

    @FindBy(xpath = "//strong[@class='subtitle empty']")
    WebElement deletedAlertMessage;


    @FindBy(xpath = "(//aside[@data-type='popup'])[1]")
    WebElement cancelRemoveItemOverlay;


    public void clickMiniCart(){
        cartButton.click();
    }

    public boolean openMiniCart() throws Exception {
        String miniAttributeClass = miniCartDialog.getAttribute("class");
        if (miniAttributeClass.equals("action showcart active")) {
            return true;
        } else if (miniAttributeClass.equals("action showcart")){
            return false;
        }else {
            throw new NoSuchElementException("Unexpected class attribute: "+miniAttributeClass);
        }
    }

    public boolean closeMiniCart() throws Exception {
        minCartCloseButton.click();
        String minAttributeClass = miniCartDialogClosed.getAttribute("class");
        if(minAttributeClass.equals("action showcart")){
            return true;
        }else if (minAttributeClass.equals("action showcart active")){
            return false;
        }else {
            throw new NoSuchElementException("Unexpected class attribute: "+minAttributeClass);
        }

    }

    public void setEditItem(){
        editItem.click();
    }

    public void setViewAndEditCartLink(){
        viewAndEditCartLink.click();
    }

    public String getTitleOfShoppingCartPage(){
        return shoppingCarTitleOfPage.getText();
    }

    public void setTopCartProceedToCheckOutButton(){
        topCartProceedToCheckOutButton.click();
    }

    public String getTitleOfShoppingAddressPage(){
        return shippingAddressTitleOfPage.getText();
    }

    public void removeItem(){
        removeItemButton.click();
    }
    public void deleteItemConfirmation(){
        modelOkButton.click();
    }

    public boolean cancelModel() {
        try {
            modelCancelButton.click();

            //Check if model overlay is displayed or not, after cancel button clicked.
            if(cancelRemoveItemOverlay.isDisplayed()){
                return false; //Model was not closed.
            }else {
                return true; //Model is closed.
            }
        }catch (NoSuchElementException e){
            return true; //Model is closed.
        }

    }


    public String getItemDeletedMessage(){
        return itemDeletedMessage.getText();
    }


    public boolean closeAlert() throws Exception {
        alertCloseButton.click();
        String minAttributeClass = miniCartDialogClosed.getAttribute("class");
        if(minAttributeClass.equals("action showcart")){
            return true;
        }else if (minAttributeClass.equals("action showcart active")){
            return false;
        }else {
            throw new NoSuchElementException("Unexpected class attribute: "+minAttributeClass);
        }

    }


}
