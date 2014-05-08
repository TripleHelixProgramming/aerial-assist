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
public class RightHotGoalShootCommand extends SelectableCommand {
    
    public RightHotGoalShootCommand() { 
        HotGoalProcessing hotGoalProc = new HotGoalProcessing();
        addParallel(new TurnLightsOnCommand());
        addParallel(new HotGoalProcessing());
        addSequential(new WaitForHotGoal(HotGoalFinder.RIGHT, hotGoalProc), 3);
        addSequential(new ShiftCommand(Drivetrain.LOW_GEAR));
        addSequential(new DriveStraightCommand(0.85, 3900));
        addSequential(new ShootSeries());
    }

    public String getCommandName() {
        return "Right Hot Goal Shoot One Ball";
    }
}
