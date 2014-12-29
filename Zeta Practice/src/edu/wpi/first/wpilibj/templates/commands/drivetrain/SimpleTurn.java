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
public class SimpleTurn extends CommandBase {
    double power;
    double distance;
    boolean direction;
    
    public SimpleTurn(double power, double distance, boolean direction){
        requires(drivetrain);
        this.power = power;
        this.distance = distance;
        this.direction = direction;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(direction == true){
            //true = right
            drivetrain.tankDrive(power, -power);
        }else if(direction == false){
            //false = left
            drivetrain.tankDrive(-power, power);
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Math.abs(drivetrain.rightEncoder.getDistance()) >= Math.abs(distance)) {
            return true;
        }else{
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
