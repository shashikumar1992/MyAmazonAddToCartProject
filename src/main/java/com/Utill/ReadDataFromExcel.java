package com.Utill;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

/**
 * Utility class for reading test data from Excel files using Apache POI.
 */
public class ReadDataFromExcel {

    /**
     * Reads test data from the specified sheet of the Excel file.
     *
     * @param sheetName the name of the sheet from which to read data
     * @return a two-dimensional Object array containing the cell values (excluding header row)
     */
    public static Object[][] readDataExcel(String sheetName) {
        Object[][] data = null;
        String filepath = System.getProperty("user.dir").concat("//src/main/resources/TestData//TestData.xlsx");

        try (FileInputStream file = new FileInputStream(filepath)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetName);
            int lastRow = sheet.getLastRowNum(); // number of rows with data (0-based)
            int lastCell = sheet.getRow(0).getLastCellNum(); // number of columns in header row

            data = new Object[lastRow][lastCell]; // skip header row, so [lastRow] not [lastRow + 1]

            for (int i = 1; i <= lastRow; i++) {  // start from row 1 (skipping headers)
                Row row = sheet.getRow(i);
                for (int j = 0; j < lastCell; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = getCellValue(cell);
                }
            }

        }
            catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Converts a Cell object to a corresponding Java object based on its type.
     *
     * @param cell the Excel cell to convert
     * @return the Java representation of the cell's value
     */
    private static Object getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return DateUtil.isCellDateFormatted(cell) ? cell.getDateCellValue() : cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}
