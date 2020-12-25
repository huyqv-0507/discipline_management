/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.actions;

/**
 *
 * @author Huy Nguyen
 */
public interface IBaseAction {

    public String create() throws Exception;

    public String search() throws Exception;

    public String update() throws Exception;

    public String delete() throws Exception;
}
