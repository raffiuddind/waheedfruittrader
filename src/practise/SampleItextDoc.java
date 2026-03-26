/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practise;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import static java.awt.JobAttributes.DestinationType.FILE;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author raffiuddin
 */
public class SampleItextDoc {
    private static String FILE = "FirstPdf.pdf";
    public static void main(String[] args) {
        
         try {
                        
                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream(new File("rafi")));
                        document.open();
                        document.addTitle("Head");
                        document.close();
                        
                } catch (Exception e) {
                        e.printStackTrace();
                }
        
        
        
    }
    
    
    
}
