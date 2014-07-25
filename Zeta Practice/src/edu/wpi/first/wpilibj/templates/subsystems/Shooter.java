/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.shooter.SetShooterPosition;
import edu.wpi.first.wpilibj.templates.util.PropertyReader;

/**
 *
 * @author robotics
 */
public class Shooter extends Subsystem {
    public String currentHot;
    
    public Solenoid blockerPole1 = new Solenoid(PropertyReader.getProperty("BLOCKER_SOLENOID_MODULE", 1), PropertyReader.getProperty("BLOCKER_POLE_CHANNEL_A", RobotMap.blockerPolePort1));
    public Solenoid blockerPole2 = new Solenoid(PropertyReader.getProperty("BLOCKER_SOLENOID_MODULE", 1), PropertyReader.getProperty("BLOCKER_POLE_CHANNEL_B", RobotMap.blockerPolePort2));
    
    public static final boolean FIRE = true;
    public static final boolean PRIME = false;
    
    private Solenoid shooterSolenoid1 = new Solenoid(PropertyReader.getProperty("SHOOTER_SOLENOID_MODULE", 1), PropertyReader.getProperty("SHOOTER_SOLENOID_CHANNEL_A", RobotMap.shooterSolenoid1Port));
    private Solenoid shooterSolenoid2 = new Solenoid(PropertyReader.getProperty("SHOOTER_SOLENOID_MODULE", 1), PropertyReader.getProperty("SHOOTER_SOLENOID_CHANNEL_B", RobotMap.shooterSolenoid2Port));

    public void initDefaultCommand() {
        setDefaultCommand(new SetShooterPosition(Shooter.PRIME));
    }
    
    public void primeShooter(boolean prime) {
        //System.out.println("IN PRIME SHOOTER " + prime);
        shooterSolenoid1.set(prime);
        shooterSolenoid2.set(prime);
    }
}
