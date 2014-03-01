/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.DriveStraightCommand;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.ShiftCommand;
import edu.wpi.first.wpilibj.templates.commands.shooter.SetShooterPosition;
import edu.wpi.first.wpilibj.templates.commands.shooter.ShootSeries;
import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;
import edu.wpi.first.wpilibj.templates.util.SelectableCommand;

/**
 *
 * @author robotics
 */
public class LeftHotGoalShootCommand extends SelectableCommand {
    
    public LeftHotGoalShootCommand() {
        addSequential(new ShiftCommand(Drivetrain.LOW_GEAR));
        addSequential(new DriveStraightCommand(0.80, 2500));
//        addSequential(new WaitCommand(1));
//        addSequential(new HotGoalProcessing(HotGoalProcessing.LEFT, 2));
        addSequential(new ShootSeries());
    }

    public String getCommandName() {
        return "Left Hot Goal Shoot One Ball";
    }
}
