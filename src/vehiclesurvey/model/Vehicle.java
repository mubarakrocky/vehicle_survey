
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
    private Direction direction;
    

    public Vehicle(ReadingPoint firstReading, ReadingPoint secondReading, Direction vehicleDirection) {
        
        this.firstReading   = firstReading;
        this.secondReading  = secondReading;
        this.direction = vehicleDirection;
        
        // Setting vehicle speed
        this.setSpeed();
    }
    
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
    
    public ReadingPoint getFirstReading() {
        return this.firstReading;
    }
    
    private void setSpeed() {
        
        int timeDelay = secondReading.timeSegment - firstReading.timeSegment;
        
        this.speed = Vehicle.vehicleSpeed(timeDelay);
    }
    
    public String getDirection() {
        return this.direction.getDirectionIdentifier();
    }
    
    public static double vehicleSpeed(int delay) {
        
        return ((wheelbase/1000)/((double)delay/3600000));
    }
}
