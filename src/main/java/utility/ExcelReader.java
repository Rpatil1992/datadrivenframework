package utility;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

public class ExcelReader {

    private String filePath = "C:\\Users\\rahul\\Downloads\\Legislation.xlsx";
    private String sheetName = "Legislation";

    public String[][] getData() {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("❌ Sheet not found: " + sheetName);
            }

            int rowCount = sheet.getPhysicalNumberOfRows();

            // ❗ Only first 2 columns
            int colCount = 2;

            String[][] data = new String[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    data[i - 1][j] = cell.toString();
                }
            }

            return data;

        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage());
        }
    }
}
