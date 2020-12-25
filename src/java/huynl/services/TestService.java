/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.services;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Huy Nguyen
 */
public class TestService implements Serializable{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    
}
