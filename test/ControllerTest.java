/*
 * File: ControllerTest.java
 * Author: David Green DGreen@uab.edu
 * Assignment:  2018-4FallP1toP3 - EE333 Fall 2018
 * Vers: 1.0.0 09/02/2018 dgg - initial coding
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the Controller operation (without logging)
 *
 * @author David Green DGreen@uab.edu
 */
public class ControllerTest {

    private Heater h1;
    private Blower b1;
    private TempSensor ts1;
    private Controller controller;

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
        expectedValue = "Controller with no temperature sensor and " + b1.toString() + " and " +  h1.toString();
        assertEquals(expectedValue, controller.toString());

        controller.connect(ts1);
        expectedValue = "Controller with " + ts1.toString() + " and " +  b1.toString() + " and " +  h1.toString();
        assertEquals(expectedValue, controller.toString());
    }

    /**
     * Test of clock method, of class Controller.
     */
    @Test
    public void testOperation()throws MissingComponentException {

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
     * Test connection between Heater and Blower, to see that they come and go together
     */
    @Test
    public void testHeaterBlowerRelationship()throws MissingComponentException {

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

}
