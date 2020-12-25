/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import huynl.dtos.UserInfoDTO;
import huynl.services.UserInfoService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Huy Nguyen
 */
public class LoginAction extends ActionSupport {

    private final String ADMIN = "admin";
    private final String STUDENT = "student";
    private final String INPUT = "input";

    private String userName;
    private String password;

    public LoginAction() {
    }

    @Action(value = "login",
            results = {
                @Result(name = ADMIN, location = "/admin.jsp")
                ,
                @Result(name = STUDENT, location = "/student.jsp")
                ,
                @Result(name = INPUT, location = "/login.jsp")
            })
    public String login() throws Exception {
        UserInfoService userInfoService = new UserInfoService();
        UserInfoDTO userInfoDTO = userInfoService.login(userName, password);
        if (userInfoDTO != null) {
            if (userInfoDTO.getRoleId().equals(ADMIN)) {
                return ADMIN;
            } else if (userInfoDTO.getRoleId().equals(STUDENT)) {
                return STUDENT;
            }
        }
        return INPUT;
    }

    public String getUserName() {
        return userName;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "UserName is required!")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Password is required!")
    public void setPassword(String password) {
        this.password = password;
    }

}
