/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.commands.pickup.PickUpDeploy;
import edu.wpi.first.wpilibj.templates.subsystems.PickUp;

/**
 *
 * @author robotics
 */
public class LowGoalShootSeries extends CommandGroup {
    
    public LowGoalShootSeries() {
        
        addSequential(new PickUpDeploy(PickUp.DEPLOY, 0.4));
        addSequential(new WaitCommand(0.8));
        addSequential(new LowGoalShot());
        addSequential(new PickUpDeploy(PickUp.DEPLOY, 0));
        
    }
}
