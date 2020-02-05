package com.asu.ser515.team20.mathboard.dao;


import com.asu.ser515.team20.mathboard.model.Quiz;
import com.asu.ser515.team20.mathboard.model.QuizWrapper;
import com.asu.ser515.team20.mathboard.model.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExcelWriter {

    public boolean addUser(User user) {
        try {

            List<String> details = new ArrayList<>();
            details.add(user.getUserid());
            details.add(user.getName());
            details.add(user.getEmail());
            details.add(user.getRole());
            details.add(user.getGrade());
            details.add(user.getPassword());

            String SAMPLE_XLSX_FILE_PATH = "sample-xlsx-file.xlsx";
            FileInputStream inputStream = new FileInputStream(SAMPLE_XLSX_FILE_PATH);
            int numberOfcolumns = details.size();
            // Obtain a workbook from the excel file
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("Users");
            if (sheet != null) {
                int rowCount = sheet.getLastRowNum();
                Row row = sheet.createRow(rowCount + 1);
                for (int i = 0; i < numberOfcolumns; i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(details.get(i));
                }
                inputStream.close();
                FileOutputStream fileOut = new FileOutputStream(SAMPLE_XLSX_FILE_PATH);
                workbook.write(fileOut);
                fileOut.close();

                // Closing the workbook
                workbook.close();
            }

        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean DeleteUser(String userId) {
        try {

            String SAMPLE_XLSX_FILE_PATH = "./sample-xlsx-file.xlsx";  // push this to app.properties
            FileInputStream inputStream = new FileInputStream(SAMPLE_XLSX_FILE_PATH);
            // Obtain a workbook from the excel file
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("Users");
            if (sheet != null) {
                int rowNoForDeletion = searchUserForDeletion(userId);
                Row row = sheet.getRow(rowNoForDeletion);
                sheet.removeRow(row);
                inputStream.close();
                FileOutputStream fileOut = new FileOutputStream(SAMPLE_XLSX_FILE_PATH);
                workbook.write(fileOut);
                fileOut.close();
                // Closing the workbook
                workbook.close();
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private static int searchUserForDeletion(String user) {
        try {
            String SAMPLE_XLSX_FILE_PATH = "./sample-xlsx-file.xlsx";

            Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
            Sheet sheet = workbook.getSheet("Users");
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet) {
                Cell cellUser = row.getCell(0);
                if (cellUser.getStringCellValue().equals(user)) {
                    return row.getRowNum();
                }
            }
            workbook.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;

    }

    public boolean addQuizzes(QuizWrapper quizWrapper) {
        List<String> details;
        String grade = quizWrapper.getGrade();
        for (Quiz quiz : quizWrapper.getQuizzes()) {
            try {
                details = new ArrayList<>();
                details.add(grade);
                details.add(quiz.getQuestion());
                details.add(quiz.getAnswer());

                String SAMPLE_XLSX_FILE_PATH = "sample-xlsx-file.xlsx";
                FileInputStream inputStream = new FileInputStream(SAMPLE_XLSX_FILE_PATH);
                int numberOfcolumns = details.size();
                // Obtain a workbook from the excel file
                Workbook workbook = WorkbookFactory.create(inputStream);
                Sheet sheet = workbook.getSheet("Quizzes");
                if (sheet != null) {
                    int rowCount = sheet.getLastRowNum();
                    Row row = sheet.createRow(rowCount + 1);
                    for (int i = 0; i < numberOfcolumns; i++) {
                        Cell cell = row.createCell(i);
                        cell.setCellValue(details.get(i));
                    }
                    inputStream.close();
                    FileOutputStream fileOut = new FileOutputStream(SAMPLE_XLSX_FILE_PATH);
                    workbook.write(fileOut);
                    fileOut.close();

                    // Closing the workbook
                    workbook.close();
                }

            } catch (InvalidFormatException | IOException e) {
                e.printStackTrace();
                return false;
            }

        }
        return true;
    }
}

