
package vehiclesurvey.analysis;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vehiclesurvey.DataReader;

import vehiclesurvey.model.Vehicle;

/**
 *
 * @author mubarak
 */
public class ResultTest {
    
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

        
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testConstructor() throws FileNotFoundException {     
        
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        dataReader.read();

        
        // Result should accept the day vise vehicle list
        
        Result result = new Result(dataReader.dayViseVehicles);
    }
    
    @Test
    public void testPrint() throws FileNotFoundException {
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        dataReader.read();

        
        // Result should accept the day vise vehicle list
        
        Result result = new Result(dataReader.dayViseVehicles);
        result.print();
    }
     
}
