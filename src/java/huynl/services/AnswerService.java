/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.services;

import huynl.dtos.AnswerDTO;
import huynl.helpers.DbHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huy Nguyen
 */
public class AnswerService implements Serializable, IBaseService<AnswerDTO>{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public boolean updateQA(AnswerDTO t, int answerId) {
        try {
            String sql = "UPDATE Answer SET Description = ?, IsCorrect = ? WHERE AnswerId = ? AND QuestionId = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getDescription());
            preparedStatement.setBoolean(2, t.isIsCorrect());
            preparedStatement.setInt(4, t.getQuestionId());
            preparedStatement.setInt(3, answerId);
            if (preparedStatement.executeUpdate() > 0) return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public List<AnswerDTO> getAnswersByQuestion(int question) {
        List<AnswerDTO> answers = null;
        try {
            String sql = "SELECT AnswerId, Description, IsCorrect FROM Answer WHERE QuestionId = ? AND Status = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, question);
            preparedStatement.setBoolean(2, true);
            resultSet = preparedStatement.executeQuery();
            answers = new ArrayList<>();
            int answerId;
            String description;
            boolean isCorrect;
            while(resultSet.next()) {
                answerId = resultSet.getInt("AnswerId");
                description = resultSet.getString("Description");
                isCorrect = resultSet.getBoolean("IsCorrect");
                answers.add(new AnswerDTO(answerId, description, isCorrect, question));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return answers;
    }
    
    public int getAnswerIdByDescription(String description) {
        int answerId = 0;
        try {
            String sql = "SELECT AnswerId FROM Answer WHERE Description = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, description);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                answerId = resultSet.getInt("AnswerId");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return answerId;
    }
    
    @Override
    public boolean create(AnswerDTO t) {
        try {
            String sql = "INSERT INTO Answer(Description, IsCorrect, Status, QuestionId) VALUES (?,?,?,?)";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getDescription());
            preparedStatement.setBoolean(2, t.isIsCorrect());
            preparedStatement.setBoolean(3, true);
            preparedStatement.setInt(4, t.getQuestionId());
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
        String lastId = null;
        try {
            String sql = "SELECT COUNT(AnswerId) FROM Answer";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            lastId = resultSet.toString();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return lastId;
    }

    @Override
    public AnswerDTO search(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnswerDTO> searchMany(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(AnswerDTO t) {
        try {
            String sql = "UPDATE Answer SET Description = ?, IsCorrect = ? WHERE AnswerId = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getDescription());
            preparedStatement.setBoolean(2, t.isIsCorrect());
            preparedStatement.setInt(3, t.getAnswerId());
            if (preparedStatement.executeUpdate() > 0) return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnswerDTO> getAll() {
        List<AnswerDTO> answers = null;
        try {
            String sql = "SELECT AnswerId, Description, IsCorrect, QuestionId FROM Answer WHERE Status = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            resultSet = preparedStatement.executeQuery();
            answers = new ArrayList<>();
            int answerId;
            String description;
            boolean isCorrect;
            int questionId;
            while(resultSet.next()) {
                answerId = resultSet.getInt("AnswerId");
                description = resultSet.getString("Description");
                isCorrect = resultSet.getBoolean("IsCorrect");
                questionId = resultSet.getInt("QuestionId");
                answers.add(new AnswerDTO(answerId, description, isCorrect, questionId));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return answers;
    }

    @Override
    public List<AnswerDTO> getPaging(int pageSize, int size, String where) {
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
