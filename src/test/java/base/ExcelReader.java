package base;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;

public class ExcelReader {
    
    public static Object[][] getTestData(String filePath, String sheetName) {
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            
            // Count only rows that have data, skip empty rows
            int lastRowNum = sheet.getLastRowNum();
            int colNum = sheet.getRow(0).getLastCellNum();
            
            // First count how many REAL data rows (excluding empty)
            int dataRowCount = 0;
            for(int i=1; i<=lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if(row != null && row.getCell(0) != null && !row.getCell(0).toString().trim().isEmpty()) {
                    dataRowCount++;
                }
            }
            
            System.out.println("Total rows with data: " + dataRowCount);
            
            Object[][] data = new Object[dataRowCount][colNum];
            
            int dataIndex = 0;
            for(int i=1; i<=lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if(row == null) continue; // Skip empty row
                if(row.getCell(0) == null || row.getCell(0).toString().trim().isEmpty()) continue; // Skip if first cell empty
                
                for(int j=0; j<colNum; j++) {
                    Cell cell = row.getCell(j);
                    if(cell == null) {
                        data[dataIndex][j] = "";
                    } else {
                        data[dataIndex][j] = cell.toString();
                    }
                }
                dataIndex++;
            }
            
            workbook.close();
            fis.close();
            return data;
            
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}