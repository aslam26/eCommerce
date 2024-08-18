package com.luma.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by Aslam Mujawar on 11/08/24
 */

public class MenTest extends BaseTest{

    @Test(priority = 0)
    public void verifyUserIsNavigateToJacketPage(){
        reportsUtils.createATestcase("Verify user is navigated to Jackets Page.");
        String actualResult= menPage.navigateToProductPage();
        String expectedResult="Jackets";
        Assert.assertEquals(actualResult,expectedResult,"Heading not matching!");
        logger.info("Navigation Completed.");
    }

    @Test(priority = 1)
    public void verifyUI() throws IOException {
        reportsUtils.createATestcase("Verify UI");
        menPage.closeAdvAlert();
        boolean result= screenshotUtils.visualTest(currentWorkingDir+"/resources/magento.softwaretestingboard.com_men_tops-men_jackets-men.html.png");
        Assert.assertTrue(result,"Images are not identical");
    }


    @Test(priority = 3, dependsOnMethods = "verifyUserIsNavigateToJacketPage")
    public void productsWithNameAndPrice(){
        reportsUtils.createATestcase("Verify all Items in page is displayed.");
        List<String> list=new ArrayList<>();
        list=menPage.getProductNames();

        List<String> price=new ArrayList<>();
        price=menPage.getProductPrice();

        for(int i=0;i<list.size();i++){
            System.out.println("Product Name: "+list.get(i)+", Price: "+price.get(i));
        }
    }

}
