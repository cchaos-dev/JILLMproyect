/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author felixplajamarcos
 */
public class XmlIRepository implements IRepository{

    @Override
    public HashMap<String, Conversation> importConversations() {
        
        
        HashMap<String, Conversation> conversations = null;
        
        try{
            
            Path ruta = Paths.get(System.getProperty("user.home"),"jILLM", "conversations.xml");
                    
            
            XmlMapper xmlMapper = new XmlMapper(); //Instanciating XML manager

            String xml = new String(Files.readAllBytes(ruta), StandardCharsets.UTF_8); //Reading the XML file

            MapType conversationMapType = TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, Conversation.class); //Getting the HashMap<String, Conversation> type
            
            conversations = xmlMapper.readValue(xml, conversationMapType); //XML String -> HashMap<String, Conversation>
            
            
        }catch(IOException e){
            
            conversations = new HashMap<>();
            
        }finally{
            
            return conversations;
            
        }
        
    }

    @Override
    public void exportConversations(HashMap<String, Conversation> conversations) {
        
        
        try{
            Path ruta = Paths.get(System.getProperty("user.home"),"jILLM", "conversations.xml");
            
            XmlMapper xmlMapper = new XmlMapper(); //Getting the XML Mapper

            String xml = xmlMapper.writeValueAsString(conversations); //HashMap<String, Conversation> -> XML

            Files.write(ruta, xml.getBytes(StandardCharsets.UTF_8)); //Writing into the file
            
        }catch(IOException e){
            
            
        }

    }
    
    
    
}
