package com.luma.Tests;

import CommonLibs.Implementation.CommonDrivers;
import CommonLibs.Utils.Helper;
import CommonLibs.Utils.ScreenshotUtils;
import CommonLibs.Utils.WaitUtils;
import com.Luma.Pages.*;
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
   // String getBrowserType;
    String getBaseUrl;
    LoginPage loginPage;
    String currentWorkingDir=System.getProperty("user.dir");
    public static Logger logger;
    MenPage menPage;
    Faker faker;
    CreateNewCustomerAccountPage cncAccount;
    ProductListPage productListPage;
    ScreenshotUtils screenshotUtils;
    WaitUtils waitUtils;
    MiniCartPage miniCartPage;
    Helper helper;



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


    @Parameters("browser")
    @BeforeClass
    public void setup(String getBrowserType) throws Exception {

      //  getBrowserType=prop.getProperty("browserType");
        cmnDriver=new CommonDrivers(getBrowserType);

        getBaseUrl=prop.getProperty("baseURL");
        cmnDriver.navigateToURl(getBaseUrl);

        driver=cmnDriver.getDriver();
        loginPage=new LoginPage(driver);
        logger=LoggerFactory.getLogger(getClass());
        menPage=new MenPage(driver);
        faker=new Faker();
        cncAccount=new CreateNewCustomerAccountPage(driver);
        productListPage=new ProductListPage(driver);
        screenshotUtils=new ScreenshotUtils(driver);
        waitUtils=new WaitUtils(driver,20);
        miniCartPage=new MiniCartPage(driver);
        helper=new Helper(driver);

    }


    @AfterMethod
    public void postFailure(ITestResult result) throws Exception {
            String testCaseName = result.getName();
            long executionTime=System.currentTimeMillis();
            String screenshotFilename=currentWorkingDir+"/Failed_Screenshots/"+testCaseName+executionTime+".jpeg";
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
