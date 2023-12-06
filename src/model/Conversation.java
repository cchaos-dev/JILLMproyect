
package model;


/**
 *
 * @author felixplajamarcos
 */

import java.util.List;
import java.time.Instant;
import java.util.ArrayList;



public class Conversation {
    
    //Atributes
    
    private String identifierLLM = new String();
    
    List <Message> messages;
    
    private long startTime;
    private long endTime;
    
    
    //Constructor
    
    public Conversation(String identifier){
        
        this.identifierLLM = identifier;
        startTime = Instant.EPOCH.getEpochSecond();
        endTime = startTime;
        
        messages = new ArrayList<>();
    }
    
    
    //Methods
    
    public void addMessage(Message input){
        
        messages.add(input);
        
    }
    
    public String getHeader(){
        
        return String.format("%d | %d | %-20s",startTime, messages.size(), messages.get(0).getContent());
        
    }
    
    
    
    //Getters and Setters

    public String getIdentifierLLM() {
        return identifierLLM;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
    
    

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setIdentifierLLM(String identifierLLM) {
        this.identifierLLM = identifierLLM;
    }
    
    
    
}
