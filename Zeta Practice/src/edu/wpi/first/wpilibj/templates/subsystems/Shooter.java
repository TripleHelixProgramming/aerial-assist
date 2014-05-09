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
    public String currentHot;
    
    public Solenoid blockerPole1 = new Solenoid(2, RobotMap.blockerPolePort1);
    public Solenoid blockerPole2 = new Solenoid(2, RobotMap.blockerPolePort2);
    
    public static final boolean FIRE = true;
    public static final boolean PRIME = false;
    
    public static final boolean UP = false;
    public static final boolean DOWN = true;
    
    private Solenoid shooterSolenoid = new Solenoid(RobotMap.shooterSolenoid1Port);
    private Solenoid shooterSolenoid2 = new Solenoid(RobotMap.shooterSolenoid2Port);

    public void initDefaultCommand() {
        
    }
    
    public void primeShooter(boolean prime) {
        //System.out.println("IN PRIME SHOOTER " + prime);
        shooterSolenoid.set(prime);
        shooterSolenoid2.set(prime);
    }
    public void setBlocker(boolean set) {
        blockerPole1.set(set); 
        blockerPole2.set(!set);
    }
}
