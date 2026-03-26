/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.component.util;

/**
 *
 * @author Raffiuddin
 */
public class Logger {
    
    private static org.apache.log4j.Logger log;
    
    public static org.apache.log4j.Logger getLogger(Class<?> class1){
       
        log = org.apache.log4j.Logger.getLogger(class1);
        return log;
        
    }
    
    public void info(String msg){
        log.info(msg);
    }
    public void info(String msg, Object e){
        log.info(msg, (Throwable) e);
    }
    public void info(Object e){
        log.info(e);
    }
    public void warn(String msg){
        log.warn(msg);
    }
    public void warn(String msg, Object e){
        log.warn(msg, (Throwable) e);
    }
    public void warn(Object e){
        log.warn(e);
    }
    public void error(String msg){
        log.error(msg);
    }
    public void error(String msg, Object e){
        log.error(msg, (Throwable) e);
    }
    public void error(Object e){
        log.error(e);
    }
    public void debug(String msg){
        log.debug(msg);
    }
    public void debug(String msg, Object e){
        log.debug(msg, (Throwable) e);
    }
    public void debug(Object e){
        log.debug(e);
    }
    public void trace(String msg){
        log.trace(msg);
    }
    public void trace(String msg, Object e){
        log.trace(msg, (Throwable) e);
    }
    public void trace(Object e){
        log.trace(e);
    }
}
