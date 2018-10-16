/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
