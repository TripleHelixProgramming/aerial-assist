/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.RobotMap;
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
public class TwoBallAutonomous extends SelectableCommand {
    public TwoBallAutonomous() {
       addSequential(new ShiftCommand(Drivetrain.LOW_GEAR));
       addSequential(new DriveStraightCommand(0.85, 1500));
       addSequential(new ShootSeries());
       addParallel(new PickUpDeploy(PickUp.DEPLOY, RobotMap.intakeRollerSpeed, PickUp.CLOSE));
       addSequential(new DriveStraightCommand(-0.85, -1750));
       addSequential(new WaitCommand(1.5));
       addSequential(new DriveStraightCommand(0.85, 2000));
       addParallel(new PickUpDeploy(PickUp.RETRACT, 0, PickUp.CLOSE));
       addSequential(new WaitCommand(1));
       addParallel(new PickUpDeploy(PickUp.DEPLOY, 0, PickUp.CLOSE));
       addSequential(new WaitCommand(1));
       addSequential(new ShootSeries());
    }

    public String getCommandName() {
        return "Shoot two Ball";
    }
}
