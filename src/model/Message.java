
package model;

/**
 *
 * @author felixplajamarcos
 */

import java.io.Serializable;
import java.time.Instant;

public class Message implements Serializable{
    
    //Atributes
    
    private String sender = new String();
    
    private String content = new String();
    
    private long time;
    
    
    //Constructor
    
    public Message (String sender, String content){
        
        this.sender = sender;
        this.content = content;
        
        time = Instant.EPOCH.getEpochSecond();
    }

    
    
    //Getters and Setters
    
    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public long getTime() {
        return time;
    }
    
}
