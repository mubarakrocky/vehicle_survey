
package com.aconex.vehiclesurvey.analysis;

import com.aconex.vehiclesurvey.model.Vehicle;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

/**
 *
 * @author mubarak
 */
public class AverageDistanceInsight extends AverageSpeedInsight {
    @Override
    public void printHeader() {
        System.out.println("\n\nShowing average distance between cars per hour");
    }

    @Override
    public void printResult() {
        
        System.out.println("----Average Distance per Hour----------");
        System.out.println("");
        System.out.println("\tHour\t|\tAvg Distane B/W vehicle");
        System.out.println("-----------------------------------------");
        
        TreeMap<String, Double> result = this.getAverageDistancePerHour();
        for(int i = 0; i < 24; i++) {
            String key = String.format("%02d:00", i);
            System.out.println("\t"+key+"\t|\t"+result.get(key));
        }
    }
    
    private TreeMap<String, Double> getAverageDistancePerHour() {
        Iterator<Vehicle> iterartor = this.vehicles.iterator();
        
        TreeMap<String, Double> averageSpeed = this.getResult();
        
        TreeMap<String, Double> averageDisatnce = new TreeMap<>();
        HashMap<String, Integer> counter = new HashMap<>();
        // Initing the average distance
        for(int i = 0; i < 24; i++) {
            averageDisatnce.put(String.format("%02d:00", i), 0.0);
            counter.put(String.format("%02d:00", i), 0);
        }
        Vehicle previousVehicle = null;
        while(iterartor.hasNext()) {
            
            Vehicle vehicle = iterartor.next();
            int hourReaded = vehicle.getFirstReading().getTimeRecordedAt().get(Calendar.HOUR_OF_DAY);
            String key = String.format("%02d:00", hourReaded);
            double speed = vehicle.getSpeed();
            
            if (previousVehicle != null) {
                int timeDifference = vehicle.getFirstReading().timeSegment - previousVehicle.getFirstReading().timeSegment;
                System.out.println(timeDifference);
                double distance = speed * timeDifference * (1/3600000.0);
                Double oldDistance = averageDisatnce.get(key);
                double disatnceSum = Math.abs(distance) + oldDistance;
                averageDisatnce.put(key, disatnceSum);
                
                Integer prevoiusCounter = counter.get(key);
                int incrementedCounter = prevoiusCounter + 1;
                counter.put(key, incrementedCounter);
            }
            
            previousVehicle = vehicle;
        }
        // Finding average
        for(int i = 0; i < 24; i++) {
            String key = String.format("%02d:00", i);
            Double totalDistance = averageDisatnce.get(key);
            Integer totalCount = counter.get(key);
            if(totalCount > 0){
                averageDisatnce.put(key, (totalDistance/totalCount));
                
            }
           
        }
        return averageDisatnce;
    }
}
