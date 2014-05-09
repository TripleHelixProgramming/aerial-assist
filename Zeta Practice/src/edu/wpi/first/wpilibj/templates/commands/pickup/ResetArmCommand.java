/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.pickup;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author robotics
 */
public class ResetArmCommand extends CommandBase {
    
    public ResetArmCommand() {
        requires(pickUp);
    }
    
    protected void initialize() { }

    protected void execute() { }

    protected boolean isFinished() {
        return true;
    }

    protected void end() { }

    protected void interrupted() { }
}
