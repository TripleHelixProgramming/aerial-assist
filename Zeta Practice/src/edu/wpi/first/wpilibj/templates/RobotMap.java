package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
    //Constants
    public static double intakeRollerSpeed = 0.66;
    public static double turnScaling = 0.72;
    public static double passRollerSpeed = -.75;
    
    //Joystick Buttons: Operator Control
    public static int shootButton = 1;
    public static int lowGoalShotButton = 2;
    public static int pickUpDeployButton = 3;
    public static int rollerPowerButton = 4;
    public static int releaseBallButton = 5;
    public static int catchButton = 6;
    public static int autoSelectorButton1 = 7;
    public static int autoSelectorButton2 = 8;
    public static int autoSelectorButton3 = 9;
    
    //Joystick Buttons: Gamepad
    public static int kissPassButton = 2;
    public static int shiftDownButton = 5;
    public static int shiftUpButton = 6;
 
    //Motors
    public static int frontLeftTalonChannel = 1;
    public static int frontRightTalonChannel = 2;
    public static int backLeftTalonChannel = 3;
    public static int backRightTalonChannel = 4;
    public static int upperPickUpRoller = 5;
    public static int bottomPickUpRoller = 9;
    
    //Solenoids Module 1
    public static int shiftSolenoid1Channel = 1;
    public static int shiftSolenoid2Channel = 2 ;
    public static int pickUpSolenoid1 = 3;
    public static int pickUpSolenoid2 = 4;
    public static int shooterSolenoid1Port = 5; 
    public static int shooterSolenoid2Port = 6;
    public static int wingSolenoidChannel = 7;
    
    //Solenoid Module 2
    public static int blockerPolePort1 = 1;
    public static int blockerPolePort2 = 2;
    
    //Digital IO Ports
    public static int leftEncoderChannelA = 2;
    public static int leftEncoderChannelB = 3;
    public static int rightEncoderChannelA = 4;
    public static int rightEncoderChannelB = 5;
    
    //Relays
    public static int lightChannel = 2;
    
    //Analog
    public static int pressureChannel = 1;

}
