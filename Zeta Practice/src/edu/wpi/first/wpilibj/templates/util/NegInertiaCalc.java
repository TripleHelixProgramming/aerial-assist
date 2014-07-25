/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.util;

/**
 *
 * @author Joseph Grube
 */
public class NegInertiaCalc {
    
    private double negInertiaScalar;
    private double negInertiaAccumulator;
    private double oldTurn;
    
    public NegInertiaCalc(double negInertiaScalar) {
        this.negInertiaScalar = negInertiaScalar;
    }
    
    public double calculate(double turn) {
        double newTurn = turn;
        double negInertia = newTurn - oldTurn;
        oldTurn = newTurn;
        
        double negInertiaPower = negInertia * negInertiaScalar;
        negInertiaAccumulator += negInertiaPower;
        newTurn += negInertiaAccumulator;
        
        if (negInertiaAccumulator > 1) {
            negInertiaAccumulator -= 1;
        } else if (negInertiaAccumulator < -1) {
            negInertiaAccumulator += 1;
        } else {
            negInertiaAccumulator = 0;
        }
        
        return newTurn;
    }
}
