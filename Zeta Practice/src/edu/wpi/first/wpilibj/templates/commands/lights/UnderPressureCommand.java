/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.lights;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author robotics
 */
public class UnderPressureCommand extends CommandBase {
    
    public UnderPressureCommand() {
        requires(lights);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (lights.getPressure() < 80) {
            lights.turnOn(false);
        } else {
            lights.turnOn(true);
        }
        
        NetworkTable table = NetworkTable.getTable("controls");
        table.putNumber("Pressure", lights.getPressure());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
