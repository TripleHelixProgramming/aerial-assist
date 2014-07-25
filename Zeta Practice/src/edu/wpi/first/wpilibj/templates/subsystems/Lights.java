/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.lights.UnderPressureCommand;
import edu.wpi.first.wpilibj.templates.util.PropertyReader;

/**
 *
 * @author robotics
 */
public class Lights extends Subsystem {
    
    private Relay lightController = new Relay(PropertyReader.getProperty("LIGHT_RELAY_CHANNEL", RobotMap.lightChannel));
    private AnalogChannel pressure = new AnalogChannel(PropertyReader.getProperty("PRESSURE_TRANSDUCER_CHANNEL" ,RobotMap.pressureChannel));
    
    public void initDefaultCommand() {
        UnderPressureCommand underPressureCommand = new UnderPressureCommand();
        underPressureCommand.setRunWhenDisabled(true);
        setDefaultCommand(underPressureCommand);
    }
    
    public void turnOn(boolean on) {
        if (on) {
            lightController.set(Relay.Value.kForward);
        } else {
            lightController.set(Relay.Value.kOff);
        }
    }
    
    public int getPressure() {
        return (int) (pressure.getVoltage() * 15);
    }
}