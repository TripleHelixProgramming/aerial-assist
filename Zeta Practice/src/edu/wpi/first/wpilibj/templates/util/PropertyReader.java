/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.util;

import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.networktables2.util.List;
import java.io.DataInputStream;
import java.io.InputStreamReader;
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.openDataInputStream()));
            String read;
            System.out.println("Loading properties:");
            while ((read = reader.readLine()) != null) {
                String propertyName = read.substring(0, read.indexOf(':'));
                String value = read.substring(read.indexOf(':') + 1);
                int propertyValue = Integer.parseInt(value);
                System.out.println(propertyName + " - " + propertyValue);
                propertyNames.add(propertyName);
                propertyValues.add(new Integer(propertyValue));
             }
        } catch(Exception e) {
            e.printStackTrace();
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
