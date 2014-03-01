/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.util;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 * @author robotics
 */
public class DigitalIOButton extends Button {
    
    private DigitalInput input;
    
    public DigitalIOButton(int port) {
        input = new DigitalInput(port);
    }

    public boolean get() {
        return input.get();
    }
    
}
