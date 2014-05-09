/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.drivetrain;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.util.HotGoalLocation;
import edu.wpi.first.wpilibj.templates.util.ProvidesDirection;
/**
 *
 * @author robotics
 */
public class TurnCommand extends CommandBase {
    
    private double powerLevel;
    private double distance;
    private ProvidesDirection direction;
    
    public TurnCommand(ProvidesDirection direction, double powerLevel, double distance) {
        requires(drivetrain);
        
        this.distance = distance;
        this.powerLevel = powerLevel;
        this.direction = direction;
    }

    protected void initialize() {
        CommandBase.drivetrain.rightEncoder.reset();
        CommandBase.drivetrain.leftEncoder.reset();
        System.out.println("Direction" + direction);
        System.out.println("Left Encoder: " + CommandBase.drivetrain.leftEncoder.getDistance());
        System.out.println("Right Encoder: " + CommandBase.drivetrain.rightEncoder.getDistance());
    }

    protected void execute() {
        if(direction.getDirection().equals(HotGoalLocation.RIGHT)){
            drivetrain.tankDrive(0, powerLevel);
        } else {
            drivetrain.tankDrive(powerLevel, 0);
        }
    }

    protected boolean isFinished() {
        if(direction.getDirection().equals(HotGoalLocation.RIGHT) && Math.abs(drivetrain.rightEncoder.getDistance()) >= Math.abs(distance) - 5) {
            System.out.println("Finished turning");
            return true;
        } else if (direction.getDirection().equals(HotGoalLocation.LEFT) && Math.abs(drivetrain.leftEncoder.getDistance()) >= Math.abs(distance)  - 5) {
            System.out.println("Finished turning");
            return true;
        } else {
            return false;
        }
    }

    protected void end() {
        drivetrain.tankDrive(0, 0);
    }

    protected void interrupted() { }
}
