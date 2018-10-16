/*
 * File: ClockTest.java
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
 * Inner class to use as a Clockable object
 * @author David Green DGreen@uab.edu
 */
class Item implements Clockable {

    private int preClockCount = 0;
    private int clockCount = 0;

    @Override
    public void preClock() {
        preClockCount++;
    }

    /**
     * If clock is not exactly 1 behind preClock then mess things up
     */
    @Override
    public void clock() {
        if (preClockCount == clockCount + 1) {
            clockCount++;
        } else {
            clockCount = -999999999;
        }
    }

    /**
     * Get the number of preClock events
     * @return number of preClock events
     */
    public int getPreClockCount() {
        return preClockCount;
    }

    /**
     * Get the number of clock events (or a weird number if things failed)
     * @return number of clock events
     */
    public int getClockCount() {
        return clockCount;
    }

}

/**
 * Test Clock
 * @author David G. Green DGreen@uab.edu
 */
public class ClockTest {

    private Item i1;
    private Item i2;
    private Item i3;
    private Clock c;

    public ClockTest() {
    }

    @Before
    public void setUp() {
        i1 = new Item();
        i2 = new Item();
        i3 = new Item();
        c = new Clock();
        c.add(i1);
        c.add(i2);

        // note i3 not clocked
    }

    /**
     * Test of run method, of class Clock.
     */
    @Test
    public void testRun() throws MissingComponentException {
        c.run();
        assertEquals(1, i1.getPreClockCount());
        assertEquals(1, i1.getClockCount());
        assertEquals(1, i2.getPreClockCount());
        assertEquals(1, i2.getClockCount());
        assertEquals(0, i3.getPreClockCount());
        assertEquals(0, i3.getClockCount());
    }

    /**
     * Test of run method, of class Clock.
     */
    @Test
    public void testRun_int()throws MissingComponentException {
        c.run(100);
        assertEquals(100, i1.getPreClockCount());
        assertEquals(100, i1.getClockCount());
        assertEquals(100, i2.getPreClockCount());
        assertEquals(100, i2.getClockCount());
        assertEquals(0, i3.getPreClockCount());
        assertEquals(0, i3.getClockCount());
    }

}
