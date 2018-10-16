/*
 * File: TempSensor.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.2.0 10/14/2019 hah - added blower functionality and MissingComponentException
 * Vers: 1.1.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 */

/**
 * Models a Temperature Sensor that sends its reading to a heater controller
 *
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class TempSensor {
    //variables needed for this class

    private static long UIDSource = 10000;
    private long tsUID;
    public double tsTemp;

    public Logger logger;

    /**
     * Constructor for TempSensor object Creates a TempSensor Creates a valid
     * UID and increases the count of TempSensors
     * @param logger
     */
    public TempSensor(Logger logger) {
        tsUID = UIDSource++;
        tsTemp = Double.NaN;
        this.logger = logger;

    }

    /**
     * Get the UID for the sensor
     * @return sensor UID
     */
    public long getUID() {

        return tsUID;
    }

    /**
     * get temperature from Temp Sensor
     * @return Temp. in degrees F
     */
    public double getTemp() {

        return tsTemp;
    }

    /**
     * Set the temp of the sensor
     * @param temperature temperature to set TempSensor to
     */
    public void setTemp(double temperature) {
        this.tsTemp = temperature;
        
        if(this.logger != null){
            this.logger.log(10, "Temperature Sensor Temp is: " + (int)this.tsTemp + " degrees F");
        }else{
            
        }
    }

    /**
     * returns the string “TS:{UID} = {temperature}”
     * @return formatted string
     */
    @Override
    public String toString() {

        return ("TS:" + this.tsUID + " = " + this.tsTemp);
    }

}
