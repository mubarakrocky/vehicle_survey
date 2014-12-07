
package com.aconex.vehiclesurvey.model;

/**
 * Class for mapping sensors
 * 
 * @author mubarak
 */
public class Sensor {
    
    public String name;

    public Sensor(String name) {
        this.name = name;
    }
    
    // Returning the sensor name
    public String getName() {
        return this.name;
    }
}
