

package controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import model.Model;
import model.*;


/**
 *
 * @author felixplajamarcos
 */
public class Controller {
    
    private UserBotData names;
    
    private Model model;
    
    private IOSerializableUserBotData namesSerializable = new IOSerializableUserBotData();
    
    
    
    public Controller(Model model){
        
        this.model = model;
    }
    
    
    
    
    //Importing and exporting names
    
    public int importNames(){
        
        names = namesSerializable.importEstate();
        
        if(names == null){
            
            names = new UserBotData();
            
            return -1;
            
        } else {
            
            model.setUserName(names.getUserName());
            model.setBotName(names.getBotName());
            
            return 0;
        }
    }
    
    public void exportNames(){
        
        namesSerializable.exportEstate(names);
    }
    
    
    
    //Getting and Setting bot and human names
    
    public void setUserName(String name){
        model.setUserName(name);
        names.setUserName(name);
    }
    
    public void setBotName(String name){
        model.setBotName(name);
        names.setBotName(name);
    }
    
    public String getUserName(){
        return names.getUserName();
    }
    
    public String getBotName(){
        return names.getBotName();
    }
   
    
    
    //Method that checks if the folder jILLM exists
    
    //0 the folder exists
    //-1 the folder doesn't exists
    
    public int folderChecker(){
        
        //Path to the folder
        
        Path pathFolder = Paths.get(System.getProperty("user.home"), "Desktop", "jILLM");
        
        
        if( ! Files.exists(pathFolder) || ! Files.isDirectory(pathFolder) ) return -1; //The folder does not exist
        
        return 0; //All good
        
    }
    
    
    
    //Importing to Desktop
    
    public String importDesktop(){
        
        return model.importDesktop();
    }
    
    
    //Exporting to Desktop
    
    public void exportDesktop(){
        
        model.exportDesktop();
    }
    
    
    
    //Getting reply
    
    public String getReply(String message){
        
        return model.getReply(message);
    }
    
    
    //Getting all headers
    
    public List<String> getAllHeaders(){
        
        return model.getHeaders();
    }
    
    
    //Getting and Setting conversation through header
    
    public Conversation getConversation(String header){
        
        model.setConversation(header);
        
        return model.getConversation();
    }
    
    
    //Exporting the conversation
    
    public void exportConversation(){
        
        model.exportConversation();
    }
    
    public void exportAllConversations(){
        
        model.exportAllConversations();
    }
    
    //Importing all conversations
    
    public String importAllConversations(){
        
        return model.importAllConversations();
    }
    
    
    //New conversation
    
    public void newConversation(){
        
        model.resetConversation();
    }
    
    
    //Delete conversation
    
    public void deleteConversation(String header){
        
        model.deleteConversation(header);
        
        model.exportAllConversations(); //It will refresh the conversations that were storaged
    }
    
    
    
}
