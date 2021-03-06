/*
 * File: Blower.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.2.0 10/14/2019 hah - added blower functionality and MissingComponentException
 * Vers: 1.1.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 */

/**
 * Model a blower to move hot air from the heater to the room
 *
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class Blower {

    // Variables used in this class
    private static long bUIDSource = 30000;

    private long bUID;
    private boolean state;
    public String status;

    public Logger logger;
    public Heater heater;
    
    public double outputTemp;
    
    

    /**
     * Default Constructor for Blower
     */
    public Blower() {

    }

    /**
     * Creates blower with a logger
     * @param logger
     */
    public Blower(Logger logger) {
        this.bUID = bUIDSource++;
        this.state = false;
        this.status = "OFF";
        this.logger = logger;
    }
    
    public void add(Heater heater){
        this.heater = heater;
    }

    /**
     * passes up the unique ID for a particular blower object
     *
     * @return UID
     */
    public long getUID() {
        return bUID;
    }

    /**
     * Allows  controller to set current blower state
     * @param state desired state of blower
     */
    public void setState(boolean state) {

        if (state == true && heater.state == true) {
            this.status = "ON";
            this.state = true;
            this.outputTemp = 95.0;

            if (this.logger != null) {
                this.logger.log(Logger.INFO, "Blower is switched ON");
            }

        } else {
            this.status = "OFF";
            this.state = false;

            if (this.logger != null) {
                this.logger.log(Logger.INFO, "Blower is switched OFF");
            }
        }
    }

    /**
     * Returns current state of blower
     * @return state
     */
    public boolean getState() {
        return state;
    }

    /**
     * converts true/false to ON/OFF for ease of displaying status returns the
     * string “Blower:{UID} = {status}”
     * @return formatted string
     */
    @Override
    public String toString() {
        if (state == true) {
            this.status = "ON";
            this.state = true;
        } else {
            this.status = "OFF";
            this.state = false;
        }
        return ("Blower:" + this.bUID + " = " + this.status);
    }

}
