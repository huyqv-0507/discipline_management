/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import huynl.dtos.LessonDTO;
import huynl.services.LessonService;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Huy Nguyen
 */
@Namespace("/lessonManagement")
public class LessonManagementAction extends ActionSupport implements IBaseAction {

    private String department;
    private String content;
    private String title;
    private String fileURL;

    private boolean notifyEmpty;

    private String searchWord;

    private String lessonId;
    private String lessonIdUpdate;
    private String deleteId;

    private String smButton;
    public LessonManagementAction() {

    }

    @Action(value = "showAll", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/lessonManagement.jsp")
        , 
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")})
    public String showAll() {
        LessonService lessonService = new LessonService();
        try {
            List<LessonDTO> lessons = lessonService.getAll();
            Map session = ActionContext.getContext().getSession();

            session.remove("LESSONS");
            session.put("LESSONS", lessons);
            if (lessons == null) {
                return ActionSupport.ERROR;
            }
        } catch (Exception ex) {
            System.out.println("err====: " + ex.getMessage());
        }
        return ActionSupport.SUCCESS;
    }

    @Action(value = "create", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/lessonManagement.jsp")
        , 
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")
        , 
        @Result(name = ActionSupport.INPUT, location = "/lessonManagement.jsp")})
    @Override
    public String create() throws Exception {
        LessonService lessonService = new LessonService();
        LessonDTO lesson = new LessonDTO(0, title, content, fileURL, department, "");
        boolean result = lessonService.create(lesson);
        if (!result) {
            return ActionSupport.ERROR;
        }
        this.content = "";
        Map session = ActionContext.getContext().getSession();
        List<LessonDTO> lessons = (List<LessonDTO>) session.get("LESSONS");
        lessons.add(lesson);

        return ActionSupport.SUCCESS;
    }

    @Action(value = "search", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/lessonManagement.jsp")
        , 
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")
        , 
        @Result(name = ActionSupport.INPUT, location = "/lessonManagement.jsp")})
    @Override
    public String search() throws Exception {
        LessonService lessonService = new LessonService();
        List<LessonDTO> lessons = lessonService.searchMany(this.searchWord);
        Map session = ActionContext.getContext().getSession();
        session.remove("LESSONS");
        session.put("LESSONS", lessons);
        if (lessons == null || lessons.isEmpty()) {
            this.notifyEmpty = true;
        } else {
            this.notifyEmpty = false;
        }
        return ActionSupport.SUCCESS;
    }

    @Action(value = "update", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/lessonManagement.jsp")
        , 
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")
        , 
        @Result(name = ActionSupport.INPUT, location = "/lessonManagement.jsp")})
    @Override
    public String update() throws Exception {
        LessonService lessonService = new LessonService();
        LessonDTO lesson = new LessonDTO(Integer.parseInt(this.lessonIdUpdate), title, content, "", department, "");
        boolean result = lessonService.update(lesson);
        if (!result) {
            return ActionSupport.ERROR;
        }

        List<LessonDTO> lessons = (List<LessonDTO>) ActionContext.getContext().getSession().get("LESSONS");
        for (LessonDTO lessonDTO : lessons) {
            if (lessonDTO.getLessonId() == lesson.getLessonId()) {
                lessonDTO.setLessonTitle(this.title);
                lessonDTO.setLessonContent(this.content);
                lessonDTO.setDepartment(this.department);
            }
        }
        title = "";
        content = "";
        return ActionSupport.SUCCESS;
    }

    @Action(value = "delete", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/lessonManagement.jsp")
        , 
        @Result(name = ActionSupport.ERROR, location = "/notFound.jsp")})
    @Override
    public String delete() throws Exception {
        LessonService lessonService = new LessonService();
        boolean result = lessonService.delete(this.deleteId);
        System.out.println("result: " + result);
        System.out.println("deleteId" + this.deleteId);
        if (!result) {
            return ActionSupport.ERROR;
        }
        LessonDTO tmpLesson = null;
        List<LessonDTO> lessons = (List<LessonDTO>) ActionContext.getContext().getSession().get("LESSONS");
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getLessonId() == Integer.parseInt(deleteId)) {
                tmpLesson = lessons.get(i);
            }
        }
        lessons.remove(tmpLesson);
        return ActionSupport.SUCCESS;
    }

    @Override
    public void validate() {
        if (smButton.equals("Create") && content.length() == 0) {
            addFieldError("content", "Lesson content is required.");
        }
        if (smButton.equals("Search") && searchWord.length() == 0) {
            addFieldError("searchWord", "Lesson content is required.");
        }

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isNotifyEmpty() {
        return notifyEmpty;
    }

    public void setNotifyEmpty(boolean notifyEmpty) {
        this.notifyEmpty = notifyEmpty;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public String getLessonIdUpdate() {
        return lessonIdUpdate;
    }

    public void setLessonIdUpdate(String lessonIdUpdate) {
        this.lessonIdUpdate = lessonIdUpdate;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(String deleteId) {
        this.deleteId = deleteId;
    }

    public String getSmButton() {
        return smButton;
    }

    public void setSmButton(String smButton) {
        this.smButton = smButton;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    

}
