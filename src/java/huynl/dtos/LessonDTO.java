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
public class LessonDTO implements Serializable{
    private int lessonId;
    private String lessonTitle;
    private String lessonContent;
    private String lessonUrl;
    private String department;
    private String testId;

    public LessonDTO() {
    }

    public LessonDTO(int lessonId, String lessonTitle) {
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
    }
    

    public LessonDTO(int lessonId, String lessonTitle, String lessonContent, String lessonUrl, String department, String testId) {
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
        this.lessonContent = lessonContent;
        this.lessonUrl = lessonUrl;
        this.department = department;
        this.testId = testId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonContent() {
        return lessonContent;
    }

    public void setLessonContent(String lessonContent) {
        this.lessonContent = lessonContent;
    }

    public String getLessonUrl() {
        return lessonUrl;
    }

    public void setLessonUrl(String lessonUrl) {
        this.lessonUrl = lessonUrl;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    
}
