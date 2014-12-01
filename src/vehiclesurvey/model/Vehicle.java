
package vehiclesurvey.model;

/**
 * Class declaration for vehicle
 * 
 * @author mubarak
 */
public class Vehicle {
    
    public static double wheelbase = 2.5;
    
    private double speed;
    private ReadingPoint firstReading;
    private ReadingPoint secondReading;

    public Vehicle(ReadingPoint firstReading, ReadingPoint secondReading) {
        
        this.firstReading   = firstReading;
        this.secondReading  = secondReading;
        
        // Setting vehicle speed
        this.setSpeed();
    }
        
    // Get the vehicle speed
    public double getSpeed() {
        return speed;
    }
    
    private void setSpeed() {
        
        int timeDelay = secondReading.timeSegment - firstReading.timeSegment;
        
        this.speed = Vehicle.vehicleSpeed(timeDelay);
    }
    
    public static double vehicleSpeed(int delay) {
        
        return ((wheelbase/1000)/((double)delay/3600000));
    }
}