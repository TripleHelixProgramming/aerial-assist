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
    
    public PickUpDeploy(boolean deploy, double rollerSpeed) {
        requires(pickUp);
        this.deploy = deploy;
        this.rollerSpeed = rollerSpeed;
    }

    protected void initialize() {
        pickUp.deployArm(deploy);
        if(oi.isRollerOn()){
            pickUp.setRollerSpeed(rollerSpeed);
            System.out.println("Roller On");
        }else{
            pickUp.setRollerSpeed(0);
            System.out.println("Roller Off");
        }
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        
    }

    protected void interrupted() {
    }
}
