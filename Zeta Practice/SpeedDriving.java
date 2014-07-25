
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.util.DrivePIDOutput;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author robotics
 */
public class SpeedDriving extends CommandBase {
    
    private PIDController leftController;
    private PIDController rightController;
    
    public SpeedDriving() {
        requires(drivetrain);
        leftController = new PIDController(1, 0, 0, drivetrain.leftEncoder, new DrivePIDOutput(drivetrain.frontLeft, drivetrain.backLeft));
        rightController = new PIDController(1, 0, 0, drivetrain.rightEncoder, new DrivePIDOutput(drivetrain.frontRight, drivetrain.backRight));
        leftController.setOutputRange(-0.95, 0.95);
        rightController.setOutputRange(-0.95, 0.95);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        leftController.enable();
        rightController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        leftController.setSetpoint(4000);
        rightController.setSetpoint(4000);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
