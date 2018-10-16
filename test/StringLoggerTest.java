/*
 * File: StringLoggerTest.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.0.0 10/14/2018 hah - initial coding
 */

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class StringLoggerTest {
    
    public StringLoggerTest() {
    }
    
    
    @Before
    public void setUp() {
    }
    


    /**
     * Test of getLog method, of class StringLogger.
     */
    @Test
    public void testGetLog() {
        System.out.println("getLog");
        StringLogger instance = new StringLogger();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getLog();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of log method, of class StringLogger.
     */
    @Test
    public void testLog() {
        System.out.println("log");
        int level = 0;
        String logEntry = "";
        StringLogger instance = new StringLogger();
        instance.log(level, logEntry);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class StringLogger.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        StringLogger instance = new StringLogger();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
