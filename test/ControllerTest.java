/*
 * File: ControllerTest.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.2.0 10/14/2019 hah - added blower functionality and MissingComponentException
 * Vers: 1.1.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the Controller operation (without logging)
 * @author David Green DGreen@uab.edu
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class ControllerTest {

    private Heater h1;
    private Blower b1;
    private TempSensor ts1;
    private Controller controller;
    private String exceptionString;

    public ControllerTest() {
    }

    @Before
    public void setUp() {
        h1 = new Heater(null);
        b1 = new Blower(null);
        ts1 = new TempSensor(null);
        controller = new Controller(null);
    }

    /**
     * Test of toString method, of class Controller.
     */
    @Test
    public void testToString() {
        String expectedValue;

        expectedValue = "Controller with no temperature sensor and no blower and no heater";
        assertEquals(expectedValue, controller.toString());

        controller.connect(h1);
        expectedValue = "Controller with no temperature sensor and no blower and " + h1.toString();
        assertEquals(expectedValue, controller.toString());

        controller.connect(b1);
        expectedValue = "Controller with no temperature sensor and " + b1.toString() + " and " + h1.toString();
        assertEquals(expectedValue, controller.toString());

        controller.connect(ts1);
        expectedValue = "Controller with " + ts1.toString() + " and " + b1.toString() + " and " + h1.toString();
        assertEquals(expectedValue, controller.toString());
    }

    /**
     * Test of clock method, of class Controller.
     */
    @Test
    public void testOperation() throws MissingComponentException {

        double[] temps = {65.0, 67.0, 68.0, 70.0, 71.0, 72.0, 71.0, 68.0, 67.0};
        boolean[] expected = {true, true, true, true, true, false, false, false, true};

        controller.connect(h1);
        controller.connect(b1);
        b1.add(h1);
        controller.connect(ts1);

        for (int i = 0; i < temps.length; i++) {
            ts1.setTemp(temps[i]);
            controller.preClock();
            controller.clock();
            assertEquals(expected[i], h1.getState());
        }
    }

    /**
     * Test connection between Heater and Blower, to see that they come and go
     * together
     */
    @Test
    public void testHeaterBlowerRelationship() throws MissingComponentException {

        double[] temps = {65.0, 67.0, 68.0, 70.0, 71.0, 72.0, 71.0, 68.0, 67.0};
        boolean[] expected = {true, true, true, true, true, false, false, false, true};

        controller.connect(h1);
        controller.connect(b1);
        b1.add(h1);
        controller.connect(ts1);

        for (int i = 0; i < temps.length; i++) {
            ts1.setTemp(temps[i]);
            controller.preClock();
            controller.clock();
            assertEquals(expected[i], (b1.getState() && h1.getState()));
        }
    }
    
     /**
     * Test MissingComponentException in the case of no missing components
     * @throws MissingComponentException
     */
    @Test
    public void testNoMissingComponents() {

        controller.connect(h1);
        controller.connect(b1);
        b1.add(h1);
        controller.connect(ts1);

        try {
            controller.preClock();
        } catch (MissingComponentException e) {
            exceptionString = e.getMessage();
        }

        assertEquals(null, exceptionString);
    }

    /**
     * Test MissingComponentException in the case of missing heater
     * @throws MissingComponentException
     */
    @Test
    public void testMissingHeater() {

        //controller.connect(h1);
        controller.connect(b1);
        //b1.add(h1);
        controller.connect(ts1);

        try {
            controller.preClock();
        } catch (MissingComponentException e) {
            exceptionString = e.getMessage();
        }

        assertEquals("Error: No Heater Connected", exceptionString);
    }

    /**
     * Test MissingComponentException in the case of missing blower
     * @throws MissingComponentException
     */
    @Test
    public void testMissingBlower() {

        controller.connect(h1);
        //controller.connect(b1);
        //b1.add(h1);
        controller.connect(ts1);

        try {
            controller.preClock();
        } catch (MissingComponentException e) {
            exceptionString = e.getMessage();
        }

        assertEquals("Error: No Blower Connected", exceptionString);
    }

    /**
     * Test MissingComponentException in the case of missing Temp Sensor
     * @throws MissingComponentException
     */
    @Test
    public void testMissingTempSensor() {

        controller.connect(h1);
        controller.connect(b1);
        b1.add(h1);
        //controller.connect(ts1);

        try {
            controller.preClock();
        } catch (MissingComponentException e) {
            exceptionString = e.getMessage();
        }

        assertEquals("Error: No Temperature Sensor Connected", exceptionString);
    }

}
