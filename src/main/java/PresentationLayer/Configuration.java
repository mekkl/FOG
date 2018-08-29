/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import java.util.logging.Logger;

/** specifies the path to a log file on the Ubuntu server for logging. It also hold the Logger which we add handlers to.
 *
 * @author Orchi
 */
public class Configuration {
    public static final boolean PRODUCTION = true;
    public static final String LOGFILEPATH = "/var/log/tomcat8/foglogs/loginlog.log"; // PATH TO UBUNTU!!!
    private static Logger myLogger = Logger.getLogger("");
    
    public static Logger getMyLogger() {
        return myLogger;
    }
}
