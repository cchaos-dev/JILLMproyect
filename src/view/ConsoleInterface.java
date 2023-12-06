
package view;

import static com.coti.tools.Esdia.*;

import java.time.Instant;
import java.util.List;

import controller.Controller;
import model.*;


public class ConsoleInterface extends AplicationView {
    
    private final Controller controller = new Controller();
    
    private String userName;
    private String botName;
    
    
    
    @Override
    public void showApplicationStart(String initInfo) {
        
        System.out.println("Bienvenido!!!!");
        
        
        
        if (controller.importNames() == -1){
        
            userName = readString("Indica tu nombre: ");
            botName = readString("Indica el nombre del bot: ");

            controller.setUserName(userName);
            controller.setBotName(botName);
            
            controller.exportNames();
            
        } else {
            
            userName = controller.getUserName();
            botName = controller.getBotName();
            
            
        }
        
        bigScreenSeparator(); //Just for the format (it adds some \n)
    }

    
    
    @Override
    public void showMainMenu() {
        
        String option;
        
        do{
            System.out.printf("""
                              i) Iniciar nueva conversación
                              m) Mostrar conversaciones ya existentes
                              e) Eliminar una conversación existente
                              x) Exportar o Importar datos desde Escritorio
                              s) Salir
                              """);

            option = readString("> Opción: "); 
            
            bigScreenSeparator();
            
            switch (option.toLowerCase()){
                
                case "i" -> {
                    
                    //Llamar a controller para crear una nueva conversación (tendrá que usar args para el ILLM)
                    newChat();
                    
                }
                
                case "m" -> {
                    
                    oldChat();
                    
                }
                
                case "e" -> {
                    
                    deleteChat();
                    
                }
                
                case "x" ->{
                    
                    ioDataDesktop();
                    
                }
                
            }
            
            bigScreenSeparator();
            
        }while(! option.equalsIgnoreCase("s"));
        
        showApplicationEnd("");
        //Finalizar
        
    }

    
    
    @Override
    public void showApplicationEnd(String endInfo) {

    }
    
    
    
    //Exporting menu
    
    private void ioDataDesktop(){
        
        
        
        
        
    }
    
    
    
    
    
    
    //Deleting, loading and creating chats
    
    
    public void deleteChat(){
        
        String header = showChatHistory();
        
        if(header == null) return;
        
        controller.deleteConversation(header);
        
    }
    
    
    
    public void oldChat(){
        
        String header = showChatHistory();
        
        if(header == null) return;
        
        
        bigScreenSeparator();
        
        printConversation(header);
        
        chatInterface();
        
        
        controller.exportConversation();
        
        
    }
    
    
    public void newChat(){
        
        controller.resetConversation();
        
        System.out.printf("Conversación del %d\n", Instant.EPOCH.getEpochSecond());
        chatInterface();
        
        controller.exportConversation();
        
    }
    
    
    
    //User Interfaces
    
    //It prints a conversation
    
    private void printConversation(String header){
        
        Conversation conversation = controller.getConversation(header);
        
        System.out.printf("Conversación del %d\n", conversation.getEndTime());
        
        List<Message> messages = conversation.getMessages();
        
        for(Message message : messages)
            System.out.println(message.getSender() + " ["+message.getTime()+"]: "+message.getContent());
        
        
        
    }
    
    
    //It shows the chat History and returns the one you chose
    
    private String showChatHistory(){
        
        List<String> headers = controller.getAllHeaders(); // Getting all headers
        int index = 1;
        int option;
     
        
        for(String header : headers){
            System.out.printf("%d. %s\n", index, header);
            index++;
        }
        System.out.printf("0. Salir\n");
        
        
        do{
            option = readInt("> Opción: ");
            
            if (option <= headers.size() && option != 0){
                
                //Returns the header
        
                return headers.get(option-1);
                
                
            }
            
        }while(option != 0);
        
        return null;
        
    }
    
    
    
    //It shows the chat interface and let you interactuate with the bot
    
    private void chatInterface(){
        
        String messageUser;
        String replyBot;
        
        String outputMessage;
       
        
        do{
            outputMessage = userName + " ["+Instant.EPOCH.getEpochSecond()+"]: ";
            messageUser = readString(outputMessage);
            
            
            if (! messageUser.equals("/salir")){
                
                replyBot = controller.getReply(messageUser);

                System.out.printf("%s [%d]: %s\n",botName,Instant.EPOCH.getEpochSecond(), replyBot);
                
            }
            
        }while (! messageUser.equalsIgnoreCase("/salir"));
        
    }
    
    
    //Just for screen format
    
    private void bigScreenSeparator(){
        System.out.printf("\n\n\n\n");
    }
    
    
}
