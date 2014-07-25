
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.networktables2.util.List;
import edu.wpi.first.wpilibj.templates.commands.autonomous.BlockerDrive;
import edu.wpi.first.wpilibj.templates.commands.autonomous.HotGoalCheesy;
import edu.wpi.first.wpilibj.templates.commands.autonomous.TwoBallAutonomous;
import edu.wpi.first.wpilibj.templates.commands.autonomous.TwoBallAutonomousHotGoal;
import edu.wpi.first.wpilibj.templates.commands.autonomous.TwoBallAutonomousWithPickUp;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.JoystickDrive;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.ShiftCommand;
import edu.wpi.first.wpilibj.templates.commands.pickup.PassCommand;
import edu.wpi.first.wpilibj.templates.commands.pickup.PickUpDeploy;
import edu.wpi.first.wpilibj.templates.commands.pickup.RollerPowerDashboardSet;
import edu.wpi.first.wpilibj.templates.commands.shooter.LowGoalShootSeries;
import edu.wpi.first.wpilibj.templates.commands.shooter.ShootSeries;
import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.templates.subsystems.PickUp;
import edu.wpi.first.wpilibj.templates.util.AutonomousSelector;
import edu.wpi.first.wpilibj.templates.util.SelectableCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //Joysticks
    private boolean practice = false;
    private Joystick gamepad = new Joystick(1);
    private Joystick operatorControl = new Joystick(2);
    
    //Joystick Buttons: Operator Control
    private JoystickButton pickUpDeployButton;
    private JoystickButton passButton;
    private JoystickButton rollerPowerButton;
    private JoystickButton shootButton;
    private JoystickButton lowGoalShotButton;
    private JoystickButton catchButton;
    
    //Joystick Buttons: Gamepad
    private JoystickButton shiftUpButton; 
    private JoystickButton shiftDownButton; 
    private JoystickButton kissPassButton;
    
    //Other Buttons
    private JoystickButton autoSelectorButton1;
    private JoystickButton autoSelectorButton2;
    private JoystickButton autoSelectorButton3;
    
    //Other
    public static AxisCamera camera;
    private AutonomousSelector autoSelector;
    
    public OI() {     
        //Autonomous Selector
        autoSelectorButton1 = new JoystickButton(operatorControl, RobotMap.autoSelectorButton1);
        autoSelectorButton2 = new JoystickButton(operatorControl, RobotMap.autoSelectorButton2);
        autoSelectorButton3 = new JoystickButton(operatorControl, RobotMap.autoSelectorButton3);
        autoSelector = new AutonomousSelector(autoSelectorButton1, autoSelectorButton2, autoSelectorButton3);
        List autonomousCommandsList = new List();
        
        //Autonomous Selector Command
        autonomousCommandsList.add(new HotGoalCheesy());// 0
        autonomousCommandsList.add(new TwoBallAutonomous());// 1
        autonomousCommandsList.add(new TwoBallAutonomousHotGoal());// 2
        autonomousCommandsList.add(new TwoBallAutonomousWithPickUp());// 3
        autonomousCommandsList.add(new BlockerDrive(0.70));// 4
        autoSelector.setCommands(autonomousCommandsList);
        
        //Joystick Buttons: Operator Control
        rollerPowerButton = new JoystickButton(operatorControl, RobotMap.rollerPowerButton);
        new RollerPowerDashboardSet().start();
        
        pickUpDeployButton = new JoystickButton(operatorControl, RobotMap.pickUpDeployButton);
        pickUpDeployButton.whileHeld(new PickUpDeploy(PickUp.DEPLOY, RobotMap.intakeRollerSpeed));
        
        passButton = new JoystickButton (operatorControl, RobotMap.releaseBallButton);
        passButton.whenPressed(new PassCommand());
        
        shootButton = new JoystickButton(operatorControl, RobotMap.shootButton);
        shootButton.whenPressed(new ShootSeries());
        
        lowGoalShotButton = new JoystickButton(operatorControl, RobotMap.lowGoalShotButton);
        lowGoalShotButton.whenPressed(new LowGoalShootSeries());
        
        catchButton = new JoystickButton(operatorControl, RobotMap.catchButton);
        catchButton.whileHeld(new PickUpDeploy(PickUp.DEPLOY, 0, PickUp.OPEN));
        
        //Joystick Buttons: Gamepad
        shiftUpButton = new JoystickButton(gamepad, RobotMap.shiftUpButton);
        shiftUpButton.whenPressed(new ShiftCommand(Drivetrain.HIGH_GEAR));
        
        shiftDownButton = new JoystickButton(gamepad, RobotMap.shiftDownButton);
        shiftDownButton.whenPressed(new ShiftCommand(Drivetrain.LOW_GEAR));
        
        kissPassButton = new JoystickButton(gamepad, RobotMap.kissPassButton);
        kissPassButton.whileHeld(new PickUpDeploy(PickUp.RETRACT, RobotMap.intakeRollerSpeed));
    }
    
    public double getThrottle() {
        // Xbox Controller
        if(practice){
            if(gamepad.getZ() < 0){
                return getWithDeadband(-gamepad.getX());
            }
            return getWithDeadband(gamepad.getX());
        }
        // Ps4 Controller
        if(gamepad.getRawAxis(4) > 0){
            return getWithDeadband(-gamepad.getRawAxis(2));
        }
        return getWithDeadband(gamepad.getRawAxis(2));
    }
    
    public double getWithDeadband(double power) {
        if (Math.abs(power) < 0.05) {
            return 0;
        }
        return power;
    }
    
    public double getTurn() {
        if(practice){
            // Xbox Controller
            if(gamepad.getZ() > 0){
               return getWithDeadband(gamepad.getRawAxis(4) * -getTurnScaling());
            }
            return getWithDeadband(gamepad.getRawAxis(4) * getTurnScaling());
        }
        
        // Ps4 Controller
        if (gamepad.getRawAxis(5) > 0){
            return getWithDeadband(gamepad.getRawAxis(3) * -getTurnScaling());
        }
        return getWithDeadband(gamepad.getRawAxis(3) * getTurnScaling());
    }
    
    private double getTurnScaling() {
        double highSpeedTurn = 0.6;
        double lowSpeedTurn = 0.8;
        // (Low Speed turning - High Speed Turn) + High Speed turning
        return Math.abs(getThrottle()) * (highSpeedTurn - lowSpeedTurn) + lowSpeedTurn;
    }
    
    // Methods
    public boolean isRollerOn() {
        return !rollerPowerButton.get();
    }
    
    public SelectableCommand getSelectedAutoCommand(){
        return autoSelector.getSelectedCommand();
    }
}