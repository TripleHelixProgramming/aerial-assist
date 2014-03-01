/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.DriveStraightCommand;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.ShiftCommand;
import edu.wpi.first.wpilibj.templates.commands.pickup.PickUpDeploy;
import edu.wpi.first.wpilibj.templates.commands.shooter.ShootSeries;
import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.templates.subsystems.PickUp;
import edu.wpi.first.wpilibj.templates.util.SelectableCommand;

/**
 *
 * @author robotics
 */
public class FasterTwoBallAutonomous extends SelectableCommand {
    
    public FasterTwoBallAutonomous() {
       
       addSequential(new ShiftCommand(Drivetrain.LOW_GEAR));
       addParallel(new PickUpDeploy(PickUp.DEPLOY, 0.3));
       addSequential(new DriveStraightCommand(0.85, 1500));
       addSequential(new ShootSeries());
       addSequential(new WaitCommand(1.5));
       addParallel(new PickUpDeploy(PickUp.DEPLOY, 0));
       addSequential(new WaitCommand(1));
       addSequential(new ShootSeries());
       
    }

    public String getCommandName() {
        return "Two Ball Dragger";
    }
}
