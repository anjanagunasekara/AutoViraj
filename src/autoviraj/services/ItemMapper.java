/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.services;

import autoviraj.models.Item;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anjanag
 */
public class ItemMapper {
    Map<String,Item> itemMap = new HashMap<>(); //key item code
    
    public Map<String,Item> getItemMap(){
        return itemMap;
    }
    
    public void createNewItem(Item i){
        //inset to db
    }
    
    private void createItemMap(){
        //get data from db
    }
}
