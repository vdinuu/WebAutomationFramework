package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtil {
    public static Map<String, Map<String, Object>> getExcelData(String fileName, String sheetName)  {
        List<String> columnHeader = new ArrayList<>();
        Map<String, Object> rowDataMap;
        Map<String, Map<String, Object>> testDataMap = new HashMap<>();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/test/resources/testdata/"+fileName);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(0);
            Iterator<Cell> cellIterator = row.iterator();
            while (cellIterator.hasNext()) {
                columnHeader.add(cellIterator.next().getStringCellValue());
            }
            int rowCount = sheet.getLastRowNum();
            int columnCount = row.getLastCellNum();
            String testCaseName = "";
            for (int i = 1; i <= rowCount; i++) {
                Row row1 = sheet.getRow(i);
                rowDataMap = new HashMap<>();
                for (int j = 0; j < columnCount; j++) {
                    Cell cell = row1.getCell(j);
                    if (j == 0) {
                        testCaseName = cell.getStringCellValue().trim();
                    } else {
                        switch (cell.getCellType()){
                            case STRING -> rowDataMap.put(columnHeader.get(j), cell.getStringCellValue().trim());
                            case NUMERIC -> rowDataMap.put(columnHeader.get(j), cell.getNumericCellValue());
                            case BOOLEAN -> rowDataMap.put(columnHeader.get(j), cell.getBooleanCellValue());
                        }
                    }
                }
                testDataMap.put(testCaseName, rowDataMap);
            }

        } catch (IOException ignored) {
        }
        return testDataMap;
    }

    public static void updateExcelData(String path, String sheetName, String testName, int cellNum, String value)  {
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(0);
            int rowCount = sheet.getLastRowNum();
            int columnCount = row.getLastCellNum();
            String testCaseName;
            int rowTst = 0;
            for (int i = 1; i <= rowCount; i++) {
                Row row1 = sheet.getRow(i);
                for (int j = 0; j < columnCount; j++) {
                    if (j == 0) {
                        Cell cell = row1.getCell(j);
                        testCaseName = cell.getStringCellValue().trim();
                        if (testCaseName.equals(testName)) {
                            rowTst = i;
                            break;
                        }
                    }
                }
            }
            Cell cell = sheet.getRow(rowTst).getCell(cellNum);
            cell.setCellValue(value);
            inputStream.close();
            FileOutputStream fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

