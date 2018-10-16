/*
 * File: TestP2.java
 * Author: David Green DGreen@uab.edu
 * Assignment:  Fall2018P1toP3 - EE333 Fall 2018
 * Vers: 1.2.0 09/25/2018 dgg - use Clock object
 * Vers: 1.1.0 09/02/2018 dgg - converted to TestP2 for P2
 * Vers: 1.0.0 08/18/2018 dgg - initial coding
 */

/**
 * Test program for P3
 * @author David Green DGreen@uab.edu
 */
public class TestP3 {

    public static void main(String[] args) throws MissingComponentException {

        Heater h1;
        Blower b1;
        TempSensor ts1;
        Controller controller;
        Logger logger;
        Clock clock;

        print("P3 Test program by David Green dgreen@uab.edu");
        print("Testing P3 by Hunter Holmes hholmes1@uab.edu ");

        logger = new PrintLogger(20);
        h1 = new Heater(logger);
        b1 = new Blower(logger);
        ts1 = new TempSensor(logger);
        controller = new Controller(logger);
        clock = new Clock(logger);

        // Wire up controller
        controller.connect(ts1);    // connect temperature sensor
        controller.connect(h1);     // connect heater
        controller.connect(b1);     // connect blower

        clock.add(controller);

        // check room dynamics with controller
        h1.setState(false);

        Room room;
        double[] disturb = {65, 65, 65, 65, 65, 65, 65, 65,
            65, 65, 65, 65, 65, 65, 73, 73, 73, 73, 73, 65};
        boolean[] predictedState = {true, false, false, true, false, false, true, false,
            false, true, false, false, true, false, false, false, false, false, false, false};
        double[] predictedTemp = {67.5, 75.8, 70.4, 67.7, 75.9, 70.5, 67.7, 75.9,
            70.5, 67.7, 75.9, 70.5, 67.7, 75.9, 74.5, 73.7, 73.4, 73.2, 73.1, 69.0};

        room = new Room(disturb, 70.);
        room.add(b1);
        b1.add(h1);
        room.add(ts1);
        clock.add(room);

        for (int i = 0; i < disturb.length; i++) {
            clock.run();

            passFail((h1.getState() == predictedState[i]), "room State " + i);
            passFail(isClose(ts1.getTemp(), predictedTemp[i]),
                    "room Temp " + i);
        }

    }

    public static boolean isClose(double a, double b) {
        return (Math.abs(a - b) < .1);
    }

    /**
     * Print message if the boolean result is true
     * @param result
     * @param message
     */
    public static void passFail(boolean result, String message) {
        if (result) {
            print("Passes: " + message);
        } else {
            print("Fails:  " + message);
        }
    }

    /**
     * Output string to stdout
     * @param s
     */
    public static void print(String s) {
        System.out.println(s);
    }
}
