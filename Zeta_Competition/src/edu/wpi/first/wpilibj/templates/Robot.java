/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.drivetrain.BlockerDrive;
import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.templates.util.CheesyVisionServer;
import edu.wpi.first.wpilibj.templates.util.HotGoalFinder;

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
        CheesyVisionServer server = CheesyVisionServer.getInstance();
        server.start();
        CommandBase.init();
    }

    public void autonomousInit() {
//        try {
//            ColorImage image = OI.camera.getImage();
//            image.write("AutoImage.png");
//        } catch (AxisCameraException ex) {
//            ex.printStackTrace();
//        } catch (NIVisionException ex) {
//            ex.printStackTrace();
//        }
        
//        autonomousCommand = new TwoBallHotGoaTurnAutonomous();
        autonomousCommand = CommandBase.oi.getSelectedAutoCommand(); //new BlockerDrive(0.854);//CommandBase.oi.getSelectedAutoCommand();
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        CommandBase.drivetrain.shift(Drivetrain.HIGH_GEAR);
        CommandBase.shooter.blockerPole1.set(true);
        CommandBase.shooter.blockerPole2.set(false);
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
    public void disabledPeriodic(){
        NetworkTable table = NetworkTable.getTable("controls");
        table.putString("Selected Autonomous", CommandBase.oi.getSelectedAutoCommand().getCommandName());
    }
}
