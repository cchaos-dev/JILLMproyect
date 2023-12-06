/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author felixplajamarcos
 */
public class UserBotData implements Serializable {
    
    //Atributes
    
    private String userName;
    private String botName;
    
    
    //Constructor
    
    public UserBotData(){}
    
    public UserBotData(String userName, String botName){
        
        this.userName = userName;
        this.botName = botName;
        
    }

    
    //Atributes
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }
    
    
    
    
    
}
