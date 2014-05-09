/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.util;

import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.networktables2.util.List;
import java.io.DataInputStream;
import javax.microedition.io.Connector;

/**
 *
 * @author robotics
 */
public class PropertyReader {
    
    private static List propertyNames;
    private static List propertyValues;
    
    public static void loadProperties() {
        propertyNames = new List();
        propertyValues = new List();
        
        try {
            FileConnection file = (FileConnection) Connector.open("file:///properties.txt");
            DataInputStream reader = file.openDataInputStream();
            String propertyName;
            while ((propertyName = reader.readUTF()) != null) {
                int propertyValue = reader.readInt();
                System.out.println("Loading Property : " + propertyName + " - " + propertyValue);
                propertyNames.add(propertyName);
                propertyValues.add(new Integer(propertyValue));
             }
        } catch(Exception e) {
            System.out.println("Property file not found");
        }
    }
    
    public static int getProperty(String name, int defaultValue) {
        for (int i = 0; i < propertyNames.size(); i++) {
            if (name.equalsIgnoreCase((String) propertyNames.get(i))) {
                return ((Integer)propertyValues.get(i)).intValue();
            }
        }
        return defaultValue;
    }
}
