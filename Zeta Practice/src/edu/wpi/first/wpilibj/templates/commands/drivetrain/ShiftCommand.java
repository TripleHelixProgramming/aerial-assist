/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.drivetrain;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author robotics
 */
public class ShiftCommand extends CommandBase {
    
    private boolean shiftUp;
    
    public ShiftCommand(boolean shiftUp) {
        requires(shifters);
        this.shiftUp = shiftUp;
    }

    protected void initialize() { }

    protected void execute() {
        shifters.shift(shiftUp);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() { }

    protected void interrupted() { }
}
