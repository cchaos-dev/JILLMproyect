

package controller;

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
