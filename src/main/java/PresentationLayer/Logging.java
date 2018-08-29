/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FogException;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Orchi
 */
public class Logging {
    
    /** Made as a singleton - if Configuration.getMyLogger() doesn't have any handlers, we add a ConsoleHandler
     * which writes to System.err and a handler which writes to a log file on the Ubuntu server.
    *
    * @author Orchi
     * @throws FunctionLayer.FogException
    */
    public static void setUp() throws FogException {
        if(Configuration.getMyLogger().getHandlers().length < 1) {
            Configuration.getMyLogger().addHandler(new ConsoleHandler());
            if(Configuration.PRODUCTION) {
                try {
                    FileHandler fileHandler = new FileHandler(Configuration.LOGFILEPATH);
                    fileHandler.setFormatter(new SimpleFormatter());
                    Configuration.getMyLogger().addHandler(fileHandler);
                    fileHandler.close();
                } catch (IOException | SecurityException ex) {
                    throw new FogException(ex.getMessage());
                } 
            }
        }
    }
}
