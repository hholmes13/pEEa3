/*
 * File: Controller.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.2.0 10/14/2019 hah - added blower functionality and MissingComponentException
 * Vers: 1.1.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 */

/**
 * Model a controller that controls heater and blower based on readings from
 * temperature sensor in a room
 *
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class Controller implements Clockable {

    private TempSensor   tempSensor;         // input to controller
    
    private Heater       heater;             // heat output of controller
    private boolean      presentHeatState;   // present command to heater
    
    private Blower       blower;             // blower output of controller
    private boolean      presentBlowerState; // present command to blower
    
    // Configuration 
    private final double LOW_HEAT_TEMP     = 68.0;
    private final double HIGH_HEAT_TEMP    = 71.0;

    private Logger       logger;             // logger 
    
    // Constructors

    /**
     * Create a controller
     * @param logger
     */
    public Controller(Logger logger) {
        this.logger = (logger != null) ? logger : new NullLogger();
        presentHeatState = false;
        presentBlowerState =false;
    }
    
    // Queries

    /**
     * Provide the string “Controller with TS:{UID} = {temperature} and Heater:{UID} = {state}”
     * example: <code>Contoller with TS:10000 = 75.0 and Heater:20000 = ON</code>
     * Use "no xyz" if there is no corresponding object
     * @return formatted string
     */
    @Override
    public String toString() {
        String tempSensorString = (tempSensor == null ) ? "no temperature sensor"
                                                        : tempSensor.toString();
        String heaterString     = (heater == null )     ? "no heater"
                                                        : heater.toString();
        String blowerString     = (blower == null)      ? "no blower"
                                                        : blower.toString();
        
        return "Controller with " + tempSensorString + " and " + blowerString + " and " + heaterString;
    }

    // Commands

    /**
     * Connect the temperature sensor to the controller.  
     * Only one connection at a time is possible.  The method will
     * silently overwrite on multiple requests.
     *     
     * @param  ts temperature sensor
     */
    public void connect(TempSensor ts) {
        this.tempSensor = ts;
        logger.log(Logger.INFO, "Connect Temperature Sensor " + ts);
    }

    /**
     * Connect the heater to the controller.  
     * Only one connection at a time is possible.  The method will
     * silently overwrite on multiple requests.
     *     
     * @param  heater heater to connect
     */
    public void connect(Heater heater) {
        this.heater = heater;
        this.heater.setState(false);
        logger.log(Logger.INFO, "Connect Heater " + heater);
    }
    
    /**
     * Connect blower to the controller
     * Only one connection at a time is possible
     * @param blower 
     */
    public void connect(Blower blower){
        this.blower = blower;
        this.blower.setState(false);
        logger.log(Logger.INFO,"Connect Blower " + blower);
    }

    /**
     * Do actions before clock
     */
    @Override
    public void preClock() throws MissingComponentException {
        if (blower == null){
            logger.log(Logger.ERROR,"Error: No Blower Connected");
            throw new MissingComponentException("Error: No Blower Connected");
        }else if(heater == null){
            logger.log(Logger.ERROR,"Error: No Heater Connected");
            throw new MissingComponentException("Error: No Heater Connected");
        }else if(tempSensor == null){
            logger.log(Logger.ERROR,"Error: No Temperature Sensor Connected");
            throw new MissingComponentException("Error: No Temperature Sensor Connected");
        }else{
            //Do nothing
        }
    }
    
    /**
     * Do one pass of the controller (read the temperature, determine 
     * whether to turn heater and blower on or off, and then do it)
     */
    @Override
    public void clock() {
        double temp = tempSensor.getTemp();
        
        boolean hs;
        boolean bs;
        
        if((temp < LOW_HEAT_TEMP) ||
                    ( presentHeatState && !(temp > HIGH_HEAT_TEMP) )){
            hs = true;
            bs = true;
        }else{
            hs = false;
            bs = false;
        }
        
        heater.setState(hs);
        blower.setState(bs);
        presentHeatState = hs;
        presentBlowerState = bs;
        logger.log(Logger.INFO, "Temperature is " + temp + ", set heater to " + hs + " and blower to " + bs);
    }

}
