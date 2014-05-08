/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.util;

import edu.wpi.first.wpilibj.networktables2.util.List;

/**
 *
 * @author robotics
 */
public class RollingAverager {
    private List values = new List();
    private int numValues;
    
    public RollingAverager(int numValues) {
        if (numValues <= 0) {
            throw new IllegalArgumentException("Cannot have 0 or fewer values.");
        }
        this.numValues = numValues;
    }
    
    public RollingAverager(int numValues, double initialValue) {
        if (numValues <= 0) {
            throw new IllegalArgumentException("Cannot have 0 or fewer values.");
        }
        this.numValues = numValues;
        for (int i = 0; i < numValues; i++) {
            values.add(new Double(initialValue));
        } 
    }
    
    public void addValue(double value) {
        if (values.size() >= numValues) {
            values.remove(0);
        }
        values.add(new Double(value));
    }
    
    public double getAverage() {
        double sum = 0;
        
        for(int i = 0; i < values.size(); i++) {
            sum += ((Double) values.get(i)).doubleValue();
        }
        
        return sum / values.size();
    }
}
