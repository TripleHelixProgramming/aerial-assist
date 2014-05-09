/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.DriveStraightCommand;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.ShiftCommand;
import edu.wpi.first.wpilibj.templates.commands.shooter.ShootSeries;
import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.templates.util.SelectableCommand;

/**
 *
 * @author robotics
 */
public class HotGoalCheesy extends SelectableCommand {
    
    public HotGoalCheesy() {
        WaitForAnyGoal waitFor = new WaitForAnyGoal();
        
        addSequential(new ShiftCommand(Drivetrain.LOW_GEAR));
        addSequential(new DriveStraightCommand(0.95, 3800));//3800
        addSequential(waitFor, 5);
        addSequential(new ShootSeries());
    }

    public String getCommandName() {
        return "Hot Goal Cheesy";
    }
}
