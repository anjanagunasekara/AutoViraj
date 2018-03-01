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
public class Vehicle {
    private String regNo;
    private String odometer;
    private Customer customer;
    private String type;
    private String model;

    public Vehicle(String regNo, String odometer, Customer customer, String type, String model) {
        this.regNo = regNo;
        this.odometer = odometer;
        this.customer = customer;
        this.type = type;
        this.model = model;
    }
    
    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
}
