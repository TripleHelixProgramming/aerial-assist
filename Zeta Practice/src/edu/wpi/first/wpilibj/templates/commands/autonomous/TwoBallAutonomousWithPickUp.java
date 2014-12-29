/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.DriveStraightCommand;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.ShiftCommand;
import edu.wpi.first.wpilibj.templates.commands.lights.TurnLightsOnCommand;
import edu.wpi.first.wpilibj.templates.commands.pickup.PickUpDeploy;
import edu.wpi.first.wpilibj.templates.commands.shooter.ShootSeries;
import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.templates.subsystems.PickUp;
import edu.wpi.first.wpilibj.templates.subsystems.Shifters;
import edu.wpi.first.wpilibj.templates.util.SelectableCommand;

/**
 *
 * @author robotics
 */
public class TwoBallAutonomousWithPickUp extends SelectableCommand {
    
    public TwoBallAutonomousWithPickUp() {
       //1000 ticks is approximately 28 inches
       addParallel(new TurnLightsOnCommand());
       addSequential(new ShiftCommand(Shifters.HIGH_GEAR));
       addSequential(new DriveStraightCommand(0.85, 3500));
       addSequential(new ShiftCommand(Shifters.LOW_GEAR));
       addSequential(new ShootSeries());
       addSequential(new ShiftCommand(Shifters.HIGH_GEAR));
       addSequential(new WaitCommand(0.1));
       addParallel(new PickUpDeploy(PickUp.DEPLOY, RobotMap.intakeRollerSpeed, PickUp.CLOSE));
       addSequential(new DriveStraightCommand(-0.90, -3850));
       addSequential(new ShiftCommand(Shifters.LOW_GEAR));
       addSequential(new WaitCommand(0.3));
       addSequential(new ShiftCommand(Shifters.HIGH_GEAR));
       addSequential(new WaitCommand(0.1));
       addParallel(new PickUpDeploy(PickUp.RETRACT, 0, PickUp.CLOSE));
       addSequential(new DriveStraightCommand(0.90, 3850));
       addSequential(new ShiftCommand(Shifters.LOW_GEAR));
       addSequential(new ShootSeries());
       addSequential(new ShiftCommand(Shifters.HIGH_GEAR));
       addSequential(new WaitCommand(0.1));
       addParallel(new PickUpDeploy(PickUp.DEPLOY, RobotMap.intakeRollerSpeed, PickUp.CLOSE));
       addSequential(new DriveStraightCommand(-0.90, -5550));
       addSequential(new ShiftCommand(Shifters.LOW_GEAR));
       addSequential(new WaitCommand(0.3));
       addSequential(new ShiftCommand(Shifters.HIGH_GEAR));
       addSequential(new WaitCommand(0.1));
       addSequential(new DriveStraightCommand(0.90, 5550));
       addSequential(new ShootSeries());
    }

    public String getCommandName() {
        return "Shoot two Ball, Pick Up One";
    }
}
