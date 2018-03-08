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
    private String serviceId;
    private String name;
    private double price;
    private String unitName;    

    public String getServiceId() {
        return serviceId;
    }

    public Service(String serviceId, String name, double price) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
    }

    public Service() {
    }
    
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    
    
}
