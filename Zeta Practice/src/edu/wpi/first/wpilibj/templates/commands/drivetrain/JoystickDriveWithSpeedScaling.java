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
public class JoystickDriveWithSpeedScaling extends CommandBase {
    
    public JoystickDriveWithSpeedScaling() {
        // Use requires() here to declare subsystem dependencies
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double lowTurn = 1;
        double highTurn = 0.7;
        double maxSpeed = 12;
        double throttle = Math.abs(((drivetrain.getCurrentLeftSpeed() + drivetrain.getCurrentRightSpeed()) / 2) / maxSpeed);
        double turnScalar = Math.abs(throttle * (highTurn - lowTurn) + lowTurn);
        drivetrain.arcadeDrive(oi.getThrottle(), oi.getTurnWithoutScaling() * turnScalar);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
