/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.ui;


/**
 *
 * @author USER3
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
 
public class WindowsReqistry {

    public WindowsReqistry() {
        
          String reg=null,ID=null,D2E = null;
       Date dr = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String dt = df.format(dr);
        
         reg = WindowsReqistry.readRegistry("HKLM\\SOFTWARE\\SITA", "reg");
         ID = WindowsReqistry.readRegistry("HKLM\\SOFTWARE\\SITA", "ID");
         D2E = WindowsReqistry.readRegistry("HKLM\\SOFTWARE\\SITA", "D2E");
         
//        if(reg == null || Integer.parseInt(D2E)<=30){
//            reg = WindowsReqistry.addRegistry("HKLM\\SOFTWARE\\SITA", "Reg", "1");
//            ID = WindowsReqistry.addRegistry("HKLM\\SOFTWARE\\SITA", "ID", dt.replaceAll("/", ""));
////            int day =Integer.parseInt(D2E)+356;
//            D2E = WindowsReqistry.addRegistry("HKLM\\SOFTWARE\\SITA", "D2E","365" );
//            if(WindowsReqistry.readRegistry("HKLM\\SOFTWARE\\SITA", "reg").equals("1")){
//                JOptionPane.showMessageDialog(null, "Registration successful");
//            }else{
//                
//            }
//            
//        } else{
//            JOptionPane.showMessageDialog(null, "Already registered");
//        }
//        if(reg.equals("0")){
//            reg = WindowsReqistry.editRegistry("HKLM\\SOFTWARE\\SITA", "Reg", "1");
//            ID = WindowsReqistry.editRegistry("HKLM\\SOFTWARE\\SITA", "ID", dt.replaceAll("/", ""));
////            int day =Integer.parseInt(D2E)+356;
//            D2E = WindowsReqistry.editRegistry("HKLM\\SOFTWARE\\SITA", "D2E","365" );
//        }
         
        System.out.println("Reg : "+reg);
        System.out.println("Reg : "+ID);
        System.out.println("Reg : "+D2E);
        
    }
 
    /**
     * 
     * @param location path in the registry
     * @param key registry key
     * @return registry value or null if not found
     */
    
    public static final String readRegistry(String location, String key){
        try {
            // Run reg query, then read output with StreamReader (internal class)
            Process process = Runtime.getRuntime().exec("reg query " + 
                    '"'+ location + "\" /v " + key);
 
            StreamReader reader = new StreamReader(process.getInputStream());
            reader.start();
            process.waitFor();
            reader.join();
 
            // Parse out the value
            String[] parsed = reader.getResult().split("\\s+");
            if (parsed.length > 1) {
                return parsed[parsed.length-1];
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
 
        return null;
    }
    public static final String addRegistry(String location, String key, String value){
        try {
            // Run reg query, then read output with StreamReader (internal class)
            Process process = Runtime.getRuntime().exec("reg add " + 
                    '"'+ location + "\" /v " + key + " /t REG_SZ /d "+value);
 
            StreamReader reader = new StreamReader(process.getInputStream());
            reader.start();
            process.waitFor();
            reader.join();
 
            // Parse out the value
            String[] parsed = reader.getResult().split("\\s+");
            if (parsed.length > 1) {
                return parsed[parsed.length-1];
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
 
        return null;
    }
    public static final String editRegistry(String location, String key, String value){
        try {
            // Run reg query, then read output with StreamReader (internal class)
            Process process = Runtime.getRuntime().exec("reg add " + 
                    '"'+ location + "\" /v " + key + " /t REG_SZ /d "+value+" /f");
 
            StreamReader reader = new StreamReader(process.getInputStream());
            reader.start();
           
            
            process.waitFor();
            reader.join();
 
            // Parse out the value
            String[] parsed = reader.getResult().split("\\s+");
            if (parsed.length > 1) {
                return parsed[parsed.length-1];
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
 
        return null;
    }
 
    static class StreamReader extends Thread {
        private InputStream is;
        private StringWriter sw = new StringWriter();
 
        public StreamReader(InputStream is) {
            this.is = is;
        }
 
        public void run() {
            try {
                int c;
                while ((c = is.read()) != -1)
                    sw.write(c);
            } catch (IOException e) { 
                e.printStackTrace();
            }
        }
        
        public String getResult() {
            return sw.toString();
        }
        
    }
    public static void main(String[] args) {
 
        // Sample usage
       String reg=null,ID=null,D2E = null;
       
        
         reg = WindowsReqistry.readRegistry("HKLM\\SOFTWARE\\SITA", "reg");
         ID = WindowsReqistry.readRegistry("HKLM\\SOFTWARE\\SITA", "ID");
         D2E = WindowsReqistry.readRegistry("HKLM\\SOFTWARE\\SITA", "D2E");
        if(reg == null){
            reg = WindowsReqistry.addRegistry("HKLM\\SOFTWARE\\SITA", "Reg", "1");
            ID = WindowsReqistry.addRegistry("HKLM\\SOFTWARE\\SITA", "ID", "20141020");
            D2E = WindowsReqistry.addRegistry("HKLM\\SOFTWARE\\SITA", "D2E", "365");
            if(WindowsReqistry.readRegistry("HKLM\\SOFTWARE\\SITA", "reg")!=null){
                JOptionPane.showMessageDialog(null, "Registry success");
            }
            
        }
         
        System.out.println("Reg : "+reg);
        System.out.println("Reg : "+ID);
        System.out.println("Reg : "+D2E);
       
        
        
    }
    
}
