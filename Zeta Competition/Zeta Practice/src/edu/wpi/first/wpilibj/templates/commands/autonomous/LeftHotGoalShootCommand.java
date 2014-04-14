/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.templates.commands.drivetrain.DriveStraightCommand;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.ShiftCommand;
import edu.wpi.first.wpilibj.templates.commands.lights.TurnLightsOnCommand;
import edu.wpi.first.wpilibj.templates.commands.shooter.ShootSeries;
import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.templates.util.HotGoalFinder;
import edu.wpi.first.wpilibj.templates.util.SelectableCommand;

/**
 *
 * @author robotics
 */
public class LeftHotGoalShootCommand extends SelectableCommand {
    
    public LeftHotGoalShootCommand() {
        HotGoalProcessing hotGoalProc = new HotGoalProcessing();
        addParallel(new TurnLightsOnCommand());
        addParallel(hotGoalProc);
        addSequential(new WaitForHotGoal(HotGoalFinder.LEFT, hotGoalProc), 5);
        addSequential(new ShiftCommand(Drivetrain.LOW_GEAR));
        addSequential(new DriveStraightCommand(0.85, 3500));
        addSequential(new ShootSeries());
    }

    public String getCommandName() {
        return "Left Hot Goal Shoot One Ball";
    }
}
