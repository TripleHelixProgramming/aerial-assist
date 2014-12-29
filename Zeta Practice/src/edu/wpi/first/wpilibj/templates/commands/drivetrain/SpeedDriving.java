package edu.wpi.first.wpilibj.templates.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.util.DrivePIDOutput;
import edu.wpi.first.wpilibj.templates.util.RollingAverager;

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
    
    RollingAverager leftAverager = new RollingAverager(10);
    RollingAverager rightAverager = new RollingAverager(10);
    
    public SpeedDriving() {
        requires(drivetrain);
        leftController = new PIDController(0.001, 0.0001, 0.0000005, drivetrain.leftEncoder, leftOutput);
        rightController = new PIDController(0.001, 0.0001, 0.0000005, drivetrain.rightEncoder, rightOutput);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        leftController.enable();
        rightController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//        double left = -oi.getThrottle() + (oi.getTurn());
//        double right = -oi.getThrottle() - (oi.getTurn());
        double left = 10;
        double right = 10;
        
        double leftSetpoint = left;
        double rightSetpoint = right;
        leftController.setSetpoint(leftSetpoint);
        rightController.setSetpoint(rightSetpoint);
        
        SmartDashboard.putNumber("Left Setpoint", leftSetpoint);
        SmartDashboard.putNumber("Right Setpoint", rightSetpoint);
        
        SmartDashboard.putNumber("Left Error", leftOutput.getError());
        SmartDashboard.putNumber("Right Error", rightOutput.getError());
        
        SmartDashboard.putNumber("Left Output", -(left / 15) - leftOutput.getError());
        SmartDashboard.putNumber("Right Output", -(right / 15) - rightOutput.getError());
        drivetrain.tankDrive(-(left / 15)  - leftOutput.getError(), -(right / 15) - rightOutput.getError());

        //rightController.setSetpoint(4000);
        this.leftAverager.addValue(drivetrain.leftEncoder.getRate());
        this.rightAverager.addValue(drivetrain.rightEncoder.getRate());
        SmartDashboard.putNumber("Average Rate of Right Encoder:", + this.rightAverager.getAverage());
        SmartDashboard.putNumber("Average Rate of Left Encoder:", + this.leftAverager.getAverage());
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
