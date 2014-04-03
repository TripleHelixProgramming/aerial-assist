/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.autonomous;

import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.util.HotGoalFinder;
import edu.wpi.first.wpilibj.templates.util.ProvidesHotGoal;

/**
 *
 * @author robotics
 */
public class HotGoalProcessing extends CommandBase implements ProvidesHotGoal{
    CriteriaCollection cc;
    private String currentGoal = HotGoalFinder.NONE;
    HotGoalFinder finder = new HotGoalFinder();
    
    public HotGoalProcessing() {
      
    }

    protected void initialize() {
       
    }

    protected void execute() {
        currentGoal = finder.findHotGoal();
        System.out.println(currentGoal);
    }

    protected boolean isFinished() {
        if (!currentGoal.equals(HotGoalFinder.NONE)) {
            System.out.println("Hot Goal = " + currentGoal);
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
