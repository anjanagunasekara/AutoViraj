/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.services;

import autoviraj.models.Service;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anjanag
 */
public class ServiceMapper {
     Map<String,Service> serviceMap = new HashMap<>(); //key item code
    
    public Map<String,Service> getItemMap(){
        return serviceMap;
    }
    
    public void createNewService(Service s){
        //inset to db
    }
    
    private void createItemMap(){
        //get data from db
    }
}
