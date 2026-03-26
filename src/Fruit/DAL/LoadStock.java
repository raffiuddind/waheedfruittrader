/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.DAL;

import Fruit.Common.DbConnection;
import Fruit.Model.InvoiceModel;
import Fruit.Model.ProductCategoryModel;
import Fruit.Model.ProductEntryModel;
import Fruit.Model.SaleCatelogModel;
import Fruit.Model.SaleTransactionModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author raffiuddin
 */
public class LoadStock {
    
    
    public static List<SaleCatelogModel> getInvoiceById(int invoiceID){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                List<SaleCatelogModel> listofModel = new ArrayList();
                int tId = 0;
		String query = "SELECT inv.`INVOICE_ID`,ld.`LOAD_ID`,inv.`INV_DATE`,p.`PRODUCT_NAME`,\n" +
"ld.`QNTY`,ld.`SUPPLIER_ID`,s.`SUPPLIER_NAME`,p.P_ID\n" +
" ,ld.`UNIT_COST`,ld.`TOTAL`,ld.`LOAD_ID`,inv.`TRUCK_NO` FROM load_details ld\n" +
"INNER JOIN invoice inv ON inv.`INVOICE_ID`=ld.`INVOICE_ID`\n" +
"INNER JOIN product p ON p.`P_ID`=ld.`P_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE inv.`INVOICE_ID`="+invoiceID+" ";
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         while(resultado.next()){
                             SaleCatelogModel model = new SaleCatelogModel(
                                     new ProductCategoryModel(
                                     "", 
                                     resultado.getString("SUPPLIER_NAME"), 
                                    
                                     true, 
                                     resultado.getString("TRUCK_NO"), 
                                     resultado.getInt("SUPPLIER_ID"), 
                                     resultado.getInt("P_ID"), 
                                     
                                     new ProductEntryModel(
                                       resultado.getString("PRODUCT_NAME"),
                                       resultado.getString("INV_DATE"),
                                       
                                       true, 
                                       query,
                                       resultado.getInt("P_ID"))), 
                                     new SaleTransactionModel(
                                       resultado.getInt("QNTY"),
                                       resultado.getInt("UNIT_COST"),
                                       resultado.getInt("TOTAL"),
                                       resultado.getInt("LOAD_ID"),
                                       resultado.getInt("INVOICE_ID"),
                                       resultado.getInt("INVOICE_ID")
                                      
                                       ), 
                                     tId);
//                             InvoiceModel invoiceModel = new InvoiceModel(
//                                     resultado.getInt("INVOICE_ID"), 
//                                     resultado.getInt("QNTY"), 
//                                     resultado.getInt("stock"), 
//                                     resultado.getInt("qntysold"), 
//                                     resultado.getString("DATE"),
//                                     resultado.getInt("LOAD_ID"), 
//                                     resultado.getInt("SUPPLIER_ID"), 
//                                     resultado.getString("TRUCK_NO"),
//                                     resultado.getString("TRANSPORT"),
//                                     true
//                             );
                             
                             listofModel.add(model);
                         }
                        
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in InvoiceDAL's getInvoiceById method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return listofModel;

        
        
    }
    
    public static List<SaleCatelogModel> getInvoiceByTruckNo(String truckNo,String dt){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                List<SaleCatelogModel> listofModel = new ArrayList();
                int tId = 0;
		String query = "SELECT inv.`INVOICE_ID`,ld.`LOAD_ID`,inv.`INV_DATE`,p.`PRODUCT_NAME`,ld.`QNTY`,ld.`CAT_ID`,ld.`SUPPLIER_ID`,s.`SUPPLIER_NAME`,p.P_ID\n" +
" ,ld.`UNIT_COST`,ld.`TOTAL`,ld.`LOAD_ID`,inv.`TRUCK_NO` FROM load_details ld\n" +
"INNER JOIN invoice inv ON inv.`INVOICE_ID`=ld.`INVOICE_ID`\n" +
"INNER JOIN product p ON p.`P_ID`=ld.`P_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE inv.`TRUCK_NO`='"+truckNo+"' AND inv.`INV_DATE`='"+dt+"' ";
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         while(resultado.next()){
                             SaleCatelogModel model = new SaleCatelogModel(
                                     new ProductCategoryModel(
                                     "", 
                                     resultado.getString("SUPPLIER_NAME"), 
                                    
                                     true, 
                                     resultado.getString("TRUCK_NO"),
                                     resultado.getInt("SUPPLIER_ID"), 
                                     resultado.getInt("P_ID"), 
                                     
                                     new ProductEntryModel(
                                       resultado.getString("PRODUCT_NAME"),
                                       resultado.getString("INV_DATE"),
                                       true, 
                                       query,
                                       resultado.getInt("P_ID"))), 
                                     new SaleTransactionModel(
                                       resultado.getInt("QNTY"),
                                       resultado.getInt("UNIT_COST"),
                                       resultado.getInt("TOTAL"),
                                       resultado.getInt("LOAD_ID"),
                                       resultado.getInt("INVOICE_ID"),
                                       resultado.getInt("INVOICE_ID")
                                      
                                       ), 
                                     tId);
//                             InvoiceModel invoiceModel = new InvoiceModel(
//                                     resultado.getInt("INVOICE_ID"), 
//                                     resultado.getInt("QNTY"), 
//                                     resultado.getInt("stock"), 
//                                     resultado.getInt("qntysold"), 
//                                     resultado.getString("DATE"),
//                                     resultado.getInt("LOAD_ID"), 
//                                     resultado.getInt("SUPPLIER_ID"), 
//                                     resultado.getString("TRUCK_NO"),
//                                     resultado.getString("TRANSPORT"),
//                                     true
//                             );
                             
                             listofModel.add(model);
                         }
                        
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in InvoiceDAL's getInvoiceById method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return listofModel;

        
        
    }
    
    public List<InvoiceModel> getProductQntyByCatId(int productcat){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                List<InvoiceModel> listofModel = new ArrayList();
                int tId = 0;
		String query = "select inv.`INVOICE_ID`,inv.`CAT_ID`,inv.`QNTY`,(inv.`QNTY`-\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`INVOICE_ID` = inv.`INVOICE_ID`)) as stock,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`INVOICE_ID` = inv.`INVOICE_ID`) as qntysold,inv.`DATE`\n" +
",ld.`LOAD_ID`,ld.`SUPPLIER_ID`,ld.`TRUCK_NO`,ld.`TRANSPORT`,ld.`EXPENCE` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"WHERE inv.`CAT_ID`="+productcat+" AND inv.`ACTIVE`=true";
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         while(resultado.next()){
                             InvoiceModel invoiceModel = new InvoiceModel(
                                     resultado.getInt("INVOICE_ID"), 
                                     resultado.getInt("QNTY"), 
                                     resultado.getInt("stock"), 
                                     resultado.getInt("qntysold"), 
                                     resultado.getString("DATE"),
                                     resultado.getInt("LOAD_ID"), 
                                     resultado.getInt("SUPPLIER_ID"), 
                                     resultado.getString("TRUCK_NO"),
                                     resultado.getString("TRANSPORT"),
                                     true
                             );
                             
                             listofModel.add(invoiceModel);
                         }
                        
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's addCustomerTtlTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return listofModel;

        
        
    }
    
}
