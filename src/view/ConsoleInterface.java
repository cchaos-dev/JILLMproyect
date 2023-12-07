
package view;

import static com.coti.tools.Esdia.*;

import java.time.format.DateTimeFormatter;

import java.util.List;

import controller.Controller;
import java.time.LocalDateTime;
import model.*;


public class ConsoleInterface extends AplicationView {
    
    private final Controller controller = new Controller();
    
    private String userName;
    private String botName;
    
    
    
    @Override
    public void showApplicationStart(String initInfo) {
        
        System.out.println("Bienvenido!!!!");
        
        controller.importAllConversations();
        
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
    
    
    
    //Exporting/Importing menu
    
    private void ioDataDesktop(){
        
        String option;
        
        
        do{
            
            System.out.printf("""
                              i) Importar archivos
                              e) Exportar archivos
                              s) Salir
                              """);
            
            option = readString("> Opción: ");
            
            
            switch(option.toLowerCase()){
                
                case "i" -> {
                    
                    bigScreenSeparator();
                    importDesktop();
                    
                }
                
                case "e" -> {
                    
                    bigScreenSeparator();
                    exportDesktop();
                    
                }
                
                
            }
            
            bigScreenSeparator();
            
        }while(! option.equalsIgnoreCase("s"));        
    }
    
    
    
    //Exporting and Importing JSON and XML conversations
    
    private void importDesktop (){
        
        String option;
        
        int errMssg = 0;
        
        
        do{
            
            System.out.printf("""
                              j) Importar JSON
                              x) Importar XML
                              s) Salir
                              """);
            
            option = readString("> Opción: ");
            
            
            switch(option.toLowerCase()){
                
                case "j" -> {
                    
                    errMssg = controller.fileChecker("conversations.json");
                    
                    if (errMssg != 0){
                        showErrorFile(errMssg, "conversations.json");
                        break;
                    }

                    controller.importJSON();
                    
                    System.out.printf("Archivo importado con éxito\n");
                    readString("Presiona ENTER para continuar...");
                    
                }
                
                case "x" -> {
                    
                    errMssg = controller.fileChecker("conversations.xml");
                    
                    if (errMssg != 0){
                        showErrorFile(errMssg, "conversations.xml");
                        break;
                    }

                    controller.importXML();
                    
                    System.out.printf("Archivo importado con éxito\n");
                    readString("Presiona ENTER para continuar...");
                    
                }
                
                
            }
            
            bigScreenSeparator();
            
        }while(! option.equalsIgnoreCase("s"));            
        
    }
    
    
    
    private void exportDesktop (){
        
        String option;
        
        int errMssg = 0;
        
        
        do{
            
            System.out.printf("""
                              j) Exportar JSON
                              x) Exportar XML
                              s) Salir
                              """);
            
            option = readString("> Opción: ");
            
            
            switch(option.toLowerCase()){
                
                case "j" -> {
                    
                    errMssg = controller.fileChecker("conversations.json");
                    
                    if (errMssg == -2){
                        showErrorFile(errMssg, "conversations.json");
                        break;
                    }

                    controller.exportJSON();
                    
                    System.out.printf("Archivo exportado con éxito\n");
                    readString("Presiona ENTER para continuar...");
                    
                }
                
                case "x" -> {
                    
                    errMssg = controller.fileChecker("conversations.xml");
                    
                    if (errMssg == -2){
                        showErrorFile(errMssg, "conversations.xml");
                        break;
                    }

                    controller.exportXML();
                    
                    System.out.printf("Archivo exportado con éxito\n");
                    readString("Presiona ENTER para continuar...");
                    
                }
                
                
            }
            
            bigScreenSeparator();
            
        }while(! option.equalsIgnoreCase("s"));      
        
        
    }
    
    
    
    //Showing the error message
    
    private void showErrorFile (int errNumb, String fileName){
        
        switch (errNumb){
            
            case -1 ->{
                System.err.printf("El archivo %s no se encontró\n", fileName);
                readString("Presiona ENTER para continuar...");
            }
            
            case -2 ->{
                System.err.printf("La carpeta jILLMdata no se encontró en el escritorio\n");
                readString("Presiona ENTER para continuar...");
            }
            
        }
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
        
        controller.newConversation();
        
        LocalDateTime actualTime = LocalDateTime. now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        String finalDate = actualTime.format (format);
        
        System.out.printf("Conversación del %s\n\n", finalDate);
        chatInterface();
        
        controller.exportConversation();
        
    }
    
    
    
    //User Interfaces
    
    //It prints a conversation
    
    private void printConversation(String header){
        
        Conversation conversation = controller.getConversation(header);
        
        System.out.printf("Conversación del %s\n\n", conversation.getEndTime());
        
        List<Message> messages = conversation.getMessages();
        
        for(Message message : messages)
            System.out.println(message.getSender() + " ["+message.getTime()+"]: "+message.getContent());
        
        
        
    }
    
    
    //It shows the chat History and returns the one you chose
    
    private String showChatHistory(){
        
        //controller.importAllConversations();
        
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
            LocalDateTime actualTime = LocalDateTime. now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
            String finalDate = actualTime.format (format);
            
            outputMessage = userName + " ["+finalDate+"]: ";
            messageUser = readString(outputMessage);
            
            
            if (! messageUser.equals("/salir")){
                
                replyBot = controller.getReply(messageUser);

                System.out.printf("%s [%s]: %s\n",botName,finalDate, replyBot);
                
            }
            
        }while (! messageUser.equalsIgnoreCase("/salir"));
        
    }
    
    
    //Just for screen format
    
    private void bigScreenSeparator(){
        System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    
}
