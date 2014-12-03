/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesurvey.analysis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import vehiclesurvey.model.Vehicle;

/**
 *
 * @author mubarak
 */
public class AverageSpeedInsight extends Insight {
    public ArrayList<Vehicle> vehicles;
    
    @Override
    public void printHeader() {
        System.out.println("\n\nShowing average speed per hour");
        System.out.println("----Average Speed per Hour----------");
        System.out.println("");
        System.out.println("\tHour\t|\tAvg Speed");
        System.out.println("-----------------------------------------");
    }

    @Override
    public void printResult() {
        
        TreeMap<String, Double> result = this.getResult();
        for(int i = 0; i < 24; i++) {
            String key = String.format("%02d:00", i);
            System.out.println("\t"+key+"\t|\t"+result.get(key));
        }
    }
    
    @Override
    public void setVehiclesList(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    
    public TreeMap<String, Double> getResult() {
        Iterator<Vehicle> iterartor = this.vehicles.iterator();
        
        TreeMap<String, Double> result = new TreeMap<>();
        HashMap<String, Integer> counter = new HashMap<>();
        for(int i = 0; i < 24; i++) {
            result.put(String.format("%02d:00", i), 0.0);
            counter.put(String.format("%02d:00", i), 0);
        }
        while(iterartor.hasNext()) {
            
            Vehicle vehicle = iterartor.next();
            int hourReaded = vehicle.getFirstReading().getTimeRecordedAt().get(Calendar.HOUR_OF_DAY);
            String key = String.format("%02d:00", hourReaded);
            double speed = vehicle.getSpeed();
            Integer prevoiusCounter = counter.get(key);
            Double totalSpeed = result.get(key);
            totalSpeed += speed;
            result.put(key, totalSpeed);
            int incrementedCounter = prevoiusCounter + 1;
            counter.put(key, incrementedCounter);
        }
        // Finding average
        for(int i = 0; i < 24; i++) {
            String key = String.format("%02d:00", i);
            Double totalSpeed = result.get(key);
            Integer totalCount = counter.get(key);
            
            if(totalCount > 0){
                result.put(key, totalSpeed/totalCount);
                
            }
           
        }
        
        return result;
    }

    
 }
