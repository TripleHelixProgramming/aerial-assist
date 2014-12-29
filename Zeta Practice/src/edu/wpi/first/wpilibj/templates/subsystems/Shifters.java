/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.ShiftCommand;
import edu.wpi.first.wpilibj.templates.util.PropertyReader;

/**
 *
 * @author robotics
 */
public class Shifters extends Subsystem {
    public static final boolean HIGH_GEAR = false;
    public static final boolean LOW_GEAR = true;
    
    private Solenoid shiftSolenoid1 = new Solenoid(PropertyReader.getProperty("SHIFT_SOLENOID_MODULE", 2), PropertyReader.getProperty("SHIFT_SOLENOID_1_CHANNEL" , RobotMap.shiftSolenoid1Channel));
    private Solenoid shiftSolenoid2 = new Solenoid(PropertyReader.getProperty("SHIFT_SOLENOID_MODULE", 2), PropertyReader.getProperty("SHIFT_SOLENOID_2_CHANNEL" , RobotMap.shiftSolenoid2Channel));

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ShiftCommand(LOW_GEAR));
    }
    
    public void shift(boolean shiftUp) {
        shiftSolenoid1.set(!shiftUp);
        shiftSolenoid2.set(shiftUp);
    }
}
