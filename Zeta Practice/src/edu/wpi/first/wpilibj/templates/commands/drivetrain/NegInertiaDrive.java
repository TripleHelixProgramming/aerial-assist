/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.drivetrain;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.util.NegInertiaCalc;

/**
 *
 * @author robotics
 */
public class NegInertiaDrive extends CommandBase {
    
    private NegInertiaCalc nic = new NegInertiaCalc(0);
    
    public NegInertiaDrive() {
        requires(drivetrain);
    }

    protected void initialize() { }

    protected void execute() {       
        drivetrain.arcadeDrive(oi.getThrottle(), nic.calculate(oi.getTurn()));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() { }

    protected void interrupted() { }
}
