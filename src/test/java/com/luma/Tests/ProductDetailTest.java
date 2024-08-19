package com.luma.Tests;

import org.testng.annotations.Test;

public class ProductDetailTest extends BaseTest {

    /**
     * Create by Aslam Mujawar on 08/08/24
     */

    @Test
    public void verifyItemName(){
        test=extent.createTest("verify relative laoctor");
        productDetailPage.navigateToProductPage();
        String actualResult=productDetailPage.findTheElement();
        System.out.println(actualResult);
    }
}
