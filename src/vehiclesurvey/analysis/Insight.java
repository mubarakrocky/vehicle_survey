/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesurvey.analysis;

import java.util.ArrayList;
import vehiclesurvey.model.Vehicle;

/**
 *
 * @author mubarak
 */
public abstract class Insight {
    
    private ArrayList<Vehicle> vehicles;
    
    public abstract void printHeader();
    
    public abstract void printResult();
    
    
    private void printLine() {
        System.out.print("----------------------------------------------------------");
    }
    
    public void setVehiclesList(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
   
}
