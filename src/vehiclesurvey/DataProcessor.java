package vehiclesurvey;

import java.util.ArrayList;
import java.util.ListIterator;
import vehiclesurvey.model.Direction;
import vehiclesurvey.model.ReadingPoint;
import vehiclesurvey.model.Vehicle;

/**
 *
 * @author mubarak
 */
public class DataProcessor {

    private ArrayList<ReadingPoint> pointList;
    private boolean hasNext = true;
    private ArrayList<Vehicle> vehicleList = new ArrayList<>();

    public DataProcessor(ArrayList points) {
        pointList = points;
    }

    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void process() {

        ListIterator<ReadingPoint> iterator = pointList.listIterator();

        while (this.hasNext) {
            Vehicle vehicle = this.getVehicleWithDirection(iterator, "A");
            if (vehicle != null) {
                this.vehicleList.add(vehicle);
            }
        }
    }
    
    private Vehicle getVehicleWithDirection(ListIterator<ReadingPoint> readingPointIterator, String sensorName) {
        boolean loopDecider = true;
        Vehicle vehicle = null;
        Direction vehicleDirection = new Direction("NORTH");
        
        ReadingPoint frontWheelReading = null;
        ReadingPoint backWheelReading = null;
        while (loopDecider) {
            
            if (readingPointIterator.hasNext()) {
                ReadingPoint nextReading = readingPointIterator.next();

                if (frontWheelReading == null && sensorName.equals(nextReading.sensor.name)) {
                    frontWheelReading = nextReading;
                    continue;
                } else if (backWheelReading == null && sensorName.equals(nextReading.sensor.name)) {
                    backWheelReading = nextReading;
                    loopDecider = false;
                } else if (frontWheelReading != null) {
                    //Found opposite direction
                    vehicleDirection = new Direction("SOUTH");
                    continue;
                } else {
                    continue;
                }
                this.hasNext = true;
            } else {
                this.hasNext = false;
                loopDecider = false;
            }

            if (frontWheelReading != null && backWheelReading != null) {
                return new Vehicle(frontWheelReading, backWheelReading, vehicleDirection);
            }
        }
        return vehicle;
    }
}
