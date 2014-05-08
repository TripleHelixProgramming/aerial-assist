/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//import edu.wpi.first.wpilibj.DigitalInput;

package edu.wpi.first.wpilibj.templates.util;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 * @author robotics
 */
public class BinarySelector {
    private Button autoSelector1, autoSelector2, autoSelector3;
    
    public BinarySelector(Button button1, Button button2, Button button3){
        autoSelector1 = button1;
        autoSelector2 = button2;
        autoSelector3 = button3;
        
    }
    
    public int getSelectedNumber(){
        return (autoSelector1.get() ? 1 : 0) +        
                2 * (autoSelector2.get() ? 1 : 0) +
                4 * (autoSelector3.get() ? 1 : 0);
        
    }
    
}
