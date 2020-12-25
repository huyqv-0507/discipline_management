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
public class TestDTO implements Serializable{
    private String testId;
    private Date createdTime;
    private String lessonId;
    private int questionId;
    private String question;
    private String answerAId;
    private String answerBId;
    private String answerCId;
    private String answerDId;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private int isCorrect;

    public TestDTO() {
    }

    public TestDTO(String testId, Date createdTime, String lessonId, int questionId, String question, String answerAId, String answerBId, String answerCId, String answerDId, String answerA, String answerB, String answerC, String answerD, int isCorrect) {
        this.testId = testId;
        this.createdTime = createdTime;
        this.lessonId = lessonId;
        this.questionId = questionId;
        this.question = question;
        this.answerAId = answerAId;
        this.answerBId = answerBId;
        this.answerCId = answerCId;
        this.answerDId = answerDId;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.isCorrect = isCorrect;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerAId() {
        return answerAId;
    }

    public void setAnswerAId(String answerAId) {
        this.answerAId = answerAId;
    }

    public String getAnswerBId() {
        return answerBId;
    }

    public void setAnswerBId(String answerBId) {
        this.answerBId = answerBId;
    }

    public String getAnswerCId() {
        return answerCId;
    }

    public void setAnswerCId(String answerCId) {
        this.answerCId = answerCId;
    }

    public String getAnswerDId() {
        return answerDId;
    }

    public void setAnswerDId(String answerDId) {
        this.answerDId = answerDId;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }
    
}
