
package model;

import java.util.HashMap;

/**
 *
 * @author felixplajamarcos
 */

public interface IRepository {
    
    public HashMap<String, Conversation> importConversations();
    
    public void exportConversations(HashMap<String, Conversation> conversations);
    
}
