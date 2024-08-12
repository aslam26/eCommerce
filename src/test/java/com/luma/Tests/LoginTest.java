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
    public void verifyUserIsNavigateToSignIn(){
        reportsUtils.createATestcase("Verify user is navigated to SignIn.");
        reportsUtils.addTestLog(Status.INFO,"Test start");
        loginPage.naviagteSignIn();
        Assert.assertTrue(loginPage.isSignInPageDisplayed(),"Customer Login is not displayed.");
        reportsUtils.addTestLog(Status.INFO,"Test Passed");
        logger.info("Navigation Completed");
    }

    @Test(dependsOnMethods = "verifyUserIsNavigateToSignIn",dataProvider = "loginData")
    public void verifyLoginFunctionality(String username,String password,boolean isValid) throws InterruptedException {
        String testName="Verify Login Functionality - Email: "+username+ ", Password: "+password+", Expected Result: "+ (isValid ? "valid":"invalid");
        reportsUtils.createATestcase(testName);
        reportsUtils.addTestLog(Status.INFO,"Test Start");
        loginPage.performLogin(username,password);
        if(!isValid){
            Assert.assertEquals(loginPage.getErrorMessage(),"The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");
        }else{
            reportsUtils.createATestcase("Verify user is logged In.");
            reportsUtils.addTestLog(Status.PASS,"test case passed.");
            Assert.assertTrue(loginPage.isSignOutDisplayed(),"user is not Logged In");
            reportsUtils.addTestLog(Status.INFO,"Test Passed");
            logger.info("User logged in Successfully.");
        }

    }




}
