/*
  * File: PrintLogger.java
  * Author: Hunter Holmes hholmes1@uab.edu
  * Assignment:  P2
  * Vers: 1.0.0 09/17/2018 hah - initial coding
  * Vers: 2.0.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 */

/**
 * Model a room with temperature being controlled with the use of a heater
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class PrintLogger extends Logger {

    /**
     * Create a PrintLogger object at DEBUG level
     */
    public PrintLogger() {
        this.threshold = DEBUG;
    }

    /**
     * Create a PrintLogger with specified threshold
     * @param threshold level that has to be met or exceeded for logging to
     * occur
     */
    public PrintLogger(int threshold) {
        this.threshold = threshold;
    }

    /**
     * Prints a string to stdout if level is >= threshold The printing routine
     * will add a newline to the logEntry The logger class defines common values
     * for level
     * @param level value noting the type of the information
     * @param logEntry text to be part of the printed log
     */
    @Override
    public void log(int level, String logEntry) {
        if (level >= threshold) {
            System.out.println(logEntry);
        }
    }
}
