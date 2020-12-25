/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.services;

import huynl.dtos.UserInfoDTO;
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
public class UserInfoService implements Serializable, IBaseService<UserInfoDTO> {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserInfoDTO login(String userName, String password) {
        UserInfoDTO userInfo = null;
        try {
            String sql = "SELECT UserInfoId, FullName, RoleId FROM UserInfo WHERE UserName = ? AND Password = ?";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String userInfoId, fullName, roleId;
                userInfoId = resultSet.getString("UserInfoId");
                fullName = resultSet.getString("FullName");
                roleId = resultSet.getString("RoleId");
                userInfo = new UserInfoDTO(userInfoId, userName, fullName, roleId);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfoService.class.getName()).log(Level.SEVERE, "ERROR at {0}\nMessage: {1}", new Object[]{this.getClass().getSimpleName(), ex.getMessage()});
        } finally {
            closeConnection();
        }
        return userInfo;
    }

    @Override
    public boolean create(UserInfoDTO t) {
        try {
            String sql = "INSERT INTO UserInfo(UserInfoId, UserName, Password, FullName, RoleId) VALUES(?,?,?,?,?)";
            connection = DbHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getUserInfoId());
            preparedStatement.setString(2, t.getUserName());
            preparedStatement.setString(3, t.getPassword());
            preparedStatement.setString(4, t.getFullName());
            preparedStatement.setString(5, t.getRoleId());
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
    public UserInfoDTO search(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserInfoDTO> searchMany(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(UserInfoDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserInfoDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserInfoDTO> getPaging(int pageSize, int size, String where) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
