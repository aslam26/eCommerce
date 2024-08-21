package com.luma.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductDetailTest extends BaseTest {

    /**
     * Create by Aslam Mujawar on 08/08/24
     */

    @Test
    public void verifySizeIsSelected() throws Exception {
        test=extent.createTest("verify XL size is selected.");
        productDetailPage.navigateToProductPage();
        Assert.assertTrue(productDetailPage.sizeSelection());
    }

    @Test(dependsOnMethods = "verifySizeIsSelected")
    public void verifyColorIsSelected() throws Exception {
        test=extent.createTest("Verify color is selected.");
        Assert.assertTrue(productDetailPage.colorSelection());
        Thread.sleep(5000);
    }


    @Test(dependsOnMethods = "verifyColorIsSelected")
    public void verifyProductAddedToCartSuccessMessage(){
        test=extent.createTest("Verify success is displayed when product added from List.");
        productDetailPage.AddToCart();
        waitUtils.waitUntilTextVisible(productDetailPage.successMessage,"You added");
        String productName=productDetailPage.getSelectProductName();
        String actualMessage=productDetailPage.getSuccessMessage();
        String expectedMessage="You added "+productName+" to your shopping cart.";
        Assert.assertEquals(actualMessage,expectedMessage,"The success message is not matching.");
    }

}
