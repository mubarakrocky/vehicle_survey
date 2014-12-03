
package vehiclesurvey.model;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
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
    private Calendar calendar;
    
    
    public ReadingPoint(String value) throws DataFormatException {
        
        if (value.matches("^[A|B][0-9]+$")) {
            this.recordedValue = value;
        } else {
            throw new DataFormatException("The value is not in expected format");
        }
        
        this.splitSensorAndTimeFromRecordedValue();
        
        this.setTimeRecordedAt();
    }
    
    public Calendar getTimeRecordedAt() {
        return calendar;
    }
    
    private void splitSensorAndTimeFromRecordedValue() {
        // Split by first letter
        String[] splitedValue = this.recordedValue.split("^[A|B]");
        timeSegment = Integer.parseInt(splitedValue[1]);

        // Split by digits
        splitedValue = this.recordedValue.split("[0-9]+$");
        sensor = new Sensor(splitedValue[0]);

    }
    
    private Calendar setTimeRecordedAt() {
        Date date = new Date(timeSegment);
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return calendar;
    }
}
