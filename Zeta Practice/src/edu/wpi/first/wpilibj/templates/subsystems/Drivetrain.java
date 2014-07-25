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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.JoystickDrive;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.NegInertiaDrive;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.SpeedDriving;
import edu.wpi.first.wpilibj.templates.util.PropertyReader;
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
    
    public SpeedController frontLeft = new Talon(PropertyReader.getProperty("FRONT_LEFT_TALON_CHANNEL" ,RobotMap.frontLeftTalonChannel));
    public SpeedController frontRight = new Talon(PropertyReader.getProperty("FRONT_RIGHT_TALON_CHANNEL" ,RobotMap.frontRightTalonChannel));
    public SpeedController backLeft = new Talon(PropertyReader.getProperty("BACK_LEFT_TALON_CHANNEL" ,RobotMap.backLeftTalonChannel));
    public SpeedController backRight = new Talon(PropertyReader.getProperty("BACK_RIGHT_TALON_CHANNEL" ,RobotMap.backRightTalonChannel));
    
    private Solenoid shiftSolenoid1 = new Solenoid(PropertyReader.getProperty("SHIFT_SOLENOID_MODULE", 2), PropertyReader.getProperty("SHIFT_SOLENOID_1_CHANNEL" , RobotMap.shiftSolenoid1Channel));
    private Solenoid shiftSolenoid2 = new Solenoid(PropertyReader.getProperty("SHIFT_SOLENOID_MODULE", 2), PropertyReader.getProperty("SHIFT_SOLENOID_2_CHANNEL" , RobotMap.shiftSolenoid2Channel));
    
    private RobotDrive robotDrive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
    
    public Encoder leftEncoder = new Encoder(PropertyReader.getProperty("LEFT_ENCODER_CHANNEL_A" ,RobotMap.leftEncoderChannelA), PropertyReader.getProperty("LEFT_ENCODER_CHANNEL_B" ,RobotMap.leftEncoderChannelB), normal);
    public Encoder rightEncoder = new Encoder(PropertyReader.getProperty("RIGHT_ENCODER_CHANNEL_A" ,RobotMap.rightEncoderChannelA), PropertyReader.getProperty("RIGHT_ENCODER_CHANNEL_B" ,RobotMap.rightEncoderChannelB), normal);
    
    
    private RollingAverager throttleAverager = new RollingAverager(9, 0);
    private RollingAverager turningAverager = new RollingAverager(9, 0);
    
    private RollingAverager leftAverager = new RollingAverager(9, 0);
    private RollingAverager rightAverager = new RollingAverager(9, 0);
    
    public Drivetrain() {
        robotDrive.setSafetyEnabled(false);
        leftEncoder.setDistancePerPulse(1.0);
        leftEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        leftEncoder.setSamplesToAverage(10);
        leftEncoder.setMaxPeriod(0.1);
        leftEncoder.start();
        rightEncoder.setDistancePerPulse(1.0);
        rightEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        rightEncoder.setSamplesToAverage(10);
        rightEncoder.setMaxPeriod(0.1);
        rightEncoder.start();
    }
     

    public void initDefaultCommand() {
        setDefaultCommand(new NegInertiaDrive());
//        setDefaultCommand(new JoystickDrive());
    }
    public void arcadeDrive(double throttle, double turn) {
        throttleAverager.addValue(throttle);
        turningAverager.addValue(turn);
        robotDrive.arcadeDrive(throttleAverager.getAverage(), turningAverager.getAverage());
        
        SmartDashboard.putNumber("Average Rate of Right Encoder:", + rightEncoder.getRate());
        SmartDashboard.putNumber("Average Rate of Left Encoder:", + leftEncoder.getRate());
    }
    
    public void tankDrive(double leftPower, double rightPower) {
        leftAverager.addValue(leftPower);
        rightAverager.addValue(rightPower);
        robotDrive.tankDrive(leftAverager.getAverage(), rightAverager.getAverage());
    }
    
    public void shift(boolean shiftUp) {
        shiftSolenoid1.set(!shiftUp);
        shiftSolenoid2.set(shiftUp);
    }
}
