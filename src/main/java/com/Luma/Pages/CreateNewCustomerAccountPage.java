package com.Luma.Pages;

import com.github.javafaker.Faker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Create by Aslam Mujawar on 11/08/24
 */

public class CreateNewCustomerAccountPage extends BasePage{
    public CreateNewCustomerAccountPage(WebDriver driver) {
        super(driver);
    }

    WebDriver driver;

    @FindBy(css = "input#firstname")
    WebElement addFirstName;
    @FindBy(css="input#lastname")
    WebElement addLastName;
    @FindBy(css="input#email_address")
    WebElement addEmailAddress;
    @FindBy(css= "input#password")
    WebElement addPassword;
    @FindBy(css = "input#password-confirmation")
    WebElement addPasswordConfirmation;
    @FindBy(xpath = "//button[@class='action submit primary']")
    WebElement createAccountButton;
    @FindBy(css="div#firstname-error")
    WebElement firstnameerror;
    @FindBy(css="div#lastname-error")
    WebElement lastnameerror;
    @FindBy(css="div#email_address-error")
    WebElement emailaddresserror;
    @FindBy(css = "div#password-error")
    WebElement passworderror;
    @FindBy(css="div#password-confirmation-error")
    WebElement passwordconfirmationerror;
    @FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Create an Account')]")
    WebElement createAccountLink;
    @FindBy(css="h1.page-title")
    WebElement pageTitle;
    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    WebElement successMessage;

    @FindBy(xpath = "//div[@class='panel header']//button[@class='action switch']")
    WebElement dropDown;

    public boolean clickingCreateAnAccountLink(){
        createAccountLink.click();
        String actualTitle=pageTitle.getText();
        return actualTitle.equals("Create New Customer Account");
    }

    public void enterDetailsToCreateAccount(String firstName,String lastname, String emailAddress, String password,String confirmPassword){
        addFirstName.sendKeys(firstName);
        addLastName.sendKeys(lastname);
        addEmailAddress.sendKeys(emailAddress);
        addPassword.sendKeys(password);
        addPasswordConfirmation.sendKeys(confirmPassword);
        createAccountButton.click();
    }

    public String getFirstNameErrorText(){
        return firstnameerror.getText();
    }
    public String getLastNameErrorText(){
        return lastnameerror.getText();
    }
    public String getEmailErrorText(){
        return emailaddresserror.getText();
    }
    public String getPasswordErrorText(){
        return passworderror.getText();
    }
    public String getConfirmPasswordText(){
        return passwordconfirmationerror.getText();
    }



    public void checkEmailAddress(String email) throws InterruptedException {
        addFirstName.sendKeys("oggy");
        addLastName.sendKeys("cartoon");
        addEmailAddress.clear();
        addEmailAddress.sendKeys(email);
        createAccountButton.click();
    }

    public String invalidEmailErrorDisplayed(){
        return emailaddresserror.getText();
    }

    public String getInvalidPasswordErrorMessage(){
        return passworderror.getText();
    }

    public String getInvalidConfirmPasswordErrorMessage(){
        return passwordconfirmationerror.getText();
    }

    public String checkForSuccessMessage() throws InterruptedException {
        return successMessage.getText();
    }


    public void clearFields(){
        addFirstName.clear();
        addLastName.clear();
        addEmailAddress.clear();
        addPassword.clear();
        addPasswordConfirmation.clear();
    }

}
