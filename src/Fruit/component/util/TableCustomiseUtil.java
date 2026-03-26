/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.component.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import org.apache.log4j.Logger;

/**
 *
 * @author Raffiuddin
 */
public class TableCustomiseUtil {
    
        private static final Logger LOG = Logger.getLogger(TableCustomiseUtil.class);
        public static void setTableHeaderFont(javax.swing.JTable table){
            try {
                table.getTableHeader().setBackground(Color.DARK_GRAY);
            table.getTableHeader().setForeground(Color.white);
            Font tableFont = new Font("Tahoma", Font.BOLD, 16);
            table.getTableHeader().setFont(tableFont);
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error(e);
            }
            
        }
        public static void setTableColSize(javax.swing.JTable table, int columnIndex, int width){
            
            table.getColumnModel().getColumn(columnIndex).setMaxWidth(width);
        }
        
        public static void setFrameSize(JFrame frame){
            
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            int height = screenSize.height;
            int width = screenSize.width;
            screenSize.setSize(width*(0.90), height*(0.80));
            int newheight = screenSize.height;
            int newwidth = screenSize.width;

             //Then I put some print statements in the code
            System.out.println("height="+height);
            System.out.println("width="+width);
            System.out.println("0.80*height="+(height*0.80));
            System.out.println("0.90*width="+(width*0.90));
            System.out.println("newheight="+newheight);
            System.out.println("newwidth="+newwidth);
            LOG.info("height="+height);
            LOG.info("width="+width);
            LOG.info("0.80*height="+(height*0.80));
            LOG.info("0.90*width="+(width*0.90));
            LOG.info("newheight="+newheight);
            LOG.info("newwidth="+newwidth);

            frame.setSize(newwidth, newheight);
            frame.setLocationRelativeTo(null);
//            frame.addWidgets();
//            frame.setVisible(true);
        } 
}
