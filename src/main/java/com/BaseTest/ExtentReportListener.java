package com.BaseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Singleton class for managing ExtentReports instance. Provides a single
 * ExtentReports object to be used across tests.
 */
public class ExtentReportListener {

	private static ExtentReports extent;

	/**
	 * Returns the singleton ExtentReports instance. If it doesn't exist,
	 * initializes it with ExtentSparkReporter.
	 *
	 * @return ExtentReports instance
	 */
	public static ExtentReports getExtentReport() {
		if (extent == null) {
			// Define the location and filename of the generated report
			String reportPath = System.getProperty("user.dir") + "\\Reports\\ExtentReport.html";

			// Initialize the Spark reporter (HTML reporter)
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.config().setReportName("Automation Test Report");
			sparkReporter.config().setDocumentTitle("Test Results");

			// Create ExtentReports and attach reporter
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);

			// Add system info to the report for context
			extent.setSystemInfo("Tester", "Your Name");
			extent.setSystemInfo("Environment", "QA");
		}
		return extent;
	}
}
