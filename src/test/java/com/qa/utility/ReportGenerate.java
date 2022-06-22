package com.qa.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportGenerate extends TestListenerAdapter{
	public ExtentHtmlReporter htmlReport;
	public ExtentReports xReports;
	public ExtentTest xTest;
	
	
	  // LoC to configure the extent reports
	  // create a new HTML reports on every test execution triggered by testng.xml
	  // HTML file should be casted as ExtentReport
	
	  public void onStart(ITestContext testContext) {
		  // Location and file name for the HTML reports
		  
		  String dateStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		  String repName = "Test-Automation-Report"+dateStamp+".html";
		  
		  htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/"+repName);
		  htmlReport.config().setDocumentTitle("Automation Report");
		  htmlReport.config().setReportName("Functional Test Report");
		  htmlReport.config().setTheme(Theme.DARK);
		  htmlReport.config().setAutoCreateRelativePathMedia(true);
		  
		  xReports = new ExtentReports();
		  xReports.attachReporter(htmlReport);
		  xReports.setSystemInfo("QA Name", "TesterA");
		  xReports.setSystemInfo("Hostname", "localhost");
		  xReports.setSystemInfo("Platform", "Win10");
      }
	
	  
	  public void onFinish(ITestContext testContext) {
		  xReports.flush();
	  }
	  
	  public void onTestSuccess(ITestResult tr) {
		  xTest = xReports.createTest(tr.getName());
		  xTest.log(Status.PASS, "Test is passed");
		  xTest.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	  }
	
	  public void onTestFailure(ITestResult tr) {
		  xTest = xReports.createTest(tr.getName());
		  xTest.log(Status.FAIL, "Test is failed");
		  xTest.log(Status.FAIL, tr.getThrowable());
		  xTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		  
		  String ssPath = System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
		  File file = new File(ssPath);
		  
		  if(file.exists()) {
		    try {
		    	xTest.fail("Screenshot for the faild test is : "+xTest.addScreenCaptureFromPath(ssPath));
			} catch (IOException e) {
				e.printStackTrace();
			}			  
		  }
	  }
	
	  public void onTestSkipped(ITestResult tr) {
		  xTest = xReports.createTest(tr.getName());
		  xTest.log(Status.SKIP, "Test is skipped");
		  xTest.log(Status.SKIP, tr.getThrowable());
		  xTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.AMBER));	    
	  }
}
