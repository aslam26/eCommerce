package com.Luma.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Create by Aslam Mujawar on 08/08/24
 */

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }



        @FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
        WebElement customerLogin;

        @FindBy(css = "input#email")
        WebElement emailField;

        @FindBy(css = "[name='login[password]']")
        WebElement passwordField;

        @FindBy(xpath = "//fieldset[@class='fieldset login']//button[@id='send2']")
        WebElement sigInButton;

        @FindBy(tagName = "title>")
        WebElement titlePage;

        @FindBy(xpath = "//div[@class='panel header']//button[@class='action switch']")
        WebElement actionButton;

        @FindBy(xpath = "//div[@aria-hidden='false']//a[normalize-space()='Sign Out']")
        public WebElement signOutButton;

        @FindBy(css=":nth-child(7)")
        WebElement customerLoginTitle;

        @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
        public WebElement errorMessage;



    public void naviagteSignIn(){
        try { // Error handling come on Method not on elements
            customerLogin.click();
        }catch (NoSuchElementException e) {
            System.out.println("Element not found" + e.getMessage());
        }catch (Exception e){
            System.out.println("An error occured: "+e.getMessage());
        }

    }
    public boolean isSignInPageDisplayed(){
        try {
            return sigInButton.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }

    }

    public void performLogin(String username,String password){
        emailField.clear();
        emailField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        sigInButton.click();
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }
    public boolean isSignOutDisplayed() throws InterruptedException {
        actionButton.click();
        Thread.sleep(3000);
        return signOutButton.isDisplayed();
    }

}
