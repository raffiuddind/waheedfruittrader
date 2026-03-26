/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.ui;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author user01
 */
public class macAdr {
public long k = 0;
public long s = 0;
public String sno = null;
    public  String getSerialNo(){
        
        
        String command = "/sbin/ifconfig";
    
    String sOsName = System.getProperty("os.name");
    if (sOsName.startsWith("Windows")) {
        command = "ipconfig /all";
    } else {

        if ((sOsName.startsWith("Linux")) || (sOsName.startsWith("Mac"))
                || (sOsName.startsWith("HP-UX"))) {
            command = "/sbin/ifconfig";
        } else {
            System.out.println("The current operating system '" + sOsName
                    + "' is not supported.");
        }
    }

    Pattern p = Pattern.compile("([a-fA-F0-9]{1,2}(-|:)){5}[a-fA-F0-9]{1,2}");
    try {
        Process pa = Runtime.getRuntime().exec(command);
        pa.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                pa.getInputStream()));

        String line;
        Matcher m;
        while ((line = reader.readLine()) != null) {

            m = p.matcher(line);

            if (!m.find())
                continue;
            line = m.group();
            break;

        }
//        System.out.println(line);
        line = line.replaceAll("-", "");
         k = 0;
                for(int i=line.length();i>0;i--){
//                    System.out.println("i : "+(ad.length()-i)+" val of "+ad.charAt(i-1)+" : "+Integer.parseInt(String.valueOf(ad.charAt(i-1)),16) );
//                    System.out.println((Long.parseLong(String.valueOf(ad.charAt(i-1)),16) * (Math.pow(16, ad.length()-i))));
                    k=(long) (k + (Long.parseLong(String.valueOf(line.charAt(i-1)),16) * (Math.pow(16, line.length()-i))));
                   
                    
                }
//                System.out.println("Decimal value of MAC address : "+k );
//                System.out.println("Serial No : "+k*7 );
                 sno=Long.toString(k*7);
                System.out.println(sno.length());
                StringBuilder sb = new StringBuilder();
for (int i = 0; i < sno.length(); i++) {
    if (i > 0 && (i % 4 == 0)) {
        sb.append(" - ");
    }

    sb.append(sno.charAt(i));
}

sno = sb.toString();
                JOptionPane.showMessageDialog(null, "Serial No : "+ (sno), "", JOptionPane.INFORMATION_MESSAGE);
                
//                System.out.println(msg);
                
    } catch (Exception e) {
        e.printStackTrace();
    }

        
        
        
        
        
        return sno;
        
    }

 public static void main(String[] args) {

    String command = "/sbin/ifconfig";
    
    String sOsName = System.getProperty("os.name");
    if (sOsName.startsWith("Windows")) {
        command = "ipconfig /all";
    } else {

        if ((sOsName.startsWith("Linux")) || (sOsName.startsWith("Mac"))
                || (sOsName.startsWith("HP-UX"))) {
            command = "/sbin/ifconfig";
        } else {
            System.out.println("The current operating system '" + sOsName
                    + "' is not supported.");
        }
    }

    Pattern p = Pattern.compile("([a-fA-F0-9]{1,2}(-|:)){5}[a-fA-F0-9]{1,2}");
    try {
        Process pa = Runtime.getRuntime().exec(command);
        pa.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                pa.getInputStream()));

        String line;
        Matcher m;
        while ((line = reader.readLine()) != null) {

            m = p.matcher(line);

            if (!m.find())
                continue;
            line = m.group();
            break;

        }
        System.out.println(line);
        line = line.replaceAll("-", "");
        long k = 0;
                for(int i=line.length();i>0;i--){
//                    System.out.println("i : "+(ad.length()-i)+" val of "+ad.charAt(i-1)+" : "+Integer.parseInt(String.valueOf(ad.charAt(i-1)),16) );
//                    System.out.println((Long.parseLong(String.valueOf(ad.charAt(i-1)),16) * (Math.pow(16, ad.length()-i))));
                    k=(long) (k + (Long.parseLong(String.valueOf(line.charAt(i-1)),16) * (Math.pow(16, line.length()-i))));
                   
                    
                }
                System.out.println("Decimal value of MAC address : "+k );
                System.out.println("Serial No : "+k*7 );
                String sno=Long.toString(k*7);
                System.out.println(sno.length());
                StringBuilder sb = new StringBuilder();
for (int i = 0; i < sno.length(); i++) {
    if (i > 0 && (i % 4 == 0)) {
        sb.append("-");
    }

    sb.append(sno.charAt(i));
}

sno = sb.toString();
                JOptionPane.showMessageDialog(null, "Serial No : "+ (sno), "", JOptionPane.INFORMATION_MESSAGE);
                
//                System.out.println(msg);
                
    } catch (Exception e) {
        e.printStackTrace();
    }

}   
}
