/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.services;

import huynl.dtos.DisciplineLessonDTO;
import huynl.helpers.DbHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huy Nguyen
 */
public class DisciplineLessonService implements Serializable, IBaseService<DisciplineLessonDTO>{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public boolean create(DisciplineLessonDTO t) {
        try {
            String sql = "INSERT INTO DisciplineLesson(DisciplineId, LessonId, DisciplineName, LessonTitle) VALUES (?,?,?,?)";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, t.getDisciplineId());
            preparedStatement.setInt(2, t.getLessonId());
            preparedStatement.setString(3, t.getDisciplineName());
            preparedStatement.setString(4, t.getLessonTitle());
            if (preparedStatement.executeUpdate() > 0) return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return false;
    }

    @Override
    public String getLastId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DisciplineLessonDTO search(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DisciplineLessonDTO> searchMany(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(DisciplineLessonDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DisciplineLessonDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DisciplineLessonDTO> getPaging(int pageSize, int size, String where) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        }
    }
    
}
