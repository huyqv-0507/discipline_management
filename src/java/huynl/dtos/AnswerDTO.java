/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.dtos;

import java.io.Serializable;

/**
 *
 * @author Huy Nguyen
 */
public class AnswerDTO implements Serializable{
    private int answerId;
    private String description;
    private boolean isCorrect;
    private int questionId;
    

    public AnswerDTO() {
    }

    public AnswerDTO(String description, boolean isCorrect, int questionId) {
        this.description = description;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }

    public AnswerDTO(int answerId, String description, boolean isCorrect, int questionId) {
        this.answerId = answerId;
        this.description = description;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }
    

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    
}
