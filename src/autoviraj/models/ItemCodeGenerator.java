/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.models;

import autoviraj.services.ItemMapper;
import autoviraj.services.ServiceMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anjanag
 */
public class ItemCodeGenerator {
    static Map<String, Item> itemMap = new HashMap<>();
    static Map<String, Service> serviceMap = new HashMap<>();
    public static Map<String, Item> getItemMap(){
        return itemMap;
    }
    
    public static Map<String, Service> getServiceMap(){
        return serviceMap;
    }
    
    public static void createItemMap () throws IOException{
        URL f = ItemCodeGenerator.class.getClassLoader().getResource("resources/codes_item.csv");
        System.out.println(f.getPath());
        FileReader fileReader = new FileReader(f.getPath());
        ItemMapper mapper = new ItemMapper();
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null) {            
            String split[] = line.split(",");
            Item item = new Item(split[1], Double.parseDouble(split[2]), split[0]);
            itemMap.put(split[0],item);
            mapper.createNewItem(item);
        }
        bufferedReader.close();
    }
    
    public static void createServiceMap () throws IOException{
        URL f = ItemCodeGenerator.class.getClassLoader().getResource("resources/codes_service.csv");
        System.out.println(f.getPath());
        FileReader fileReader = new FileReader(f.getPath());
        ServiceMapper mapper = new ServiceMapper();
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null) {            
            String split[] = line.split(",");
            Service service = new Service(split[1], Double.parseDouble(split[2]), split[0]);
            serviceMap.put(split[0],service);
            mapper.createNewService(service);
        }
        bufferedReader.close();
    }
    
}
