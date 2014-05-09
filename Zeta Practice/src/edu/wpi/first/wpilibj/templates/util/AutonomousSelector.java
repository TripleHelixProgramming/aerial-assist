/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.util;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.networktables2.util.List;

/**
 *
 * @author robotics
 */
public class AutonomousSelector extends BinarySelector {
    
    private List commandList = new List();
    
    public AutonomousSelector(Button button1, Button button2, Button button3) {
        super(button1, button2, button3);
    }
    
    public void setCommands(List commandList) {
        this.commandList = commandList;
    }

    public SelectableCommand getSelectedCommand() {
        if (getSelectedNumber() >= commandList.size()) {
            return new NoneCommand();
        }
        return (SelectableCommand)commandList.get(this.getSelectedNumber());
    }
       
}
