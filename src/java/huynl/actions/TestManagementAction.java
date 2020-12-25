/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Huy Nguyen
 */
@Namespace("/testManagement")
public class TestManagementAction extends ActionSupport{
    
    public TestManagementAction() {
    }
    
    @Action(value = "createTest", results = {
        @Result(name = ActionSupport.SUCCESS, location = "/createTest.jsp")
    })
    public String createTest() throws Exception {
        return ActionSupport.SUCCESS;
    }
    
}
