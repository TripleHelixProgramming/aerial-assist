/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.JoystickDrive;
import edu.wpi.first.wpilibj.templates.util.RollingAverager;

/**
 *
 * @author robotics
 */
public class Drivetrain extends Subsystem {
    public static final boolean inverted = false;
    public static final boolean normal = true;

    public static final boolean HIGH_GEAR = false;
    public static final boolean LOW_GEAR = true;
    
    private SpeedController frontLeft = new Talon(RobotMap.frontLeftTalonChannel);
    private SpeedController frontRight = new Talon(RobotMap.frontRightTalonChannel);
    private SpeedController backLeft = new Talon(RobotMap.backLeftTalonChannel);
    private SpeedController backRight = new Talon(RobotMap.backRightTalonChannel);
    
    private Solenoid shiftSolenoid1 = new Solenoid(2, RobotMap.shiftSolenoid1Channel);
    private Solenoid shiftSolenoid2 = new Solenoid(2, RobotMap.shiftSolenoid2Channel);
    
    private RobotDrive robotDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
    
    public Encoder leftEncoder = new Encoder(RobotMap.leftEncoderChannelA, RobotMap.leftEncoderChannelB, normal);
    public Encoder rightEncoder = new Encoder(RobotMap.rightEncoderChannelA, RobotMap.rightEncoderChannelB, normal);
    
    private RollingAverager throttleAverager = new RollingAverager(9, 0);
    private RollingAverager turningAverager = new RollingAverager(9, 0);
    
    public Drivetrain() {
        robotDrive.setSafetyEnabled(false);
        leftEncoder.setDistancePerPulse(1.0);
        leftEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        leftEncoder.setSamplesToAverage(10);
        leftEncoder.start();
        rightEncoder.setDistancePerPulse(1.0);
        rightEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        rightEncoder.setSamplesToAverage(10);
        rightEncoder.start();
        
    }
     

    public void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }
    public void arcadeDrive(double throttle, double turn){
        throttleAverager.addValue(throttle);
        turningAverager.addValue(turn);
        robotDrive.arcadeDrive(throttleAverager.getAverage(), turningAverager.getAverage());
        System.out.println("left encoder: " + leftEncoder.getDistance());
        System.out.println("right encoder: " + rightEncoder.getDistance());
    }
    public void tankDrive(double leftPower, double rightPower){
        robotDrive.tankDrive(leftPower, rightPower);
    }
    public void shift(boolean shiftUp){
        shiftSolenoid1.set(!shiftUp);
        shiftSolenoid2.set(shiftUp);
    }
}
