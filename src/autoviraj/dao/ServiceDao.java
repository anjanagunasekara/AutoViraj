/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.dao;

import autoviraj.models.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Sehan Rathnayake
 */
public class ServiceDao {
    public static ArrayList<Service> getAllServices(){
       try {
            Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SERVICE");
            ArrayList<Service> services= new ArrayList<Service>();
            if(rs.next())
            {
                Service service = new Service();
                service.setServiceId(rs.getString("SERVICE_ID"));
                service.setName(rs.getString("NAME"));
                service.setPrice(rs.getDouble("PRICE"));
                service.setUnitName(rs.getString("UNIT_NAME"));
                services.add(service);
            }
            return services;
            
         } catch (SQLException ex) {
           System.err.println("ex");
           return null;
        }

       }
}
