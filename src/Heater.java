/*
 * File: Heater.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.2.0 10/14/2019 hah - added blower functionality and MissingComponentException
 * Vers: 1.1.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 */

/**
 * Model a heater whose state is changed by a controller
 *
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class Heater {

    // Variables used in this class
    private static long hUIDSource = 20000;

    private long hUID;
    public boolean state;
    public String status;

    public Logger logger;

    /**
     * Constructor for a heater Increases count of heaters and creates a unique
     * ID for a new heater
     * @param logger
     */
    public Heater(Logger logger) {
        this.hUID = hUIDSource++;
        this.state = false;
        this.status = "OFF";
        this.logger = logger;
    }

    /**
     * passes up the unique ID for a particular heater object
     *
     * @return UID
     */
    public long getUID() {
        return hUID;
    }

    /**
     * sets state for the heater when prompted to change converts true/false to
     * ON/OFF for ease of displaying status
     *
     * @param state desired state of heater
     */
    public void setState(boolean state) {

        if (state == true) {
            this.status = "ON";
            this.state = true;
            
            if(this.logger != null){
                this.logger.log(Logger.INFO, "Heater is switched ON"); 
            }
            
        } else {
            this.status = "OFF";
            this.state = false;
            
            if(this.logger != null){
                this.logger.log(Logger.INFO, "Heater is switched OFF");
            }
        }
    }

    /**
     * returns the heater state as a boolean true or false true = ON, false =
     * OFF
     *
     * @return state
     */
    public boolean getState() {
        return state;
    }

    /**
     * converts true/false to ON/OFF for ease of displaying status returns the
     * string “Heater:{UID} = {status}”
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
        return ("Heater:" + this.hUID + " = " + this.status);
    }

}
