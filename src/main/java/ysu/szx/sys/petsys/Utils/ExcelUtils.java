package ysu.szx.sys.petsys.Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static void test() {
        try {
            FileInputStream excelFile = new FileInputStream(new File("E:\\YSUACMOJ\\YSUACMOJ\\src\\main\\resources\\ScoreFile\\Rank-test.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            System.out.println(cell.toString());
            workbook.close();
            excelFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void getCompRank(File file) {
        try {
            FileInputStream excelFile = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                System.out.print(row.getRowNum());
                if (row.getRowNum() <= 1) continue;
                // 遍历所有单元格
                for (Cell cell : row) {
                    // 输出单元格的值
                    System.out.print(cell.getStringCellValue() + "\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}