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
public class Answer {
    private int transId;
    private int userId;
    private int quizId;
    private int questionId;
    private int choiceId;
    private boolean isRight;

    public Answer(int transId, int userId, int quizId, int questionId, int choiceId, boolean isRight) {
        this.transId = transId;
        this.userId = userId;
        this.quizId = quizId;
        this.questionId = questionId;
        this.choiceId = choiceId;
        this.isRight = isRight;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public boolean isIsRight() {
        return isRight;
    }

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

    
}
