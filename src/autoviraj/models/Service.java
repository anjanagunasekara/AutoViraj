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
public class Service {
    private String name;
    private double price;
    private String code;    

    public Service(String name, double prie, String code) {
        this.name = name;
        this.price = prie;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double prie) {
        this.price = prie;
    }
    
}
