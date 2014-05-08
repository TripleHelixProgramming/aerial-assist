/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.util.ProvidesDirection;

/**
 *
 * @author robotics
 */
public class WaitForHotGoal extends CommandBase {
    private String hotPosition;
    private ProvidesDirection providesHotGoal;
            
    public WaitForHotGoal(String hotPosition, ProvidesDirection providesHotGoal) {
        this.hotPosition = hotPosition;
        this.providesHotGoal = providesHotGoal;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println(hotPosition + " = " + providesHotGoal.getDirection());
        return hotPosition.equals(providesHotGoal.getDirection());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
