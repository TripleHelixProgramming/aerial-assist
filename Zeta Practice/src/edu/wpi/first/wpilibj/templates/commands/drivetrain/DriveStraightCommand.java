/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.util.GenericPIDOutput;


/**
 *
 * @author robotics
 */
public class DriveStraightCommand extends PIDCommand {
    
    private double power;
    private int distance; //this is in ticks
    private final PIDController distanceController;
    private final GenericPIDOutput distanceOutput;
    
    public DriveStraightCommand(double power, int inches) {
        super(.0016, 0.00009, 0); //This is for turning
        this.power = power;
        this.distance = -inches;
        requires(CommandBase.drivetrain);
        this.setRunWhenDisabled(false);
        distanceOutput = new GenericPIDOutput();
        distanceController = new PIDController(0.001, 0.0003, 0.008414159265358979323846838, CommandBase.drivetrain.rightEncoder, distanceOutput); //This is for distance
    }
    
    protected double returnPIDInput() {
        double left = CommandBase.drivetrain.leftEncoder.getDistance();
        double right = CommandBase.drivetrain.rightEncoder.getDistance();
        return left - right;
    }

    protected void usePIDOutput(double d) {
        double calculatedPower = -distanceOutput.getPower();
        if (Math.abs(calculatedPower) > Math.abs(power)) {
            calculatedPower = power;
        }
        CommandBase.drivetrain.tankDrive(calculatedPower - d, calculatedPower);
    }

    protected void initialize() {
        System.out.println("Driving Straight");
        CommandBase.drivetrain.rightEncoder.reset();
        CommandBase.drivetrain.leftEncoder.reset();
        this.getPIDController().reset();
        this.getPIDController().enable();
        distanceController.reset();
        distanceController.setSetpoint(distance);
        distanceController.setAbsoluteTolerance(100);
        distanceController.enable();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return distanceController.onTarget();
    }  
    
    protected void end() {
        System.out.println("We there");
        this.getPIDController().disable();
        distanceController.disable();
        CommandBase.drivetrain.arcadeDrive(0, 0);
    }
    
    private int convertToTicks(int inches, double ratio, double diameter) {
        final double pi = 3.14159265358979323846838;
        int ticks = (int) Math.ceil((360 * ratio) / (diameter * pi));
        return ticks;
    }

    protected void interrupted() {
        this.getPIDController().disable();
        distanceController.disable();
    }
}
