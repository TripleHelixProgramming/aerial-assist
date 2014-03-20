/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.DriveStraightCommand;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.ShiftCommand;
import edu.wpi.first.wpilibj.templates.commands.lights.TurnLightsOnCommand;
import edu.wpi.first.wpilibj.templates.commands.shooter.ShootSeries;
import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.templates.util.SelectableCommand;

/**
 *
 * @author robotics
 */
public class RightHotGoalShootCommand extends SelectableCommand {
    
    public RightHotGoalShootCommand() { 
        addParallel(new TurnLightsOnCommand());
        addSequential(new ShiftCommand(Drivetrain.LOW_GEAR));
        addSequential(new DriveStraightCommand(0.90, 3500));
        addSequential(new HotGoalProcessing(), 2);
//        addSequential(new WaitCommand(0.7));
        addSequential(new ShootSeries());
    }

    public String getCommandName() {
        return "Right Hot Goal Shoot One Ball";
    }
}
