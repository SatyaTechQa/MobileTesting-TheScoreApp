package src.main.common;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import src.main.constants.Constants;
import src.main.core.CapsAndDriverOpts;
import src.main.core.utils.ReadWriteProperties;
import src.main.core.utils.TimeAndDate;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static String reportLocation;
    protected static String reportFileFullPath;
    protected static AppiumDriverLocalService appiumDriverLocalService;
    protected static AppiumServiceBuilder appiumServiceBuilder;
    static ExtentReports extent;
    static ExtentTest test;
    protected static WebDriver driver = null;

    @BeforeSuite
    public void beforeSuite() throws Exception {
        System.out.println("[INFO] Before Suite -------------------------------------------------------");
        // Report directories creation and get ready with the file name of the report.
        String dateTime = TimeAndDate.getDateAndTime();
        String reportDirectoryName = "TestReport_" + dateTime;
        String reportsBaseDir = Paths
        .get(System.getProperty("user.dir") + "/" + ReadWriteProperties.props.getProperty("test.results.dir"))
        .toString();
        reportLocation = Paths.get(reportsBaseDir + "/" + reportDirectoryName).toString();

        reportFileFullPath = Paths
                .get(reportLocation + "/" + "TestReport_" + dateTime + ".html").toString();

        createReportDirs();

        if (ReadWriteProperties.props.getProperty("appium.service").equalsIgnoreCase("local")) {
            appiumDriverLocalService = null;
            appiumServiceBuilder = new AppiumServiceBuilder();
            appiumServiceBuilder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
            appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "warn");
            appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
            try {
                if (appiumPortAvailable()) appiumDriverLocalService.start();
                else
                    System.out.printf("Appium service port already in use; Hence assuming that the service is already running and skipping the service start...");
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("An error occurred while starting the Appium service on the local system");
            }
        }
    }

    @Parameters("Device")
    @BeforeClass
    public void beforeClass(String device, ITestResult result){
        try{

            this.getAndroidDriver(device);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("[INFO] Before method---------------------------");
        extent = new ExtentReports(reportFileFullPath);
        test = extent.startTest(m.getName());
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("[INFO] After Method------------------------");

    }

    @AfterClass
    public void afterClass(){
        extent.endTest(getTest());
        extent.flush();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("[INFO] After Suite -------------------------------------------------------");
        extent.flush();
        try {
            appiumDriverLocalService.stop();
        } catch (Exception e) {
        }
    }

    public static boolean createReportDirs() {
        if (new File(reportLocation).mkdir()) {
            return true;
        } else
            return false;
    }

    public static String getTestReportFilename() {
        return reportFileFullPath;
    }

    private static boolean appiumPortAvailable() {
        System.out.printf("checking for Appium service port availability ....");
        try (Socket ignored = new Socket("localhost", 4723)) {
            return false;
        } catch (IOException ignored) {
            System.out.printf("Port already in use ! Appium service might be already running.....");
            return true;
        }
    }

    public WebDriver getAndroidDriver(String TARGET) {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        CapsAndDriverOpts capsAndDriverOpts;
        try {
            capsAndDriverOpts = this.myGetCapabilities(TARGET, capabilities);
            capabilities = capsAndDriverOpts.getCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("newCommandTimeout", "300");
            // Create the driver
            driver = new AndroidDriver(new URL(capsAndDriverOpts.getDriverURL()), capabilities);
            System.out.println("==> Your device driver = " + driver.toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could be a problem with Appium service. Pls cross check the same.");
            return null;
        }
        System.out.println("==> Driver created: " + driver.toString());
        return driver;
    }

    public Map getCapabilities(String target) {
        try {
            Map deviceCaps = getTargetDeviceCaps(target);
            return deviceCaps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map getTargetDeviceCaps(String TARGET) throws Exception {
        try {
            System.out.println("getTargetDeviceCaps(): Getting device capabilities of :" + TARGET);
            Map m = (Map) ((Map) readTargetCapsJson().get(TARGET)).get("capabilities");
            System.out.println("Capabilities:\n" + m);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("getTargetDeviceCaps(): Error while fetching the device capabilities map from the json");
        }
    }

    public static JSONObject readTargetCapsJson() {
        // parsing file "target_capabilities.json"
        try {
            JSONObject obj_target_capabilities = (JSONObject) new JSONParser().parse(new FileReader(Paths.get(Constants.ConfigurationFiles_Dir + "device_capabilities.json").toFile()));
            return obj_target_capabilities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CapsAndDriverOpts myGetCapabilities(String TARGET, DesiredCapabilities capabilities) {
        // Fetch the Device capabilities that are needed for the driver to be created
        CapsAndDriverOpts capsAndDriverOpts = this.targetCapabilities(TARGET, capabilities);
        return capsAndDriverOpts;
    }

    public CapsAndDriverOpts targetCapabilities(String TARGET, DesiredCapabilities capabilities) {

        try {
            Map deviceCaps = getTargetDeviceCaps(TARGET);
            String deviceNodeURL = this.getDeviceGridNodeURL(TARGET);
            if ((deviceCaps != null) && (deviceNodeURL != null)) {
                // Device capabilities
                Iterator<Map.Entry> itr1 = deviceCaps.entrySet().iterator();
                while (itr1.hasNext()) {
                    Map.Entry pair = itr1.next();
                    capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                }

                System.out.println("==> Your Device/Browser Capabilities = " + capabilities.toString());
                System.out.println("==> Your Device/Browser URL = " + deviceNodeURL);
                //return new CapsAndDriverOpts(capabilities);
                return new CapsAndDriverOpts(capabilities, deviceNodeURL);
            } else System.out.println("Device capabilities & node url config should not be empty");
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("[FAIL] : Unable to create required Target Capabilities for = " + TARGET);
        return null;
    }

    public JSONObject readGridNodePropsJson() {
        // parsing file "nodeURL.json"
        try {
            JSONObject obj_selenium_grid_node = (JSONObject) new JSONParser().parse(new FileReader(Paths.get(Constants.ConfigurationFiles_Dir + "appium_service_urls.json").toFile()));
            return obj_selenium_grid_node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDeviceGridNodeURL(String TARGET) throws Exception {
        try {
            return (String) readGridNodePropsJson().get(TARGET);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("getDeviceGridNodeURL(): Error while fetching the device grid node url info");
        }
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void setTest(ExtentTest test) {
        BaseTest.test = test;
    }
}
