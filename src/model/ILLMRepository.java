/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.google.gson.Gson;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author felixplajamarcos
 */
public class ILLMRepository {
    
    
    //Atributes
    
    HashMap<String, Conversation> messageHistory = new HashMap<>();
    //The string will be the header of a conversation: EpochUnixSecondsInicio | Número de mensajes | Primeros 20 caracteres del primer mensaje
    //The List will be the messages sent
    
    
    //Constructor
    
    public ILLMRepository(){
        
        //Once its created, it will import all the conversations
        
        importAllHistory();
        
    }
    
    
    //Methods
    

    public void deleteConversation(String header){
        
        messageHistory.remove(header);
        
    }
    
    
    private void importAllHistory(){
        
        try{
            File file = new File("ILLM.json"); 
        
            Gson gson = new Gson();
            
            Type conversationListType = new TypeToken<HashMap<String, Conversation>>() {}.getType(); //Getting HashMap<String, List<Conversation>> type

            String json = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8); //Reading JSON

            
            messageHistory = gson.fromJson(json, conversationListType);  //JSON -> GSON -> HashMap<String, List<Conversation>>
            
        
        }catch(IOException e){
            
            messageHistory = new HashMap<>();
            
        } 
    }
    
    

    public List<String> getHeadersConversations(){ 

        //It will return all the chat headers
        
        List<String> identifier = new ArrayList<>();
        
        for( String id : messageHistory.keySet() )
            identifier.add(id);
        
        
        return identifier;
        
    }
    


    public Conversation getConversation(String headerConversation) {
        
        //Returns the chat based on the header given
        
        return messageHistory.get(headerConversation);
        
    }

    
    

    public void exportConversations(Conversation conversation) {
        
        //It will add the new conversation
        //Then, it will export all the converations
        
        if (conversation != null) messageHistory.put(conversation.getHeader() ,conversation);
        
        
        try{
            File file = new File("ILLM.json"); 

            Gson gson = new Gson(); //GSON

            String json = gson.toJson(messageHistory); //conversations -> GSON -> JSON

            Files.write(file.toPath(), json.getBytes(StandardCharsets.UTF_8)); 
        
        }catch(IOException e){
            
        }
    }
    
}
