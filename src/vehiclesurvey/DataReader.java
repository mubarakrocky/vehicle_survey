
package vehiclesurvey;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import vehiclesurvey.model.ReadingPoint;

/**
 *
 * @author mubarak
 */
public class DataReader  {
    private final BufferedReader bufferReader;
    public ArrayList<ReadingPoint> pointList;

    public DataReader(String fileName) throws FileNotFoundException {
        bufferReader = new BufferedReader(new FileReader(fileName));
    }
    
    public void read() {
        try {
            String line;
            pointList = new ArrayList<>();
            while((line = bufferReader.readLine()) != null) {
                try {
                    pointList.add(new ReadingPoint(line));
                } catch (DataFormatException ex) {
                    Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
