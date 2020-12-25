/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Huy Nguyen
 */
public interface IBaseService<T> {
    public boolean create(T t);
    public String getLastId();
    public T search(String search);
    public List<T> searchMany(String search);
    public boolean update(T t);
    public boolean delete(String id);
    public List<T> getAll();
    public List<T> getPaging(int pageSize, int size, String where);
    public void closeConnection();
}
