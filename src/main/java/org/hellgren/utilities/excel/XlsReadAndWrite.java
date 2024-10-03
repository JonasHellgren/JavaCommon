package org.hellgren.utilities.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class XlsReadAndWrite {

    public static void addRow(Sheet sheet, int ri, List<String> values) {
        Row row = sheet.createRow(ri);
        IntStream.range(0, values.size())
                .forEach(i -> row.createCell(i).setCellValue(values.get(i)));
    }

    public static void writeToFile(Workbook workbook, String resPath, String resultFile) {
        String filePath = new File(resPath, resultFile).getAbsolutePath();
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
