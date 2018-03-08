/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.dao;


import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
     public static final String URL = "jdbc:mysql://localhost:3306/garage";
    public static final String USER = "root";
    public static final String PASS = "";
     
    public static Connection getConnection(){
       try {
          DriverManager.registerDriver(new Driver());
          return DriverManager.getConnection(URL, USER, PASS);
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      }
    }
    
}
