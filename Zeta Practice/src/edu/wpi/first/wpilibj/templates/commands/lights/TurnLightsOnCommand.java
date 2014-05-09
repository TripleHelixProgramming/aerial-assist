/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.lights;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author robotics
 */
public class TurnLightsOnCommand extends CommandBase {
    
    public TurnLightsOnCommand() {
        requires(lights);
    }

    protected void initialize() { }

    protected void execute() {
        lights.turnOn(true);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() { }

    protected void interrupted() { }
}
