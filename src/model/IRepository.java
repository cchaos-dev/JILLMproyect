
package model;

import java.util.List;

/**
 *
 * @author felixplajamarcos
 */

public interface IRepository {
    
    public void deleteConversation(String header);
    
    public List<String> getHeadersConversations();
    
    public Conversation importConversation(String headerConversation);
    
    public void exportConversations(Conversation conversation);
    
}
