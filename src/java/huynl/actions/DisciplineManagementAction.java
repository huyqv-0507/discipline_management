/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import huynl.constants.CodeConstant;
import huynl.dtos.DisciplineDTO;
import huynl.dtos.LessonDTO;
import huynl.helpers.CodeHelper;
import huynl.services.DisciplineService;
import huynl.services.LessonService;
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
@Namespace("/disciplineManagement")
public class DisciplineManagementAction extends ActionSupport implements IBaseAction {

    private String disciplineName;
    private String major;

    //Search
    private String searchWord;
    private String smButton;

    //Delete
    private String disciplineId;

    private boolean notifyEmpty;

    public DisciplineManagementAction() {

    }

    @Action(value = "addLesson", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/addLesson.jsp")})
    public String addLesson() {
        LessonService lessonService = new LessonService();
        List<LessonDTO> lessons = lessonService.getAll();
        Map session = ActionContext.getContext().getSession();
        session.put("LESSONS", lessons);
        
        DisciplineService disciplineService = new DisciplineService();
        List<DisciplineDTO> disciplines = disciplineService.getAll();
        
        Map disciplineMap = new HashMap();
        for (int i = 0; i < disciplines.size(); i++) {
            disciplineMap.put(disciplines.get(i).getDisciplineId(), disciplines.get(i).getDisciplineName());
        }
        
        session.put("DISCIPLINES", disciplineMap);
        
        return ActionSupport.SUCCESS;
    }

    @Action(value = "showAll", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/disciplineManagement.jsp")
        , 
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")})
    public String showAll() {
        DisciplineService disciplineService = new DisciplineService();
        List<DisciplineDTO> disciplines = disciplineService.getAll();
        Map session = ActionContext.getContext().getSession();
        session.remove("DISCIPLINES");
        session.put("DISCIPLINES", disciplines);
        if (disciplines == null) {
            return ActionSupport.ERROR;
        }
        return ActionSupport.SUCCESS;
    }

    @Action(value = "create", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/disciplineManagement.jsp")
        , 
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")
        , 
        @Result(name = ActionSupport.INPUT, location = "/disciplineManagement.jsp")})
    @Override
    public String create() throws Exception {
        DisciplineService disciplineService = new DisciplineService();
        DisciplineDTO discipline = new DisciplineDTO(0, disciplineName, major);
        boolean result = disciplineService.create(discipline);

        Map session = ActionContext.getContext().getSession();

        //List<DisciplineView> disciplineViews = (List<DisciplineView>) session.get("DISCIPLINES");
        List<DisciplineDTO> disciplines = (List<DisciplineDTO>) session.get("DISCIPLINES");
        //MajorService majorService = new MajorService();
        //disciplineViews.add(new DisciplineView(newCode, disciplineName, majorService.getMajorNamebyId(major)));
        disciplines.add(discipline);

        if (!result) {
            return ActionSupport.ERROR;
        }
        return ActionSupport.SUCCESS;
    }

    @Action(value = "update", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/disciplineManagement.jsp")
        , 
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")
        , 
        @Result(name = ActionSupport.INPUT, location = "/disciplineManagement.jsp")})
    @Override
    public String update() throws Exception {

        DisciplineService disciplineService = new DisciplineService();
        boolean result = disciplineService.update(new DisciplineDTO(Integer.parseInt(this.disciplineId), this.disciplineName, this.major));
        if (!result) {
            return ActionSupport.ERROR;
        }
        Map session = ActionContext.getContext().getSession();
        List<DisciplineDTO> disciplines = (List<DisciplineDTO>) session.get("DISCIPLINES");
        for (DisciplineDTO discipline : disciplines) {
            if (discipline.getDisciplineId() == Integer.parseInt(this.disciplineId)) {
                discipline.setDisciplineName(this.disciplineName);
                discipline.setMajorId(this.major);
            }
        }
        return ActionSupport.SUCCESS;
    }

    @Action(value = "delete", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/disciplineManagement.jsp")
        , 
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")})
    @Override
    public String delete() throws Exception {
        System.out.println("Delete action");
        DisciplineService disciplineService = new DisciplineService();
        boolean result = disciplineService.delete(this.disciplineId);
        if (!result) {
            return ActionSupport.ERROR;
        }

        Map session = ActionContext.getContext().getSession();
        List<DisciplineDTO> disciplines = (List<DisciplineDTO>) session.get("DISCIPLINES");
        DisciplineDTO tmpDiscipline = null;
        for (DisciplineDTO discipline : disciplines) {
            if (discipline.getDisciplineId() == Integer.parseInt(this.disciplineId)) {
                tmpDiscipline = discipline;
            }
        }
        disciplines.remove(tmpDiscipline);
        return ActionSupport.SUCCESS;
    }

    @Action(value = "search", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/disciplineManagement.jsp")
        , 
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")
        , 
        @Result(name = ActionSupport.INPUT, location = "/disciplineManagement.jsp")})
    @Override
    public String search() throws Exception {
        DisciplineService disciplineService = new DisciplineService();
        List<DisciplineDTO> disciplines = disciplineService.searchMany(this.searchWord);
        Map session = ActionContext.getContext().getSession();
        session.remove("DISCIPLINES");
        session.put("DISCIPLINES", disciplines);
        if (disciplines == null || disciplines.isEmpty()) {
            this.notifyEmpty = true;
        } else {
            this.notifyEmpty = false;
        }

        return ActionSupport.SUCCESS;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getSmButton() {
        return smButton;
    }

    public void setSmButton(String smButton) {
        this.smButton = smButton;
    }

    public String getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(String disciplineId) {
        this.disciplineId = disciplineId;
    }

    public boolean isNotifyEmpty() {
        return notifyEmpty;
    }

    public void setNotifyEmpty(boolean notifyEmpty) {
        this.notifyEmpty = notifyEmpty;
    }

    @Override
    public void validate() {
        if (smButton.equals("Create") && disciplineName.length() == 0) {
            addFieldError("disciplineName", "Discipline name is required.");
        }
        if (smButton.equals("Search") && searchWord.length() == 0) {
            addFieldError("searchWord", "Discipline name is required.");
        }

    }

}
