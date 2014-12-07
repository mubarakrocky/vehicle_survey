
package com.aconex.vehiclesurvey.analysis;


import com.aconex.vehiclesurvey.model.Vehicle;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


public class VehicleCountInsight extends Insight {
    
    public TreeMap<String, HashMap<String, Integer>> perHourCounter = new TreeMap<>();
    public TreeMap<String, HashMap<String, Integer>> perHalfHourCounter = new TreeMap<>();
    public TreeMap<String, HashMap<String, Integer>> perTwentyMinuteCounter = new TreeMap<>();
    public TreeMap<String, HashMap<String, Integer>> perFifteenMinuteCounter = new TreeMap<>();
    
    @Override
    public void printHeader() {
        System.out.print("Total vehicle counts in each direction\n");
    }

    @Override
    public void printResult() {
        // Initiate the maps
        this.buildResultMaps();
        // Make the result
        this.makeResultList();
          
        this.printFormatedForPerHour("Vehicle Count Per Hour", perHourCounter);
        this.printFormatedForPerHour("Vehicle Count Per Half Hour", perHalfHourCounter);
        this.printFormatedForPerHour("Vehicle Count Per 20 Minute", perTwentyMinuteCounter);
        this.printFormatedForPerHour("Vehicle Count Per 15 Minute", perFifteenMinuteCounter);
    }
    
    
    public void makeResultList() {
        
        for(int i=0; i < this.vehicles.size(); i++) {
            
            Vehicle vehicle = this.vehicles.get(i);
            int hourReaded = vehicle.getFirstReading().getTimeRecordedAt().get(Calendar.HOUR_OF_DAY);
            
            int minutesReaded = vehicle.getFirstReading().getTimeRecordedAt().get(Calendar.MINUTE);
            
            // increment per hour count
            this.incrementCounter(String.format("%02d:00", hourReaded), vehicle.getDirection(), perHourCounter);
            
            // increment per half hour count
            this.incrementCounter(String.format("%02d:%02d", hourReaded, (minutesReaded/30)*30), vehicle.getDirection(), perHalfHourCounter);
            
            // increment 20 minute count
            this.incrementCounter(String.format("%02d:%02d", hourReaded, (minutesReaded/20)*20), vehicle.getDirection(), perTwentyMinuteCounter);
            
            // Increment 15 minute count
            this.incrementCounter(String.format("%02d:%02d", hourReaded, (minutesReaded/15)*15), vehicle.getDirection(), perFifteenMinuteCounter);
        }
    }

    public void buildResultMaps() {
        
        // Map the per hour data
        for(int i = 0; i < 24; i++) {
            perHourCounter.put(String.format("%02d:00", i), getHashForDirection());
        }
        
        for(int i = 0; i < 48; i++) {
            perHalfHourCounter.put(String.format("%02d:%02d", i/2, (i%2)*30), getHashForDirection());
        }
        
        for(int i = 0; i < 72; i++) {
            perTwentyMinuteCounter.put(String.format("%02d:%02d", i/3, (i%3)*20), getHashForDirection());
        }
        
        for(int i = 0; i < 96; i++) {
            perFifteenMinuteCounter.put(String.format("%02d:%02d", i/4, (i%4)*15), getHashForDirection());
        }
    }
    
    // Create has for direction key
    private HashMap<String, Integer> getHashForDirection() {
        HashMap<String, Integer> directionHash = new HashMap<>();
        directionHash.put("NORTH", 0);
        directionHash.put("SOUTH", 0);
        return directionHash;
    }
    
    private void incrementCounter(String key, String direction, TreeMap<String, HashMap<String, Integer>> resultHash) {
        // Increment 30 minute count
        HashMap<String, Integer> hashValue = resultHash.get(key);
        hashValue.put(direction, hashValue.get(direction)+1);
    }
    
    private void printFormatedForPerHour(String header, TreeMap<String, HashMap<String, Integer>> resultHash) {
        System.out.println("");
        System.out.println("-------"+header+"---------");
        System.out.println("Hour\t|\t SOUTH\t|\t NORTH");
        
        Iterator hashIterator = resultHash.entrySet().iterator();
        while (hashIterator.hasNext()) {
            Map.Entry pairs = (Map.Entry) hashIterator.next();
            
            HashMap<String, Integer> hashValue = (HashMap<String, Integer>) pairs.getValue();
            System.out.println(pairs.getKey() + "\t|\t " + hashValue.get("SOUTH") + "\t|\t "+hashValue.get("NORTH"));
        }
        System.out.println("");
    }
}
