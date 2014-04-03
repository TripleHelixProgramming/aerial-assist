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
import edu.wpi.first.wpilibj.templates.util.HotGoalFinder;
import edu.wpi.first.wpilibj.templates.util.SelectableCommand;

/**
 *
 * @author robotics
 */
public class TwoBallAutonomousHotGoal extends SelectableCommand {
    
    private String startingSide;
    
    public TwoBallAutonomousHotGoal(String startingSide) {
        this.startingSide = startingSide;
        HotGoalProcessing hotGoalProc = new HotGoalProcessing();
                //1000 ticks is approximately 28 inches

       addParallel(new TurnLightsOnCommand());
       addSequential(new ShiftCommand(Drivetrain.LOW_GEAR));
       addSequential(new DriveStraightCommand(0.95, /*3800*/ 0));
       addSequential(new ShootSeries());
       addParallel(new PickUpDeploy(PickUp.DEPLOY, RobotMap.intakeRollerSpeed, PickUp.CLOSE));
       addSequential(new DriveStraightCommand(-0.95, /*-4250*/ -0));
       addSequential(hotGoalProc, 1);
       addSequential(new WaitCommand(1));
       if (startingSide.equals(HotGoalFinder.LEFT)) {
            addSequential(new TurnToHotGoal(hotGoalProc, HotGoalFinder.RIGHT));
       } else {
            addSequential(new TurnToHotGoal(hotGoalProc, HotGoalFinder.LEFT));
       }
       addParallel(new PickUpDeploy(PickUp.RETRACT, 0, PickUp.CLOSE));
       addSequential(new DriveStraightCommand(0.95, /*4250*/ 0));
       addSequential(new ShootSeries());
    }

    public String getCommandName() {
        return "Hot two ball " + startingSide;
    }
}
