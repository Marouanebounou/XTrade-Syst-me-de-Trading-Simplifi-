package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExcelGenerator {
    private ExcelGenerator(){

    }
    public static void execute(){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        generateHeader(sheet ,workbook);
        setColumnWidth(sheet);
        SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Transaction> transactions = Platform.getTransactions();
        for (int i = 0 ;i < transactions.size() ; i++){
            Row row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(transactions.get(i).getId());
            row.createCell(1).setCellValue(transactions.get(i).getTrader().getName() + " ID : " + transactions.get(i).getId());
            row.createCell(2).setCellValue(transactions.get(i).getAsset().getName() + " ID : " + transactions.get(i).getId());
            row.createCell(3).setCellValue(transactions.get(i).getType());
            row.createCell(4).setCellValue(transactions.get(i).getQuantity());
            row.createCell(5).setCellValue(transactions.get(i).getPrice().doubleValue());
            row.createCell(6).setCellValue(smp.format(transactions.get(i).getDate()));
        }
        writeFile(workbook);
    }
    private static void generateHeader(Sheet sheet , Workbook workbook){
        Row row = sheet.createRow(0);
        String[] header = {"Id" , "Trader" , "Asset" , "Type" , "Quantity" , "Unit Price" , "Date"};
        for (int i = 0 ; i < header.length ; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
            cell.setCellStyle(getCellStyle(workbook));
        }
    }
    private static CellStyle getCellStyle(Workbook workbook){
        CellStyle headerStyle = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        headerStyle.setFont(boldFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        return headerStyle;
    }

    private static void writeFile(Workbook workbook){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("transactionhistory.xlsx");
            workbook.write(fileOutputStream);
            workbook.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void setColumnWidth(Sheet sheet){

            sheet.setColumnWidth(0 , 5_000);
            sheet.setColumnWidth(1 , 5_000);
            sheet.setColumnWidth(2 , 5_000);
            sheet.setColumnWidth(3 , 5_000);
            sheet.setColumnWidth(4 , 5_000);
            sheet.setColumnWidth(5 , 5_000);
            sheet.setColumnWidth(6 , 5_000);

    }
}
