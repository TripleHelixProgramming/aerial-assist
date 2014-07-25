/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.util;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 * @author robotics
 */
public class DrivePIDOutput implements PIDOutput {

    private double error;
    
    public void pidWrite(double d) {
        error = d;
    }
    
    public double getError() {
        return error;
    }
}
