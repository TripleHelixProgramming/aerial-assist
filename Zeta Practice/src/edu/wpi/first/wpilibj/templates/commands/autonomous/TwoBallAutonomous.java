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
import edu.wpi.first.wpilibj.templates.util.SelectableCommand;

/**
 *
 * @author robotics
 */
public class TwoBallAutonomous extends SelectableCommand {
    
    public TwoBallAutonomous() {
       //1000 ticks is approximately 28 inches
       addSequential(new ShiftCommand(Drivetrain.LOW_GEAR));
       addSequential(new DriveStraightCommand(0.95, 3800));
       addSequential(new ShootSeries());
       addParallel(new PickUpDeploy(PickUp.DEPLOY, RobotMap.intakeRollerSpeed));
       addSequential(new DriveStraightCommand(-0.95, -4250));
       addSequential(new WaitCommand(1.5));
       addParallel(new PickUpDeploy(PickUp.RETRACT, 0));
       addSequential(new DriveStraightCommand(0.95, 4250));
       addSequential(new WaitCommand(0.5));
       addSequential(new ShootSeries());
    }

    public String getCommandName() {
        return "Shoot two Ball";
    }
}
