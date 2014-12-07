
package com.aconex.vehiclesurvey.model;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mubarak
 */
public class DirectionTest {
   
   @Test
   public void testDirectionIdentifier() {
        Direction direction = new Direction("NORTH");
        // The getDirection should return NORTH
        assertEquals("NORTH", direction.getDirectionIdentifier());
   }
}
