/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.drivetrain;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.util.HotGoalFinder;
/**
 *
 * @author robotics
 */
public class TurnCommand extends CommandBase {
    private double powerLevel;
    private double distance;
    private String direction;
    
    public TurnCommand(String direction, double powerLevel, double distance) {
        requires(drivetrain);
        
        this.distance = distance;
        this.powerLevel = powerLevel;
        this.direction = direction;
    }

    protected void initialize() {
        CommandBase.drivetrain.rightEncoder.reset();
        CommandBase.drivetrain.leftEncoder.reset();
        System.out.println("Direction" + direction);
    }

    protected void execute() {
        if(direction.equals(HotGoalFinder.RIGHT)){
            System.out.println("Turn Right");
            drivetrain.tankDrive(0, powerLevel);
        }else {
            System.out.println("Turn Left");
            drivetrain.tankDrive(powerLevel, 0);
        }
    }

    protected boolean isFinished() {
        if(Math.abs(drivetrain.rightEncoder.getDistance()) >= Math.abs(distance) || Math.abs(drivetrain.leftEncoder.getDistance()) >= Math.abs(distance)) {
            System.out.println("Finished turning");
            return true;
        }else{
            return false;
        }
    }

    protected void end() {
        drivetrain.tankDrive(0, 0);
    }

    protected void interrupted() {
    }
}
