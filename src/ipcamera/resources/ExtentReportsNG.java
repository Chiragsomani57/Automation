package ipcamera.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	public static ExtentReports getreport()
	{
		String path = System.getProperty("user.dir")
                + "\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("ipcamera repoprt");
		
		
		ExtentReports	extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Chirag somani");
		return extent;
	}

}
