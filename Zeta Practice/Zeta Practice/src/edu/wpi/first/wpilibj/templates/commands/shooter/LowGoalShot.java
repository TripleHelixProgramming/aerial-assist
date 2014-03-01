/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.shooter;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;

/**
 *
 * @author robotics
 */
public class LowGoalShot extends CommandBase {
        private int iterations;
        private int ITERATIONS;
    public LowGoalShot() {
        requires(shooter);
    }

    protected void initialize() {
        iterations = 0;
    }

    protected void execute() {
        iterations++;
        ITERATIONS++;
        if(iterations < 14){
            shooter.primeShooter(Shooter.FIRE);
        }else if(iterations < 15){
            shooter.primeShooter(Shooter.PRIME);
        }else{
            iterations = 0;
        }
    }

    protected boolean isFinished() {
        if(ITERATIONS >= 15){
            return true;
        }
        return false;
    }

    protected void end() {
        shooter.primeShooter(Shooter.PRIME);
    }

    protected void interrupted() {
    }
}
