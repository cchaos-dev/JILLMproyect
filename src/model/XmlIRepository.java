/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felixplajamarcos
 */
public class XmlIRepository implements IRepository{

    @Override
    public HashMap<String, Conversation> importConversations() {
        
        
        HashMap<String, Conversation> conversationsOutput = null;
        
        List<Conversation> conversations = new ArrayList<>();
        
        try{
            
            Path ruta = Paths.get(System.getProperty("user.home"),"Desktop","jILLMdata", "input.xml");
            
            ObjectMapper objectMapper = new XmlMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //Reading the XML file
            
            conversations = objectMapper.readValue(ruta.toFile(), new TypeReference<List<Conversation>>() {}); //Reading the XML file

            conversationsOutput = this.arrayListToHashMap(conversations);
            
            
        }catch(IOException e){
            
            conversationsOutput = new HashMap<>();
            
        }finally{
            
            return conversationsOutput;
            
        }
        
    }

    @Override
    public void exportConversations(HashMap<String, Conversation> conversationsInput) {
        
        ArrayList<Conversation> conversations = this.hashMapToArrayList(conversationsInput);
        
        
        try{
            Path ruta = Paths.get(System.getProperty("user.home"),"Desktop","jILLMdata", "output.xml");
            
            XmlMapper xmlMapper = new XmlMapper(); //Getting the XML Mapper

            String xml = xmlMapper.writeValueAsString(conversations); //HashMap<String, Conversation> -> XML

            Files.write(ruta, xml.getBytes(StandardCharsets.UTF_8)); //Writing into the file
            
        }catch(IOException e){
            
            
        }

    }
    
    
    
    private HashMap<String, Conversation> arrayListToHashMap(List<Conversation> conversations){
        
        
        HashMap<String, Conversation> output = new HashMap<>();
        
        for (Conversation conversation : conversations)
            output.put(conversation.getHeader(), conversation);
        
        return output;
    }
    
    
   
    private ArrayList<Conversation> hashMapToArrayList(HashMap<String, Conversation> conversations){
        
        ArrayList<Conversation> output = new ArrayList<>();
        
        for(Conversation conversation: conversations.values())
            output.add(conversation);
        
        return output;
    }
    
    
    
    
    
    
}
