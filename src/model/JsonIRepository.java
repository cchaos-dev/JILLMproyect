
package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author felixplajamarcos
 */
public class JsonIRepository implements IRepository{

    @Override
    public HashMap<String, Conversation> importConversations() {
        
        HashMap<String, Conversation> conversation = null;
        
        try{
            Path ruta = Paths.get(System.getProperty("user.home"),"Desktop","jILLMdata", "input.json");
                    
            File file = ruta.toFile(); 
        
            Gson gson = new Gson();
            
            Type conversationListType = new TypeToken<HashMap<String, Conversation>>() {}.getType(); //Getting HashMap<String, List<Conversation>> type

            String json = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8); //Reading JSON

            
            conversation = gson.fromJson(json, conversationListType);  //JSON -> GSON -> HashMap<String, List<Conversation>>
            
        
        }catch(IOException e){
            
            conversation = new HashMap<>();
            
        }finally{
            
            return conversation;
        } 
        
    }

    
    
    
    @Override
    public void exportConversations(HashMap<String, Conversation> conversations) {
        
        try{
            Path ruta = Paths.get(System.getProperty("user.home"),"Desktop","jILLMdata", "output.json");
                    
            File file = ruta.toFile(); 

            Gson gson = new Gson(); //GSON

            String json = gson.toJson(conversations); //conversations -> GSON -> JSON

            Files.write(file.toPath(), json.getBytes(StandardCharsets.UTF_8)); 
        
        }catch(IOException e){
            
        }

    }
    
    
    
}
