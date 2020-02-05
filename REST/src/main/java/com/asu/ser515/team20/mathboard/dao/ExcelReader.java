package com.asu.ser515.team20.mathboard.dao;

import com.asu.ser515.team20.mathboard.model.Quiz;
import com.asu.ser515.team20.mathboard.model.QuizWrapper;
import com.asu.ser515.team20.mathboard.model.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExcelReader {

    public User searchUser(String userID, String password) {

        String SAMPLE_XLSX_FILE_PATH = "sample-xlsx-file.xlsx";
        User presentUser = new User();
        try {
            Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
            Sheet sheet = workbook.getSheet("Users");
            for (Row row : sheet) {
                Cell cellUser = row.getCell(0);
                if (cellUser.getStringCellValue().equals(userID)) {
                    Cell cellPass = row.getCell(5);
                    if (cellPass.getStringCellValue().equals(password)) {
                        presentUser.setUserid(row.getCell(0).getStringCellValue());
                        presentUser.setName(row.getCell(1).getStringCellValue());
                        presentUser.setEmail(row.getCell(2).getStringCellValue());
                        presentUser.setRole(row.getCell(3).getStringCellValue());
                        presentUser.setGrade(row.getCell(4).getStringCellValue());
                        presentUser.setPassword(row.getCell(5).getStringCellValue());
                    }
                }
            }
            workbook.close();
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return presentUser;
    }

    public User searchUserForDelete(String userID) {

        String SAMPLE_XLSX_FILE_PATH = "sample-xlsx-file.xlsx";
        User presentUser = new User();
        try {
            Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
            Sheet sheet = workbook.getSheet("Users");
            for (Row row : sheet) {
                Cell cellUser = row.getCell(0);
                if (cellUser.getStringCellValue().equals(userID)) {
                    presentUser.setUserid(row.getCell(0).getStringCellValue());
                    presentUser.setName(row.getCell(1).getStringCellValue());
                    presentUser.setEmail(row.getCell(2).getStringCellValue());
                    presentUser.setRole(row.getCell(3).getStringCellValue());
                    presentUser.setGrade(row.getCell(4).getStringCellValue());
                    presentUser.setPassword(row.getCell(5).getStringCellValue());
                 }
            }
            workbook.close();
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return presentUser;
    }

    public QuizWrapper getQuizzes(String grade){
        QuizWrapper quizWrapper = new QuizWrapper();
        List<Quiz> quizzes = new ArrayList<>();
        quizWrapper.setQuizzes(quizzes);
        quizWrapper.setGrade(grade);

        String SAMPLE_XLSX_FILE_PATH = "sample-xlsx-file.xlsx";
        try {
            Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
            Sheet sheet = workbook.getSheet("Quizzes");
            for (Row row : sheet) {
                Cell cellUser = row.getCell(0);
                if (cellUser.getStringCellValue().equals(grade)) {
                    Quiz quiz = new Quiz();
                    quiz.setQuestion(row.getCell(1).getStringCellValue());
                    quiz.setAnswer(row.getCell(2).getStringCellValue());
                    quizzes.add(quiz);
                }
            }
            workbook.close();
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return quizWrapper;
    }
}
