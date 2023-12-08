
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import java.io.IOException;

/**
 *
 * @author felixplajamarcos
 */
public class dataBaseConversations {
    
    private HashMap<String, Conversation> messageHistory = new HashMap<>();
    //The string will be the header of a conversation: EpochUnixSecondsInicio | Número de mensajes | Primeros 20 caracteres del primer mensaje
    //The List will be the messages sent
    
    private IRepository serializableIO = new SerializableIRepository();
    private IRepository jsonIO = new JsonIRepository();
    private IRepository xmlIO = new XmlIRepository();
    
    
    
    
    //Deleting conversation
    
    public void deleteConversation(String header){
        
        messageHistory.remove(header);
        
    }
    
    
    //Getting all headers
    
    public List<String> getHeadersConversations(){ 

        //It will return all the chat headers
        
        List<String> identifier = new ArrayList<>();
        
        for( String id : messageHistory.keySet() )
            identifier.add(id);
        
        
        return identifier;
        
    }
    
    
    //Getting conversations by header
    
    public Conversation getConversation(String headerConversation) {
        
        //Returns the chat based on the header given
        
        return messageHistory.get(headerConversation);
    } 
    
    
    //Adding conversations
    
    public void addConversation(Conversation conversation){
        
        
        //Checking if the user is working in an old conversation
        
        String[] conversationChunks = conversation.getHeader().split(Pattern.quote("|"));
        String[] historyChunks;
        
        for (String header : messageHistory.keySet()){
            
            historyChunks = messageHistory.get(header).getHeader().split(Pattern.quote("|"));
            
            if (conversationChunks[0].equals( historyChunks[0] ) ){
                
                messageHistory.remove(header);
                
                messageHistory.put(conversation.getHeader(), conversation);
                return;
                
            } 
        }
        
        messageHistory.put(conversation.getHeader(), conversation);
    }
    
    
    
    
    //Importing all
    
    public String importSerializable(){
        
        try{
            
            messageHistory = serializableIO.importConversations();
            return null;
        
        }catch (IOException e){
            
            messageHistory = new HashMap<>();
            return e.getMessage();
        }
    }
    
    
    public void addNewConversations(HashMap<String, Conversation> importedConversations){
        
        //Adding only the new conversations
        
        for (String header : importedConversations.keySet())
            if( ! messageHistory.containsKey(header) )
                messageHistory.put(header, importedConversations.get(header));
        
    }
    
    
    
    //Exporting all
    
    public void exportSerializable(){
        
        serializableIO.exportConversations(messageHistory);
    }
  
      
    
    //Getters and Setters

    public HashMap<String, Conversation> getMessageHistory() {
        return messageHistory;
    }

    public void setMessageHistory(HashMap<String, Conversation> messageHistory) {
        this.messageHistory = messageHistory;
    }
    
}
