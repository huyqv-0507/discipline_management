/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.services;

import huynl.dtos.DisciplineDTO;
import huynl.helpers.DbHelper;
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
public class DisciplineService implements IBaseService<DisciplineDTO>{
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public String getDisciplineNameById(String id) {
        String disciplineName = null;
        try {
            String sql = "SELECT DisciplineName FROM Discipline WHERE DisciplineId = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                disciplineName = resultSet.getString("DisciplineName");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return disciplineName;
    }
    public int getDisciplineIdByName (String name) {
        int disciplineId = 0;
        try {
            String sql = "SELECT DisciplineId FROM Discipline WHERE DisciplineName = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                disciplineId = resultSet.getInt("DisciplineId");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return disciplineId;
    }
    
    @Override
    public boolean create(DisciplineDTO t) {
        try {
            String sql = "INSERT INTO Discipline(DisciplineName, MajorId, Status) VALUES(?,?,?)";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getDisciplineName());
            preparedStatement.setString(2, t.getMajorId());
            preparedStatement.setBoolean(3, true);
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
    public DisciplineDTO search(String searchWord) {
        throw  new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DisciplineDTO> searchMany(String search) {
        System.out.println("searchWord: " + search);
        List<DisciplineDTO> disciplines = null;
        try {
            String sql = "SELECT DisciplineId, DisciplineName, MajorId FROM Discipline WHERE DisciplineName LIKE ? AND Status = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setBoolean(2, true);
            resultSet = preparedStatement.executeQuery();
            int disciplineId;
            String disciplineName, majorId;
            disciplines = new ArrayList<>();
            while (resultSet.next()) {
                disciplineId = resultSet.getInt("DisciplineId");
                disciplineName = resultSet.getString("DisciplineName");
                majorId = resultSet.getString("MajorId");
//                System.out.println("disciplineId: " + disciplineId);
//                System.out.println("disciplineName: " + disciplineName);
//                System.out.println("majorId: " + majorId);
                disciplines.add(new DisciplineDTO(disciplineId, disciplineName, majorId));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return disciplines; 
    }

    @Override
    public boolean update(DisciplineDTO t) {
        try {
            String sql = "UPDATE Discipline SET DisciplineName = ?, MajorId = ? WHERE DisciplineId = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getDisciplineName());
            preparedStatement.setString(2, t.getMajorId());
            preparedStatement.setInt(3, t.getDisciplineId());
            System.out.println("Discipline id: " + t.getDisciplineId());
            System.out.println("Discipline name: " + t.getDisciplineName());
            System.out.println("Major id: " + t.getMajorId());
            
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
            String sql = "UPDATE Discipline SET Status = ? WHERE DisciplineId = ?";
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
    public List<DisciplineDTO> getAll() {
        List<DisciplineDTO> disciplines = null;
        try {
            String sql = "SELECT DisciplineId, DisciplineName, MajorId FROM Discipline WHERE Status = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            resultSet = preparedStatement.executeQuery();
            disciplines = new ArrayList<>();
            int disciplineId;
            String disciplineName, majorId ;
            while (resultSet.next()) {                
                disciplineId = resultSet.getInt("DisciplineId");
                disciplineName = resultSet.getString("DisciplineName");
                majorId = resultSet.getString("MajorId");
                disciplines.add(new DisciplineDTO(disciplineId, disciplineName, majorId));
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
    public List<DisciplineDTO> getPaging(int pageSize, int size, String where) {
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

    @Override
    public String getLastId() {
       try {
            String sql = "SELECT COUNT(DisciplineId) FROM Discipline";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            System.out.println("resultset: " + resultSet);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
       return resultSet.toString();
    }
    
}
