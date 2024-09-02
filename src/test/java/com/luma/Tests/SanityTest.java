package com.luma.Tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SanityTest extends BaseTest{

    @Test(groups = {"sanity"}, priority = 0)
    public void verifyLoginFunctionality() throws InterruptedException {
        test=extent.createTest("Verify user is logged In using valid credentials");
       String username= prop.getProperty("username");
       String password= prop.getProperty("password");
       loginPage.naviagteSignIn();
        loginPage.performLogin(username,password);
        Assert.assertTrue(loginPage.isSignOutDisplayed(),"user is logged in");
    }

    @Test(groups = {"sanity"}, priority = 1)
    public void verifyUserIsNavigateToJacketPage(){
        test= extent.createTest("Verify user is navigated to Jackets Page.");
        String actualResult= menPage.navigateToProductPage();
        String expectedResult="Jackets";
        Assert.assertEquals(actualResult,expectedResult,"Heading not matching!");
        logger.info("Navigation Completed.");
    }

    @Test(groups={"sanity"}, priority = 2)
    public void verifySearchforItems() throws InterruptedException {
        test=extent.createTest("Verify Search functionality");
        menPage.searchForItem("pant");
        Thread.sleep(5000);
        List<String> list= new ArrayList<>();
        list=menPage.getProductNames();


        boolean flag=false;
        for(String productname: list){
            if(productname.toLowerCase().contains("pant")){
                flag=true;
            }
        }
        Assert.assertTrue(flag,"Not Found");
    }

    @Test(groups = {"sanity"}, priority = -1)
    public void verifyCreateAccountPage(){
        test=extent.createTest("Vreify create an account is opened");
       Assert.assertTrue(cncAccount.clickingCreateAnAccountLink(),"Not found");
    }



}
