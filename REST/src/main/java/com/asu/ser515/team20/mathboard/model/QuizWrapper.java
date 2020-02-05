package com.asu.ser515.team20.mathboard.model;

import java.util.List;

public class QuizWrapper {

    private String grade;
    private List<Quiz> quizzes;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
