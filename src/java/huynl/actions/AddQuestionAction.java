/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import huynl.dtos.LessonDTO;
import huynl.dtos.LessonQuestionDTO;
import huynl.dtos.QuestionDTO;
import huynl.services.LessonQuestionService;
import huynl.services.LessonService;
import huynl.services.QuestionService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Huy Nguyen
 */
@Namespace("/addQuestionManagement")
public class AddQuestionAction extends ActionSupport{
    
    private String[] checkQuestion;
    private String selectLesson;
    private int deleteKey;
    
    public AddQuestionAction() {
    }
    @Action(value = "addQuestion", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/addQuestion.jsp")
    })
    public String addQuestion() throws Exception {
        Map session = ActionContext.getContext().getSession();
        LessonService lessonService = new LessonService();
        List<LessonDTO> lessons = lessonService.getAll();
        
        Map lessonMap = new HashMap();
        for (int i = 0; i < lessons.size(); i++) {
            lessonMap.put(lessons.get(i).getLessonId(), lessons.get(i).getLessonTitle());
        }
        session.put("LESSONS", lessonMap);
        
        QuestionService questionService = new QuestionService();
        List<QuestionDTO> questions = questionService.getAll();
        
        session.put("QUESTIONS", questions);
        
        return ActionSupport.SUCCESS;
    }
    
    @Action(value = "add", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/addQuestion.jsp")
    })
    public String add() throws Exception {
        Map<LessonDTO, List<QuestionDTO>> shoppingQuestion = (Map<LessonDTO, List<QuestionDTO>>) ActionContext.getContext().getSession().get("SHOPPINGQUESTION");
        if (shoppingQuestion == null) {
            shoppingQuestion = new HashMap<>();
        }
        QuestionService questionService = new QuestionService();
        List<QuestionDTO> questionList = new ArrayList();
        for (int i = 0; i < this.checkQuestion.length; i++) {
            QuestionDTO question = questionService.getQuestionByQuestionId(Integer.parseInt(this.checkQuestion[i]));
            questionList.add(question);
        }
        LessonService lessonService = new LessonService();
        System.out.println("selectLesson: " + this.selectLesson);
        LessonDTO lesson = new LessonDTO(Integer.parseInt(this.selectLesson), lessonService.getLessonTitleById(this.selectLesson));
        shoppingQuestion.put(lesson, questionList);
        Map session = ActionContext.getContext().getSession();
        session.put("SHOPPINGQUESTION", shoppingQuestion);
        
        return ActionSupport.SUCCESS;
    }
    @Action(value = "view", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/shoppingCartQuestion.jsp")
    })
    public String view() throws Exception {

        return ActionSupport.SUCCESS;
    }
    
    @Action(value = "delete", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/shoppingCartQuestion.jsp")
    })
    public String delete() throws Exception {
        Map<LessonDTO, List<QuestionDTO>> shoppingQuestion = (Map<LessonDTO, List<QuestionDTO>>) ActionContext.getContext().getSession().get("SHOPPINGQUESTION");

        LessonDTO tmpLesson = null;
        for (Map.Entry<LessonDTO, List<QuestionDTO>> entry : shoppingQuestion.entrySet()) {
            LessonDTO key = entry.getKey();
            if (key.getLessonId() == this.deleteKey) {
                tmpLesson = key;
            }
        }
        shoppingQuestion.remove(tmpLesson);
        
        return ActionSupport.SUCCESS;
    }
    @Action(value = "addToDb", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/shoppingCartQuestion.jsp")
    })
    public String addToDb() throws Exception {
        Map<LessonDTO, List<QuestionDTO>> shoppingQuestion = (Map<LessonDTO, List<QuestionDTO>>) ActionContext.getContext().getSession().get("SHOPPINGQUESTION");
        LessonQuestionService lessonQuestionService = new LessonQuestionService();
        for (Map.Entry<LessonDTO, List<QuestionDTO>> entry : shoppingQuestion.entrySet()) {
            LessonDTO key = entry.getKey();
            List<QuestionDTO> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                lessonQuestionService.create(new LessonQuestionDTO(key.getLessonId(), value.get(i).getQuestionId(), key.getLessonTitle(), value.get(i).getDesciption()))
;            }
        }
        shoppingQuestion.clear();
        return ActionSupport.SUCCESS;
    }

    public String[] getCheckQuestion() {
        return checkQuestion;
    }

    public void setCheckQuestion(String[] checkQuestion) {
        this.checkQuestion = checkQuestion;
    }

    public String getSelectLesson() {
        return selectLesson;
    }

    public void setSelectLesson(String selectLesson) {
        this.selectLesson = selectLesson;
    }

    public int getDeleteKey() {
        return deleteKey;
    }

    public void setDeleteKey(int deleteKey) {
        this.deleteKey = deleteKey;
    }
}
