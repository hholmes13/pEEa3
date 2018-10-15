/*
 * File: RoomTest.java
 * Author: David Green DGreen@uab.edu
 * Assignment:  2018-4FallP1toP3 - EE333 Fall 2018
 * Vers: 1.0.0 09/02/2018 dgg - initial coding
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the Room
 * @author David Green DGreen@uab.edu
 */
public class RoomTest {
    
    private Room       room;
    private TempSensor ts1;
    private Heater     h1;
    private Blower     b1;
    
    private double[]   disturb        = {65,   65,    65,    65,   65,    65,    65,   65,    
            65,    65,   65,    65,    65,   65,    73,    73,    73,    73,    73,    65    };
    private boolean[]  heaterState    = {true, false, false, true, false, false, true, false,
            false, true, false, false, true, false, false, false, false, false, false, false };
    private double[]   expectedTemp   = {67.5, 75.8,  70.4,  67.7, 75.9,  70.5,  67.7, 75.9,
            70.5,  67.7, 75.9,  70.5,  67.7, 75.9,  74.5,  73.7,  73.4,  73.2,  73.1,  69.0  };
    
    private static final double INITIAL_TEMP = 70.0;

    public RoomTest() {
    }

    @Before
    public void setUp() {
        room = new Room(disturb, INITIAL_TEMP );
        ts1  = new TempSensor(null);
        h1   = new Heater(null);
        b1 = new Blower(null);
        
        room.add(ts1);
        room.add(b1);
        b1.add(h1);
    }

    /**
     * Test of getTemp method, of class Room.
     */
    @Test
    public void testInitialGetTemp() {
        assertEquals(INITIAL_TEMP, room.getTemp(), .001);
    }

    /**
     * Test of preClock method, of class Room.
     */
    @Test
    public void testRoomDynamics() {
        for ( int i = 0; i < disturb.length; i++) {
            room.preClock();
            room.clock();
            h1.setState(heaterState[i]);
            assertEquals(expectedTemp[i], ts1.getTemp(), .1 );
        }
    }

}