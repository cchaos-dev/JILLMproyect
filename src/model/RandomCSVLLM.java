/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Random;

/**
 *
 * @author felixplajamarcos
 */
public class RandomCSVLLM implements ILLM{
    
    
    HashMap<String, ArrayList<String>> replies = new HashMap<>();
    //String: identifier (sorpresa, pregunta, negación...)
    //ArrayList: possible answers
    
    
    //Constructor
    
    public RandomCSVLLM(){
        
        loadReplies();
        
    }
    
    
    
    
    //Methods
    

    @Override
    public String speak(String input) {
        
        //Checking if the input is valid
        
        if (! replies.keySet().contains(input)) return "No tengo respuesta para eso...";
        
        
        //Getting the maximum number of replies possible for that identifier
        
        int numberOfReplies = replies.get(input).size();
        
        
        //Getting the random reply
        
        Random random = new Random();
        
        return replies.get(input).get(random.nextInt(numberOfReplies));
    }

    
    
    @Override
    public String getIdentifier() {
        return "RandomCSVLLM";
    }
    
    
    
    
    private void loadReplies(){
        
        List<String> lines;
        
        String[] chunks;
        
        
        //Instanciating all the keys of the HashMap
        
        for(String identifier : loadIdentifiers())
            replies.put(identifier, new ArrayList<>());
            
        
        try{
            
            Path ruta = Paths.get("input.csv");
            lines = Files.readAllLines(ruta); 
            
            lines.remove(0); //Removing the "tipo,longitud,frase" line
            
            for(String currentLine : lines){
                
                chunks = currentLine.split(",");
                
                if(chunks.length != 3 || chunks[2].equals("Frase adicional de ejemplo para "+chunks[0])) continue; //Not a valid line
                
                replies.get(chunks[0]).add(chunks[2]);
                
            }
            
            
            
        }catch(IOException e){
            
            
        }
        
        
        
        
    }
    
    
    
    private List<String> loadIdentifiers(){
        
        List<String> identifiers = new ArrayList<>();
        List<String> lines;
        
        String[] chunks;
        
        
        try{
            
            Path ruta = Paths.get("input.csv");
            lines = Files.readAllLines(ruta); 
            
            lines.remove(0); //Removing the "tipo,longitud,frase" line
            
            for(String currentLine : lines){
                
                chunks = currentLine.split(",");
                
                if(chunks.length != 3) continue; //Not a valid identifier
                
                if(!identifiers.contains(chunks[0])) identifiers.add(chunks[0]);
                
            }
            
            
        }catch(IOException e){
            
            
        }
        
        
        return identifiers;
    }
    
}
