
package com.aconex.vehiclesurvey.analysis;

import com.aconex.vehiclesurvey.DataReader;
import com.aconex.vehiclesurvey.model.Vehicle;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import static org.junit.Assert.*;
import org.junit.Test;

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
    public void testGetHashForDirection() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        VehicleCountInsight vehicleCountInsight = new VehicleCountInsight();
        
        Method method = vehicleCountInsight.getClass().getDeclaredMethod("getHashForDirection");
        method.setAccessible(true);
        HashMap<String, Integer> directionHash = (HashMap<String, Integer>) method.invoke(vehicleCountInsight);
        
        // The directionHash should contains these keys
        assertTrue(directionHash.containsKey("SOUTH"));
        assertTrue(directionHash.containsKey("NORTH"));
    }
    
    @Test
    public void testIncrementCounter() throws NoSuchMethodException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        VehicleCountInsight vehicleCountInsight = new VehicleCountInsight();
        
        Class[] args = new Class[] {String.class, String.class, TreeMap.class};
        Method method = vehicleCountInsight.getClass().getDeclaredMethod("incrementCounter", args);
        method.setAccessible(true);
        
        TreeMap<String, HashMap<String, Integer>> resultHash = new TreeMap<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("SOUTH", 0);
        resultHash.put("KEY", hashMap);
        
        method.invoke(vehicleCountInsight,"KEY","SOUTH",resultHash);
        
        // The has maps south key should be incremented to 1
        assertEquals(1, (long)hashMap.get("SOUTH"));
        
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
    
    @Test 
    public void testPrintFormatedForPerHour() throws FileNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // It should set the vehciles array list
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        dataReader.read();

        
        ArrayList<Vehicle> vehicleList = dataReader.dayViseVehicles.get("0");
        
        VehicleCountInsight vehicleCountInsight = new VehicleCountInsight();
        vehicleCountInsight.setVehiclesList(vehicleList);
        // Initialize the results holding variables
        vehicleCountInsight.buildResultMaps();
        
        // Making the reults
        vehicleCountInsight.makeResultList();
        
        Class[] args = new Class[] {String.class, TreeMap.class};
        Method method = vehicleCountInsight.getClass().getDeclaredMethod("printFormatedForPerHour", args);
        method.setAccessible(true);
        method.invoke(vehicleCountInsight, "Test header", vehicleCountInsight.perFifteenMinuteCounter);
        
    }
}
