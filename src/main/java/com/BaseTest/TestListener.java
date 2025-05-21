package com.BaseTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Utill.TakeScreenShot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * TestNG listener that integrates ExtentReports reporting with test execution.
 * Handles test start, success, failure, and suite start/finish events.
 */
public class TestListener implements ITestListener, ISuiteListener {

	private static ExtentReports extent = ExtentReportListener.getExtentReport();

	@Override
	public void onTestStart(ITestResult result) {
		// Create a new test entry in the report for the current test method
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		ExtentTestManager.setTest(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Log pass status in the report
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Log failure status with the throwable message/stacktrace
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());

		try {
			// Capture screenshot and attach it to the report
			Object currentClass = result.getInstance();

			WebDriver driver = ((BaseTest) currentClass).driver;
			String path = TakeScreenShot.takeScreenShot(driver, result);
			ExtentTestManager.getTest().addScreenCaptureFromPath(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Optionally log skipped tests
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		// Optional: logic before any test methods in this context run
	}

	@Override
	public void onFinish(ITestContext context) {
		// Flush report to write all logs to the report file at the end of the test
		// context
		 extent.flush();
	}

	@Override
	public void onStart(ISuite suite) {
		// Optionally create a test entry for the suite start
		 ExtentTest test = extent.createTest(suite.getName());
		 ExtentTestManager.setTest(test);
	}

	@Override
	public void onFinish(ISuite suite) {
		 //Flush the report after the suite execution completes
		if (extent != null) {
			extent.flush();
		}
	}
}
