/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;
import edu.wpi.first.wpilibj.templates.util.CheesyVisionServer;
import edu.wpi.first.wpilibj.templates.util.SelectableCommand;

/**
 *
 * @author robotics
 */
public class BlockerDrive extends SelectableCommand {
    
    private CheesyVisionServer server = CheesyVisionServer.getInstance();
    private double power;
    
    public BlockerDrive(double power) {
        requires(CommandBase.drivetrain);
        this.power = power;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        CommandBase.shooter.setBlocker(Shooter.UP);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(server.getLeftStatus()){
            CommandBase.drivetrain.tankDrive(-power, -power);
        }else if(server.getRightStatus()){
            CommandBase.drivetrain.tankDrive(power, power);
        }else{
            CommandBase.drivetrain.tankDrive(0, 0);
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        CommandBase.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        CommandBase.drivetrain.tankDrive(0, 0);
    }

    public String getCommandName() {
        return "Goalie";
    }
}
