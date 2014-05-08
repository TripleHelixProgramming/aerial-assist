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
//import edu.wpi.first.wpilibj.templates.Robot;


/**
 *
 * @author robotics
 */
public class GoalieBotDrive extends PIDCommand {
    
    private double power;
    private int distance; //this is in ticks
    private final PIDController distanceController;
    private final GenericPIDOutput distanceOutput;
    private boolean direction;
    
    public GoalieBotDrive(boolean direction, double power, int inches) {
        super(.0016, 0.00009, 0); //This is for turning
        this.power = power;
//        this.distance = convertToTicks(inches, 1.47058824, 4);
        this.distance = -inches;
        //TRUE = LEFT
        //FALSE = RIGHT
        this.direction = direction;
        requires(CommandBase.drivetrain);
        this.setRunWhenDisabled(false);
        distanceOutput = new GenericPIDOutput();
        distanceController = new PIDController(0.001, 0.0003, 0.008414159265358979323846838, CommandBase.drivetrain.rightEncoder, distanceOutput); //This is for distance
    }
    
    protected double returnPIDInput() {
        double left = CommandBase.drivetrain.leftEncoder.getDistance();
        double right = CommandBase.drivetrain.rightEncoder.getDistance();
        try {
            SmartDashboard.putNumber("Difference", left - right);
        } catch (Exception e) {}
        return left - right;
    }

    protected void usePIDOutput(double d) {
        System.out.println("Error: " + d);
        double calculatedPower = -distanceOutput.getPower();
        if (Math.abs(calculatedPower) > Math.abs(power)) {
            calculatedPower = power;
        }
        try {
            SmartDashboard.putNumber("Calculated Power", calculatedPower);
        } catch (Exception e) {}
        try {
            SmartDashboard.putNumber("Error", distanceController.getError());
        } catch (Exception e) {}
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
        SmartDashboard.putNumber("Set Point", distance);
    }

    protected void execute() {
        System.out.println("Right Encoder: " + CommandBase.drivetrain.leftEncoder.getDistance());
        System.out.println("Left Encoder: " + CommandBase.drivetrain.rightEncoder.getDistance());
    }

    protected boolean isFinished() {
        try {
            SmartDashboard.putNumber("Distance", CommandBase.drivetrain.rightEncoder.getDistance());
//            System.out.println("Left Distance: " + CommandBase.drivetrain.leftEncoder.getDistance());
//            System.out.println("Right Distance: " + CommandBase.drivetrain.rightEncoder.getDistance());
        } catch (Exception e) {}
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
