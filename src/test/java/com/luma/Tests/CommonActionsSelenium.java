package com.luma.Tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CommonActionsSelenium extends BaseTest {

    @Test
    public void navigateBackwardForwad(){

        driver.navigate().to("https://magento.softwaretestingboard.com/men/bottoms-men/pants-men.html");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
    }

    @Test
    public void Sorted(){
        driver.navigate().to("https://magento.softwaretestingboard.com/men/tops-men/jackets-men.html");
        menPage.selectFromDropDown("Price");

    }

    @Test
    public void searchforItems(){
        menPage.searchForItem("pants");
    }

    @Test
    public void takeScreenShot() throws IOException {
        driver.navigate().to("https://magento.softwaretestingboard.com/gear/bags.html");
        TakesScreenshot screenshot=(TakesScreenshot) driver;
        File captScreenShot=screenshot.getScreenshotAs(OutputType.FILE);
        File destFile=new File(currentWorkingDir+"/Screenshots/test.jpeg");
        FileHandler.copy(captScreenShot,destFile);
    }
}
