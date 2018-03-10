/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.dao;

import autoviraj.models.Vehicle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author anjanag
 */
public class VehicleDao {
    public static ArrayList<Vehicle> getAllVehicles() {
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM VEHICLE");
            ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
            if (rs.next()) {
                Vehicle v = new Vehicle();
                v.setVehicleId(rs.getInt("VEHICLE_ID"));
                v.setModel(rs.getString("MODEL"));
                v.setType(rs.getString("TYPE"));
                v.setRegNo(rs.getString("REG_NO"));
                vehicles.add(v);
            }
            return vehicles;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
