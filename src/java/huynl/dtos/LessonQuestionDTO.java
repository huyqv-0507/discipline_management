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
public class LessonQuestionDTO implements Serializable{
    private int lessonQuestionId;
    private int lessonId;
    private int questionId;
    private String lessonTitle;
    private String questionDescription;

    public LessonQuestionDTO() {
    }

    public LessonQuestionDTO(int lessonId, int questionId, String lessonTitle, String questionDescription) {
        this.lessonId = lessonId;
        this.questionId = questionId;
        this.lessonTitle = lessonTitle;
        this.questionDescription = questionDescription;
    }

    public int getLessonQuestionId() {
        return lessonQuestionId;
    }

    public void setLessonQuestionId(int lessonQuestionId) {
        this.lessonQuestionId = lessonQuestionId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }
    
}
