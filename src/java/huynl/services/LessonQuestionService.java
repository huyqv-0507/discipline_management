/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.services;

import huynl.dtos.LessonQuestionDTO;
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
public class LessonQuestionService implements Serializable, IBaseService<LessonQuestionDTO> {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean create(LessonQuestionDTO t) {
        try {
            String sql = "INSERT INTO LessonQuestion(LessonId, QuestionId, LessonTitle, QuestionDescription) VALUES (?,?,?,?)";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, t.getLessonId());
            preparedStatement.setInt(2, t.getQuestionId());
            preparedStatement.setString(3, t.getLessonTitle());
            preparedStatement.setString(4, t.getQuestionDescription());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
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
    public LessonQuestionDTO search(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LessonQuestionDTO> searchMany(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(LessonQuestionDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LessonQuestionDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LessonQuestionDTO> getPaging(int pageSize, int size, String where) {
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
