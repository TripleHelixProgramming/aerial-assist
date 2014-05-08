/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.shooter;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author robotics
 */
public class ShooterDashboardSet extends CommandBase {
    private boolean dashboard;
    public ShooterDashboardSet(boolean dashboard) {
        this.dashboard = dashboard;
    }

    protected void initialize() {
    }

    protected void execute() {
        NetworkTable table = NetworkTable.getTable("controls");
        table.putBoolean("Shoot", dashboard);
        
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
