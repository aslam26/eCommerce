package com.luma.Tests;

import com.Luma.Pages.ProductDetailPage;
import org.testng.annotations.Test;

public class ProductDetailTest extends BaseTest {

    @Test
    public void verifyItemName(){
        productDetailPage.navigateToProductPage();
        String actualResult=productDetailPage.findTheElement();
        System.out.println(actualResult);
    }
}
