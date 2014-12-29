/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.util;

import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.networktables2.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.microedition.io.Connector;

/**
 *
 * @author robotics
 */
public class PathReader {
    
    private double[][] speeds;
    private String fileName;
    private int lastIndex = 0;
    
    public PathReader(String fileName) {
        this.fileName = fileName;
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void readFile() throws IOException {
        FileConnection file = (FileConnection) Connector.open("file:///" + fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.openDataInputStream()));
        List lines = new List();
        String reading = "";
        while ((reading = reader.readLine()) != null) {
            lines.add(reading);
        }
        
        speeds = new double[lines.size()][3];
        for (int i = 0; i < speeds.length; i++) {
            String line = (String)lines.get(i);
            int firstTab = line.indexOf("\t");
            int secondTab = line.indexOf("\t", firstTab  + 1);
            speeds[i][0] = Double.parseDouble(line.substring(0, firstTab));
            speeds[i][1] = Double.parseDouble(line.substring(firstTab + 1, secondTab));
            speeds[i][2] = Double.parseDouble(line.substring(secondTab + 1));
        }
    }
    
    public double[] getNextSpeeds(int timeRunning) {
        int i = lastIndex;
        while (timeRunning / 1000 < speeds[i][0]) {
            i++;
            if (i >= speeds.length) {
                return null;
            }
        }
        lastIndex = i;
        double[] nextRead = {speeds[i][1], speeds[i][2]};
        return nextRead;
    }
}
