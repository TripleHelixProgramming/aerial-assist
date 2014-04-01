/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.TurnCommand;
import edu.wpi.first.wpilibj.templates.util.ProvidesHotGoal;

/**
 *
 * @author robotics
 */
public class TurnToHotGoal extends TurnCommand {
    
    private ProvidesHotGoal hotGoalProc;
    private String turnToSide;
    
    public TurnToHotGoal(ProvidesHotGoal hotGoalProc, String turnToSide) {
        super(turnToSide, 0.85, 500);
        this.turnToSide = turnToSide;
        this.hotGoalProc = hotGoalProc;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        super.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (hotGoalProc.getHotGoal().equals(turnToSide) || super.isFinished()) {
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        drivetrain.tankDrive(0, 0);
    }
}
