/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Raffiuddin
 */
public class LoggerUtil {
    
    public static Logger getLogger(String className){
        
        
        Logger logger = LogManager.getLogger(className);
        
        return logger;
    }
    
}
