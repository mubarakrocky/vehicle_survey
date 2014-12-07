
package com.aconex.vehiclesurvey.model;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.zip.DataFormatException;
import static org.junit.Assert.*;
import org.junit.Test;

public class ReadingPointTest {
    
    @Test
    public void testReadingPoint() throws DataFormatException {
        
        // The class should accept value as B1089951. Start with alphabet and remaining digits
        try {
            // Should raise DataFormatException
            ReadingPoint readingPoint = new ReadingPoint("AA22345567");
            fail("The expected exception is not triggered");
        } catch(DataFormatException ex) {
            System.out.println("Exception triggered for wrong value");
        }
        
        // Should not raise exception if correct format is supplied
        ReadingPoint readingPoint = new ReadingPoint("A22345567");
        
    }
    
    @Test
    public void splitSensorAndTimeFromRecordedValue() {
        int timeSincemidnight = 0;
        String sensorName = "";
        try{
            ReadingPoint readingPoint = new ReadingPoint("A22345568");
            Class readingPointClass = Class.forName("vehiclesurvey.model.ReadingPoint");
            
            Method method = readingPointClass.getDeclaredMethod("splitSensorAndTimeFromRecordedValue");
            method.setAccessible(true);
            method.invoke(readingPoint);
            
            timeSincemidnight = readingPoint.timeSegment;
            sensorName  = readingPoint.sensor.name;
            
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        // The time part should be 22345568
        assertEquals(22345568, timeSincemidnight);
        
        // The sensor name should be A
        assertEquals("A", sensorName);
    }
    
    @Test
    public void getTimeRecordedAt() {
        Calendar expectedTime = null;
        
        try{
            ReadingPoint readingPoint = new ReadingPoint("A268981");
            expectedTime = readingPoint.getTimeRecordedAt();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    
        assertEquals(0, expectedTime.get(Calendar.HOUR));
    }
    
    @Test
    public void setTimeRecordedAt() {
        try{
            ReadingPoint readingPoint = new ReadingPoint("A0");
            Class readingPointClass = Class.forName("vehiclesurvey.model.ReadingPoint");
            
            Method method = readingPointClass.getDeclaredMethod("setTimeRecordedAt");
            method.setAccessible(true);
            method.invoke(readingPoint);
            
            assertEquals(0, readingPoint.getTimeRecordedAt().get(Calendar.HOUR));
            
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            fail("Exception occured");
        }
    }
}
