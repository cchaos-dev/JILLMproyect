/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.util.HashMap;



/**
 *
 * @author felixplajamarcos
 */
public class FakeILLM implements ILLM{

    HashMap<String, String> replyOptions = new HashMap<>();
    
    
    //Constructor
    
    public FakeILLM(){
        
        //Uploading the phrases
        replyOptions.put("Hola","Hola :)");
        replyOptions.put("Hola, ¿cómo estás?","¡Hola! Estoy bien, ¿y tú?");
        replyOptions.put("Tienes alguna mascota?","No tengo mascotas, ya que no tengo una forma física, pero me encantaría escuchar sobre la tuya.");
        replyOptions.put("Explícame la teoría de cuerdas","La teoría de cuerdas es un marco teórico en la física que sugiere que las partículas fundamentales son en realidad cuerdas vibrantes.");
        replyOptions.put("goto()","Viva la mejor instrucción de C!!!");
        replyOptions.put("café","Viva Java!!!");
        
    }
    
    
    
    //Methods
    
    @Override
    public String speak(String input) {
        
        
        if (replyOptions.containsKey(input)) return replyOptions.get(input);
        else return "No tengo respuesta para eso...";
        
    }
    

    @Override
    public String getIdentifier() {
        return "FakeILLM";
    }
    
}