/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.dao;

import java.util.ArrayList;
import autoviraj.models.Item;
import autoviraj.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Sehan Rathnayake
 */
public class ItemDao {
  
    public static ArrayList<Item> getAllItems(){
       try {
            Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ITEM");
            ArrayList<Item> items= new ArrayList<Item>();
            if(rs.next())
            {
                Item item = new Item();
                item.setItemId(rs.getString("ITEM_ID"));
                item.setName(rs.getString("NAME"));
                item.setPrice(rs.getDouble("UNIT_PRICE"));
                item.setUnitName(rs.getString("UNIT_NAME"));
                items.add(item);
            }
            return items;
            
         } catch (SQLException ex) {
           ex.printStackTrace();
           return null;
        }

       }
    public static void main(String[] args) {
        getAllItems();
        System.out.println(getAllItems().get(0).getName());
    }
       
}
