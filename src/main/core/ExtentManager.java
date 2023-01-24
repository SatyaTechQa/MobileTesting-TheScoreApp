package src.main.core;

import src.main.constants.Constants;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.nio.file.Paths;

public class ExtentManager {
	private static ExtentReports extent;
	private static String resultsFilePath;

	public synchronized static ExtentReports getReporter(String filePath) {
		System.out.println("==> Inside ExtentManager::getReporter");
			System.out.println("==> ExtentManager::getReporter(): Results File Path = "+filePath);
			resultsFilePath = "Results/Extent.html";
			extent = new ExtentReports(filePath, true);
			File configFile = Paths.get(Constants.ConfigurationFiles_Dir + "extent-config.xml").toFile();
			extent.loadConfig(configFile);
		return extent;
	}
}
