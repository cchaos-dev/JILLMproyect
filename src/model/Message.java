
package model;

/**
 *
 * @author felixplajamarcos
 */

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable{
    
    //Atributes
    
    private String sender = new String();
    
    private String content = new String();
    
    private String time;
    
    
    //Constructor
    
    public Message(){}
    
    public Message (String sender, String content){
        
        this.sender = sender;
        this.content = content;
        
        LocalDateTime actualTime = LocalDateTime. now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        String finalDate = actualTime.format (format);
        
        time = finalDate;
    }

    
    
    //Getters and Setters
    
    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
    
}
