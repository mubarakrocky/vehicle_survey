
package com.aconex.vehiclesurvey.analysis;


import com.aconex.vehiclesurvey.model.ReadingPoint;
import com.aconex.vehiclesurvey.model.Vehicle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.zip.DataFormatException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mubarak
 */
public class AverageDistanceInsightTest {
    
     @Test
     public void testGetAverageDistancePerHour() throws DataFormatException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
        
        AverageDistanceInsight averageDistance = new AverageDistanceInsight();
        averageDistance.setVehiclesList(vehicleList);
        Method method = averageDistance.getClass().getDeclaredMethod("getAverageDistancePerHour");
        method.setAccessible(true);
        
        TreeMap<String, Double> results = (TreeMap<String, Double>) method.invoke(averageDistance);
        
        // The time gap between first vehicle and second vehicle is 50 ms and second vehicles speed is 60kmh
        // and the time difference is 300ms
        // So the distance between them is speed * time = 60 * 300 * (1/3600000)
        // Which is 5 m
        System.out.println();
        assertEquals(5, results.get("00:00"), 0.001);
     }
}
