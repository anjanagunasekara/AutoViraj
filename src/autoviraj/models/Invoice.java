/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.models;

import java.util.List;

/**
 *
 * @author anjanag
 */
public class Invoice {
    private String invoiceCode;
    private List<Item> itemList;
    private List<Service> serviceList;
    private Vehicle vehicle;
    private double grandTotal;
    private double serviceTotal;
    private double itemTotal;
    private double serviceDiscount;
    private double itemDiscount;
    private String odometer;
    private String date;

    public Invoice(String invoiceCode, List<Item> itemList, List<Service> serviceList, Vehicle vehicle, double grandTotal, double serviceTotal, double itemTotal, double serviceDiscount, double itemDiscount, String odometer, String date) {
        this.invoiceCode = invoiceCode;
        this.itemList = itemList;
        this.serviceList = serviceList;
        this.vehicle = vehicle;
        this.grandTotal = grandTotal;
        this.serviceTotal = serviceTotal;
        this.itemTotal = itemTotal;
        this.serviceDiscount = serviceDiscount;
        this.itemDiscount = itemDiscount;
        this.odometer = odometer;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public double getServiceTotal() {
        return serviceTotal;
    }

    public void setServiceTotal(double serviceTotal) {
        this.serviceTotal = serviceTotal;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public double getServiceDiscount() {
        return serviceDiscount;
    }

    public void setServiceDiscount(double serviceDiscount) {
        this.serviceDiscount = serviceDiscount;
    }

    public double getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(double itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }
    
    
}
