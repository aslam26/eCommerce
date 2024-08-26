package com.luma.Tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MiniCartTest extends BaseTest{

    @Test(priority = 0)
    public void loginIntoApp() throws InterruptedException {
        test=extent.createTest("Verify user is logged in and product is added into mini cart");
        loginPage.naviagteSignIn();
        String username=prop.getProperty("username");
        String password=prop.getProperty("password");
        loginPage.performLogin(username,password);
        productListPage.navigateToProductPage();
        productListPage.setAddtoCart();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = "loginIntoApp")
    public void verifyUserIsNavgatedToMiniCartModel() throws Exception {
        test=extent.createTest("Verify MiniCart is displayed.");
        miniCartPage.clickMiniCart();
        waitUtils.waitUntilElementVisible(miniCartPage.miniCartDialog);
        Assert.assertTrue(miniCartPage.openMiniCart(),"Mini-Cart is not Displayed.");
        Assert.assertTrue(miniCartPage.topCartProceedToCheckOutButton.isDisplayed());
    }

    @Test(priority = 1, dependsOnMethods = "verifyUserIsNavgatedToMiniCartModel")
    public void verifyModelIsClosed() throws Exception {
        test=extent.createTest("Verify mini cart gets closed on clicking close button");
        waitUtils.waitUntilElementVisible(miniCartPage.miniCartDialog);
        Assert.assertTrue(miniCartPage.closeMiniCart(),"Mini-cart is still open");
    }

    @Test(priority = 3)
    public void verifyNavigatesToShippingAddressPage() throws Exception {
        test=extent.createTest("Verify Clicking Proceed To CheckOut button, navigates to Shopping Address Page.");
        miniCartPage.clickMiniCart();
        miniCartPage.setTopCartProceedToCheckOutButton();
        waitUtils.waitUntilElementVisible(miniCartPage.shippingAddressTitleOfPage);
        Assert.assertEquals(miniCartPage.getTitleOfShoppingAddressPage(),"Shipping Address");
        driver.navigate().back();
    }

    @Test(priority = 4)
    public void verifyNavigateToUpdateCartPage(){
        test=extent.createTest("Verify clicking Edit item, user is navigate to update cart page.");
        miniCartPage.clickMiniCart();
        miniCartPage.setEditItem();
        Assert.assertTrue(miniCartPage.updateCartButton.isDisplayed());
    }

    @Test(priority = 5)
    public void verifyUserIsNavigatedToShoppingCartPage(){
        test=extent.createTest("Verify user is navigated to shopping cart page, on clicking 'View and Edit Cart' link");
        miniCartPage.clickMiniCart();
        miniCartPage.setViewAndEditCartLink();
        Assert.assertEquals(miniCartPage.getTitleOfShoppingCartPage(),"Shopping Cart");
    }

    @Test(priority = 6, dependsOnMethods = "loginIntoApp")
    public void VerifyCancelClosesTheModelOverlay() throws InterruptedException {
        test=extent.createTest("Verify model overlay is closed, upon clicking cancel button");
        miniCartPage.clickMiniCart();
        miniCartPage.removeItem();
        Thread.sleep(5000);
        Assert.assertEquals(miniCartPage.cancelModel(),false);
    }
    @Test(priority = 7)
    public void verifyRemoveItem(){
        test=extent.createTest("Verify item is removed from Mini-Cart upon clicking Remove Item button");
        miniCartPage.removeItem();
        waitUtils.waitUntilElementVisible(miniCartPage.modelOkButton);
        miniCartPage.deleteItemConfirmation();
        Assert.assertEquals(miniCartPage.getItemDeletedMessage(),"You have no items in your shopping cart.");
    }

    @Test(priority = 8)
    public void verifyAlertPopClose() throws Exception {
        test=extent.createTest("Verify alert up is closed upon clicking X image");
        miniCartPage.clickMiniCart();
        Assert.assertTrue(miniCartPage.closeAlert(),"Alert pop-up not closed.");
    }

}
