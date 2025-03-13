package org.hellgren.utilities.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WorkBookPrinter {

    public static void print(Workbook wb) {
        for (Sheet sheet : wb) {
            System.out.println("Sheet: " + sheet.getSheetName()); // Print sheet name
            System.out.println("--------------------------------");

            // Get the maximum number of columns in the sheet
            int maxColumns = 0;
            for (Row row : sheet) {
                if (row.getLastCellNum() > maxColumns) {
                    maxColumns = row.getLastCellNum();
                }
            }

            // Iterate through all rows
            for (Row row : sheet) {
                // Iterate through all cells in the row
                for (int i = 0; i < maxColumns; i++) {
                    Cell cell = row.getCell(i);
                    String value = "";
                    if (cell != null) {
                        // Print cell value based on type
                        switch (cell.getCellType()) {
                            case STRING:
                                value = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                value = String.valueOf(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                value = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                value = cell.getCellFormula();
                                break;
                            case BLANK:
                                value = "BLANK";
                                break;
                            default:
                                value = "UNKNOWN";
                        }
                    }
                    System.out.printf("%-20s", value); // Print with fixed width
                }
                System.out.println(); // New line after each row
            }
            System.out.println("--------------------------------\n");
        }

    }
}
