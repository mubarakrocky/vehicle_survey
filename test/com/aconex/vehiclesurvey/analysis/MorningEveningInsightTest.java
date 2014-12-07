
package com.aconex.vehiclesurvey.analysis;

import com.aconex.vehiclesurvey.DataReader;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mubarak
 */
public class MorningEveningInsightTest {
   
    
    @Test
    public void testGetCountForDuration() throws FileNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data1.txt" ));
        dataReader.read();
        
        Class[] arg = new Class[] {int.class, int.class, String.class};
         
        MorningEveningInsight insight = new MorningEveningInsight();
        insight.setVehiclesList(dataReader.dayViseVehicles.get("1"));
        
        Method method = insight.getClass().getDeclaredMethod("getCountForDuration", arg);
        method.setAccessible(true);
        assertEquals(15, method.invoke(insight, 0, 1, "SOUTH"));
    }
    
    @Test
    public void testPrint() throws FileNotFoundException {
        DataReader dataReader = new DataReader((System.getProperty( "basedir", "." ) + "/test/sample_data1.txt" ));
        dataReader.read();
        MorningEveningInsight insight = new MorningEveningInsight();
        insight.setVehiclesList(dataReader.dayViseVehicles.get("1"));
        insight.printHeader();
        insight.printResult();
    }
}
