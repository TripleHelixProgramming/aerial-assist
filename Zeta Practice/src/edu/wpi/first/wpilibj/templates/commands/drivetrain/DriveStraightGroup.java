/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain;

/**
 *
 * @author robotics
 */
public class DriveStraightGroup extends CommandGroup {
    
    public DriveStraightGroup(double power, int distance) {
        
        addSequential(new ShiftCommand(Drivetrain.LOW_GEAR));
        addSequential(new DriveStraightCommand(power, distance));
    }
}
