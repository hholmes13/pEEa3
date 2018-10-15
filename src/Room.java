/*
 * File: Room.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 * Vers: 2.0.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 */

/**
 * Model a room with temperature being controlled with the use of a heater
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class Room implements Clockable {

    //Variables used in this class
    public Blower blower;
    public TempSensor tempSensor;

    private double[] disturbance;
    private double roomTemp;
    private int dIndex;
    private final static double HOT_AIR = 95.0;

    /**
     * Room Constructor
     * @param tempDisturbance
     * @param initialTemp
     */
    public Room(double[] tempDisturbance, double initialTemp) {
        disturbance = tempDisturbance;
        dIndex = 0;
        roomTemp = initialTemp;
    }

    /**
     * @return
     */
    public double getTemp() {
        return roomTemp;
    }

    /**
     * add a temperature sensor to a room
     * @param ts
     */
    public void add(TempSensor ts) {
        tempSensor = ts;
    }

    /**
     * add a blower to a room
     * @param blwr
     */
    public void add(Blower blower) {
        this.blower = blower;
    }

    /**
     * Calculates new temp based on a given disturbance and based on whether or
     * not the heater is running
     */
    @Override
    public void preClock() {
        if (blower.heater.getState()) {
            roomTemp = (roomTemp + disturbance[dIndex] + this.blower.outputTemp) / 3.;
        } else {
            roomTemp = (roomTemp + disturbance[dIndex]) / 2.;
        }
        tempSensor.setTemp(roomTemp);
    }

    /**
     * Sets the temperature of the room's sensor to the new temp so that it may
     * be responded to Increases loopCount so that array within this class
     * follows along with disturbance array in main method Sets tOld equal to
     * tNew so that tOld will be ready for the next increment through the
     * disturbance array
     */
    @Override
    public void clock() {
        dIndex++;
        if (dIndex >= disturbance.length) {
            dIndex = 0;
        }

    }

    /**
     * Provide a string for Room which will be stable for unit tests
     * @return
     */
    @Override
    public String toString() {
        return "Room";
    }

}
