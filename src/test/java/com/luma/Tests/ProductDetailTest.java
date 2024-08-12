package com.luma.Tests;

import com.Luma.Pages.ProductDetailPage;
import org.testng.annotations.Test;

public class ProductDetailTest extends BaseTest {

    /**
     * Create by Aslam Mujawar on 08/08/24
     */

    @Test
    public void verifyItemName(){
        reportsUtils.createATestcase("Verify relative locator");
        productDetailPage.navigateToProductPage();
        String actualResult=productDetailPage.findTheElement();
        System.out.println(actualResult);
    }
}
