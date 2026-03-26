/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author raffiuddin
 */
public class Config {
    
    static org.apache.logging.log4j.Logger log = LogManager.getLogger(Config.class);
    private static Config configIns;
    public static Config getInstancce(){
        if(configIns!=null)
            return configIns;
        else
            configIns = new Config();
        
        return configIns;
    
    }
    Properties config;
    private InputStream ip=null;
    public Config() {
        config = new Properties();
        try {
            final String property = System.getProperty("configFile");
            //URL url = getClass().getResource("config.properties");
            System.out.println(property);
           
            if(null != property) {
                ip = new FileInputStream(property);
                log.info("loading from business_Report "+ip);
                System.out.println("loading from business_Report "+ip);
            }
            if(ip==null)
            {
                ip = getClass().getClassLoader().getResourceAsStream("config/config.properties");
                System.out.println("loaded from config");
                log.info("loaded from config "+ip);
            }
            if(ip!=null){
                config.load(ip);
            }else{
                System.out.println("ip is "+ip);
                log.info("ip is "+ip);
            }
            //config.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
            
        } catch (IOException e) {
            e.printStackTrace();
            log.info(e);
            if(ip==null)
            {
                ip = getClass().getClassLoader().getResourceAsStream("config/config.properties");
                System.out.println("loading from main");
            }else
                System.out.println("loaded from lib");
            if(ip!=null){
                try {
                    config.load(ip);
                } catch (IOException ex) {
                    Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                System.out.println("ip is "+ip);
            }
        }
        finally{
            if(ip!=null)
                try {
                ip.close();
            } catch (IOException ex) {
                log.info(ex);
            }
        }
        
    }
    public String getProperty(String key){
        String value = this.config.getProperty(key);
        return value;
    }
    public static void main(String ar[]){
//        System.setProperty("log4configFilej2.configurationFile", "log4j2.xml");
//        System.setProperty("configFile", "Business_Report/config.properties");
        System.out.print(new Config().config.getProperty("backup"));
    }
}
