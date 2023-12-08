
package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author felixplajamarcos
 */
public class Model {
    
    //Atributes
    
    private ILLM currentILLM; //ILLM that the current conversations uses
    private ILLM startILLM; //ILLM that the user chooses at the beggining of the program
    
    private IRepository managerIO;
    
    private final dataBaseConversations dataBase = new dataBaseConversations(); //dataBase with all conversations
    
    private Conversation currentConversation; //Data Base with the conversations
    
    private String userName;
    private String botName;
    
    
    //Constructor
    
    public Model (ILLM illm, IRepository io){
        currentILLM = illm;
        startILLM = illm;
        
        managerIO = io;
        
        currentConversation = new Conversation(currentILLM.getIdentifier());
        
        userName = "User";
        botName = "Bot";
    }
    
    
    //Methods
    
    //Create a new conversation
    
    public void newConversation(){
        
        currentILLM = startILLM;
        
        currentConversation = new Conversation(currentILLM.getIdentifier());
    }
    
    
    //Adding messages to the current conversation
    
    private void addMessage(String sender, String content){
        
        currentConversation.addMessage(new Message(sender,content));
        
    }
    
    
    //Getting the bot reply (it will automatically add the conversation)
    
    public String getReply(String content){
        
        addMessage(userName, content); //Adding User Message
        
        String reply = currentILLM.speak(content); //Getting the Bot reply
        
        addMessage(botName, reply); //Adding Bot reply
        
        return reply;
        
    }
    
    
    //Getting all the conversations headers
    
    public List<String> getHeaders(){
        
        return dataBase.getHeadersConversations();
    }
    
    
    //Setting current conversation to one in the dataBase by it's header
    
    public void setConversation(String header){
        
        currentConversation = dataBase.getConversation(header);
        setILLM(currentConversation.getIdentifierLLM());
        
    }
    
    
    
    
    //Exporting Conversations
    
    
    public void exportConversation(){
        
        dataBase.addConversation(currentConversation);
        
        dataBase.exportSerializable();   
    }
    
    
    public void exportAllConversations(){
        
        //It will export again all the conversations without adding any new one
        
        dataBase.exportSerializable();  
    }
    
    
    public void exportDesktop(){
        
        managerIO.exportConversations(dataBase.getMessageHistory());
    }
    
    
    
    //Importing Conversations
    
    public String importAllConversations(){
        
        
        return dataBase.importSerializable();
    }
    
    
    public String importDesktop(){
        
        try{
            
            dataBase.addNewConversations(managerIO.importConversations());
            
        }catch(IOException e){
            
            return e.getMessage();
        }
        
        return null;
    }
    
    
    
    //Setting current ILLM based on the identifier
    
    public void setILLM(String identifier){
        
        switch (identifier){
            
            case "FakeILLM" ->{
                
                currentILLM = new FakeILLM();
                
            }
            
            case "RandomCSVLLM" ->{
                
                currentILLM = new RandomCSVLLM();
            }
            
        }
    }
    
    
    //Deleting Conversation
    
    public void deleteConversation(String header){
        
        dataBase.deleteConversation(header);
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
