/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author felixplajamarcos
 */
public class FakeILLM implements ILLM{

    HashMap<String, String> replyOptions = new HashMap<>();
    
    
    //Constructor
    
    public FakeILLM(){
        
        importPhrases(); //When FakeILLM is created, it will imports its phrases
        
    }
    
    
    
    //Methods
    
    @Override
    public String speak(String input) {
        
        String reply = replyOptions.get(input);
        
        if(reply == null || reply.length() == 0){
            return "No tengo respuesta para eso...";
        }
        
        return reply;
    }
    

    @Override
    public String getIdentifier() {
        return "FakeILLM";
    }
    
    
    private void importPhrases(){
        
        String[] chunks;
        
        try{
            
            Path ruta = Paths.get("frasesFILLM.csv");
            
            List<String> phrases = Files.readAllLines(ruta, StandardCharsets.UTF_8);
        
            
            for(String phrase : phrases){
                
                chunks = phrase.split(";");
                
                if (chunks.length == 2){
                    replyOptions.put(chunks[0], chunks[1]);
                }
                
            }
        
        
        }catch(IOException e){
            
            
        }
    }
        
    
}
