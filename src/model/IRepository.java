
package model;

import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author felixplajamarcos
 */

public interface IRepository {
    
    public HashMap<String, Conversation> importConversations() throws IOException;
    
    public void exportConversations(HashMap<String, Conversation> conversations);
    
}
