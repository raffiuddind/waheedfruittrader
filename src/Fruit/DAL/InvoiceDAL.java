/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.DAL;

import Fruit.Common.DbConnection;
import Fruit.Model.InvoiceEntryModel;
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
public class InvoiceDAL {
    private TrxDAL trxDAL;
    public int addSupplierTrx(InvoiceEntryModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "INSERT INTO invoice(TRUCK_NO, TRANSPORT, INV_DATE, INVOICE_TOTAL, CREATED) VALUES('"
				+ productEntryModel.getInvoiceModel().getTrkNo()
				+ "', '"
				+ productEntryModel.getInvoiceModel().getTrnsprt()
                                + "', '"
                                + productEntryModel.getInvoiceModel().getDt()
                                + "', "
                                + productEntryModel.getInvTotal()
                                + ", "
                                + " NOW())";
                String sql = "SELECT INVOICE_ID FROM invoice ORDER BY INVOICE_ID DESC LIMIT 1";
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
                        statement = conn.prepareCall(sql);
                        resultado = statement.executeQuery();
                        if(resultado != null){
                            resultado.next();
                            tId = resultado.getInt(1);
                            
                                    
                        }
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in InvoiceDAL's addSupplierTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int updateSupplierTrx(InvoiceEntryModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "UPDATE invoice SET "
                        + "TRUCK_NO ='"+productEntryModel.getInvoiceModel().getTrkNo()
                        + "', TRANSPORT = '"+ productEntryModel.getInvoiceModel().getTrnsprt()
                        + "', INV_DATE = '"+ productEntryModel.getInvoiceModel().getDt()
                        + "', INVOICE_TOTAL = "+ productEntryModel.getInvTotal()
                        + " WHERE INVOICE_ID="+ productEntryModel.getInvoiceModel().getInvoiceId();
				
			     System.out.println(query);
              
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
                        tId = productEntryModel.getInvoiceModel().getInvoiceId();
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in InvoiceDAL's updateSupplierTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int deleteSupplierTrx(int invoiceId){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "DELETE FROM load_details WHERE INVOICE_ID="+ invoiceId;
		String query1 = "DELETE FROM invoice WHERE INVOICE_ID="+ invoiceId;
				
			     System.out.println(query);
              
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			statement = conn.prepareStatement(query1);
			tId =  statement.executeUpdate();
//                        tId = productEntryModel.getInvoiceModel().getInvoiceId();
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in InvoiceDAL's updateSupplierTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
                        
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int addSupplierTtlTrx(SaleTransactionModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "INSERT INTO load_details(QNTY, UNIT_COST, TOTAL, SUPPLIER_ID, P_ID, INVOICE_ID, ACTIVE) VALUES("
				+ productEntryModel.getQnty()
				+ ", "
				+ productEntryModel.getUcost()
                                + ", "
                                + productEntryModel.getUtot()
                                + ", "
                                + productEntryModel.getSuplierId()
                                + ", "
                                + productEntryModel.getPid()
                                + ", "
                                + productEntryModel.getInvoiceId()
				+ ", true)";
                System.out.println(query);
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 tId = statement.executeUpdate();
                        
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's addCustomerTtlTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int updateSupplierTtlTrx(SaleTransactionModel saleTransactionModel, int loadId){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "UPDATE load_details SET "
                        + "QNTY = "+ saleTransactionModel.getQnty()
                        + ", UNIT_COST = "+ saleTransactionModel.getUcost()
                        + ", TOTAL = "+ saleTransactionModel.getUtot()
                        + ", SUPPLIER_ID = "+ saleTransactionModel.getSuplierId()
                        + ", P_ID = "+ saleTransactionModel.getPid()
                        + ", INVOICE_ID = "+ saleTransactionModel.getInvoiceId()   + ", ACTIVE = true "
                        + " WHERE LOAD_ID = "+ saleTransactionModel.getLoadId();
				
                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 tId = statement.executeUpdate();
                        
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in updateSupplierTtlTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
            trxDAL = new TrxDAL();
                    trxDAL.setLoadStatus(loadId);
                    DbConnection.closeConnection(conn, statement, resultado);
                        
		}
        return tId;

        
        
    }
    public int deleteSupplierTtlTrx(int loadId){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "DELETE FROM load_details WHERE LOAD_ID = "+ loadId;
				
                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 tId = statement.executeUpdate();
                        
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's addCustomerTtlTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int getCustomerBalance(int cid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "select SUM(ttrx.`TOTAL`) as total,SUM(tr.`PAID`) as paid,\n" +
"SUM(ttrx.`TOTAL`)-SUM(tr.`PAID`) as balance from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE tr.`CUSTOMER_ID`="+cid;
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                        while(resultado.next()){
                            tId = resultado.getInt("balance");
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
        return tId;

        
        
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
    
    public ArrayList<SaleCatelogModel> getInvoiceById(int invoiceId){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                ArrayList<SaleCatelogModel> listofModel = new ArrayList();
                
		String query = "SELECT inv.`INVOICE_ID`,ld.`LOAD_ID`,inv.`INV_DATE`,p.`PRODUCT_NAME`,\n" +
"ld.`QNTY`,ld.`SUPPLIER_ID`,s.`SUPPLIER_NAME`,p.P_ID\n" +
" ,ld.`UNIT_COST`,ld.`TOTAL`,ld.`LOAD_ID`,inv.`TRUCK_NO` FROM load_details ld\n" +
"INNER JOIN invoice inv ON inv.`INVOICE_ID`=ld.`INVOICE_ID`\n" +
"INNER JOIN product p ON p.`P_ID`=ld.`P_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE inv.`INVOICE_ID`="+invoiceId+" ";
                
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
                                       resultado.getInt("P_ID"),
                                       resultado.getInt("INVOICE_ID"),
                                       resultado.getInt("INVOICE_ID")
                                      
                                       ), 
                                     resultado.getInt("INVOICE_ID"));
                             model.getTransactionModel().setSuplierId(resultado.getInt("SUPPLIER_ID"));
                             model.getTransactionModel().setLoadId(resultado.getInt("LOAD_ID"));
                             System.out.println("loadis "+model.getTransactionModel().getLoadId());
                             
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
    public ArrayList<SaleCatelogModel> getInvoiceByTruckNo(String productcat,String dt){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                ArrayList<SaleCatelogModel> listofModel = new ArrayList();
                int tId = 0;
		String query = "SELECT inv.`INVOICE_ID`,ld.`LOAD_ID`,inv.`INV_DATE`,p.`PRODUCT_NAME`,ld.`QNTY`,ld.`CAT_ID`,ld.`SUPPLIER_ID`,s.`SUPPLIER_NAME`,p.P_ID\n" +
" ,ld.`UNIT_COST`,ld.`TOTAL`,ld.`LOAD_ID`,inv.`TRUCK_NO` FROM load_details ld\n" +
"INNER JOIN invoice inv ON inv.`INVOICE_ID`=ld.`INVOICE_ID`\n" +
"INNER JOIN product p ON p.`P_ID`=ld.`P_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE inv.`TRUCK_NO`='"+productcat+"' AND inv.`INV_DATE`='"+dt+"' ";
                
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
                                     resultado.getInt("INVOICE_ID"));
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
                             model.getTransactionModel().setSuplierId(resultado.getInt("SUPPLIER_ID"));
                             model.getTransactionModel().setLoadId(resultado.getInt("LOAD_ID"));
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
    
}
