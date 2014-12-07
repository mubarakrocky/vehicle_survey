
package com.aconex.vehiclesurvey.analysis;

import com.aconex.vehiclesurvey.model.Vehicle;
import java.util.ArrayList;

/**
 *
 * @author mubarak
 */
public abstract class Insight {
    
    public ArrayList<Vehicle> vehicles;
    
    
    public abstract void printHeader();
    
    public abstract void printResult();
    
    public void printLine() {
        System.out.print("----------------------------------------------------------");
    }
    
    public  void setVehiclesList(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    
}
