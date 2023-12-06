
package view;

import controller.Controller;

/**
 *
 * @author felixplajamarcos
 */
public abstract class AplicationView {
    
    Controller controoller;
    
    abstract void showApplicationStart(String initInfo);
    abstract void showMainMenu();
    abstract void showApplicationEnd(String endInfo);
    
}
