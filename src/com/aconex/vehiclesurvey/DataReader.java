
package com.aconex.vehiclesurvey;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import com.aconex.vehiclesurvey.model.ReadingPoint;
import com.aconex.vehiclesurvey.model.Vehicle;

/**
 *
 * @author mubarak
 */
public class DataReader  {
    private final BufferedReader bufferReader;
    public ArrayList<ReadingPoint> pointList;
    
    public TreeMap<String, ArrayList<Vehicle>> dayViseVehicles;

    public DataReader(String fileName) throws FileNotFoundException {
        bufferReader = new BufferedReader(new FileReader(fileName));
        pointList = new ArrayList<>();
        dayViseVehicles = new TreeMap<>();
    }
    
    public void read() {
        String line;
        
        ReadingPoint readingPoint;
        ReadingPoint lastReadingPoint = null;
        
        
        int day = 0;
        try {
            
            while((line = bufferReader.readLine()) != null) {
                try {
                    // Get the reading object
                    readingPoint = new ReadingPoint(line);
                    if (lastReadingPoint == null) {
                        lastReadingPoint = readingPoint;
                    }
                    
                    // The data is ascending in time order
                    if(lastReadingPoint != null && lastReadingPoint.timeSegment <= readingPoint.timeSegment) {
                        // Adding to the list
                        pointList.add(readingPoint);
                    } else {
                        // The reading is over for one day
                        this.addDayViseVehicles(day++);
                        pointList.add(readingPoint);
                    }
                    
                    // Assigning last reading point
                    lastReadingPoint = readingPoint;
                    
                } catch (DataFormatException ex) {
                    Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // Last line
            if (line == null) {
                if (pointList.size() > 0) {
                    this.addDayViseVehicles(day++);
                }
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void addDayViseVehicles(int day) {
        
        DataProcessor dataProccessor = new DataProcessor(this.pointList);
        // Processing the point list
        dataProccessor.process();
        Integer dayCount = new Integer(day);

        // Map day vise vehicle list
        dayViseVehicles.put(dayCount.toString(), dataProccessor.getVehicleList());

        // Reset the reading point list
        this.pointList.clear();
        
    }
}
