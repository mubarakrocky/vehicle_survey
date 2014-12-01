
package vehiclesurvey;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import vehiclesurvey.model.ReadingPoint;
import vehiclesurvey.model.Vehicle;

/**
 *
 * @author mubarak
 */
public class DataReader  {
    private final BufferedReader bufferReader;
    public ArrayList<ReadingPoint> pointList;
    
    private HashMap<String, ArrayList<Vehicle>> dayViseVehicles;

    public DataReader(String fileName) throws FileNotFoundException {
        bufferReader = new BufferedReader(new FileReader(fileName));
    }
    
    public void read() {
        String line;
        pointList = new ArrayList<>();
        dayViseVehicles = new HashMap<>();
        
        ReadingPoint readingPoint;
        ReadingPoint lastReadingPoint = null;
        DataProcessor dataProccessor = null;
        
        int day = 0;
        try {
            
            while((line = bufferReader.readLine()) != null) {
                try {
                    readingPoint = new ReadingPoint(line);
                    if (lastReadingPoint == null) {
                        lastReadingPoint = readingPoint;
                    }
             
                    if(lastReadingPoint != null && lastReadingPoint.timeSegment <= readingPoint.timeSegment) {
                        
                        pointList.add(readingPoint);
                    } else {
                        dataProccessor = new DataProcessor(pointList);
                        dataProccessor.process();
                        Integer dayCount = new Integer(++day);
                        
                        dayViseVehicles.put(dayCount.toString(), dataProccessor.getVehicleList());
                        
                        // Reset the reading point list
                        pointList.clear();
                        pointList.add(readingPoint);
                    }
                    
                    lastReadingPoint = readingPoint;
                    
                } catch (DataFormatException ex) {
                    Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // Last line
            if (line == null) {
                if (pointList.size() > 0) {
                    
                    dataProccessor = new DataProcessor(pointList);
                    dataProccessor.process();
                    Integer dayCount = new Integer(++day);
                    ArrayList<Vehicle> vehicleList = dataProccessor.getVehicleList();
                      
                    dayViseVehicles.put(dayCount.toString(), dataProccessor.getVehicleList());
                }
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        for (Map.Entry entry : dayViseVehicles.entrySet()) {
            System.out.println(entry.getValue());
            System.out.println(entry.getKey());
        }
    }
}
