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
public class DisciplineLessonDTO implements Serializable{
    private int disciplineLessonId;
    private int disciplineId;
    private int lessonId;
    private String disciplineName;
    private String lessonTitle;

    public DisciplineLessonDTO() {
    }

    public DisciplineLessonDTO(int disciplineId, int lessonId, String disciplineName, String lessonTitle) {
        this.disciplineId = disciplineId;
        this.lessonId = lessonId;
        this.disciplineName = disciplineName;
        this.lessonTitle = lessonTitle;
    }

    
    public DisciplineLessonDTO(int disciplineLessonId, int disciplineId, int lessonId, String disciplineName, String lessonTitle) {
        this.disciplineLessonId = disciplineLessonId;
        this.disciplineId = disciplineId;
        this.lessonId = lessonId;
        this.disciplineName = disciplineName;
        this.lessonTitle = lessonTitle;
    }

    public int getDisciplineLessonId() {
        return disciplineLessonId;
    }

    public void setDisciplineLessonId(int disciplineLessonId) {
        this.disciplineLessonId = disciplineLessonId;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    
}
