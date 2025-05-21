package com.BaseTest;

import com.aventstack.extentreports.ExtentTest;

/**
 * Manages ExtentTest instances in a thread-safe manner using ThreadLocal.
 * This allows parallel test execution without conflicts in reporting.
 */
public class ExtentTestManager {

    // ThreadLocal variable to hold ExtentTest instances per thread
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Retrieves the current thread's ExtentTest instance.
     * 
     * @return ExtentTest for the current thread
     */
    public static ExtentTest getTest() {
        return test.get();
    }

    /**
     * Sets the ExtentTest instance for the current thread.
     * 
     * @param extentTest ExtentTest instance to set
     */
    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    /**
     * Removes the ExtentTest instance from the current thread to prevent memory leaks.
     */
    public static void unload() {
        test.remove();
    }
}
