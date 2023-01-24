package TestListener;

import org.testng.IAlterSuiteListener;
import org.testng.xml.*;
import src.main.core.utils.ReadWriteProperties;
import src.main.datamodels.TestDataHelper;
import src.main.datamodels.sheets.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Listener implements IAlterSuiteListener {

    @Override
    public void alter(List suites) {
        XmlSuite suite = (XmlSuite) suites.get(0);

        List<XmlTest> xmlTestList = new ArrayList<>();
        List<XmlPackage> packageList = new ArrayList();
        List<XmlInclude> xmlIncludeList = new ArrayList<>();

        try {
            // Set test thread pool size
            //suite.setThreadCount(Integer.parseInt((ReadWriteProperties.props.getProperty("testng.setSuiteThreadPoolSize"))));
            String excelFilePath = System.getProperty("user.dir") + "/" +
                    ReadWriteProperties.props.getProperty("test.data.dir") + "/" +
                    ReadWriteProperties.props.getProperty("test.selection.file");

            TestDataHelper testDataHelper = TestDataHelper.getInstance();
            testDataHelper.loadExcelSheet(excelFilePath);
            for (Data data : testDataHelper.getDataList()) {
                // Check if the device is selected
                if (data.getSelected().equalsIgnoreCase("yes")) {
                    List<XmlClass> xmlClassList = new ArrayList<>();
                    XmlTest xmlTest = new XmlTest(suite);
                    // Set name and parameters
                    xmlTest.addParameter("Device", data.getDevice());
                    xmlTest.addParameter("UserName", data.getUserName());
                    xmlTest.addParameter("Password", data.getPassword());
                    XmlClass xmlClass = new XmlClass(data.getClassName());
                    List<XmlInclude> xmlIncludes = new ArrayList<>();
                    xmlIncludes.add(new XmlInclude(data.getTestCase()));
                    xmlClass.setIncludedMethods(xmlIncludes);
                    System.out.println("xmlClass-->" + xmlClassList.toString());
                    xmlTest.setClasses(xmlClassList);
                    xmlClassList.add(xmlClass);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf(suite.toXml());
    }
}
