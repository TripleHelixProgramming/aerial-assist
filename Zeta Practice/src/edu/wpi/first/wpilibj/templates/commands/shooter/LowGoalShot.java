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
        private int count;
        private int upCount;
        private int downCount;
        private int ITERATIONS;
        private int iterationsCount;
        
    public LowGoalShot(int upCount, int downCount, int iterations) {
        requires(shooter);
        this.upCount = upCount;
        this.downCount = downCount;
        this.ITERATIONS = iterations;
        
    }

    protected void initialize() {
        count = 0;
        iterationsCount = 0;
    }

    protected void execute() {
        if(count < upCount){
            shooter.primeShooter(Shooter.FIRE);
        }else if(count < upCount + downCount){
            shooter.primeShooter(Shooter.PRIME);
        }else{
            count = 0;
            iterationsCount++;
        }
        count++;
    }

    protected boolean isFinished() {
        if(iterationsCount < ITERATIONS){
            return false;
        }
        return true;
    }

    protected void end() {
        shooter.primeShooter(Shooter.PRIME);
    }

    protected void interrupted() {
    }
}
