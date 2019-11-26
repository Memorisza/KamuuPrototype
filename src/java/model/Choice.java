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
public class Choice {
    private int choiceId;
    private String choiceAns;
    private boolean isRightChoice;
    private int questionId;

    public Choice(int choiceId, String choiceAns, boolean isRightChoice, int questionId) {
        this.choiceId = choiceId;
        this.choiceAns = choiceAns;
        this.isRightChoice = isRightChoice;
        this.questionId = questionId;
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public String getChoiceAns() {
        return choiceAns;
    }

    public void setChoiceAns(String choiceAns) {
        this.choiceAns = choiceAns;
    }

    public boolean isIsRightChoice() {
        return isRightChoice;
    }

    public void setIsRightChoice(boolean isRightChoice) {
        this.isRightChoice = isRightChoice;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Choice{" + "choiceId=" + choiceId + ", choiceAns=" + choiceAns + ", isRightChoice=" + isRightChoice + ", questionId=" + questionId + '}';
    }
    
    
}
