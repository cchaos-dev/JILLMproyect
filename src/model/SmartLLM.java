
package model;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import java.io.IOException;

/**
 *
 * @author felixplajamarcos
 */
public class SmartLLM implements ILLM{
    
   
    private OllamaAPI ollamaAPI;
    
    public SmartLLM(){
        
        String host = "http://localhost:11434/";
        
        ollamaAPI = new OllamaAPI(host);
        
        
    }
    
    
    

    @Override
    public String speak(String input) {

        String response;
        
        try {
            
            response = ollamaAPI.ask("mistral",input);
            
        } catch (OllamaBaseException | IOException | InterruptedException ex) {
            
            response = "Hola, no me encuentro disponible en este momento, prueba más tarde...";
            
        }
        
        
        return response;
    }
    

    @Override
    public String getIdentifier() {
        return "SmartLLM";
    }
    
    
    
}
