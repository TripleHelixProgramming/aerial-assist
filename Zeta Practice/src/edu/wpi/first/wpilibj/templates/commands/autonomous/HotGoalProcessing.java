/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.util.HotGoalFinder;
import edu.wpi.first.wpilibj.templates.util.ProvidesHotGoal;
import edu.wpi.first.wpilibj.templates.util.VisionProcessing;
import java.io.IOException;
import java.util.Date;
import javax.microedition.io.Connector;

/**
 *
 * @author robotics
 */
public class HotGoalProcessing extends CommandBase implements ProvidesHotGoal{
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    public static final String NONE = "none";
    CriteriaCollection cc;
    private String currentGoal = HotGoalFinder.NONE;
    HotGoalFinder finder = new HotGoalFinder();
    
    public HotGoalProcessing() {
      
    }

    protected void initialize() {
       
    }

    protected void execute() {
        currentGoal = finder.findHotGoal();
    }

    protected boolean isFinished() {
        if (!currentGoal.equals(HotGoalFinder.NONE)) {
            System.out.println("Hot goal found!");
            return true;
        }
        return false;
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }

    public String getHotGoal() {
        return currentGoal;
    }

}
