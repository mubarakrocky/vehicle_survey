
package vehiclesurvey;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mubarak
 */
public class DataReaderTest {
    
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
        assertEquals(1, dataReader.dayViseVehicles.size());
    }
    
    @Test
    public void testAddDayViseVehicles() throws FileNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data.txt" ));
        Class[] args1 = new Class[1];
        args1[0] = int.class;
        Method method = dataReader.getClass().getDeclaredMethod("addDayViseVehicles", args1);
        method.setAccessible(true);
        method.invoke(dataReader, 2);
        // The hash result should have 2 as key
        assertTrue(dataReader.dayViseVehicles.containsKey(Integer.toString(2)));        
    }
}
