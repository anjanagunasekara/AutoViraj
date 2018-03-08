/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.models;

/**
 *
 * @author anjanag
 */
public class Item {
    private String itemId;
    private String name;
    private double price;
    private String unitName;    

    public Item(String itemId, String name,double price,  String unitName) {
        this.itemId = itemId;
        this.price = price;
        this.name = name;
        this.unitName = unitName;
    }

    public Item() {
    }
    
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    
}