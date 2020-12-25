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
public class QuestionDTO implements Serializable{
    private int questionId;
    private String desciption;

    public QuestionDTO() {
    }

    public QuestionDTO(String desciption) {
        this.desciption = desciption;
    }

    
    public QuestionDTO(int questionId, String desciption) {
        this.questionId = questionId;
        this.desciption = desciption;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

}
