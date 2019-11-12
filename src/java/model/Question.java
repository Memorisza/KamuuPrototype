/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Win 10
 */
public class Question {
    private int questionId;
    private String questionTitle;
    private int quizId;

    public Question(int questionId, String questionTitle, int quizId) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.quizId = quizId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    @Override
    public String toString() {
        return "Question{" + "questionId=" + questionId + ", questionTitle=" + questionTitle + ", quizId=" + quizId + '}';
    }
    
    
}
