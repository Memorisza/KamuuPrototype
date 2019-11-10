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
public class Quiz {
    private int quizId;
    private String quizName;
    private boolean isActive;
    private int teacherId;

    public Quiz(int quizId, String quizName, boolean isActive, int teacherId) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.isActive = isActive;
        this.teacherId = teacherId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Quiz{" + "quizId=" + quizId + ", quizName=" + quizName + ", isActive=" + isActive + ", teacherId=" + teacherId + '}';
    }
    
    
}
