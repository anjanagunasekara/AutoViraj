/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.dao;

import autoviraj.models.Customer;
import autoviraj.models.Invoice;
import autoviraj.models.InvoiceItem;
import autoviraj.models.InvoiceService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sehan Rathnayake
 */
public class InvoiceDao {

    public static void main(String[] args) {
        saveInvoice(null);
    }

    public static boolean saveInvoice(Invoice invoice) {
        try {
            Connection con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            String insertInvoiceSQL = "INSERT INTO INVOICE"
                    + "(CUSTOMER_ID,VEHICLE_ID,DATE,SUB_TOTAL,DISCOUNT,NET_TOTAL,CURRENT_METER,NEXT_SERVICE) VALUES"
                    + "(?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(insertInvoiceSQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, invoice.getCustomer().getCustomerId());
            stmt.setInt(2, invoice.getVehicle().getVehicleId());
            stmt.setDate(3, new java.sql.Date(invoice.getDate().getTime()));
            stmt.setDouble(4, invoice.getSubtotal());
            stmt.setDouble(5, invoice.getDiscount());
            stmt.setDouble(6, invoice.getNetTotal());
            stmt.setDouble(7, invoice.getCurrentMeter());
            stmt.setDouble(8, invoice.getNextService());

            System.out.println("before commit");
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int invoiceId = rs.getInt(1);
            stmt.close();

            String insertInvoiceItemSQL = "INSERT INTO INVOICE_ITEM"
                    + "(INVOICE_ID,ITEM_ID,NAME,UNITS,PRICE,UNIT_NAME) VALUES"
                    + "(?,?,?,?,?,?)";

            for (InvoiceItem item : invoice.getInvoiceItems()) {
                stmt = con.prepareStatement(insertInvoiceItemSQL);
                stmt.setInt(1, invoiceId);
                stmt.setString(2, item.getItemId());
                stmt.setString(3, item.getName());
                stmt.setDouble(4, item.getUnits());
                stmt.setDouble(5, item.getUnitPrice());
                stmt.setString(6, item.getUnitName());
                stmt.executeUpdate();
                stmt.close();
            }
            String insertInvoiceServiceSQL = "INSERT INTO INVOICE_SERVICE"
                    + "(INVOICE_ID,SERVICE_ID,NAME,UNITS,PRICE,UNIT_NAME) VALUES"
                    + "(?,?,?,?,?,?)";
            for (InvoiceService service : invoice.getInvoiceServices()) {
                stmt = con.prepareStatement(insertInvoiceServiceSQL);
                stmt.setInt(1, invoiceId);
                stmt.setString(2, service.getServiceId());
                stmt.setString(3, service.getName());
                stmt.setDouble(4, service.getUnits());
                stmt.setDouble(5, service.getUnitPrice());
                stmt.setString(6, service.getUnitName());
                stmt.executeUpdate();
                stmt.close();
            }
            try {
                con.commit();
            } catch (SQLException ex) {
                System.out.println(ex);
                con.rollback();
                return false;
            }
            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

    }

    public static int getLastInvoiceNo() {
        int invoiceNo = 0;
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM 'invoice'");
            if (rs.next()) {
                invoiceNo = rs.getInt("MAX(INVOICE_ID)");
            }
            return invoiceNo;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static List<Invoice> getVehicleHistory(String regNo) {
        List<Invoice> vehicleHistory = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  i.INVOICE_ID, i.DATE, i.SUB_TOTAL, i.DISCOUNT, i.NET_TOTAL, v.VEHICLE_ID,c.NAME FROM invoice i join vehicle v join customer c where i.VEHICLE_ID = v.VEHICLE_ID and v.CUSTOMER_ID = c.CUSTOMER_ID and v.REG_NO = '"+regNo+"' ");
            while (rs.next()) {
                Invoice i = new Invoice();
                i.setDate(rs.getDate("DATE"));
                i.setDiscount(rs.getInt("DISCOUNT"));
                i.setSubtotal(rs.getInt("SUB_TOTAL"));
                i.setNetTotal(rs.getInt("NET_TOTAL"));
                i.setInvoiceId(rs.getInt("INVOICE_ID"));
                Customer c = new Customer();
                c.setName(rs.getString("NAME"));
                i.setCustomer(c);
                vehicleHistory.add(i);
            }
            return vehicleHistory;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return vehicleHistory;
        }
    }

}
