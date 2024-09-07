package com.luma.Tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.rmi.server.ExportException;

public class ProductListTest extends BaseTest {

    /**
     * Create by Aslam Mujawar on 08/08/24
     */

    @Test
    public void verifySizeIsSelected() throws Exception {
        test=extent.createTest("verify XL size is selected.");
        productListPage.navigateToProductPage();
        Assert.assertTrue(productListPage.sizeSelection());
    }

    @Test(dependsOnMethods = "verifySizeIsSelected")
    public void verifyColorIsSelected() throws Exception {
        test=extent.createTest("Verify color is selected.");
        Assert.assertTrue(productListPage.colorSelection());
        Thread.sleep(5000);
    }


    @Test(dependsOnMethods = "verifyColorIsSelected")
    public void verifyProductAddedToCartSuccessMessage(){
        test=extent.createTest("Verify success is displayed when product added from List.");
        productListPage.AddToCart();
        waitUtils.waitUntilTextInElement(productListPage.successMessage,"You added");
        String productName=productListPage.getSelectProductName();
        String actualMessage=productListPage.getSuccessMessage();
        String expectedMessage="You added "+productName+" to your shopping cart.";
        Assert.assertEquals(actualMessage,expectedMessage,"The success message is not matching.");
        Assert.assertEquals(productListPage.myCartCount(),"1");
    }

    @Test(priority = 4)
    public void verifyUserisNavigatedToProductDetailPage(){
        test=extent.createTest("Verify user is navigated to Product Detail Page.");
        productListPage.dontSelectAnyOptionsUnderProduct();
        waitUtils.waitUntilTextInElement(productListPage.chooseOptionMessage,"You need");
        Assert.assertEquals(productListPage.optionSelectMessage(),"You need to choose options for your item.");
    }

    @Test(priority = 5)
    public void verifySizeIsSelectedInProductDetailed() throws Exception {
        test= extent.createTest("Verify product size options is selected.");
        Assert.assertTrue(productListPage.sizeSelectionAtProduct());
    }

    @Test(dependsOnMethods = "verifySizeIsSelectedInProductDetailed")
    public void verifyColorIsSelectedInProductDetailed() throws ExportException, InterruptedException {
        test=extent.createTest("Verify product color options is selected.");
        Assert.assertTrue(productListPage.colorSelectionAtProduct());
    }

    @Test(dependsOnMethods = "verifyColorIsSelectedInProductDetailed")
    public void verifyAddQuantity(){
        test=extent.createTest("Verify quantity is entered into Quantity text-field");
        productListPage.setQuantity("1");
    }

    @Test(dependsOnMethods = "verifyAddQuantity")
    public void verifyProductIsAddedToCart(){
        test=extent.createTest("Verify product is added into cart.");
        String productName=productListPage.getTitleOfProduct();
        productListPage.clickAddToCartButton();
        waitUtils.waitUntilTextInElement(productListPage.successMessage,"You added");
        String actualMessage=productListPage.getSuccessMessage();
        String expectedMessage="You added "+productName+" to your shopping cart.";
        Assert.assertEquals(actualMessage,expectedMessage,"The success message is not matching.");
        Assert.assertEquals(productListPage.myCartCount(),"2");
    }



}
