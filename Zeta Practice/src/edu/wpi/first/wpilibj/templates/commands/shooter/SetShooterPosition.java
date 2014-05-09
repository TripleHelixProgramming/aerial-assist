/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.shooter;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author robotics
 */
public class SetShooterPosition extends CommandBase {
    
    public boolean shooterPosition;
    
    public SetShooterPosition(boolean shooterPosition) {
        requires(shooter);
        this.shooterPosition = shooterPosition;
    }

    protected void initialize() { }
    
    protected void execute() {
        shooter.primeShooter(shooterPosition);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() { }

    protected void interrupted() { }
}
