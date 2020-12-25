/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import huynl.dtos.AnswerDTO;
import huynl.dtos.QuestionDTO;
import huynl.services.AnswerService;
import huynl.services.QuestionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Huy Nguyen
 */
@Namespace("/questionManagement")
public class QuestionManagementAction extends ActionSupport implements IBaseAction {

    private String questionDescription;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String isCorrectAnswer;

    private int keyEdit;

    public QuestionManagementAction() {
    }

    @Action(value = "saveEdit", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/questionManagement.jsp")
        ,
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")})
    public String saveEdit() throws Exception {
        QuestionService questionService = new QuestionService();
        QuestionDTO question = new QuestionDTO();
        question.setDesciption(questionDescription);
        question.setQuestionId(keyEdit);
        QuestionDTO tmpQuestion = questionService.getQuestionByQuestionId(keyEdit);
        System.out.println("questionDescription: " + questionDescription);
        System.out.println("keyEdit: " + keyEdit);
        boolean result = questionService.update(question);
        if (!result) {
            return ActionSupport.ERROR;
        }

        AnswerService answerService = new AnswerService();
        List<AnswerDTO> answers = answerService.getAnswersByQuestion(keyEdit);

        Map<String, List<AnswerDTO>> questionMap = (Map<String, List<AnswerDTO>>) ActionContext.getContext().getSession().get("QBANK");
        String tmpKey = "";
        AnswerDTO tmpAnswerA = null, tmpAnswerB = null, tmpAnswerC = null, tmpAnswerD = null;
        this.answerA = "A. " + this.answerA;
        this.answerB = "B. " + this.answerB;
        this.answerC = "C. " + this.answerC;
        this.answerD = "D. " + this.answerD;

        boolean resultAnswer = false;
        if (isCorrectAnswer.equals("A")) {
            AnswerDTO a = new AnswerDTO(this.answerA.trim(), true, keyEdit);
            AnswerDTO b = new AnswerDTO(this.answerB.trim(), false, keyEdit);
            AnswerDTO c = new AnswerDTO(this.answerC.trim(), false, keyEdit);
            AnswerDTO d = new AnswerDTO(this.answerD.trim(), false, keyEdit);
            resultAnswer = answerService.updateQA(a, answers.get(0).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(b, answers.get(1).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(c, answers.get(2).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(d, answers.get(3).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }

            for (Map.Entry<String, List<AnswerDTO>> entry : questionMap.entrySet()) {
                String key = entry.getKey();
                List<AnswerDTO> value = entry.getValue();
                if (key.equals(tmpQuestion.getDesciption())) {
                    tmpKey = key;
                    tmpAnswerA = value.get(0);
                    tmpAnswerB = value.get(1);
                    tmpAnswerC = value.get(2);
                    tmpAnswerD = value.get(3);
                    tmpAnswerA.setDescription(this.answerA);
                    tmpAnswerB.setDescription(this.answerB);
                    tmpAnswerC.setDescription(this.answerC);
                    tmpAnswerD.setDescription(this.answerD);
                    tmpAnswerA.setIsCorrect(true);
                    tmpAnswerB.setIsCorrect(false);
                    tmpAnswerC.setIsCorrect(false);
                    tmpAnswerD.setIsCorrect(false);
                }
            }
        } else if (isCorrectAnswer.equals("B")) {
            System.out.println("1");
            AnswerDTO a = new AnswerDTO(this.answerA.trim(), false, keyEdit);
            AnswerDTO b = new AnswerDTO(this.answerB.trim(), true, keyEdit);
            AnswerDTO c = new AnswerDTO(this.answerC.trim(), false, keyEdit);
            AnswerDTO d = new AnswerDTO(this.answerD.trim(), false, keyEdit);
            resultAnswer = answerService.updateQA(a, answers.get(0).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(b, answers.get(1).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(c, answers.get(2).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(d, answers.get(3).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            for (Map.Entry<String, List<AnswerDTO>> entry : questionMap.entrySet()) {
                String key = entry.getKey();
                List<AnswerDTO> value = entry.getValue();
                if (key.equals(tmpQuestion.getDesciption())) {
                    tmpKey = key;
                    tmpAnswerA = value.get(0);
                    tmpAnswerB = value.get(1);
                    tmpAnswerC = value.get(2);
                    tmpAnswerD = value.get(3);
                    tmpAnswerA.setDescription(this.answerA);
                    tmpAnswerB.setDescription(this.answerB);
                    tmpAnswerC.setDescription(this.answerC);
                    tmpAnswerD.setDescription(this.answerD);
                    tmpAnswerA.setIsCorrect(false);
                    tmpAnswerB.setIsCorrect(true);
                    tmpAnswerC.setIsCorrect(false);
                    tmpAnswerD.setIsCorrect(false);
                }
            }
            questionMap.remove(tmpKey);

        } else if (isCorrectAnswer.equals("C")) {
            AnswerDTO a = new AnswerDTO(this.answerA.trim(), false, keyEdit);
            AnswerDTO b = new AnswerDTO(this.answerB.trim(), false, keyEdit);
            AnswerDTO c = new AnswerDTO(this.answerC.trim(), true, keyEdit);
            AnswerDTO d = new AnswerDTO(this.answerD.trim(), false, keyEdit);
            resultAnswer = answerService.updateQA(a, answers.get(0).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(b, answers.get(1).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(c, answers.get(2).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(d, answers.get(3).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            for (Map.Entry<String, List<AnswerDTO>> entry : questionMap.entrySet()) {
                String key = entry.getKey();
                List<AnswerDTO> value = entry.getValue();
                if (key.equals(tmpQuestion.getDesciption())) {
                    tmpKey = key;
                    tmpAnswerA = value.get(0);
                    tmpAnswerB = value.get(1);
                    tmpAnswerC = value.get(2);
                    tmpAnswerD = value.get(3);
                    tmpAnswerA.setDescription(this.answerA);
                    tmpAnswerB.setDescription(this.answerB);
                    tmpAnswerC.setDescription(this.answerC);
                    tmpAnswerD.setDescription(this.answerD);
                    tmpAnswerA.setIsCorrect(false);
                    tmpAnswerB.setIsCorrect(false);
                    tmpAnswerC.setIsCorrect(true);
                    tmpAnswerD.setIsCorrect(false);
                }
            }

        } else if (isCorrectAnswer.equals("D")) {
            AnswerDTO a = new AnswerDTO(this.answerA.trim(), false, keyEdit);
            AnswerDTO b = new AnswerDTO(this.answerB.trim(), false, keyEdit);
            AnswerDTO c = new AnswerDTO(this.answerC.trim(), false, keyEdit);
            AnswerDTO d = new AnswerDTO(this.answerD.trim(), true, keyEdit);
            resultAnswer = answerService.updateQA(a, answers.get(0).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(b, answers.get(1).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(c, answers.get(2).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.updateQA(d, answers.get(3).getAnswerId());
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }

            for (Map.Entry<String, List<AnswerDTO>> entry : questionMap.entrySet()) {
                String key = entry.getKey();
                List<AnswerDTO> value = entry.getValue();
                if (key.equals(tmpQuestion.getDesciption())) {
                    tmpKey = key;
                    tmpAnswerA = value.get(0);
                    tmpAnswerB = value.get(1);
                    tmpAnswerC = value.get(2);
                    tmpAnswerD = value.get(3);
                    tmpAnswerA.setDescription(this.answerA);
                    tmpAnswerB.setDescription(this.answerB);
                    tmpAnswerC.setDescription(this.answerC);
                    tmpAnswerD.setDescription(this.answerD);
                    tmpAnswerA.setIsCorrect(false);
                    tmpAnswerB.setIsCorrect(false);
                    tmpAnswerC.setIsCorrect(false);
                    tmpAnswerD.setIsCorrect(true);
                }
            }

        }

        List<AnswerDTO> tmpAnswers = new ArrayList<>();
        tmpAnswers.add(tmpAnswerA);
        tmpAnswers.add(tmpAnswerB);
        tmpAnswers.add(tmpAnswerC);
        tmpAnswers.add(tmpAnswerD);
        questionMap.remove(tmpKey);

        questionMap.put(this.questionDescription, tmpAnswers);

        this.questionDescription = "";
        this.answerA = "";
        this.answerB = "";
        this.answerC = "";
        this.answerD = "";
        this.isCorrectAnswer = "A";
        return ActionSupport.SUCCESS;
    }

    @Action(value = "edit", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/editQA.jsp")
        ,
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")})
    public String edit() throws Exception {
        QuestionService questionService = new QuestionService();
        QuestionDTO question = questionService.getQuestionByQuestionId(keyEdit);

        Map request = (Map) ActionContext.getContext().get("request");
        request.put("QUESTION_EDIT", question);

        AnswerService answerService = new AnswerService();

        List<AnswerDTO> answers = answerService.getAnswersByQuestion(keyEdit);
        request.put("answerA", answers.get(0).getDescription().replace("A. ", "").replace("B. ", "").replace("C. ", "").replace("D. ", ""));
        request.put("answerB", answers.get(1).getDescription().replace("A. ", "").replace("B. ", "").replace("C. ", "").replace("D. ", ""));
        request.put("answerC", answers.get(2).getDescription().replace("A. ", "").replace("B. ", "").replace("C. ", "").replace("D. ", ""));
        request.put("answerD", answers.get(3).getDescription().replace("A. ", "").replace("B. ", "").replace("C. ", "").replace("D. ", ""));

        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).isIsCorrect() == true) {
                String tmp[] = answers.get(i).getDescription().split(". ");
                System.out.println("tmp0: " + tmp[0]);
                System.out.println("tmp1: " + tmp[1]);
                if (tmp[0].equals("A")) {
                    isCorrectAnswer = "A";
                    break;
                } else if (tmp[0].equals("B")) {
                    isCorrectAnswer = "B";
                    break;
                } else if (tmp[0].equals("C")) {
                    isCorrectAnswer = "C";
                    break;
                } else if (tmp[0].equals("D")) {
                    isCorrectAnswer = "D";
                    break;
                }
            }
        }

        return ActionSupport.SUCCESS;
    }

    @Action(value = "create", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/questionManagement.jsp")
        ,
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")
        ,
        @Result(name = ActionSupport.INPUT, location = "/questionManagement.jsp")
    })
    @Override
    public String create() throws Exception {
        //Insert to Question Table
        QuestionService questionService = new QuestionService();
        QuestionDTO question = new QuestionDTO(questionDescription);
        boolean resultQuestion = questionService.create(question);
        if (!resultQuestion) {
            return ActionSupport.ERROR;
        }
        boolean resultAnswer = false;
        int questionId = questionService.getQuestionIdByDescription(questionDescription);

        AnswerService answerService = new AnswerService();
        System.out.println("correct: " + isCorrectAnswer);

        this.answerA = "A. " + this.answerA;
        this.answerB = "B. " + this.answerB;
        this.answerC = "C. " + this.answerC;
        this.answerD = "D. " + this.answerD;

        if (isCorrectAnswer.equals("A")) {
            AnswerDTO a = new AnswerDTO(this.answerA.trim(), true, questionId);
            AnswerDTO b = new AnswerDTO(this.answerB.trim(), false, questionId);
            AnswerDTO c = new AnswerDTO(this.answerC.trim(), false, questionId);
            AnswerDTO d = new AnswerDTO(this.answerD.trim(), false, questionId);
            resultAnswer = answerService.create(a);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(b);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(c);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(d);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            List<AnswerDTO> answers = new ArrayList<>();
            answers.add(a);
            answers.add(b);
            answers.add(c);
            answers.add(d);

            Map<String, List<AnswerDTO>> questionMap = (Map<String, List<AnswerDTO>>) ActionContext.getContext().getSession().get("QBANK");
            questionMap.put(this.questionDescription, answers);

        } else if (isCorrectAnswer.equals("B")) {
            AnswerDTO a = new AnswerDTO(this.answerA.trim(), false, questionId);
            AnswerDTO b = new AnswerDTO(this.answerB.trim(), true, questionId);
            AnswerDTO c = new AnswerDTO(this.answerC.trim(), false, questionId);
            AnswerDTO d = new AnswerDTO(this.answerD.trim(), false, questionId);
            resultAnswer = answerService.create(a);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(b);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(c);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(d);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            List<AnswerDTO> answers = new ArrayList<>();
            answers.add(a);
            answers.add(b);
            answers.add(c);
            answers.add(d);

            Map<String, List<AnswerDTO>> questionMap = (Map<String, List<AnswerDTO>>) ActionContext.getContext().getSession().get("QBANK");
            questionMap.put(this.questionDescription, answers);

        } else if (isCorrectAnswer.equals("C")) {
            AnswerDTO a = new AnswerDTO(this.answerA.trim(), false, questionId);
            AnswerDTO b = new AnswerDTO(this.answerB.trim(), false, questionId);
            AnswerDTO c = new AnswerDTO(this.answerC.trim(), true, questionId);
            AnswerDTO d = new AnswerDTO(this.answerD.trim(), false, questionId);
            resultAnswer = answerService.create(a);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(b);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(c);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(d);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            List<AnswerDTO> answers = new ArrayList<>();
            answers.add(a);
            answers.add(b);
            answers.add(c);
            answers.add(d);

            Map<String, List<AnswerDTO>> questionMap = (Map<String, List<AnswerDTO>>) ActionContext.getContext().getSession().get("QBANK");
            questionMap.put(this.questionDescription, answers);
        } else if (isCorrectAnswer.equals("D")) {
            AnswerDTO a = new AnswerDTO(this.answerA.trim(), false, questionId);
            AnswerDTO b = new AnswerDTO(this.answerB.trim(), false, questionId);
            AnswerDTO c = new AnswerDTO(this.answerC.trim(), false, questionId);
            AnswerDTO d = new AnswerDTO(this.answerD.trim(), true, questionId);
            resultAnswer = answerService.create(a);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(b);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(c);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            resultAnswer = answerService.create(d);
            if (!resultAnswer) {
                return ActionSupport.ERROR;
            }
            List<AnswerDTO> answers = new ArrayList<>();
            answers.add(a);
            answers.add(b);
            answers.add(c);
            answers.add(d);

            Map<String, List<AnswerDTO>> questionMap = (Map<String, List<AnswerDTO>>) ActionContext.getContext().getSession().get("QBANK");
            questionMap.put(this.questionDescription, answers);
        }

        this.questionDescription = "";
        this.answerA = "";
        this.answerB = "";
        this.answerC = "";
        this.answerD = "";
        this.isCorrectAnswer = "A";

        return ActionSupport.SUCCESS;
    }

    @Override
    public String search() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String update() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Action(value = "delete", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/questionManagement.jsp")
        ,
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")
    })
    @Override
    public String delete() throws Exception {
        QuestionService questionService = new QuestionService();
        String name = questionService.getQuestionByQuestionId(keyEdit).getDesciption();
        boolean result = questionService.delete(String.valueOf(keyEdit));
        if (!result) {
            return ActionSupport.ERROR;
        }
        Map session = ActionContext.getContext().getSession();
        Map<String, List<AnswerDTO>> quizBank = (Map<String, List<AnswerDTO>>) session.get("QBANK");

        quizBank.remove(name);
        return ActionSupport.SUCCESS;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
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

    public String getIsCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setIsCorrectAnswer(String isCorrectAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public int getKeyEdit() {
        return keyEdit;
    }

    public void setKeyEdit(int keyEdit) {
        this.keyEdit = keyEdit;
    }

}
