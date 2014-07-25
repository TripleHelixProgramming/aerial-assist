/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.pickup.PickUpDeploy;
import edu.wpi.first.wpilibj.templates.util.PropertyReader;
import edu.wpi.first.wpilibj.templates.util.RollingAverager;

/**
 *
 * @author robotics
 */
public class PickUp extends Subsystem {
    
    public static final boolean DEPLOY = true;
    public static final boolean RETRACT = false;
    public static final boolean OPEN = true;
    public static final boolean CLOSE = false;
    
    private Solenoid pickUpSolenoid1 = new Solenoid(PropertyReader.getProperty("PICKUP_SOLENOID_MODULE", 2), PropertyReader.getProperty("PICKUP_SOLENOID_CHANNEL_A", RobotMap.pickUpSolenoid1));
    private Solenoid pickUpSolenoid2 = new Solenoid(PropertyReader.getProperty("PICKUP_SOLENOID_MODULE", 2), PropertyReader.getProperty("PICKUP_SOLENOID_CHANNEL_B", RobotMap.pickUpSolenoid2));
    
    private Solenoid wingSolenoid = new Solenoid(PropertyReader.getProperty("WING_SOLENOID_MODULE", 2), PropertyReader.getProperty("WING_SOLENOID_CHANNEL", RobotMap.wingSolenoidChannel));
    
    private SpeedController upperPickUp = new Talon(PropertyReader.getProperty("PICKUP_TALON_CHANNEL", RobotMap.upperPickUpRoller));
    
    private double powerLevel;
    
    private RollingAverager pickUpAverager = new RollingAverager(10, 0);
    
    public void initDefaultCommand() {
        setDefaultCommand(new PickUpDeploy(RETRACT, 0, CLOSE));
    }
    
    public void setRollerSpeed(double power) {
        powerLevel = power;
        pickUpAverager.addValue(powerLevel);
        upperPickUp.set(pickUpAverager.getAverage());
    }
    
    public void deployArm(boolean deploy) {
       pickUpSolenoid1.set(!deploy);
       pickUpSolenoid2.set(deploy);
    }
    public void deployCatch(boolean deploy) {
        wingSolenoid.set(deploy);
    }
}
