package com.luma.Tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Create by Aslam Mujawar on 08/08/24
 */

public class CreateNewCustomerAccountTest extends BaseTest{

    @DataProvider(name="invalidPassword")
    public Object[][] provideInvalidPassword(){
        return new Object[][]{
                {"pass","pass","Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored."},
                {"PASsword","PASsword","Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters."},
                {"PASSWORD","PASSWORD","Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters."},
                {"passdword12","passdword12","Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters."},
                {"Password@123","DifferentPassword@123","Please enter the same value again."}

        };
    }

    @Test(priority = 0)
    public void verifyRequiredFieldErrorMessage(){
        test=extent.createTest("Verify Create a New Account Page is displayed.");
        cncAccount.clickingCreateAnAccountLink();
        cncAccount.enterDetailsToCreateAccount("","","","","");
        //check for the actual error text
        Assert.assertEquals(cncAccount.getFirstNameErrorText(),"This is a required field.");
        Assert.assertEquals(cncAccount.getLastNameErrorText(),"This is a required field.");
        Assert.assertEquals(cncAccount.getEmailErrorText(),"This is a required field.");
        Assert.assertEquals(cncAccount.getPasswordErrorText(),"This is a required field.");
        Assert.assertEquals(cncAccount.getConfirmPasswordText(),"This is a required field.");

        test.pass("Mandatory field error messages were validated successfully");

    }

    @Test(priority = 1)
    public void verifyEmailAddress() throws InterruptedException {
        test= extent.createTest("Verify error message is displayed for invalid email address");
        String invalidEmail=prop.getProperty("invalidEmail");
        cncAccount.checkEmailAddress(invalidEmail);
        String actualErrorMessage=cncAccount.invalidEmailErrorDisplayed();
        String expectedErrorMessage="Please enter a valid email address (Ex: johndoe@domain.com).";
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage,"Error message not matching.");
    }

    @Test(priority = 2,dataProvider = "invalidPassword")
    public void verifyForInvalidPassword(String password,String confirmPassword, String expectedError){
        test= extent.createTest("Verify error message is displayed for invalid password.");
        cncAccount.clickingCreateAnAccountLink();
        cncAccount.enterDetailsToCreateAccount("oggy","Doe","oggy@cartoon.com",password,confirmPassword);
        // Check if the expected error is for password or confirm password field
        if(!password.equals(confirmPassword)) {
            // Verify confirmation password error
            String actualConfirmPasswordError= cncAccount.getInvalidConfirmPasswordErrorMessage();
            Assert.assertEquals(actualConfirmPasswordError,expectedError,"Confirm Password error message does not match.");
        }else {
            // Verify password error
            String actualPassworError = cncAccount.getInvalidPasswordErrorMessage();
            Assert.assertEquals(actualPassworError, expectedError, "Password error message does not match.");
        }
    }

    @Test(priority = 3 )
    public void verifyNewAccountIsCreated() throws InterruptedException {
        test=extent.createTest("Verify new account is created");
        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();
        String email=faker.internet().emailAddress();
        String password=faker.internet().password(8,10,true,true,true);
        cncAccount.clearFields();
        cncAccount.enterDetailsToCreateAccount(firstname,lastname,email,password,password);
        Thread.sleep(5000);
        String actualMessage=cncAccount.checkForSuccessMessage();
        Assert.assertEquals(actualMessage,"Thank you for registering with Main Website Store.");
    }

}
