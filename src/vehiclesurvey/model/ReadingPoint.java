
package vehiclesurvey.model;

import java.sql.Time;
import java.util.zip.DataFormatException;

/**
 *
 * @author mubarak
 */
public class ReadingPoint {
    
    private String recordedValue;
    private Time timeRecordedAt;
    
    public Sensor sensor;
    public int timeSegment;
    
    
    public ReadingPoint(String value) throws DataFormatException {
        
        if (value.matches("^[A|B][0-9]+$")) {
            this.recordedValue = value;
        } else {
            throw new DataFormatException("The value is not in expected format");
        }
        
        this.splitSensorAndTimeFromRecordedValue();
        
        this.setTimeRecordedAt();
    }
    
    public Time getTimeRecordedAt() {
        return timeRecordedAt;
    }
    
    private void splitSensorAndTimeFromRecordedValue() {
        // Split by first letter
        String[] splitedValue = this.recordedValue.split("^[A|B]");
        timeSegment = Integer.parseInt(splitedValue[1]);

        // Split by digits
        splitedValue = this.recordedValue.split("[0-9]+$");
        sensor = new Sensor(splitedValue[0]);

    }
    
    private void setTimeRecordedAt() {
        timeRecordedAt = new Time(timeSegment);
    }
}
