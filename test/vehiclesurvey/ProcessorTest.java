/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesurvey;

import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mubarak
 */
public class ProcessorTest {
    private DataReader dataReader;
    
    public ProcessorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException {
        dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        dataReader.read(); // Read the test file
    }
    
    @After
    public void tearDown() {
    }
   
    @Test
    public void testConstructor() {
        Processor processor = new Processor(dataReader.pointList);
        assertTrue("The object should be initiated", processor != null);
    }
    
    @Test
    public void testProcess() {
        Processor processor = new Processor(dataReader.pointList);
        processor.process();
    }
}