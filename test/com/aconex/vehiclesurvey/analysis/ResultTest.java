
package com.aconex.vehiclesurvey.analysis;

import com.aconex.vehiclesurvey.DataReader;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 *
 * @author mubarak
 */
public class ResultTest {
    
    
    @Test
    public void testConstructor() throws FileNotFoundException {     
        
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        dataReader.read();

        Result result = new Result(dataReader.dayViseVehicles);
    }
    
    @Test
    public void testPrint() throws FileNotFoundException {
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data1.txt" ));
        dataReader.read();

        Result result = new Result(dataReader.dayViseVehicles);
        result.print();
    }
    
    
    @Test
    public void testAddToTotalVehicleList() throws FileNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        dataReader.read();

        Result result = new Result(dataReader.dayViseVehicles);
        
        Class[] args = new Class[] {ArrayList.class};
        Method method = result.getClass().getDeclaredMethod("addToTotalVehicleList", args);
        method.setAccessible(true);
        method.invoke(result, result.vehicleHash.get("0"));
        
        // Dummy data file has three records
        assertEquals(3, result.totalVehicleList.size());
    }
    
}
