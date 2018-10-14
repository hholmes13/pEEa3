/*
 * File: Clock.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 * Vers: 2.0.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 */
import java.util.ArrayList;

/**
 * Model a room with temperature being controlled with the use of a heater
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class Clock {

    
    private ArrayList<Clockable> clockableObjects;
    private long                 clockCount         = 0;
    private Logger               logger;
    
    /**
     * Create a clock with a null logger
     */
    public Clock() {
        setMeta(new NullLogger());
    }
    
    /**
     * Create a clock with a specified logger.
     * 
     * @param logger logger to use.  If null, a NullLogger will be created and used
     */
    public Clock(Logger logger) {
        setMeta(logger);
    }
    
    // Info that all constructors use
    private void setMeta(Logger aLogger) {
        clockableObjects = new ArrayList<>();
        logger = aLogger;
    }
    
    /**
     * Add a clockable object to the list of items to be clocked
     * @param item clockable object
     */
    public void add(Clockable item) {
        if (item != null) {
            clockableObjects.add(item);
        }
    }
    
    /**
     * preClock then clock all items
     */
    public void run() {
           
        clockCount++;
        logger.log(Logger.TIMESTAMP, "--- Clocking to " + clockCount + " seconds.");
        // preClock
        for (Clockable object : clockableObjects) {
            logger.log(Logger.INFO, "Preclocking " + object);
            object.preClock();
        }
        
        // Clock
        for (Clockable object : clockableObjects) {
            logger.log(Logger.INFO, "Clocking " + object);
            object.clock();
        }
    }
    
    /**
     * Run n times
     * @param n number of times to preClock then clock
     */
    public void run(int n) {
        for (int i = 0; i < n; i++) {
            run();
        }
    }
}
