/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.services;

import huynl.dtos.QuestionDTO;
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
public class QuestionService implements Serializable, IBaseService<QuestionDTO>{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public QuestionDTO getQuestionByQuestionId(int id) {
        QuestionDTO question = null;
        try {
            String sql = "SELECT Description FROM Question WHERE QuestionId = ? AND Status = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setBoolean(2, true);
            resultSet = preparedStatement.executeQuery();
            String description = "";
            if (resultSet.next()) {
                description = resultSet.getString("Description");
            }
            question = new QuestionDTO();
            question.setQuestionId(id);
            question.setDesciption(description);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return question;
    }

    public int getQuestionIdByDescription(String description) {
        int questionId = 0;
        try {
            String sql = "SELECT QuestionId FROM Question WHERE Description = ? AND Status = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, description);
            preparedStatement.setBoolean(2, true);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                questionId = resultSet.getInt("QuestionId");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return questionId;
    }
    
    @Override
    public boolean create(QuestionDTO t) {
        try {
            String sql = "INSERT INTO Question(Description, Status) VALUES (?,?)";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getDesciption());
            preparedStatement.setBoolean(2, true);
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
            String sql = "SELECT COUNT(QuestionId) FROM Question";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            lastId = resultSet.toString();
            System.out.println("Count: " + lastId);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return lastId;
    }

    @Override
    public QuestionDTO search(String search) {
       QuestionDTO question = null;
        try {
            String sql = "SELECT QuestionId FROM Question WHERE Description = ? AND Status = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return question;
    }

    @Override
    public List<QuestionDTO> searchMany(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(QuestionDTO t) {
        try {
            String sql = "UPDATE Question SET Description = ? WHERE QuestionId = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getDesciption());
            preparedStatement.setInt(2, t.getQuestionId());
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
        try {
            String sql = "UPDATE Question SET Status = ? WHERE QuestionId = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, Integer.parseInt(id));
            if (preparedStatement.executeUpdate() > 0) return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return false;
    }

    @Override
    public List<QuestionDTO> getAll() {
        List<QuestionDTO> questions = null;
        try {
            String sql  = "SELECT QuestionId, Description FROM Question WHERE Status = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            resultSet = preparedStatement.executeQuery();
            int questionId;
            String description;
            questions = new ArrayList<>();
            
            while(resultSet.next()) {
                questionId = resultSet.getInt("QuestionId");
                description = resultSet.getString("Description");
                questions.add(new QuestionDTO(questionId, description));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return questions;
    }

    @Override
    public List<QuestionDTO> getPaging(int pageSize, int size, String where) {
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
