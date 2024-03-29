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
public class FasterTwoBallAutonomous extends SelectableCommand {
    
    public FasterTwoBallAutonomous() {
        //1000 ticks is approximately 28 inches
        
       addParallel(new TurnLightsOnCommand());
       addSequential(new ShiftCommand(Drivetrain.LOW_GEAR));
       addParallel(new PickUpDeploy(PickUp.DEPLOY, 0.21, PickUp.CLOSE));
       addSequential(new WaitCommand(0.7));
       addSequential(new DriveStraightCommand(0.85, 3500));
       addSequential(new WaitCommand(0.15));
       addParallel(new PickUpDeploy(PickUp.DEPLOY, 0, PickUp.CLOSE));
       addSequential(new ShootSeries());
       addParallel(new PickUpDeploy(PickUp.DEPLOY, RobotMap.intakeRollerSpeed, PickUp.CLOSE));
       addSequential(new DriveStraightCommand(-0.75, -350));
//       addParallel(new PickUpDeploy(PickUp.DEPLOY, RobotMap.intakeRollerSpeed, PickUp.CLOSE));
       addSequential(new WaitCommand(2.5));
       addSequential(new ShootSeries());  
    }

    public String getCommandName() {
        return "Two Ball Dragger";
    }
}
