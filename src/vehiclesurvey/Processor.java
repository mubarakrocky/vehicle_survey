
package vehiclesurvey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import vehiclesurvey.model.ReadingPoint;
import vehiclesurvey.model.Vehicle;

/**
 *
 * @author mubarak
 */
public class Processor {
    
    private ArrayList <ReadingPoint> pointList;
    private HashMap<String, Vehicle[]> resultHash;
    
    public Processor(ArrayList points) {
        pointList = points;
    }
    
    public void process() {
        
        resultHash = new HashMap<>();
        ListIterator<ReadingPoint> iterator = pointList.listIterator();
        ReadingPoint previousPoint = null;
        ReadingPoint point = null;
        ArrayList<Vehicle> straightVehicles = null;
         ArrayList<Vehicle> oppositeVehicles = null;
        while(iterator.hasNext()) {
            
            
            if(previousPoint == null && point != null) {
               previousPoint  = point;
            } 
            point = iterator.next();
            
             System.out.println(point.sensor.name);
             
            if(previousPoint!= null && previousPoint.sensor.name.equals(point.sensor.name)) {
                Vehicle vehicle = new Vehicle(point, previousPoint);
                
                straightVehicles.add(vehicle);
                
                // Resetting previous entry
                previousPoint = null;
            } else if (previousPoint!= null) {
                System.out.println(previousPoint.sensor.name.equals(point.sensor.name));
            }
        }
    }
}
