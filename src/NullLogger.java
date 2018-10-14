/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hholmes
 */
public class NullLogger extends Logger {
    
    /**
     * Ignore request to log
     * @param level     unused
     * @param logEntry  unused
     */
    @Override
    public void log(int level, String logEntry) {
    }
}
