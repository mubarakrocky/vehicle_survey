
package com.aconex.vehiclesurvey.analysis;

import com.aconex.vehiclesurvey.model.Vehicle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

/**
 *
 * @author mubarak
 */
public class Result {
    
    Class[] analysisClasses = new Class[] {VehicleCountInsight.class} ;
    
    Object[] daysList;
    TreeMap<String, ArrayList<Vehicle>> vehicleHash;
    
    ArrayList<Vehicle> totalVehicleList = new ArrayList<>();
    
    public HashMap peakVolumeHash = new HashMap();
    int peakVolume = 0;
    /**
     *
     * @param dayViseVehicles
     */
    public Result(TreeMap<String, ArrayList<Vehicle>> dayViseVehicles) {
        
        vehicleHash = dayViseVehicles;
        daysList = dayViseVehicles.keySet().toArray();
    }
    
    public void print() {
       
        // Show day vise results
        for(int i = 0; i < daysList.length; i++) {
            
            System.out.println("Displaying Result For "+daysList[i]+" Day");
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------------------------------");
            
            VehicleCountInsight vehicleCount = new VehicleCountInsight();
            vehicleCount.setVehiclesList(vehicleHash.get(daysList[i]));
            vehicleCount.printHeader();
            vehicleCount.printResult();
            
            MorningEveningInsight morningEveningCount = new MorningEveningInsight();
            morningEveningCount.setVehiclesList(vehicleHash.get(daysList[i]));
            morningEveningCount.printHeader();
            morningEveningCount.printResult();
            
            PeakVolumeInsight peakVoulme = new PeakVolumeInsight();
            peakVoulme.setVehiclesList(vehicleHash.get(daysList[i]));
            peakVoulme.printHeader();
            peakVoulme.printResult();
            
            // Creating list of total vehicles
            this.addToTotalVehicleList(vehicleHash.get(daysList[i]));
        }
        
        // Shows total results
        System.out.println("Displaying Total Days Result");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------");
        
        VehicleCountInsight vehicleCount = new VehicleCountInsight();
        vehicleCount.setVehiclesList(this.totalVehicleList);
        vehicleCount.printHeader();
        vehicleCount.printResult();
        
        MorningEveningInsight morningEveningCount = new MorningEveningInsight();
        morningEveningCount.setVehiclesList(this.totalVehicleList);
        morningEveningCount.printHeader();
        morningEveningCount.printResult();
        
        PeakVolumeInsight peakVoulme = new PeakVolumeInsight();
        peakVoulme.setVehiclesList(this.totalVehicleList);
        peakVoulme.printHeader();
        peakVoulme.printResult();
        
        AverageSpeedInsight avgSpeed = new AverageSpeedInsight();
        avgSpeed.setVehiclesList(totalVehicleList);
        avgSpeed.printHeader();
        avgSpeed.printResult();
        
        AverageDistanceInsight avgDistance = new AverageDistanceInsight();
        avgDistance.setVehiclesList(totalVehicleList);
        avgDistance.printHeader();
        avgDistance.printResult();
    }
    
    private void addToTotalVehicleList(ArrayList<Vehicle> vehicles) {
        Iterator<Vehicle> iterator = vehicles.iterator();
        while(iterator.hasNext()) {
            this.totalVehicleList.add(iterator.next());
        }
     }
    
    
}
