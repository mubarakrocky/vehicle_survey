/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesurvey.analysis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import vehiclesurvey.model.Vehicle;

/**
 *
 * @author mubarak
 */
public class MorningEveningInsight extends Insight {
    
    ArrayList<Vehicle> vehicleList;
    
    @Override
    public void printHeader() {
        System.out.println("Vehicle Count For Morning (4AM to 10AM) vs Evening (3PM to 8PM)");
    }

    @Override
    public void printResult() {
        System.out.println("");
        System.out.println("---------Morning VS Evening------------");
        System.out.println("\t|\tSOUTH\t|\tNORTH");
        System.out.println("Morning\t|\t"+getCountForDuration(4, 10, "SOUTH")+"\t|\t"+getCountForDuration(4, 10, "NORTH"));
        System.out.println("Evening\t|\t"+getCountForDuration(15, 20, "SOUTH")+"\t|\t"+getCountForDuration(15, 20, "NORTH"));
        System.out.println("");
    }

    @Override
    public void setVehiclesList(ArrayList<Vehicle> vehicles) {
        this.vehicleList = vehicles;
    }
    
    private int getCountForDuration(int startHour, int endHour, String direction) {
        Iterator<Vehicle> iterator = this.vehicleList.iterator();
        int counter = 0;
        while(iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            int hourRecordedAt = vehicle.getFirstReading().getTimeRecordedAt().get(Calendar.HOUR_OF_DAY);
            if(hourRecordedAt >= startHour && hourRecordedAt <= endHour && vehicle.getDirection().equals(direction)) {
                counter++;
            }
        }
        return counter;
    }
}
