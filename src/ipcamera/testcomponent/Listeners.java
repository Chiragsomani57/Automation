package ipcamera.testcomponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ipcamera.resources.ExtentReportsNG;

public class Listeners  implements ITestListener 


{
	 ExtentReports extent = ExtentReportsNG.getreport(); ;
    ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();;
    basetest btest = new basetest();;
	
	@Override
    public void onTestStart(ITestResult result) {

		test.set(extent.createTest(result.getName()));
    }

    // runs when test passes
    @Override
    public void onTestSuccess(ITestResult result) {
    	    test.get().log(Status.PASS,"test case is pass" );
    }

    // runs when test fails
    @Override
    public void onTestFailure(ITestResult result) {
    	test.get().log(Status.FAIL, "test case is failed");
    	test.get().fail(result.getThrowable());
    	 WebDriver driver = ((basetest) result.getInstance()).driver;
    	  btest.driver = driver;
 
    	String filepath = null;
		try {
			 filepath = btest.getscreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
    	
    	
    }

    // runs when test skips
    @Override
    public void onTestSkipped(ITestResult result) {
    }

    // runs when test fails but within success percentage
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    // runs when test fails due to timeout
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    // runs before any test method in class starts
    @Override
    public void onStart(ITestContext context) {
    }

    // runs after all test methods in class finish
    @Override
    public void onFinish(ITestContext context) {
    	 extent.flush(); //Generate the report
    }
}
