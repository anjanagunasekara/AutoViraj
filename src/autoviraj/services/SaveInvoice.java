/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.services;
import autoviraj.dao.InvoiceDao;
import autoviraj.models.Customer;
import autoviraj.models.Invoice;
import autoviraj.models.InvoiceItem;
import autoviraj.models.InvoiceService;
import autoviraj.models.Vehicle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sehan Rathnayake
 */
public class SaveInvoice {
    public static void saveInvoice(Invoice invoice) {
        /*test data */
      
        InvoiceDao.saveInvoice(invoice);
        
    }
    public static void main(String[] args) {
        Customer customer=new Customer();
        customer.setCustomerId(1);
        customer.setName("Sehan Rathnayake");
        customer.setAddress("yemen road,Yemen");
        
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1);
        vehicle.setModel("Toyota Corolla");
        vehicle.setRegNo("BBK-4555");
        vehicle.setType("Car");
        
        InvoiceItem item1=new InvoiceItem();
        item1.setItemId("IT001");
        item1.setName("OIL FILTER");
        item1.setUnitPrice(100);
        item1.setUnitName("Nos");
        item1.setUnits(2);
        
        ArrayList<InvoiceItem> items= new ArrayList<InvoiceItem>();
        items.add(item1);
        
        Invoice invoice=new Invoice();
        invoice.setCustomer(customer);
        invoice.setVehicle(vehicle);
        try {
            invoice.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-28"));
        } catch (ParseException ex) {
            Logger.getLogger(SaveInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        invoice.setInvoiceItems(items);
        
        ArrayList<InvoiceService> services = new ArrayList<InvoiceService>();
        InvoiceService service1=new InvoiceService();
        service1.setServiceId("S001");
        service1.setName("Engine Tune up");
        service1.setUnitPrice(8500);
        service1.setUnits(1);
        service1.setUnitName("Nos");
        services.add(service1);
        
        invoice.setInvoiceServices(services);
        invoice.setSubtotal(121.00);
        invoice.setDiscount(10);
        invoice.setNetTotal(200);
        invoice.setCurrentMeter(9999);
        invoice.setNextService(299999);
        
        try {
            PrintInvoice.printInvoice(invoice);
        } catch (Exception ex) {
            Logger.getLogger(SaveInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
