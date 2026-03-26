/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.component.util;

/**
 *
 * @author Raffiuddin
 */
public class CommonUtils {

    public static boolean isStringEmpty(String string){
        return string == null || string.isEmpty();
    }
    
    public static boolean isStringNotEmpty(String string) {
        return !isStringEmpty(string);
    }
}
