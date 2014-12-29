/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.templates.subsystems.Shifters;
import java.util.Date;

/**
 *
 * @author robotics
 */
public class AutoShifting extends CommandBase {
    
    private Date lastShiftTime = new Date();
    public AutoShifting() {
        // Use requires() here to declare subsystem dependencies
        requires(shifters);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double shiftSpeed = 6;
        double currentRight = drivetrain.getCurrentRightSpeed();
        double currentLeft = drivetrain.getCurrentLeftSpeed();
        
        if (new Date().getTime() - lastShiftTime.getTime() > 1000) {
            if ((currentLeft > shiftSpeed && currentRight > shiftSpeed)
                    || (currentLeft < -shiftSpeed && currentRight < -shiftSpeed)) {
                shifters.shift(Shifters.HIGH_GEAR);
            } else {
                shifters.shift(Shifters.LOW_GEAR);
            }
            lastShiftTime = new Date();
        }
        SmartDashboard.putNumber("Left Speed", currentLeft);
        SmartDashboard.putNumber("Right Speed", currentRight);
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
