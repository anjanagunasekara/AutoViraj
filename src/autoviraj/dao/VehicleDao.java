/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.dao;

import autoviraj.models.Customer;
import autoviraj.models.InvoiceItem;
import autoviraj.models.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            while (rs.next()) {
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
    
    public static Vehicle getVehicle(String regNo) {
        Vehicle v = null;
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Vehicle v left outer join customer c on (v.CUSTOMER_ID = c.CUSTOMER_ID ) where v.REG_NO = '"+regNo+"'");
            ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
            if (rs.next()) {                
                Customer c = new Customer();
                c.setName(rs.getString("NAME"));
                c.setCustomerId(rs.getInt("CUSTOMER_ID"));
                c.setTel(rs.getString("TEL"));
                v = new Vehicle();
                v.setVehicleId(rs.getInt("VEHICLE_ID"));
                v.setModel(rs.getString("MODEL"));
                v.setType(rs.getString("TYPE"));
                v.setRegNo(rs.getString("REG_NO"));
                v.setCustomer(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return v;
    }
    
    public static boolean insertVehicle(Vehicle v){
         try {
            Connection con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            String insertCustomerSQL = "INSERT INTO CUSTOMER"
                    + "(NAME,ADDRESS,TEL) VALUES"
                    + "(?,?,?)";

            PreparedStatement stmt = con.prepareStatement(insertCustomerSQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, v.getCustomer().getName());
            stmt.setString(2, v.getCustomer().getAddress());
            stmt.setString(3, v.getCustomer().getTel());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int customerId = rs.getInt(1);
            stmt.close();
            
            String insertInvoiceSQL = "INSERT INTO VEHICLE"
                    + "(REG_NO,CUSTOMER_ID,ODOMETER,TYPE,MODEL) VALUES"
                    + "(?,?,?,?,?)";

            stmt = con.prepareStatement(insertInvoiceSQL);
            stmt.setString(1, v.getRegNo());
            stmt.setInt(2, customerId);
            stmt.setString(3, v.getOdometer());
            stmt.setString(4, v.getType());
            stmt.setString(5, v.getModel());

            System.out.println("before commit");
            stmt.executeUpdate();
            stmt.close();
            
            try {
                con.commit();
            } catch (SQLException ex) {
                System.out.println(ex);
                con.rollback();
                return false;
            }
         }catch(SQLException e){
             e.printStackTrace();
         }
        return true;
    }
    
     public static boolean insertCustomer(Customer c){
         try {
            Connection con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            String insertCustomerSQL = "INSERT INTO CUSTOMER"
                    + "(NAME,ADDRESS,TEL) VALUES"
                    + "(?,?,?)";

            PreparedStatement stmt = con.prepareStatement(insertCustomerSQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getAddress());
            stmt.setString(3, c.getTel());
            stmt.executeUpdate();
            stmt.close();
                      
            try {
                con.commit();
            } catch (SQLException ex) {
                System.out.println(ex);
                con.rollback();
                return false;
            }
         }catch(SQLException e){
             e.printStackTrace();
         }
        return true;
    }
     
    public static void main(String[] args) {
        Vehicle v = new Vehicle();
        v.setModel("Sedan");
        v.setType("car");
        v.setRegNo("CCA-3212");
        v.setOdometer("75000");
        Customer c = new Customer();
        c.setName("Anjana Gunasekara");
        c.setTel("0718410029");
        c.setAddress("AAA,BBB,CCC");
        v.setCustomer(c);
        insertVehicle(v);
        System.out.println("");
    }
    
}
