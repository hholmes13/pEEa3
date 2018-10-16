/*
 * File: Clockable.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.2.0 10/14/2019 hah - added blower functionality and MissingComponentException
 * Vers: 1.1.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 */

/**
 * Interface that contracts properties of clockable items so that they
 * may be clocked and pre-clocked
 * @author hholmes
 */
public interface Clockable {
    
    /**
     * Take actions based on notifications that the clock is about to happen.
     * The controller's latest actions were issued on second ago.
     * Generally used to compute dynamics before letting new control decisions occur
     * @throws MissingComponentException
     */
    public void preClock() throws MissingComponentException;
    
    /**
     * Take actions for new second
     * Generally used to allow the controller to make new decisions
     */
    public void clock();
}
