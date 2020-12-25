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
public class DisciplineDTO implements Serializable{
    private int disciplineId;
    private String disciplineName;
    private String majorId;

    public DisciplineDTO() {
    }

    public DisciplineDTO(int disciplineId, String disciplineName, String majorId) {
        this.disciplineId = disciplineId;
        this.disciplineName = disciplineName;
        this.majorId = majorId;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }
    
}
