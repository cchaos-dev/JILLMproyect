
package model;


import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;


public class SerializableIRepository implements IRepository{

    @Override
    public HashMap<String, Conversation> importConversations() {

        try (ObjectInputStream o = new ObjectInputStream(new FileInputStream("conversations.bin"))){
            
            HashMap<String, Conversation> conversations;
            
            try {
                
                conversations = (HashMap<String, Conversation>) o.readObject();
                o.close();
                return conversations;
                
                
            } catch (ClassNotFoundException ex) {
                
                o.close();
                return null;
            }
 
            
        }catch(IOException e){
            
            return null;
        } 
     
    }

    @Override
    public void exportConversations(HashMap<String, Conversation> conversations) {
        
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("conversations.bin"))){
    
            
            o.writeObject(conversations);
            
            o.close();
            
            
        }catch(IOException e){
            
        } 


    }
    
    
    
    
}
