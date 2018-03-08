/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.models;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Sehan Rathnayake
 */
public class Invoice {
    int invoiceId;
    Customer customer;
    Vehicle vehicle;
    Date date;
    double subtotal;
    double discount;
    double netTotal;
    double currentMeter;
    double nextService;
    
    ArrayList<InvoiceItem> invoiceItems;
    ArrayList<InvoiceService> invoiceServices;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }

    public ArrayList<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(ArrayList<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public ArrayList<InvoiceService> getInvoiceServices() {
        return invoiceServices;
    }

    public void setInvoiceServices(ArrayList<InvoiceService> invoiceServices) {
        this.invoiceServices = invoiceServices;
    }

    public double getCurrentMeter() {
        return currentMeter;
    }

    public void setCurrentMeter(double currentMeter) {
        this.currentMeter = currentMeter;
    }

    public double getNextService() {
        return nextService;
    }

    public void setNextService(double nextService) {
        this.nextService = nextService;
    }
    
}
