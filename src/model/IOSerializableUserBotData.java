/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felixplajamarcos
 */
public class IOSerializableUserBotData {
    
    
    public UserBotData importEstate(){
        
        try (ObjectInputStream o = new ObjectInputStream(new FileInputStream("UserBotNames.bin"))){
            
            UserBotData data;
            
            try {
                
                data = (UserBotData) o.readObject();
                o.close();
                return data;
                
                
            } catch (ClassNotFoundException ex) {
                
                Logger.getLogger(IOSerializableUserBotData.class.getName()).log(Level.SEVERE, null, ex);
                o.close();
                return null;
            }
 
            
        }catch(IOException e){
            
            return null;
        } 
        
    }
    
    
    public void exportEstate(UserBotData data){
        
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("UserBotNames.bin"))){
    
            
            o.writeObject(data);
            
            o.close();
            
            
        }catch(IOException e){
            
        } 
        
    }
    
    
    
}
