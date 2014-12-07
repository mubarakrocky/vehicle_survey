
package com.aconex.vehiclesurvey;

import com.aconex.vehiclesurvey.analysis.Result;
import java.io.FileNotFoundException;

/**
 * The main class of Aconex coding challenge
 *
 * @author mubarak
 */
public class VehicleSurvey {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length == 1) {
            try {
                DataReader dataReader = new DataReader(args[0]);
                // Read the file
                dataReader.read();
                
                // Print the analysis results
                Result analysisResult = new Result(dataReader.dayViseVehicles);
                analysisResult.print();
                
            } catch (FileNotFoundException ex) {
                System.out.println("File not found. Please specify a real file path");
            }
        } else {
            System.out.println("To run this program please pass test file name as argument");
            System.out.println("java -jar vehiclesurvey.jar test.txt");
        }
    }
}
