package com.asu.ser515.team20.mathboard.service;

import com.asu.ser515.team20.mathboard.dao.ExcelReader;
import com.asu.ser515.team20.mathboard.dao.ExcelWriter;
import com.asu.ser515.team20.mathboard.model.QuizWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherPortalService {

    @Autowired
    private ExcelWriter excelWriter;

    @Autowired
    private ExcelReader excelReader;

    public boolean addQuizzes(QuizWrapper quizWrapper) {
        return excelWriter.addQuizzes(quizWrapper);
    }

    public QuizWrapper getQuizBasedOnGrade(String grade){
        return excelReader.getQuizzes(grade);
    }
}
