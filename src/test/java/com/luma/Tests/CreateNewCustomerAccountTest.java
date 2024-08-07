package com.luma.Tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
        reportsUtils.createATestcase("Verify Create a New Account Page is displayed.");
        cncAccount.clickingCreateAnAccountLink();
        cncAccount.enterDetailsToCreateAccount("","","","","");
        cncAccount.checkMandatoryField();
        reportsUtils.createATestcase("Verify required field error messages are displayed.");
    }

    @Test(priority = 1)
    public void verifyEmailAddress() throws InterruptedException {
        reportsUtils.createATestcase("Verify error message is displayed for invalid email address");
        String invalidEmail=prop.getProperty("invalidEmail");
        cncAccount.checkEmailAddress(invalidEmail);

    }

    @Test(priority = 2,dataProvider = "invalidPassword")
    public void verifyForInvalidPassword(String password,String confirmPassword, String expectedError){
        reportsUtils.createATestcase("Verify error message is displayed for invalid password.");
        cncAccount.enterDetailsToCreateAccount("oggy","Doe","oggy@cartoon.com",password,confirmPassword);
        cncAccount.checkPassword(password,confirmPassword,expectedError);
    }

    @Test(priority = 3  )
    public void verifyNewAccountIsCreated() throws InterruptedException {
        reportsUtils.createATestcase("Verify new account is created.");
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
