
package com.aconex.vehiclesurvey.analysis;


import com.aconex.vehiclesurvey.model.ReadingPoint;
import com.aconex.vehiclesurvey.model.Vehicle;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.zip.DataFormatException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mubarak
 */
public class AverageSpeedInsightTest {
    
    
     @Test
     public void testGetResult() throws FileNotFoundException, DataFormatException {
        
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        // Fist vehicle took 250ms. Speed is 36 kmh
        ReadingPoint v1Reading1 = new ReadingPoint("A269500");
        ReadingPoint v1Reading2 = new ReadingPoint("A269750");
        Vehicle firstVehicle = new Vehicle(v1Reading1, v1Reading2);
        
         // Fist vehicle took 150ms. Speed is 60 kmh
        ReadingPoint v2Reading1 = new ReadingPoint("A269800");
        ReadingPoint v2Reading2 = new ReadingPoint("A269950");
        Vehicle secondVehicle = new Vehicle(v2Reading1, v2Reading2);
        
       
        vehicleList.add(firstVehicle);
        vehicleList.add(secondVehicle);
        
        AverageSpeedInsight speedInsight = new AverageDistanceInsight();
        speedInsight.setVehiclesList(vehicleList);
        
        TreeMap<String, Double> result = speedInsight.getResult();
        
        // The average speed should be 48
        assertEquals(48, result.get("00:00"), 0);
        
     }
}
