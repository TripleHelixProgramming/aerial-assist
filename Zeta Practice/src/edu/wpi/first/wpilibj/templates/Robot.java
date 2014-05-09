/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.templates.util.CheesyVisionServer;
import edu.wpi.first.wpilibj.templates.util.PropertyReader;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    
    public void robotInit() {
        PropertyReader.loadProperties();
        CheesyVisionServer server = CheesyVisionServer.getInstance();
        server.start();
        CommandBase.init();
    }

    public void autonomousInit() {
        autonomousCommand = CommandBase.oi.getSelectedAutoCommand();
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        CommandBase.drivetrain.shift(Drivetrain.HIGH_GEAR);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    /**
     * This function is called periodically during disabled mode
     */
    public void disabledPeriodic(){
        NetworkTable table = NetworkTable.getTable("controls");
        table.putString("Selected Autonomous", CommandBase.oi.getSelectedAutoCommand().getCommandName());
    }
}
