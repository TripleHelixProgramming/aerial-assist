/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.shooter.SetShooterPosition;

/**
 *
 * @author robotics
 */
public class Shooter extends Subsystem {
    public String currentHot;
    
    public Solenoid blockerPole1 = new Solenoid(2, 6);
    public Solenoid blockerPole2 = new Solenoid(2, 7);
    
    public static final boolean FIRE = true;
    public static final boolean PRIME = false;
    
    private Solenoid shooterSolenoid = new Solenoid(1, RobotMap.shooterSolenoid1Port);
    private Solenoid shooterSolenoid2 = new Solenoid(1, RobotMap.shooterSolenoid2Port);

    public void initDefaultCommand() {
        setDefaultCommand(new SetShooterPosition(Shooter.PRIME));
    }
    
    public void primeShooter(boolean prime){
        //System.out.println("IN PRIME SHOOTER " + prime);
        shooterSolenoid.set(prime);
        shooterSolenoid2.set(prime);
    }
}
