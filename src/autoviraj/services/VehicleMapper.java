/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.services;

import autoviraj.models.Vehicle;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anjanag
 */
public class VehicleMapper {
    Map<String,Vehicle> vehicleMap = new HashMap<>();
    
    public Map<String,Vehicle> getVehicleMap(){
        return vehicleMap;
    }
    
    public void createNewVehicle(Vehicle v){
        //inset to db
    }
    
    private void createVehicleMap(){
        //get data from db
    }
    
}
