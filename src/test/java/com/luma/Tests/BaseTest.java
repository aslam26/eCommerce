package com.luma.Tests;

import CommonLibs.Implementation.CommonDrivers;
import CommonLibs.Utils.ReportsUtils;
import CommonLibs.Utils.ScreenshotUtils;
import com.Luma.Pages.CreateNewCustomerAccountPage;
import com.Luma.Pages.LoginPage;
import com.Luma.Pages.MenPage;
import com.Luma.Pages.ProductDetailPage;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
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
    String reportFileName;
    LoginPage loginPage;
    String currentWorkingDir=System.getProperty("user.dir");
    public static Logger logger;
    MenPage menPage;
    Faker faker;
    CreateNewCustomerAccountPage cncAccount;
    ProductDetailPage productDetailPage;
    ScreenshotUtils screenshotUtils;


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
        productDetailPage=new ProductDetailPage(driver);
        reportFileName=currentWorkingDir+"/reports/eCommerceTestReport.html";
        reportsUtils=new ReportsUtils(reportFileName);
        screenshotUtils=new ScreenshotUtils(driver);


    }

    @AfterMethod
    public void postFailure(ITestResult result) throws Exception {
            String testCaseName = result.getName();
            long executionTime=System.currentTimeMillis();
            String screenshotFilename=currentWorkingDir+"/Screenshots/"+testCaseName+executionTime+".jpeg";
            if (result.getStatus() == ITestResult.FAILURE) {
                reportsUtils.addTestLog(Status.FAIL, "One of test case failed" + testCaseName);
                screenshotUtils.captureAndSaveScreenshots(screenshotFilename);
                reportsUtils.attachScreenShotToReport(screenshotFilename);
                System.out.println("Screenshot taken");
            } else if (result.getStatus()==ITestResult.SUCCESS) {
                reportsUtils.addTestLog(Status.PASS,"Test Passed.");
            } else if (result.getStatus()==ITestResult.SKIP) {
                reportsUtils.addTestLog(Status.SKIP,"Test Skipped.");
            }
    }

    @AfterClass
    public void tearDown(){
        if(cmnDriver !=null) {
            cmnDriver.closeAllBrowser();
        }

    }


    @AfterSuite(alwaysRun = true)
    public void postTeardown() {

        try {
            if (reportsUtils != null) {
                reportsUtils.flushReports();
                System.out.println("Reports flushed successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
