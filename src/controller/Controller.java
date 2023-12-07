

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
    
    private Model model = new Model(new FakeILLM());
    
    private IOSerializableUserBotData namesSerializable = new IOSerializableUserBotData();
    
    
    
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
   
    
    
    //Method that checks if a file exists and it located in the Desktop folder jILLMdata
    
    //0 file located, it exists and its in the folder
    //-1 the folder exists but the file is not there
    //-2 neither the file nor the folder exists
    
    public int fileChecker(String fileName){
        
        //Path to the folder
        
        Path pathFolder = Paths.get(System.getProperty("user.home"), "Desktop", "jILLMdata");
        
        Path pathFile = Paths.get(System.getProperty("user.home"), "Desktop", "jILLMdata", fileName);
        
        File file = pathFile.toFile();
        
        
        if( ! Files.exists(pathFolder) ) return -2; //The folder does not exist
        
        if (! file.exists() || ! file.isFile()) return -1; //The file does not exist or it's a folder
        
        return 0; //All good
        
    }
    
    
    
    
    
    //Import through JSON or XML
    
    public void importJSON(){
        
        model.importJSON();
        
    }
    
    
    public void importXML(){
        
        model.importXML();
    }
    
    
    //Export through JSON or XML
    
    public void exportJSON(){
        
        model.exportJSON();
    }
    
    public void exportXML(){
        
        model.exportXML();
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
        
        model.refreshConversationsSaved();
    }
    
    //Importing all conversations
    
    public void importAllConversations(){
        
        model.importConversations();
    }
    
    
    //New conversation
    
    public void newConversation(){
        
        model.resetConversation();
    }
    
    
    //Delete conversation
    
    public void deleteConversation(String header){
        
        model.deleteConversation(header);
        
        model.refreshConversationsSaved(); //It will refresh the conversations that were storaged
    }
    
    
    
}
