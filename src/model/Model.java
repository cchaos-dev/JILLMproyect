
package model;

import java.util.List;

/**
 *
 * @author felixplajamarcos
 */
public class Model {
    
    //Atributes
    
    private ILLM currentILLM; //ILLM
    private ILLMRepository managerIO; //IO files
    
    private Conversation currentConversation; //Data Base with the conversations
    
    private String userName;
    private String botName;
    
    
    //Constructor
    
    public Model (ILLM illm, ILLMRepository rep){
        currentILLM = illm;
        managerIO = rep;
        
        currentConversation = new Conversation(currentILLM.getIdentifier());
        
        userName = "User";
        botName = "Bot";
    }
    
    
    //Methods
    
    public void resetConversation(){
        
        currentConversation = new Conversation(currentILLM.getIdentifier());
    }
    
    
    public void addMessage(String sender, String content){
        
        currentConversation.addMessage(new Message(sender,content));
        
    }
    
    public String getReply(String content){
        
        addMessage(userName, content); //Adding User Message
        
        String reply = currentILLM.speak(content); //Getting the Bot reply
        
        addMessage(botName, reply); //Adding Bot reply
        
        return reply;
        
    }
    
    
    public List<String> getHeaders(){
        
        return managerIO.getHeadersConversations();
    }
    
    
    public void setConversation(String header){
        
        currentConversation = managerIO.getConversation(header);
        setILLM(currentConversation.getIdentifierLLM());
        
    }
    
    
    public void exportConversation(){
        
        managerIO.exportConversations(currentConversation);   
    }
    
    public void refreshConversationsSaved(){
        managerIO.exportConversations(null);  
    }
    
    
    public void setILLM(String identifier){
        
        switch (identifier){
            
            case "FakeILLM" ->{
                
                currentILLM = new FakeILLM();
                
            }
            
            
        }
    }
    
    
    public void deleteConversation(String header){
        
        managerIO.deleteConversation(header);
    }
    
    
    //Getters and Setters
    
    public List<Message> getAllMessages(){
    
        return currentConversation.getMessages();
    }
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }
    
    public Conversation getConversation(){
        return currentConversation;
    }
    
    
    
}
