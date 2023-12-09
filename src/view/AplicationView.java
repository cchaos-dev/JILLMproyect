
package view;

import controller.Controller;

/**
 *
 * @author felixplajamarcos
 */
public abstract class AplicationView {
    
    Controller controller;
    
    public abstract void showApplicationStart(String initInfo);
    public abstract void showMainMenu();
    public abstract void showApplicationEnd(String endInfo);
    
}
