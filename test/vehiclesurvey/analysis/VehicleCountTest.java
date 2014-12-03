/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesurvey.analysis;

import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vehiclesurvey.DataReader;

/**
 *
 * @author mubarak
 */
public class VehicleCountTest {
    
    public VehicleCountTest() {
    }
    
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
    
    public void makeResultList() throws FileNotFoundException {
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data1.txt" ));
        dataReader.read();

        
        // Result should accept the day vise vehicle list
        
        Result result = new Result(dataReader.dayViseVehicles);
    }
}
