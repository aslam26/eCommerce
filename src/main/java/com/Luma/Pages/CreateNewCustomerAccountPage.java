package com.Luma.Pages;

import com.github.javafaker.Faker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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

    public void checkMandatoryField(){

        if(addFirstName.getAttribute("value").isEmpty()){
            Assert.assertTrue(firstnameerror.isDisplayed());
            Assert.assertEquals(firstnameerror.getText(),"This is a required field.");
        }
        if(addLastName.getAttribute("value").isEmpty()){
            Assert.assertTrue(lastnameerror.isDisplayed());
            Assert.assertEquals(firstnameerror.getText(),"This is a required field.");
        }
        if(addEmailAddress.getAttribute("value").isEmpty()){
            Assert.assertTrue(emailaddresserror.isDisplayed());
            Assert.assertEquals(emailaddresserror.getText(),"This is a required field.");
        }
        if(addPassword.getAttribute("value").isEmpty()){
            Assert.assertTrue(passworderror.isDisplayed());
            Assert.assertEquals(passworderror.getText(),"This is a required field.");
        }
        if(addPasswordConfirmation.getAttribute("value").isEmpty()){
            Assert.assertTrue(passwordconfirmationerror.isDisplayed());
            Assert.assertEquals(passwordconfirmationerror.getText(),"This is a required field.");
        }
    }

    public void checkEmailAddress(String email) throws InterruptedException {
        addFirstName.sendKeys("joe");
        addLastName.sendKeys("pio");
        addEmailAddress.clear();
        addEmailAddress.sendKeys(email);
        createAccountButton.click();
        Assert.assertTrue(emailaddresserror.isDisplayed());
        Assert.assertEquals(emailaddresserror.getText(),"Please enter a valid email address (Ex: johndoe@domain.com).");
    }

    public void checkPassword(String password,String confirPassword, String expectedError){
        addPassword.clear();
        addPassword.sendKeys(password);
        addPasswordConfirmation.clear();
        addPasswordConfirmation.sendKeys(confirPassword);
        createAccountButton.click();
        if(password.length() < 8){
            Assert.assertTrue(passworderror.isDisplayed());
            Assert.assertEquals(passworderror.getText(),expectedError); //Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored
        }

        if(!password.matches(".*[A-Z].*")){
            Assert.assertTrue(passworderror.isDisplayed());
            Assert.assertEquals(passworderror.getText(),expectedError);
        }
        if(!password.matches(".*[a-z].*")){
            Assert.assertTrue(passworderror.isDisplayed());
            Assert.assertEquals(passworderror.getText(),expectedError);
        }
        if(!password.matches(".*\\d.")){
            Assert.assertTrue(passworderror.isDisplayed());
            Assert.assertEquals(passworderror.getText(),expectedError);
        }
        if(!password.matches(".*[@#$%^&+=].*")){
            Assert.assertTrue(passworderror.isDisplayed());
            Assert.assertEquals(passworderror.getText(),expectedError);
        }
        if(!password.equals(confirPassword)){
            Assert.assertTrue(passwordconfirmationerror.isDisplayed());
            Assert.assertEquals(passwordconfirmationerror.getText(),expectedError);
        }

    }

    public String checkForSuccessMessage() throws InterruptedException {
        String actual=successMessage.getText();
        return actual;

    }


    public void clearFields(){
        addFirstName.clear();
        addLastName.clear();
        addEmailAddress.clear();
        addPassword.clear();
        addPasswordConfirmation.clear();
    }

}
