/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesurvey.analysis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author mubarak
 */
public class PeakVolumeInsight extends VehicleCount {
    private int peakVolume;
    
    @Override
    public void printHeader() {
        System.out.print("---------------------------------------------------------\n");
        System.out.print("\t\tPeak Volumes\n");
        System.out.print("---------------------------------------------------------\n");
    }

    @Override
    public void printResult() {
        // Initiate the maps
        this.buildResultMaps();
        // Make the result
        this.makeResultList();
        
        System.out.println("The peak volume per hour is occured at "+this.findPeakVolume(this.perHourCounter)+" and the count is "+peakVolume);
        System.out.print("---------------------------------------------------------\n");
        System.out.println("The peak volume per half hour is occured at "+this.findPeakVolume(this.perHalfHourCounter)+" and the count is "+peakVolume);
        System.out.print("---------------------------------------------------------\n");
        System.out.println("The peak volume per 20 minute is occured at "+this.findPeakVolume(this.perTwentyMinuteCounter)+" and the count is "+peakVolume);
        System.out.print("---------------------------------------------------------\n");
        System.out.println("The peak volume per 15 minute is occured at "+this.findPeakVolume(this.perFifteenMinuteCounter)+" and the count is "+peakVolume);
        System.out.print("---------------------------------------------------------\n");
    }
    
    private String findPeakVolume(TreeMap<String, HashMap<String, Integer>> vehicleCounter) {
        String peakTime = "";
        peakVolume = 0;
        Iterator hashIterator = vehicleCounter.entrySet().iterator();
        while (hashIterator.hasNext()) {
            Map.Entry pairs = (Map.Entry) hashIterator.next();
            
            HashMap<String, Integer> hashValue = (HashMap<String, Integer>) pairs.getValue();
            if(peakVolume < (hashValue.get("SOUTH") + hashValue.get("NORTH"))) {
                peakVolume = (hashValue.get("SOUTH") + hashValue.get("NORTH"));
                peakTime = (String) pairs.getKey();
            }
        }
        return peakTime;
    }
}
