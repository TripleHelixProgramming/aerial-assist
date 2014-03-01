/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.pickup;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author robotics
 */
public class RollerPowerDashboardSet extends CommandBase {
    
    public RollerPowerDashboardSet() {
    }

    protected void initialize() {
        
    }

    protected void execute() {
        NetworkTable table = NetworkTable.getTable("controls");
        if(oi.isRollerOn()){
            table.putBoolean("Roller Power", true);
        }else{
            table.putBoolean("Roller Power", false);
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
