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
import java.io.InputStream;
import java.io.InputStreamReader;
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
    public Map<String, Item> getItemMap(){
        return itemMap;
    }
    
    public Map<String, Service> getServiceMap(){
        return serviceMap;
    }
    
    public void createItemMap () throws IOException{
        InputStream is = getClass().getResourceAsStream("/resources/codes_item.csv");
        ItemMapper mapper = new ItemMapper();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String line;
        while((line = bufferedReader.readLine()) != null) {            
            String split[] = line.split(",");
            Item item = new Item(split[1], Double.parseDouble(split[2]), split[0]);
            itemMap.put(split[0],item);
            mapper.createNewItem(item);
        }
        bufferedReader.close();
    }
    
    public void createServiceMap () throws IOException{
        InputStream is = getClass().getResourceAsStream("/resources/codes_service.csv");
        ServiceMapper mapper = new ServiceMapper();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
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
