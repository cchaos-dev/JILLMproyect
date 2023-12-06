
package model;


/**
 *
 * @author felixplajamarcos
 */

import java.io.Serializable;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class Conversation implements Serializable{
    
    //Atributes
    
    private String identifierLLM = new String();
    
    List <Message> messages;
    
    private String startTime;
    private String endTime;
    
    
    //Constructor
    
    public Conversation(String identifier){
        
        LocalDateTime actualTime = LocalDateTime. now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        String finalDate = actualTime.format (format);
        
        this.identifierLLM = identifier;
        startTime = finalDate;
        endTime = startTime;
        
        messages = new ArrayList<>();
    }
    
    
    //Methods
    
    public void addMessage(Message input){
        
        messages.add(input);
        
    }
    
    public String getHeader(){
        
        return String.format("%s | %d | %-20s",startTime, messages.size(), messages.get(0).getContent());
        
    }
    
    
    
    //Getters and Setters

    public String getIdentifierLLM() {
        return identifierLLM;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    
    

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setIdentifierLLM(String identifierLLM) {
        this.identifierLLM = identifierLLM;
    }
    
    
    
}
