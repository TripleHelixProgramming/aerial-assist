/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.pickup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.shooter.LowGoalShot;
import edu.wpi.first.wpilibj.templates.subsystems.PickUp;

/**
 *
 * @author robotics
 */
public class PassCommand extends CommandGroup {
    
    public PassCommand() {
        addParallel(new PickUpDeploy(PickUp.RETRACT, RobotMap.passRollerSpeed, PickUp.CLOSE));
        addSequential(new LowGoalShot(2, 1, 5));
        addSequential(new ResetArmCommand());
    }
}
