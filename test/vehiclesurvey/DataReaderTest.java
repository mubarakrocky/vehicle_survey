
package vehiclesurvey;

import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mubarak
 */
public class DataReaderTest {
    
    public DataReaderTest() {
    }
    
    
    @Test
    public void shouldRaiseExceptionIfFileNotFound() {
        try {
            DataReader dataReader = new DataReader("not_a_file.txt");
            fail("Desired exception not thrown.");
        } catch (FileNotFoundException ex) {
            System.out.println("Exception occured. Test succeeded");
        }
    }
    
    @Test
    public void fileShouldRead() {
        try {
            DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        } catch (FileNotFoundException ex) {
            fail("Exception thrown. failed");
        }
    }
    
    @Test 
    public void testRead() throws FileNotFoundException {
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        
        dataReader.read();

        // The point list should have 10 entries
        assertEquals(10, dataReader.pointList.size());
    }
}
