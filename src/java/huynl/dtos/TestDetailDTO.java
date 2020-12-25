/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Huy Nguyen
 */
public class TestDetailDTO implements Serializable{
    private int testDetailId;
    private String testId;
    private String userDo;
    private double mark;
    private Date finishedTime;

    public TestDetailDTO() {
    }

    public TestDetailDTO(int testDetailId, String testId, String userDo, double mark, Date finishedTime) {
        this.testDetailId = testDetailId;
        this.testId = testId;
        this.userDo = userDo;
        this.mark = mark;
        this.finishedTime = finishedTime;
    }

    public int getTestDetailId() {
        return testDetailId;
    }

    public void setTestDetailId(int testDetailId) {
        this.testDetailId = testDetailId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getUserDo() {
        return userDo;
    }

    public void setUserDo(String userDo) {
        this.userDo = userDo;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }
    
}
