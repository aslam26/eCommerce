package CommonLibs.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportsUtils {

    ExtentHtmlReporter htmlReport; //For HTML reports

    ExtentReports extentReports; //Generate Report

    ExtentTest extentTest; //create class for test cases.

    public ReportsUtils(String htmlReportFilename){

        htmlReportFilename=htmlReportFilename.trim();

        htmlReport=new ExtentHtmlReporter(htmlReportFilename);

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

    //flush or close the reports
    public void flushReports(){
        extentReports.flush();
    }

}
