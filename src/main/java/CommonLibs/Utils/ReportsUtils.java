package CommonLibs.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


/**
 * Create by Aslam Mujawar on 08/08/24
 */

public class ReportsUtils {


      ExtentSparkReporter htmlReport; //For HTML reports
      ExtentReports extentReports; //Generate Report
      ExtentTest extentTest; //create class for test cases.

     public ReportsUtils(String htmlReportFilename){
        htmlReportFilename=htmlReportFilename.trim();
        htmlReport=new ExtentSparkReporter(htmlReportFilename);
        extentReports=new ExtentReports();
        extentReports.attachReporter(htmlReport);

    }


    //Create TestCase Report

    public void createATestcase(String testcase){
        extentTest=extentReports.createTest(testcase);
    }

    //Logs
    public void addTestLog(Status status, String comment){ //Status is what kind of logs Info, Errors, Warnings, Failure
        extentTest.log(status,comment);
    }

    public void attachScreenShotToReport(String imagePath){
        extentTest.addScreenCaptureFromPath(imagePath);
    }

    //flush or close the reports
    public void flushReports(){
            extentReports.flush();
    }

}
