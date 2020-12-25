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
public class UserInfoDTO implements Serializable{
    private String userInfoId;
    private String userName;
    private String password;
    private String fullName;
    private String roleId;

    public UserInfoDTO() {
    }

    public UserInfoDTO(String userInfoId, String userName, String fullName, String roleId) {
        this.userInfoId = userInfoId;
        this.userName = userName;
        this.fullName = fullName;
        this.roleId = roleId;
    }

    public UserInfoDTO(String userInfoId, String userName, String password, String fullName, String roleId) {
        this.userInfoId = userInfoId;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.roleId = roleId;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
}
