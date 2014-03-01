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
import edu.wpi.first.wpilibj.templates.util.RollingAverager;

/**
 *
 * @author robotics
 */
public class PickUp extends Subsystem {
    
    public static final boolean DEPLOY = true;
    public static final boolean RETRACT = false;
    
    private Solenoid solenoid1 = new Solenoid(1, RobotMap.pickUpSolenoid1);
    private Solenoid solenoid2 = new Solenoid(1, RobotMap.pickUpSolenoid2);
    
    private SpeedController upperPickUp = new Talon(RobotMap.upperPickUpRoller);
    
    private double powerLevel;
    
    private RollingAverager pickUpAverager = new RollingAverager(20, 0);
    
    public void initDefaultCommand() {
        setDefaultCommand(new PickUpDeploy(PickUp.RETRACT, 0));
    }
    
    public void setRollerSpeed(double power){
        powerLevel = power;
        pickUpAverager.addValue(powerLevel);
        upperPickUp.set(pickUpAverager.getAverage());
    }
    
    public void deployArm(boolean deploy){
       solenoid1.set(!deploy);
       solenoid2.set(deploy);
    }
    
}
