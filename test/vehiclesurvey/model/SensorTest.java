/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclesurvey.model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mubarak
 */
public class SensorTest {
    
   
     @Test
     public void Sensor() {
         Sensor sensor = new Sensor("A");
         
         // The sensor name should be A
         assertSame("A", sensor.getName());
     }
}
