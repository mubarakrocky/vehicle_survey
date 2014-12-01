
package vehiclesurvey.model;


import java.util.zip.DataFormatException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mubarak
 */
public class VehicleTest {
    
    private Vehicle vehicle;
    private double speed = 63.38;
    private double wheelbase = 2.5;
    
    private ReadingPoint firstReading;
    private ReadingPoint secondReading;
    
    @Before
    public void setUp() throws DataFormatException {
        firstReading = new ReadingPoint("A268981");
        secondReading = new ReadingPoint("A269123");
        
        vehicle = new Vehicle(firstReading, secondReading);
    }
    
        
    @Test
    public void getSpeed() {
        System.out.println("Testing getSpeed method");
        assertEquals(speed, vehicle.getSpeed(), 0.1);
    }
    
    @Test
    public void testWheelbase() {
        System.out.println("Making sure the wheelbase is 2.5 m length");
        assertEquals(wheelbase, Vehicle.wheelbase, 0);
    }
    
    @Test
    public void testVehicleSpeed() {
        System.out.println("Testing the vehicle speed class method");
        
        int timeDelay = 1000; // In milli seconds
        
        double vehicleSpeed = Vehicle.vehicleSpeed(timeDelay);
        
        // The speed should be 9 kph
        assertEquals(9, vehicleSpeed, 0);
    }
}
