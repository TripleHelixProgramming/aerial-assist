/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.DriveStraightCommand;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.ShiftCommand;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.TurnCommand;
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
public class TwoBallAutonomousHotGoal extends SelectableCommand {
    
    public TwoBallAutonomousHotGoal() {
        WaitForAnyGoal firstTurn = new WaitForAnyGoal();
        WaitForAnyGoal secondTurn = new WaitForAnyGoal();
        
        addSequential(new ShiftCommand(Shifters.LOW_GEAR));
        addSequential(new DriveStraightCommand(0.95, 3800));//3800
        addSequential(new WaitCommand(0.75));
        addSequential(firstTurn, 0.5);
        addSequential(new TurnCommand(firstTurn, 0.65, 300));
        addSequential(new ShootSeries());
        addSequential(new TurnCommand(firstTurn, -0.65, -300));
        addParallel(new PickUpDeploy(PickUp.DEPLOY, RobotMap.intakeRollerSpeed));
        addSequential(new DriveStraightCommand(-0.95, -4250));//-4250
        addSequential(new WaitCommand(1));
        addParallel(new PickUpDeploy(PickUp.RETRACT, 0, PickUp.CLOSE));
        addSequential(new DriveStraightCommand(0.95, 4250));//4250
        addSequential(new WaitCommand(0.75));
        addSequential(secondTurn, 0.5);
        addSequential(new TurnCommand(secondTurn, 0.65, 300));
        addSequential(new ShootSeries()); 
    }

    public String getCommandName() {
        return "Hot two ball ";
    }
}
