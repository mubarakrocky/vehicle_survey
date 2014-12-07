
package com.aconex.vehiclesurvey.analysis;

import com.aconex.vehiclesurvey.DataReader;
import com.aconex.vehiclesurvey.model.Vehicle;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.TreeMap;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mubarak
 */
public class PeakVolumeInsightTest {
    
    
     @Test
     public void testFindPeakVolume() throws FileNotFoundException, SecurityException, NoSuchMethodException, InvocationTargetException, IllegalArgumentException, IllegalAccessException {
         DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        dataReader.read();

        
        ArrayList<Vehicle> vehicleList = dataReader.dayViseVehicles.get("0");
        
        VehicleCountInsight vehicleCountInsight = new VehicleCountInsight();
        vehicleCountInsight.setVehiclesList(vehicleList);
        // Initialize the results holding variables
        vehicleCountInsight.buildResultMaps();
        vehicleCountInsight.makeResultList();
                
        PeakVolumeInsight peakVolumeInsight = new PeakVolumeInsight();
        
        Class[] args = new Class[] {TreeMap.class};
        Method method = peakVolumeInsight.getClass().getDeclaredMethod("findPeakVolume", args);
        method.setAccessible(true);
        String peakTime = (String) method.invoke(peakVolumeInsight, vehicleCountInsight.perHourCounter);
        assertEquals("00:00", peakTime);
     }
}
