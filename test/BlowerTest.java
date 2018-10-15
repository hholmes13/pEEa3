/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hholmes
 */
public class BlowerTest {

    private Blower b1;
    private Blower b2;

    private Heater h1;

    public BlowerTest() {
    }

    @Before
    public void setUp() {
        b1 = new Blower(null);
        b2 = new Blower(null);

        h1 = new Heater(null);

        b1.add(h1);
    }

    /**
     * Test of getUID method, of class Blower.
     */
    @Test
    public void testGetUID() {
        assertTrue(b1.getUID() + 1 == b2.getUID());
        assertTrue(b1.getUID() >= 30000);
    }

    /**
     * Test of Blower Operation
     */
    @Test
    public void testBlowerOperation() {
        assertFalse(b1.getState());
        assertFalse(h1.getState());
        
        h1.setState(true);
        b1.setState(true);
        assertTrue(b1.getState());
        
        h1.setState(false);
        b1.setState(true);
        assertFalse(b1.getState());
        
        h1.setState(true);
        b1.setState(false);
        assertFalse(b1.getState());
        
        h1.setState(false);
        b1.setState(false);
        assertFalse(b1.getState());

        String b1Prefix = "Blower:" + b1.getUID();

        h1.setState(true);
        b1.setState(true);
        assertTrue(b1.getState());
        assertEquals(b1Prefix + " = ON", b1.toString());

        h1.setState(false);
        b1.setState(false);
        
        assertFalse(b1.getState());
        assertEquals(b1Prefix + " = OFF", b1.toString());
        
    }

}
