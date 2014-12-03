
package vehiclesurvey.analysis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import vehiclesurvey.model.Vehicle;


public class VehicleCount extends Insight {
    private ArrayList<Vehicle> vehicles;
    
    
    private TreeMap<String, HashMap<String, Integer>> perHourCounter = new TreeMap<>();
    private HashMap<String, HashMap<String, Integer>> perHalfHourCounter = new HashMap<>();
    private HashMap<String, HashMap<String, Integer>> perTwentyMinuteCounter = new HashMap<>();
    private HashMap<String, HashMap<String, Integer>> perFifteenMinuteCounter = new HashMap<>();
    
    @Override
    public void printHeader() {
        System.out.print("Total vehicle counts in each direction\n");
    }

    @Override
    public void printResult() {
        this.buildResultMaps();
        this.makeResultList();
        
        System.out.println("Count per hour");
        Iterator it = perHourCounter.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            System.out.println(pairs.getKey() + " = " + pairs.getValue());
            
        }
        
        System.out.println("Per half hour");
        Iterator it2 = perHalfHourCounter.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            System.out.println(pairs.getKey() + " = " + pairs.getValue());
            
        }
    }
    
    
    private void makeResultList() {
        System.out.print(this.vehicles.size());
        for(int i=0; i < this.vehicles.size(); i++) {
            System.out.println(this.vehicles.get(i).getDirection());
            System.out.println(this.vehicles.get(i).getSpeed());
            System.out.println(this.vehicles.get(i).getFirstReading().getTimeRecordedAt().get(Calendar.HOUR_OF_DAY));
            
            Vehicle vehicle = this.vehicles.get(i);
            int hourReaded = vehicle.getFirstReading().getTimeRecordedAt().get(Calendar.HOUR_OF_DAY);
            
            int minutesReaded = vehicle.getFirstReading().getTimeRecordedAt().get(Calendar.MINUTE);
            
            // Increment hourly count
            HashMap<String, Integer> hourHash = perHourCounter.get(String.format("%02d:00", hourReaded));
            
            hourHash.put(vehicle.getDirection(), hourHash.get(vehicle.getDirection())+1);
            perHourCounter.put(String.format("%02d:00", hourReaded), hourHash);
            
            // Increment 30 minute count
            HashMap<String, Integer> halfHourHash = perHalfHourCounter.get(String.format("%02d:%02d", hourReaded, (minutesReaded/30)*30));
            
            halfHourHash.put(vehicle.getDirection(), halfHourHash.get(vehicle.getDirection())+1);
            perHalfHourCounter.put(String.format("%02d:%02d", hourReaded, (minutesReaded/30)*30), halfHourHash);
            
        }
    }

    @Override
    public void setVehiclesList(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    
    private void buildResultMaps() {
        
        
        
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
            perFifteenMinuteCounter.put(String.format("%02d:%02d", i/3, (i%3)*15), getHashForDirection());
        }
    }
    
    private HashMap<String, Integer> getHashForDirection() {
        HashMap<String, Integer> directionHash = new HashMap<>();
        directionHash.put("NORTH", 0);
        directionHash.put("SOUTH", 0);
        return directionHash;
    }
}
