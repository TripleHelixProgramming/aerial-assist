/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.pickup;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;


    // Called repeatedly when this Command is scheduled to run
/**
 *
 * @author robotics
 */
public class PickUpDeploy extends CommandBase {
    
    private boolean deploy;
    private double rollerSpeed;
    private boolean openWings;
    
    public PickUpDeploy(boolean deploy, double rollerSpeed, boolean openWings) {
        requires(pickUp);
        this.deploy = deploy;
        this.rollerSpeed = rollerSpeed;
        this.openWings = openWings;
    }

    protected void initialize() {
        pickUp.deployArm(deploy);
        pickUp.fireCatch(openWings);
    }

    protected void execute() {
         if(oi.isRollerOn()){
            pickUp.setRollerSpeed(rollerSpeed);
        }else{
            pickUp.setRollerSpeed(0);
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
