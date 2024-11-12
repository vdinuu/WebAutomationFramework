package pages;

import utils.ExcelUtil;

import java.io.IOException;
import java.util.Map;

public class Tester {
    public static void main(String[] args) throws IOException {
        String env = System.getProperty("env") == null ? "QA": System.getProperty("env");
        Map<String, Map<String, Object>> data = ExcelUtil.getExcelData(env+"/TestData.xlsx", "Sheet1");
        System.out.println(data.get("Verify add to cart"));

    }
}
