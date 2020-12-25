/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import huynl.dtos.DisciplineLessonDTO;
import huynl.services.DisciplineLessonService;
import huynl.services.DisciplineService;
import huynl.services.LessonService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Huy Nguyen
 */
@Namespace("/addLesson")
public class AddLessonAction extends ActionSupport {

    private String[] lessonCheck;
    private String disciplineSelect;
    
    private String deleteKey;

    public AddLessonAction() {
    }

    @Action(value = "add", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/addLesson.jsp")
    })
    public String add() throws Exception {
        Map<String, List<String>> shoppingLesson = (Map<String, List<String>>) ActionContext.getContext().getSession().get("SHOPPINGLESSON");
        if (shoppingLesson == null) {
            shoppingLesson = new HashMap<>();
        }
        List lessonList = new ArrayList<>();
        if (lessonCheck != null) {
            for (int i = 0; i < lessonCheck.length; i++) {
                lessonList.add(lessonCheck[i]);
            }
        }
        DisciplineService disciplineService = new DisciplineService();
        
        String disciplineName = disciplineService.getDisciplineNameById(disciplineSelect);
        shoppingLesson.put(disciplineName, lessonList);
        
        Map session = ActionContext.getContext().getSession();
        session.put("SHOPPINGLESSON", shoppingLesson);
        session.put("SIZE", shoppingLesson.size());
        return ActionSupport.SUCCESS;
    }

    @Action(value = "view", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/shoppingCartLesson.jsp")
    })
    public String view() throws Exception {

        return ActionSupport.SUCCESS;
    }
    @Action(value = "delete", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/shoppingCartLesson.jsp")
    })
    public String delete() throws Exception {
        Map<String, List<String>> shoppingLesson = (Map<String, List<String>>) ActionContext.getContext().getSession().get("SHOPPINGLESSON");

        System.out.println("deleteKey: " + deleteKey);
        shoppingLesson.remove(deleteKey);
        return ActionSupport.SUCCESS;
    }
    @Action(value = "addToDb", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/shoppingCartLesson.jsp")
    })
    public String addToDb() throws Exception {
        Map<String, List<String>> shoppingLesson = (Map<String, List<String>>) ActionContext.getContext().getSession().get("SHOPPINGLESSON");
        
        Map<String, List<String>> shoppingLessonToDB = new HashMap<>();
        DisciplineService disciplineService = new DisciplineService();
        DisciplineLessonService disciplineLessonService = new DisciplineLessonService();
        LessonService lessonService = new LessonService();
        
        for (Map.Entry<String, List<String>> entry : shoppingLesson.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            int disciplineId = disciplineService.getDisciplineIdByName(key);
            List<String> lessonsId = new ArrayList<>();
            for (int i = 0; i < value.size(); i++) {
                String tmpId = lessonService.getLessonIdByName(value.get(i));
                System.out.println("tmpId: " + tmpId);
                lessonsId.add(tmpId);
            }
            shoppingLessonToDB.put(String.valueOf(disciplineId), lessonsId);
        }
        for (Map.Entry<String, List<String>> entry : shoppingLessonToDB.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                String tmpDisciplineName = disciplineService.getDisciplineNameById(key);
                String tmpLessonTitle = lessonService.getLessonTitleById(value.get(i));
                System.out.println("key: " + key);
                System.out.println("disciplinename: " + tmpDisciplineName);
                System.out.println("lessionid: " + value.get(i));
                System.out.println("lessionname: " + tmpLessonTitle);
                disciplineLessonService.create(new DisciplineLessonDTO(Integer.parseInt(key), Integer.parseInt(value.get(i)), tmpDisciplineName, tmpLessonTitle));
            }
        }
        shoppingLesson.clear();
        return ActionSupport.SUCCESS;
    }

    public String[] getLessonCheck() {
        return lessonCheck;
    }

    public void setLessonCheck(String[] lessonCheck) {
        this.lessonCheck = lessonCheck;
    }

    public String getDisciplineSelect() {
        return disciplineSelect;
    }

    public void setDisciplineSelect(String disciplineSelect) {
        this.disciplineSelect = disciplineSelect;
    }

    public String getDeleteKey() {
        return deleteKey;
    }

    public void setDeleteKey(String deleteKey) {
        this.deleteKey = deleteKey;
    }
    

}
