/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.util.CheesyVisionServer;
import edu.wpi.first.wpilibj.templates.util.HotGoalFinder;
import edu.wpi.first.wpilibj.templates.util.ProvidesDirection;

/**
 *
 * @author robotics
 */
public class WaitForAnyGoal extends CommandBase implements ProvidesDirection{
    private CheesyVisionServer server = CheesyVisionServer.getInstance();
    public String currentHot = HotGoalFinder.LEFT;
    public WaitForAnyGoal() {
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        server.reset();
        server.startSamplingCounts();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (server.getLeftStatus()) {
            currentHot = HotGoalFinder.LEFT;
            System.out.println("Found left hot goal");
        } else if (server.getRightStatus()) {
            currentHot = HotGoalFinder.RIGHT;
            System.out.println("Found right hot goal");
        } else {
            System.out.println("Found no hot goal");
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println("CurrentHot: " + currentHot);
        return (server.getLeftStatus() || server.getRightStatus());
    }

    // Called once after isFinished returns true
    protected void end() {
        server.stopSamplingCounts();
        server.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    public String getDirection() {
        return currentHot;
    }
}
