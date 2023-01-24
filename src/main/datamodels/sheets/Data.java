package src.main.datamodels.sheets;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

public class Data {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("Device")
    private String Device;

    @ExcelCellName("TestCase")
    private String TestCase;

    @ExcelCellName("Selected")
    private String Selected;

    @ExcelCellName("UserName")
    private String UserName;

    @ExcelCellName("Password")
    private String Password;

    @ExcelCellName("ClassName")
    private String ClassName;

    public String getTestCase() {
        return TestCase;
    }

    public void setTestCase(String testCase) {
        TestCase = testCase;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSelected() {
        return Selected;
    }

    public void setSelected(String selected) {
        Selected = selected;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
}
