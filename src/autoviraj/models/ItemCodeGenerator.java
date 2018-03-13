/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.models;

import autoviraj.gui.InputOutputForm;
import autoviraj.services.ItemMapper;
import autoviraj.services.ServiceMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author anjanag
 */
public class ItemCodeGenerator {

    static Map<String, Item> itemMap = new HashMap<>();
    static Map<String, Service> serviceMap = new HashMap<>();

    public Map<String, Item> getItemMap() {
        if (itemMap.size() == 0) {
            try {
                createItemMap();
            } catch (IOException ex) {
                Logger.getLogger(ItemCodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("itemmap"+itemMap.size());
        return itemMap;
    }

    public Map<String, Service> getServiceMap() {
        if (serviceMap.size() == 0) {
            try {
                createServiceMap();
            } catch (IOException ex) {
                Logger.getLogger(ItemCodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return serviceMap;
    }

    public void createItemMap() throws IOException {
        FileReader fr = new FileReader(InputOutputForm.inputFilePath + "\\codes_item.csv");
        if (!fr.ready()) {
            JOptionPane.showMessageDialog(null, "Cannot find item code list!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        ItemMapper mapper = new ItemMapper();
        System.out.println(InputOutputForm.inputFilePath + "\\codes_item.csv");
        BufferedReader bufferedReader = new BufferedReader(fr);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String split[] = line.split(",");
            Item item = new Item(split[0], split[1], Double.parseDouble(split[2]), "Nos");
            System.out.println(item.getItemId());
            itemMap.put(split[0], item);
            mapper.createNewItem(item);
        }
        bufferedReader.close();
    }

    public void createServiceMap() throws IOException {
        FileReader fr = new FileReader(InputOutputForm.inputFilePath + "\\codes_service.csv");
        if (!fr.ready()) {
            JOptionPane.showMessageDialog(null, "Cannot find service code list!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        ServiceMapper mapper = new ServiceMapper();
        BufferedReader bufferedReader = new BufferedReader(fr);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String split[] = line.split(",");
            Service service = new Service(split[0], split[1], Double.parseDouble(split[2]));
            serviceMap.put(split[0], service);
            mapper.createNewService(service);
        }
        bufferedReader.close();
    }

}
