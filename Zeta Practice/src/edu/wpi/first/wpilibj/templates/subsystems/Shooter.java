/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author robotics
 */
public class Shooter extends Subsystem {
    
    public static final boolean FIRE = true;
    public static final boolean PRIME = false;
    
    private Solenoid shooterSolenoid = new Solenoid(RobotMap.shooterSolenoid1Port);
    private Solenoid shooterSolenoid2 = new Solenoid(RobotMap.shooterSolenoid2Port);

    public void initDefaultCommand() {
        
    }
    
    public void primeShooter(boolean prime){
        System.out.println("IN PRIME SHOOTER " + prime);
        shooterSolenoid.set(prime);
        shooterSolenoid2.set(prime);
    }
}
