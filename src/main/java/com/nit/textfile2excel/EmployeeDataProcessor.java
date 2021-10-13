package com.nit.textfile2excel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDataProcessor {

    public static final String INPUT_FILE_PATH = System.getProperty("user.dir").concat("\\src\\main\\resources\\employeeInputFile.txt");
    public static final String OUTPUT_FILE_PATH = System.getProperty("user.dir").concat("\\src\\main\\resources\\employeeOutput.xls");

    public static void main(String[] args) throws IOException {

        System.out.println("EmployeeDataProcessor :: start");
        EmployeeDataProcessor employeeDataProcessor = new EmployeeDataProcessor();
        List<String> lineContentList = employeeDataProcessor.readFileContent(INPUT_FILE_PATH);
        List<EmployeeData> employeeDataList = employeeDataProcessor.processEmployeeData(lineContentList);
        ExcelWriter excelWriter = new ExcelWriter();
        excelWriter.writeDataToExcel(employeeDataList, OUTPUT_FILE_PATH);
        System.out.println("EmployeeDataProcessor :: completed");
    }

    public List<String> readFileContent(String fileName) {

        BufferedReader bufferedReader = null;
        List<String> lineContentList = null;
        Map<String, String> dataMap = new HashMap<>();
        try {
            System.out.println("Reading the file Started for " + fileName);
            lineContentList = new ArrayList<>();
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String lineFromFile;
            int lineNUmber = 0;
            while ((lineFromFile = bufferedReader.readLine()) != null) {
                //skip the header line
               /* if(lineNUmber == 0) {
                    lineNUmber++;
                    continue;
                }
                lineNUmber++;*/

                //Add the line from File data to List
                lineContentList.add(lineFromFile);
            }
            System.out.println("Reading the file completed for " + fileName);
        } catch (Exception e) {
            System.out.println("Error occurred while reading file " + fileName + "\t" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return lineContentList;
    }

    public List<EmployeeData> processEmployeeData(List<String> lineContentList) {
        List<EmployeeData> employeeDataList = null;
        try {
            System.out.println("Processing of employee data started...");
            employeeDataList = new ArrayList<>();
            for (String lineContent : lineContentList) {
                String[] lineContentData = lineContent.split(",");
                EmployeeData employeeData = new EmployeeData();
                employeeData.setFirstName(lineContentData[0]);
                employeeData.setLastName(lineContentData[1]);
                employeeData.setAge(lineContentData[2]);
                employeeData.setRole(lineContentData[3]);
                employeeData.setHobby(lineContentData[4]);
                employeeDataList.add(employeeData);
            }
            System.out.println("Processing of employee data completed...");
        } catch (Exception e) {
            System.out.println("Error occurred while processing employee data :: " + e.getMessage());
            e.printStackTrace();
        }
        return employeeDataList;
    }
}
