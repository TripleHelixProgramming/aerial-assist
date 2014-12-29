/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.util;

import java.util.Date;

/**
 *
 * @author robotics
 */
public class SlowdownPrintings {
    private Date timeThen = new Date(0);
    private long introvol;
   
    public SlowdownPrintings(long introvol) {
        this.introvol = introvol;
    }
    
    public void print(String value) {
       Date timeNow = new Date();
       long timePassed = timeNow.getTime() - timeThen.getTime();
       if (timePassed >= introvol) {
           System.out.println("Value: " + value);
           timeThen = timeNow;
       }
    }
}
