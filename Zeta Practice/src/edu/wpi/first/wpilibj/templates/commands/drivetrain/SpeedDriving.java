package edu.wpi.first.wpilibj.templates.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.util.DrivePIDOutput;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author robotics
 */
public class SpeedDriving extends CommandBase {
    
    private PIDController leftController;
    private PIDController rightController;
    private final DrivePIDOutput leftOutput = new DrivePIDOutput();
    private final DrivePIDOutput rightOutput = new DrivePIDOutput();
    
    public SpeedDriving() {
        requires(drivetrain);
        leftController = new PIDController(0.00001, 0, 0.000000001, drivetrain.leftEncoder, leftOutput);
        rightController = new PIDController(0.00001, 0, 0.000000001, drivetrain.rightEncoder, rightOutput);
        leftController.setOutputRange(-0.5, 0.5);
        rightController.setOutputRange(-0.5, 0.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        leftController.enable();
        rightController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double left = -oi.getThrottle() + (oi.getTurn());
        double right = -oi.getThrottle() - (oi.getTurn());
        
        if (left > 1) {
            left = 1;
        } else if (left < -1) {
            left = -1;
        }
        
        if (right > 1) {
            right = 1;
        } else if (right < -1) {
            right = -1;
        }
        
        double leftSetpoint = left * 7200;
        double rightSetpoint = right * 7200;
        leftController.setSetpoint(leftSetpoint);
        rightController.setSetpoint(rightSetpoint);
        
        SmartDashboard.putNumber("Left Setpoint", leftSetpoint);
        SmartDashboard.putNumber("Right Setpoint", rightSetpoint);
        
        SmartDashboard.putNumber("Left Output", -left  - leftOutput.getError());
        SmartDashboard.putNumber("Right Output", -right - rightOutput.getError());
        drivetrain.tankDrive(-left  - leftOutput.getError(), -right - rightOutput.getError());

        //rightController.setSetpoint(4000);
        SmartDashboard.putNumber("Average Rate of Right Encoder:", + drivetrain.rightEncoder.getRate());
        SmartDashboard.putNumber("Average Rate of Left Encoder:", + drivetrain.leftEncoder.getRate());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("DISABLED");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("DISABLED");
    }
}
