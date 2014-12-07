
package com.aconex.vehiclesurvey.analysis;

import java.io.FileNotFoundException;
import org.junit.Test;
import com.aconex.vehiclesurvey.DataReader;

/**
 *
 * @author mubarak
 */
public class ResultTest {
    
    
    @Test
    public void testConstructor() throws FileNotFoundException {     
        
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        dataReader.read();

        
        // Result should accept the day vise vehicle list
        
        Result result = new Result(dataReader.dayViseVehicles);
    }
    
    @Test
    public void testPrint() throws FileNotFoundException {
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data1.txt" ));
        dataReader.read();

        
        // Result should accept the day vise vehicle list
        
        Result result = new Result(dataReader.dayViseVehicles);
        result.print();
    }
    
    
    @Test
    
    public void testAddToTotalVehicleList() {
        
    }
    
}
