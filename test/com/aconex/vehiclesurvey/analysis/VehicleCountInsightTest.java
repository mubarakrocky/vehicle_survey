/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.vehiclesurvey.analysis;

import com.aconex.vehiclesurvey.DataReader;
import com.aconex.vehiclesurvey.model.Vehicle;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mubarak
 */
public class VehicleCountInsightTest {
    

    @Test
    public void testBuildResultMaps() throws FileNotFoundException {
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        dataReader.read();

        
        ArrayList<Vehicle> vehicleList = dataReader.dayViseVehicles.get("0");
        
        VehicleCountInsight vehicleCountInsight = new VehicleCountInsight();
        vehicleCountInsight.setVehiclesList(vehicleList);
        // Initialize the results holding variables
        vehicleCountInsight.buildResultMaps();
        
        assertEquals(96, vehicleCountInsight.perFifteenMinuteCounter.size());
        assertEquals(72, vehicleCountInsight.perTwentyMinuteCounter.size());
        assertEquals(48, vehicleCountInsight.perHalfHourCounter.size());
        assertEquals(24, vehicleCountInsight.perHourCounter.size());
    }
    
    @Test
    public void testMakeResultList() throws FileNotFoundException {
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        dataReader.read();

        
        ArrayList<Vehicle> vehicleList = dataReader.dayViseVehicles.get("0");
        
        VehicleCountInsight vehicleCountInsight = new VehicleCountInsight();
        vehicleCountInsight.setVehiclesList(vehicleList);
        // Initialize the results holding variables
        vehicleCountInsight.buildResultMaps();
        
        // Making the reults
        vehicleCountInsight.makeResultList();
        
        // Getting values for 00:00 
        HashMap<String, Integer> zeroMinuteValue = vehicleCountInsight.perFifteenMinuteCounter.get("00:00");
        Integer southCount = zeroMinuteValue.get("SOUTH");
        Integer northCount = zeroMinuteValue.get("NORTH");
        
        // Asserting the north and south count
        assertEquals(1, (long) southCount);
        assertEquals(1, (long) northCount);
        
        zeroMinuteValue = vehicleCountInsight.perTwentyMinuteCounter.get("00:00");
        southCount = zeroMinuteValue.get("SOUTH");
        northCount = zeroMinuteValue.get("NORTH");
        
        // Asserting the north and south count
        assertEquals(2, (long) southCount);
        assertEquals(1, (long) northCount);
        
        zeroMinuteValue = vehicleCountInsight.perHalfHourCounter.get("00:00");
        southCount = zeroMinuteValue.get("SOUTH");
        northCount = zeroMinuteValue.get("NORTH");
        
        // Asserting the north and south count
        assertEquals(2, (long) southCount);
        assertEquals(1, (long) northCount);
        
        zeroMinuteValue = vehicleCountInsight.perHourCounter.get("00:00");
        southCount = zeroMinuteValue.get("SOUTH");
        northCount = zeroMinuteValue.get("NORTH");
        
        // Asserting the north and south count
        assertEquals(2, (long) southCount);
        assertEquals(1, (long) northCount);
    }
    
    @Test
    public void testGetHashForDirection() {
        VehicleCountInsight vehicleCountInsight = new VehicleCountInsight();
        
        Method[] methods = vehicleCountInsight.getClass().getMethods();
        for(int i = 0; i < methods.length; i++) {
            Method m = methods[i];
            System.out.println(m.getParameterAnnotations().toString());
        }
    }
    
    @Test
    public void testSetVehiclesList() throws FileNotFoundException {
        // It should set the vehciles array list
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        dataReader.read();

        
        ArrayList<Vehicle> vehicleList = dataReader.dayViseVehicles.get("0");
        
        VehicleCountInsight vehicleCountInsight = new VehicleCountInsight();
        vehicleCountInsight.setVehiclesList(vehicleList);
        
        // The vehicles list should be 3
        assertEquals(3, vehicleCountInsight.vehicles.size());
        
    }
}
