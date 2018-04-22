/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.dao;

import autoviraj.models.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anjanag
 */
public class CustomerDao {
    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from CUSTOMER");
            while (rs.next()) {
                Customer c = new Customer();
                c.setName(rs.getString("NAME"));
                c.setAddress(rs.getString("ADDRESS"));
                c.setCustomerId(rs.getInt("CUSTOMER_ID"));
                c.setTel(rs.getString("TEL"));
                customers.add(c);
            }
            return customers;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return customers;
        }
    }
}
