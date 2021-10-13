package com.nit.textfile2excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    private Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }

    public void writeDataToExcel(List<EmployeeData> employeeDataList, String excelFilePath) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowNum = 0;

        for (EmployeeData employeeData : employeeDataList) {
            Row row = sheet.createRow(rowNum++);
            writeEmployeeData(employeeData, row);
        }
        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }

    private void writeEmployeeData(EmployeeData employeeData, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(employeeData.getFirstName());

        cell = row.createCell(1);
        cell.setCellValue(employeeData.getLastName());

        cell = row.createCell(2);
        cell.setCellValue(employeeData.getAge());

        cell = row.createCell(3);
        cell.setCellValue(employeeData.getRole());

        cell = row.createCell(4);
        cell.setCellValue(employeeData.getHobby());
    }

}
