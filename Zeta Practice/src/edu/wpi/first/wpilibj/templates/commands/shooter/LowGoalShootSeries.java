/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.commands.pickup.PickUpDeploy;
import edu.wpi.first.wpilibj.templates.commands.pickup.ResetArmCommand;
import edu.wpi.first.wpilibj.templates.subsystems.PickUp;

/**
 *
 * @author robotics
 */
public class LowGoalShootSeries extends CommandGroup {
    
    public LowGoalShootSeries() {
        addParallel(new PickUpDeploy(PickUp.DEPLOY, 0, PickUp.CLOSE));
        addSequential(new WaitCommand(0.8));
        addSequential(new LowGoalShot(6, 1, 3));
        addParallel(new ResetArmCommand());
    }
}
