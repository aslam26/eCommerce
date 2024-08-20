package com.luma.Tests;

import CommonLibs.Implementation.CommonDrivers;
import CommonLibs.Utils.ScreenshotUtils;
import com.Luma.Pages.CreateNewCustomerAccountPage;
import com.Luma.Pages.LoginPage;
import com.Luma.Pages.MenPage;
import com.Luma.Pages.ProductDetailPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
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
    LoginPage loginPage;
    String currentWorkingDir=System.getProperty("user.dir");
    public static Logger logger;
    MenPage menPage;
    Faker faker;
    CreateNewCustomerAccountPage cncAccount;
    ProductDetailPage productDetailPage;
    ScreenshotUtils screenshotUtils;



    protected static ExtentReports extent;
    protected ExtentTest test;
    ExtentSparkReporter sparkReporter;

    @BeforeSuite
    public void preSetUp() throws IOException {

        prop=new Properties();
        FileInputStream ip=new FileInputStream(currentWorkingDir+"/config/config.properties");
        prop.load(ip);

        sparkReporter = new ExtentSparkReporter(currentWorkingDir+"/reports/TestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
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
        screenshotUtils=new ScreenshotUtils(driver);

    }


    @AfterMethod
    public void postFailure(ITestResult result) throws Exception {
            String testCaseName = result.getName();
            long executionTime=System.currentTimeMillis();
            String screenshotFilename=currentWorkingDir+"/Screenshots/"+testCaseName+executionTime+".jpeg";
            if (result.getStatus() == ITestResult.FAILURE) {
                test.log(Status.FAIL,"One test case failed.");
                screenshotUtils.captureAndSaveScreenshots(screenshotFilename);
                test.addScreenCaptureFromPath(screenshotFilename);
                System.out.println("Screenshot taken");
            } else if (result.getStatus()==ITestResult.SUCCESS) {
                test.log(Status.PASS,"success");
            } else if (result.getStatus()==ITestResult.SKIP) {
                test.log(Status.SKIP,"SKipped");
            }else {
                throw new Exception("Not Found.");
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
        extent.flush();
        System.out.println("Report flushed successfully.");
    }

}
