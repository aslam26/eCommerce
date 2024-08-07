package com.luma.Tests;

import CommonLibs.Implementation.CommonDrivers;
import CommonLibs.Utils.ReportsUtils;
import com.Luma.Pages.CreateNewCustomerAccountPage;
import com.Luma.Pages.LoginPage;
import com.Luma.Pages.MenPage;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.utils.FileUtil;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class BaseTest {

    WebDriver driver;
    CommonDrivers cmnDriver;
    public static Properties prop;
    String getBrowserType;
    String getBaseUrl;
    ReportsUtils reportsUtils;
    LoginPage loginPage;
    String currentWorkingDir=System.getProperty("user.dir");
    public static Logger logger;
    MenPage menPage;
    Faker faker;
    CreateNewCustomerAccountPage cncAccount;

    @BeforeSuite
    public void preSetUp() throws IOException {

        prop=new Properties();
        FileInputStream ip=new FileInputStream(currentWorkingDir+"/config/config.properties");
        prop.load(ip);
    }

    @BeforeClass
    public void setup() throws Exception {
        getBrowserType=prop.getProperty("browserType");
        cmnDriver=new CommonDrivers(getBrowserType);

        getBaseUrl=prop.getProperty("baseURL");
        cmnDriver.navigateToURl(getBaseUrl);

        driver=cmnDriver.getDriver();

        loginPage=new LoginPage(driver);

        logger=LoggerFactory.getLogger(getClass());

        menPage=new MenPage(driver);

        faker=new Faker();

        cncAccount=new CreateNewCustomerAccountPage(driver);


        reportsUtils=new ReportsUtils(currentWorkingDir+"/reports/reports.html");

    }

    @AfterMethod
    public void postFailure(ITestResult result) throws IOException {
            String testCaseName = result.getName();
            if (result.getStatus() == ITestResult.FAILURE) {
                reportsUtils.addTestLog(Status.FAIL, "One of test case failed" + testCaseName);
                File screeshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screeshot, new File("Screenshots/failed.png"));
                System.out.println("Screenshot taken");
            }
    }

    @AfterClass
    public void tearDown(){
        if(cmnDriver !=null) {
            cmnDriver.closeAllBrowser();
        }
    }

    @AfterSuite
    public void closeReport(){
        reportsUtils.flushReports();
    }


}
