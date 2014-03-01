/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.commands.pickup.PickUpDeploy;
import edu.wpi.first.wpilibj.templates.subsystems.PickUp;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;


public class ShootSeries extends CommandGroup {
    
    public ShootSeries() {
        addParallel(new PickUpDeploy(PickUp.DEPLOY, 0.3));
        addSequential(new WaitCommand(0.5));
        addSequential(new SetShooterPosition(Shooter.FIRE));
        addSequential(new WaitCommand(1));
        addSequential(new SetShooterPosition(Shooter.PRIME));
        addSequential(new WaitCommand(0.5));
        addParallel(new PickUpDeploy(PickUp.DEPLOY, 0));
    }
}
