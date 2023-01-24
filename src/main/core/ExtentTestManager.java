package src.main.core;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
	static Map extentTestMap = new HashMap();
    static ExtentReports extent;

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static synchronized ExtentTest startTest(ExtentReports extent, String testName) {
        return startTest(extent, testName, "");
    }

    public static synchronized ExtentTest startTest(ExtentReports extent, String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}
