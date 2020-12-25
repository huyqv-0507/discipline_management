/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import huynl.constants.DepartmentConstant;
import huynl.dtos.AnswerDTO;
import huynl.dtos.DisciplineDTO;
import huynl.dtos.LessonDTO;
import huynl.dtos.MajorDTO;
import huynl.dtos.QuestionDTO;
import huynl.services.AnswerService;
import huynl.services.DisciplineService;
import huynl.services.LessonService;
import huynl.services.MajorService;
import huynl.services.QuestionService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Huy Nguyen
 */

public class ManagementAction {

    private final String DISCIPLINE_PAGE = "disciplineManagement";
    private final String LESSON_PAGE = "lessonManagement";
    private final String QUESTION_PAGE = "questionManagement";

    private String smDiscipline;
    private String smLesson;
    private String smQuestion;

    public ManagementAction() {
    }

    public String execute() throws Exception{
        
        if (smDiscipline != null) {
            MajorService majorService = new MajorService();
            List<MajorDTO> majors = majorService.getAll();
            Map session = ActionContext.getContext().getSession();
            Map majorsMap = new HashMap();
            majors.forEach((major) -> {
                majorsMap.put(major.getMajorId(), major.getMajorName());
            });
            session.put("MAJORS", majorsMap);

            DisciplineService disciplineService = new DisciplineService();
            List<DisciplineDTO> disciplines = disciplineService.getAll();

            session.put("DISCIPLINES", disciplines);

            return DISCIPLINE_PAGE;
        } else if (smLesson != null) {
            //Set department default
            Map departments = new HashMap<>();
            departments.put(DepartmentConstant.ENGLISH, DepartmentConstant.ENGLISH);
            departments.put(DepartmentConstant.INFOMATION_TECHNOLOGY_SPECIALIZATION, DepartmentConstant.INFOMATION_TECHNOLOGY_SPECIALIZATION);
            departments.put(DepartmentConstant.SOFTWARE_ENGINEERING, DepartmentConstant.SOFTWARE_ENGINEERING);
            departments.put(DepartmentConstant.SOFT_SKILL, DepartmentConstant.SOFT_SKILL);
            Map session = ActionContext.getContext().getSession();
            session.put("DEPARTMENTS", departments);
            
            LessonService lessonService = new LessonService();
            List<LessonDTO> lessons = lessonService.getAll();
            
            session.put("LESSONS", lessons);
            return LESSON_PAGE;
        } else if (smQuestion != null) {
            Map correctAnswers = new HashMap();
            correctAnswers.put("A", "A");
            correctAnswers.put("B", "B");
            correctAnswers.put("C", "C");
            correctAnswers.put("D", "D");
            Map session = ActionContext.getContext().getSession();
            session.put("ANSWERS", correctAnswers);
            
            QuestionService questionService = new QuestionService();
            List<QuestionDTO> questions = questionService.getAll();
            
            Map<String, List<AnswerDTO>> questionMap = new HashMap<>();
            AnswerService answerService = new AnswerService();
            for (int i = 0; i < questions.size(); i++) {
                List<AnswerDTO> answers = answerService.getAnswersByQuestion(questions.get(i).getQuestionId());
                questionMap.put(questions.get(i).getDesciption(), answers);
            }
            
            for (Map.Entry<String, List<AnswerDTO>> entry : questionMap.entrySet()) {
                String key = entry.getKey();
                List<AnswerDTO> value = entry.getValue();
                System.out.println("key: " + key);
                 for (int i = 0; i < value.size(); i++) {
                     System.out.println("answer : " + i + " " + value.get(i).getDescription());
                }
            }
            
            session.put("QBANK", questionMap);
            
            return QUESTION_PAGE;
        }
        return ActionSupport.ERROR;
    }

    public String getSmDiscipline() {
        return smDiscipline;
    }

    public void setSmDiscipline(String smDiscipline) {
        this.smDiscipline = smDiscipline;
    }

    public String getSmLesson() {
        return smLesson;
    }

    public void setSmLesson(String smLesson) {
        this.smLesson = smLesson;
    }

    public String getSmQuestion() {
        return smQuestion;
    }

    public void setSmQuestion(String smQuestion) {
        this.smQuestion = smQuestion;
    }

}
