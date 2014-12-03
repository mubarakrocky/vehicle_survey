/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesurvey.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import vehiclesurvey.model.Vehicle;

/**
 *
 * @author mubarak
 */
public class Result {
    
    Class[] analysisClasses = new Class[] {VehicleCount.class} ;
    
    Object[] daysList;
    HashMap<String, ArrayList<Vehicle>> vehicleHash;
    /**
     *
     * @param dayViseVehicles
     */
    public Result(HashMap<String, ArrayList<Vehicle>> dayViseVehicles) {
        
        vehicleHash = dayViseVehicles;
        daysList = dayViseVehicles.keySet().toArray();
    }
    
    public void print() {
        
        for(int i = 0; i < daysList.length; i++) {
            VehicleCount vehicleCount = new VehicleCount();
            vehicleCount.setVehiclesList(vehicleHash.get(daysList[i]));
            vehicleCount.printHeader();
            vehicleCount.printResult();
            System.out.print(i);
        }
    }
    
    
}
