package src.main.datamodels;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.poiji.bind.Poiji;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import src.main.datamodels.sheets.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TestDataHelper {

    private static TestDataHelper testDataHelper;
    private List<Data> dataList;

    public static TestDataHelper getInstance() throws IOException {
        if (testDataHelper == null) {
            testDataHelper = new TestDataHelper();
        }
        return testDataHelper;
    }

    public void loadExcelSheet(String excelFilePath) {
        try {
            FileInputStream fs = new FileInputStream(excelFilePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            Sheet deviceSelectionWorkBook = workbook.getSheet("Data");
            dataList = Poiji.fromExcel(deviceSelectionWorkBook, Data.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public void readDataFromJsonFile(String suitType, String commandInput) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject inputObj;
        if (suitType.equalsIgnoreCase("JsonString")) {
            inputObj = (JsonObject) parser.parse(commandInput);
        } else {
            inputObj = (JsonObject) parser.parse(new FileReader(commandInput));
        }
        JsonArray deviceSelectionJsonArr = inputObj.getAsJsonArray("Data");
        dataList = gson.fromJson(deviceSelectionJsonArr, new TypeToken<List<Data>>() {
        }.getType());
    }
}
