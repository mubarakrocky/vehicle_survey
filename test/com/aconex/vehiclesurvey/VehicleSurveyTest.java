
package com.aconex.vehiclesurvey;


import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mubarak
 */
public class VehicleSurveyTest {
    
    
    
    @Test
    public void testMainmethod() {
        // The main method should take one params
        VehicleSurvey.main(new String[]{"Test"});
        
        // Unless no exception found
        assertTrue(true);
    }
}
