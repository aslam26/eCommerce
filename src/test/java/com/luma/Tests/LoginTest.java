package com.luma.Tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Create by Aslam Mujawar on 08/08/24
 */



public class LoginTest extends BaseTest{

    @DataProvider(name="loginData")
    public Object[][] createLoginData(){
        return new Object[][]{
                {"emmett.murray@gmail.com","password",false},
                {"emmett.murray@mm.com","123@Password",false},
                {"","",false},
                {"emmett.murray@gmail.com","123@Password",true}
        };
    }


    @Test
    public void verifyUserIsNavigateToSignIn() throws IllegalAccessException {

       test=extent.createTest("Verify user is naviagate to SignIn");
        loginPage.naviagteSignIn();
        Assert.assertTrue(loginPage.isSignInPageDisplayed(),"Customer Login is not displayed.");


    }

    @Test(dependsOnMethods = "verifyUserIsNavigateToSignIn",dataProvider = "loginData")
    public void verifyLoginFunctionality(String username,String password,boolean isValid) throws InterruptedException, IllegalAccessException {
        String testName="Verify Login Functionality - Email: "+username+ ", Password: "+password+", Expected Result: "+ (isValid ? "valid":"invalid");
        test= extent.createTest(testName);
        loginPage.performLogin(username,password);
        if(!isValid){
            Assert.assertEquals(loginPage.getErrorMessage(),"The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");
        }else{
            test=extent.createTest("verify user is logged in");
            Assert.assertTrue(loginPage.isSignOutDisplayed(),"user is Logged In");
        }

    }




}
