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
public class GenericPIDOutput implements PIDOutput {
    
    private double power;

    public void pidWrite(double d) {
        power = d;
    }
    
    public double getPower() {
        return power;
    }
}
