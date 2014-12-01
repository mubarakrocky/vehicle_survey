
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
    private HashMap<String, Vehicle> resultHash;
    
    public Processor(ArrayList points) {
        pointList = points;
    }
    
    public void process() {
        
        resultHash = new HashMap<>();
        ListIterator<ReadingPoint> iterator = pointList.listIterator();
        ReadingPoint previousPoint = null;
        ReadingPoint point;
        while(iterator.hasNext()) {
                        
            if(previousPoint== null && pointList.listIterator().hasPrevious()) {
               previousPoint  = iterator.previous();
            } 
            point = iterator.next();
            
            if(previousPoint!= null && previousPoint.sensor.name.equals(point.sensor.name)) {
                previousPoint = null;
            }
        }
    }
}
