/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.services;

import huynl.dtos.LessonDTO;
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
public class LessonService implements Serializable, IBaseService<LessonDTO> {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public String getLessonTitleByLessonId(int id) {
        String lessonTitle = null;
        try {
            String sql = "SELECT LessonTitle FROM Lesson WHERE LessonId = ? AND Status = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setBoolean(2, true);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lessonTitle = resultSet.getString("LessonTitle");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return lessonTitle;
    }
    public String getLessonTitleById(String id) {
        String lessonTitle = null;
        try {
            String sql = "SELECT LessonTitle FROM Lesson WHERE LessonId = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lessonTitle = resultSet.getString("LessonTitle");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return lessonTitle;
    }

    public String getLessonIdByName(String name) {
        String lessonId = null;
        try {
            String sql = "SELECT LessonId FROM Lesson WHERE LessonTitle = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lessonId = resultSet.getString("LessonId");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return lessonId;
    }

    @Override
    public boolean create(LessonDTO t) {
        try {
            String sql = "INSERT INTO Lesson(LessonTitle, LessonContent, LessonUrl, Department, Status) VALUES(?,?,?,?,?)";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getLessonTitle());
            preparedStatement.setString(2, t.getLessonContent());
            preparedStatement.setString(3, t.getLessonUrl());
            preparedStatement.setString(4, t.getDepartment());
            preparedStatement.setBoolean(5, true);
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
        String lastLessonId = null;
        try {
            String sql = "SELECT COUNT(LessonId) FROM Lesson";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            lastLessonId = resultSet.toString();
            System.out.println("Count: " + lastLessonId);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return lastLessonId;

    }

    @Override
    public LessonDTO search(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LessonDTO> searchMany(String search) {
        System.out.println("searchWord: " + search);
        List<LessonDTO> lessons = null;
        try {
            String sql = "SELECT LessonId, LessonTitle, LessonContent, LessonUrl, Department, TestId FROM Lesson WHERE LessonTitle LIKE ? AND Status = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setBoolean(2, true);
            resultSet = preparedStatement.executeQuery();
            int lessonId;
            String lessonTitle, lessonContent, lessonUrl, department, testId;
            lessons = new ArrayList<>();
            while (resultSet.next()) {
                lessonId = resultSet.getInt("LessonId");
                lessonTitle = resultSet.getString("LessonTitle");
                lessonContent = resultSet.getString("LessonContent");
                lessonUrl = resultSet.getString("LessonUrl");
                department = resultSet.getString("Department");
                testId = resultSet.getString("TestId");
//                System.out.println("disciplineId: " + disciplineId);
//                System.out.println("disciplineName: " + disciplineName);
//                System.out.println("majorId: " + majorId);
                lessons.add(new LessonDTO(lessonId, lessonTitle, lessonContent, lessonUrl, department, testId));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return lessons;
    }

    @Override
    public boolean update(LessonDTO t) {
        try {
            String sql = "UPDATE Lesson SET LessonTitle = ?, LessonContent = ?, Department = ? WHERE LessonId = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getLessonTitle());
            preparedStatement.setString(2, t.getLessonContent());
            preparedStatement.setString(3, t.getDepartment());
            preparedStatement.setInt(4, t.getLessonId());

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
    public boolean delete(String id) {
        System.out.println("id:" + id);
        try {
            String sql = "UPDATE Lesson SET Status = ? WHERE LessonId = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, Integer.parseInt(id));
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
    public List<LessonDTO> getAll() {
        List<LessonDTO> disciplines = null;
        try {
            String sql = "SELECT LessonId, LessonTitle, LessonContent, LessonUrl, Department, TestId FROM Lesson WHERE Status = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            resultSet = preparedStatement.executeQuery();
            disciplines = new ArrayList<>();
            int lessonId;
            String lessonTitle, lessonContent, lessonUrl, department, testId;
            while (resultSet.next()) {
                lessonId = resultSet.getInt("LessonId");
                lessonTitle = resultSet.getString("LessonTitle");
                lessonContent = resultSet.getString("LessonContent");
                lessonUrl = resultSet.getString("LessonUrl");
                department = resultSet.getString("Department");
                testId = resultSet.getString("TestId");
                disciplines.add(new LessonDTO(lessonId, lessonTitle, lessonContent, lessonUrl, department, testId));
//                System.out.println("disciplineId: " + disciplineId);
//                System.out.println("disciplineName: " + disciplineName);
//                System.out.println("majorId: " + majorId);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return disciplines;
    }

    @Override
    public List<LessonDTO> getPaging(int pageSize, int size, String where) {
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
