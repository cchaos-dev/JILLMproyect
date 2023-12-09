
package jllm;

import view.ConsoleInterface;
import view.TTS;

import model.Model;
import controller.Controller;

import model.RandomCSVLLM;
import model.FakeILLM;
import model.ILLM;

import model.IRepository;
import model.JsonIRepository;
import model.SmartLLM;
import model.XmlIRepository;
import view.AplicationView;


/**
 *
 * @author felixplajamarcos
 */
public class JLLM {

    public static void main(String[] args) {
        
        
        //Selecting the IRepository
        
        IRepository currentIRepository;
        
        switch (args[0].toUpperCase()){
            
            case "JSON" ->{
                currentIRepository = new JsonIRepository();
            }
            
            case "XML" ->{
                currentIRepository = new XmlIRepository();
            }
            
            default ->{
                
                System.out.println("El repositorio que se ha introducido en los argumentos no es válido\n");
                return;
            }
            
        }
        
        
        //Selecting the ILLM
        
        ILLM currentILLM;
        
        switch(args[1].toUpperCase()){
            
            case "FAKE" ->{
                currentILLM = new FakeILLM();
            }
            
            case "RANDOMCSV" ->{
                currentILLM = new RandomCSVLLM();
            }
            
            case "SMART" ->{
                currentILLM = new SmartLLM();
            }
            
            default ->{
                
                System.out.println("El ILLM que se ha introducido en los argumentos no es válido\n");
                return;
            }
        }
        
        
        
        Model model = new Model(currentILLM, currentIRepository); //Instanciating the model
        
        Controller controller = new Controller(model); //Instanciating the controller
        
        
        
        //Selecting the view

        AplicationView view; //Instanciating the view
        
        
        switch(args[2].toUpperCase()){
            
            case "CONSOLE" ->{
                view = new ConsoleInterface(controller);
            }
            
            case "TTS" ->{
                view = new TTS(controller);
            }
            
            default ->{
                
                System.out.println("La interfaz que se ha introducido en los argumentos no es válida\n");
                return;
            }
            
        }
        
       
        //Program
        
        view.showApplicationStart("");
        view.showMainMenu();
        view.showApplicationEnd("");
        
        
    }
    
}
