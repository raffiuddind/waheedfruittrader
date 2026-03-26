/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.DAL;

import Fruit.Common.CommonMethods;
import Fruit.Common.DbConnection;
import Fruit.Model.InvoiceModel;
import Fruit.Model.ProductCategoryModel;
import Fruit.Model.ProductEntryModel;
import Fruit.Model.SaleCatelogModel;
import Fruit.Model.SaleEntryTrxModel;
import Fruit.Model.SaleTransactionModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author raffiuddin
 */
public class TrxDAL {

    public TrxDAL() {
    }

    
    
    public int addCustomerTrx(SaleEntryTrxModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "INSERT INTO transaction(PAID, BALANCE, T_DATE, TRX_TOTAL, CUSTOMER_ID, UPDATED) VALUES("
				+ productEntryModel.getPaid()
				+ ", "
				+ productEntryModel.getBalance()
                                + ", '"
                                + productEntryModel.getTransDate()
                                + "', "
                                + productEntryModel.getTotal()
                                + ", "
                                + productEntryModel.getCusId()
				+ ", NOW())";
                String sql = "SELECT T_ID FROM transaction WHERE CUSTOMER_ID = "+productEntryModel.getCusId()+" ORDER BY T_ID DESC";
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
			System.err.println("Exception occured in TrxDAL's addCustomerTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int updateCustomerTrx(SaleEntryTrxModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = productEntryModel.getInvoiceId();;
		String query = "UPDATE  transaction"
                        + " SET PAID="+ productEntryModel.getPaid()
                        + ", BALANCE="+ productEntryModel.getBalance()
                        + ", T_DATE='"+ productEntryModel.getTransDate()
                        + "', TRX_TOTAL="+ productEntryModel.getTotal()
                        + ", CUSTOMER_ID="+  productEntryModel.getCusId()
                        + ", UPDATED=NOW() WHERE T_ID = "+productEntryModel.getInvoiceId();
                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();

			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's updateCustomerTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int deleteCustomerTrx(int tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
                int result=0;
		String resultMsg = "";
//                int tId = productEntryModel.getInvoiceId();;
		String query1 = "DELETE FROM  ttl_transaction WHERE T_ID = "+tid;
		String query2 = "DELETE FROM  transaction WHERE T_ID = "+tid;
//		String query3 = "DELETE FROM  transaction WHERE T_ID = "+tid;
                System.out.println(query1);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query1);
			 result = statement.executeUpdate();
                         
			statement = conn.prepareStatement(query2);
			 result = statement.executeUpdate();
                        
//			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's updateCustomerTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return result;

        
        
    }
    public int addCustomerTtlTrx(SaleTransactionModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "INSERT INTO ttl_transaction(QNTY, UNIT_COST, TOTAL, T_ID, CAT_ID, LOAD_ID, UPDATED) VALUES("
				+ productEntryModel.getQnty()
				+ ", "
				+ productEntryModel.getUcost()
                                + ", "
                                + productEntryModel.getUtot()
                                + ", "
                                + productEntryModel.gettId()
                                + ", "
                                + productEntryModel.getPid()
                                + ", "
                                + productEntryModel.getInvoiceId()
				+ ", NOW())";
                System.out.println(query);
                // To update active or in-active status of load using LoadID.
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 tId = statement.executeUpdate();
                        
                       setLoadStatus(productEntryModel.getInvoiceId()); 
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
    public int updateCustomerTtlTrx(SaleTransactionModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "UPDATE  ttl_transaction SET "
                        + " QNTY = "+ productEntryModel.getQnty()
                        + ", UNIT_COST = "+ productEntryModel.getUcost()
                        + ", TOTAL = "+ productEntryModel.getUtot()
                        + ", CAT_ID = "+ productEntryModel.getPid()
                        + ", LOAD_ID = "+ productEntryModel.getInvoiceId()
                        + ", UPDATED = NOW() WHERE TT_ID="+productEntryModel.gettId();
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 tId = statement.executeUpdate();
                         System.out.println(query);
                        // To update active or in-active status of load using LoadID.
                setLoadStatus(productEntryModel.getInvoiceId());
                        
                    System.out.println("sucess : "+productEntryModel.getInvoiceId());
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's updateCustomerTtlTrx"
                                + ""
                                + " method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int deleteCustomerTtlTrx(SaleTransactionModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                System.out.println("Del : "+productEntryModel.getInvoiceId());
		String query = "DELETE  FROM ttl_transaction  WHERE TT_ID="+productEntryModel.gettId();
//		String query1 = "UPDATE  load_details SET ACTIVE=true WHERE LOAD_ID="+productEntryModel.getInvoiceId();                
                
                try {
                    System.out.println(query);
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 tId = statement.executeUpdate();
//			statement = conn.prepareStatement(query1);
//			 tId = statement.executeUpdate();
//                         setLoadActive(productEntryModel.getInvoiceId());
                        // To update active or in-active status of load using LoadID.
                setLoadStatus(productEntryModel.getInvoiceId());
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's deleteCustomerTtlTrx"
                               
                                + " method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int setLoadComplete(int id){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
//		String query = "DELETE  FROM ttl_transaction  WHERE TT_ID="+productEntryModel.gettId();
		String query1 = "UPDATE  load_details SET ACTIVE=false WHERE LOAD_ID="+id;                
                try {
//                    System.out.println(query);
			conn = DbConnection.getConnection();
//			statement = conn.prepareStatement(query);
//			 tId = statement.executeUpdate();
			statement = conn.prepareStatement(query1);
			 tId = statement.executeUpdate();
                         
                        
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's deleteCustomerTtlTrx"
                               
                                + " method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int setLoadActive(int id){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
//		String query = "DELETE  FROM ttl_transaction  WHERE TT_ID="+productEntryModel.gettId();
		String query1 = "UPDATE  load_details SET ACTIVE=true WHERE LOAD_ID="+id;                
                try {
//                    System.out.println(query);
			conn = DbConnection.getConnection();
//			statement = conn.prepareStatement(query);
//			 tId = statement.executeUpdate();
			statement = conn.prepareStatement(query1);
			 tId = statement.executeUpdate();
                         
                        
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's deleteCustomerTtlTrx"
                               
                                + " method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int setLoadStatus(int id){
        
        Connection conn = null;
		PreparedStatement statement = null;
                CallableStatement cs =null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
//		String query = "DELETE  FROM ttl_transaction  WHERE TT_ID="+productEntryModel.gettId();
		String query1 = "call p(?)"; 
//                System.out.println(query1);
                try {
                    System.out.println(query1);
			conn = DbConnection.getConnection();
//			statement = conn.prepareStatement(query);
//			 tId = statement.executeUpdate();
                        
			cs = conn.prepareCall(query1);
                        cs.setInt(1, id);
			 tId = cs.executeUpdate();
                         
                        
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's setLoadStatus"
                               
                                + " method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    
    public int getCustomerBalance(int cid,int tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "select SUM(ttrx.`TOTAL`) as total,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID` and t.`T_ID`=tr.`T_ID`)as paid,\n" +
"SUM(ttrx.`TOTAL`)-(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID` AND t.`T_ID`<tr.`T_ID`) as balance from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE tr.`CUSTOMER_ID`="+cid+" AND tr.T_ID<"+tid;
                
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
    public int getCustomerBalance(int cid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "select \n" +
"IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total,\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0, \n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`)) as pd,\n" +
"(IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) -\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0, \n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`))\n" +
") as balance\n" +
" from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE tr.`CUSTOMER_ID`="+cid;
//                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                        while(resultado.next()){
                            tId = resultado.getInt("balance");
//                            System.out.println("b :"+tId);
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
    
    public int getCustomerBalanceByDate(int cid, String dt){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "select  \n" +
"IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total, \n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"') is null,0,  \n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"')) as pd, \n" +
"(IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) - \n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"') is null,0,  \n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"')) \n" +
") as balance from ttl_transaction ttrx \n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID` \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`CUSTOMER_ID`="+cid+" and tr.`T_DATE` <= '"+dt+"' ORDER BY tr.`T_DATE`,tr.T_ID";
//                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
                        while(resultado.next()){
                            tId = resultado.getInt("balance");
//                            System.out.println("b :"+tId);
                        }
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's getCustomerBalanceByDate method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int getCustomerBalanceByDate1(int cid, String dt, int tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "select  \n" +
"IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total, \n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"') is null,0,  \n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"')) as pd, \n" +
"(IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) - \n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"') is null,0,  \n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"')) \n" +
") as balance from ttl_transaction ttrx \n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID` \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`CUSTOMER_ID`="+cid+" and tr.`T_DATE` <= '"+dt+"' and tr.T_ID <="+tid+" ORDER BY tr.`T_DATE`,tr.T_ID";
                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
                        while(resultado.next()){
                            tId = resultado.getInt("balance");
//                            System.out.println("b :"+tId);
                        }
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's getCustomerBalanceByDate method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    
    public int getCustomerBalanceByDate2(int cid, String dt, int tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "select  \n" +
"IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total, \n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"') is null,0,  \n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"')) as pd, \n" +
"(IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) - \n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"') is null,0,  \n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`  and t.`T_DATE` <= '"+dt+"')) \n" +
") as balance from ttl_transaction ttrx \n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID` \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`CUSTOMER_ID`="+cid+" and tr.`T_DATE` <= '"+dt+"' and tr.T_ID <"+tid+" ORDER BY tr.`T_DATE`,tr.T_ID";
                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
                        while(resultado.next()){
                            tId = resultado.getInt("balance");
//                            System.out.println("b :"+tId);
                        }
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's getCustomerBalanceByDate method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    
    public List<SaleEntryTrxModel> getCustomerTrxToUpdate(int cid,String dt){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<SaleEntryTrxModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = "select c.`NAME`,trx.`CUSTOMER_ID`,trx.`T_ID`,trx.`PAID`\n" +
",trx.`BALANCE`,trx.`T_DATE`,trx.`TRX_TOTAL`,ttrx.`TT_ID`,\n" +
"p.`PRODUCT_NAME`,cat.`CATEGORY_TYPE`\n" +
",ttrx.`QNTY`,ttrx.`UNIT_COST`,ttrx.`TOTAL`,ttrx.`T_ID`,ttrx.`T_ID`\n" +
",ttrx.`CAT_ID`,ttrx.`INVOICE_ID` FROM customer c\n" +
"INNER JOIN `transaction` trx ON trx.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"INNER JOIN ttl_transaction ttrx ON ttrx.`T_ID`=trx.`T_ID`\n" +
"INNER JOIN product_category cat ON cat.`CAT_ID`=ttrx.`CAT_ID`\n" +
"INNER JOIN product p ON p.`P_ID`=cat.`P_ID`\n" +
"WHERE trx.`CUSTOMER_ID`="+cid+" AND trx.`T_DATE`='"+dt+"'";
                String q1 = "select c.`NAME`,trx.`CUSTOMER_ID`,trx.`T_ID`,trx.`PAID`\n" +
",trx.`BALANCE`,trx.`T_DATE`,trx.`TRX_TOTAL`,ttrx.`TT_ID`,\n" +
"p.`PRODUCT_NAME`,cat.`CATEGORY_TYPE`\n" +
",ttrx.`QNTY`,ttrx.`UNIT_COST`,ttrx.`TOTAL`,ttrx.`T_ID`,ttrx.`T_ID`\n" +
",ttrx.`CAT_ID`,ttrx.`LOAD_ID` FROM customer c\n" +
"INNER JOIN `transaction` trx ON trx.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"INNER JOIN ttl_transaction ttrx ON ttrx.`T_ID`=trx.`T_ID`\n" +
"INNER JOIN product_category cat ON cat.`CAT_ID`=ttrx.`CAT_ID`\n" +
"INNER JOIN product p ON p.`P_ID`=cat.`P_ID`\n" +
"WHERE trx.`CUSTOMER_ID`="+cid+" AND trx.`T_DATE`='"+dt+"'\n" +
"GROUP BY trx.`T_ID`";
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(q1);
			 resultado = statement.executeQuery();
                        while(resultado.next()){
                             SaleEntryTrxModel entryTrxModel = new SaleEntryTrxModel(
                                     resultado.getInt("CUSTOMER_ID"),
                                     resultado.getInt("TRX_TOTAL"),
                                     resultado.getInt("PAID"),
                                     resultado.getInt("BALANCE"),
                                     resultado.getInt("T_ID"),
                                     resultado.getString("T_DATE"));
                             entryTrxModels.add(entryTrxModel);
                         }
                        
//                        resultado =null;
//                        statement = conn.prepareStatement(query);
//			 resultado = statement.executeQuery();
//                        while(resultado.next()){
//                            SaleCatelogModel catelogModel = new SaleCatelogModel(
//                                    new ProductCategoryModel(
//                                            resultado.getString("CATEGORY_TYPE"),
//                                            query,
//                                            true,
//                                            query,
//                                            resultado.getInt("CAT_ID"),
//                                            tId,
//                                            new ProductEntryModel(
//                                                    resultado.getString("PRODUCT_NAME"),
//                                                    query, true, query, 0)),
//                                    new SaleTransactionModel(
//                                            resultado.getInt("QNTY"),
//                                            resultado.getInt("UNIT_COST"),
//                                            resultado.getInt("TOTAL"),
//                                            resultado.getInt("CAT_ID"),
//                                            resultado.getInt("T_ID"),
//                                            resultado.getInt("INVOICE_ID")),
//                                    resultado.getInt("INVOICE_ID"));
//                            transactionModels.add(catelogModel);
//                        }
//                        v.add(transactionModels);
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's getCustomerTrxToUpdate method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    public ArrayList<SaleCatelogModel> getCustomerTtrxByTid(int cid,int tid) throws SQLException{
        System.out.println("cid : "+cid +"\t tid : "+tid);
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado1 = null;
		String resultMsg = "";
                
                ArrayList<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = "select c.`NAME`,trx.`CUSTOMER_ID`,trx.`T_ID`,trx.`PAID`\n" +
",trx.`BALANCE`,trx.`T_DATE`,trx.`TRX_TOTAL`,ttrx.`TT_ID`,\n" +
"p.`PRODUCT_NAME`,cat.`CATEGORY_TYPE`\n" +
",ttrx.`QNTY`,ttrx.`UNIT_COST`,ttrx.`TOTAL`,ttrx.`T_ID`,ttrx.`T_ID`\n" +
",ttrx.`CAT_ID`,ttrx.LOAD_ID FROM customer c\n" +
"INNER JOIN `transaction` trx ON trx.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"INNER JOIN ttl_transaction ttrx ON ttrx.`T_ID`=trx.`T_ID`\n" +
"INNER JOIN product_category cat ON cat.`CAT_ID`=ttrx.`CAT_ID`\n" +
"INNER JOIN product p ON p.`P_ID`=cat.`P_ID`\n" +
" WHERE trx.`T_ID`="+tid+"";
//                String q1 = "select c.`NAME`,trx.`CUSTOMER_ID`,trx.`T_ID`,trx.`PAID`\n" +
//",trx.`BALANCE`,trx.`T_DATE`,trx.`TRX_TOTAL`,ttrx.`TT_ID`,\n" +
//"p.`PRODUCT_NAME`,cat.`CATEGORY_TYPE`\n" +
//",ttrx.`QNTY`,ttrx.`UNIT_COST`,ttrx.`TOTAL`,ttrx.`T_ID`,ttrx.`T_ID`\n" +
//",ttrx.`CAT_ID`,ttrx.`INVOICE_ID` FROM customer c\n" +
//"INNER JOIN `transaction` trx ON trx.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
//"INNER JOIN ttl_transaction ttrx ON ttrx.`T_ID`=trx.`T_ID`\n" +
//"INNER JOIN product_category cat ON cat.`CAT_ID`=ttrx.`CAT_ID`\n" +
//"INNER JOIN product p ON p.`P_ID`=cat.`P_ID`\n" +
//"WHERE trx.`CUSTOMER_ID`="+cid+" AND trx.`T_DATE`='"+dt+"'\n" +
//"GROUP BY trx.`T_ID`";
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
                        
			 resultado1 = statement.executeQuery();
//                         System.out.println("rest : "+statement.executeQuery());
//                        while(resultado.next()){
//                             SaleEntryTrxModel entryTrxModel = new SaleEntryTrxModel(
//                                     resultado.getInt("CUSTOMER_ID"),
//                                     resultado.getInt("TRX_TOTAL"),
//                                     resultado.getInt("PAID"),
//                                     resultado.getInt("BALANCE"),
//                                     resultado.getInt("T_ID"),
//                                     resultado.getString("T_DATE"));
//                             entryTrxModels.add(entryTrxModel);
//                         }
                        
//                        resultado =null;
//                        statement = conn.prepareStatement(query);
//			 resultado = statement.executeQuery();
                        while(resultado1.next()){
//                            System.out.println("rretger");
                            SaleCatelogModel catelogModel = new SaleCatelogModel(
                                    new ProductCategoryModel(
                                            resultado1.getString("CATEGORY_TYPE"),
                                            resultado1.getString("NAME"),
                                            true,
                                            resultado1.getString("TRX_TOTAL"),
                                            resultado1.getInt("CAT_ID"),
                                            resultado1.getInt("CUSTOMER_ID"),
                                            new ProductEntryModel(
                                                    resultado1.getString("PRODUCT_NAME"),
                                                    resultado1.getString("T_DATE"), true, query,resultado1.getInt("PAID") )),
                                    new SaleTransactionModel(
                                            resultado1.getInt("QNTY"),
                                            resultado1.getInt("UNIT_COST"),
                                            resultado1.getInt("TOTAL"),
                                            resultado1.getInt("CAT_ID"),
                                            resultado1.getInt("TT_ID"),
                                            resultado1.getInt("LOAD_ID")),
                                    resultado1.getInt("LOAD_ID"));
//                            System.err.println("ttid : "+resultado1.getInt("TT_ID"));
//                            System.err.println("invid : "+resultado1.getInt("INVOICE_ID"));
                            transactionModels.add(catelogModel);
                            
                        }
//                        v.add(transactionModels);
                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's getCustomerTrxToUpdate method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return transactionModels;

        
        
    }
    
    public List<InvoiceModel> getProductQntyByCatId(int productcat){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                List<InvoiceModel> listofModel = new ArrayList();
                int tId = 0;
		String query = "select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`)) as stock,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"WHERE ld.`CAT_ID`="+productcat+" AND ld.`ACTIVE`=true";
                
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
                                     resultado.getString("INV_DATE"),
                                     resultado.getInt("LOAD_ID"), 
                                     resultado.getInt("SUPPLIER_ID"), 
                                     resultado.getString("TRUCK_NO"),
                                     resultado.getString("TRANSPORT"),
                                     true
                             );
                             
                             if(resultado.getInt("QNTY")==resultado.getInt("qntysold")){
                                 
                                 String a = "UPDATE load_details SET ACTIVE = false WHERE LOAD_ID="+resultado.getInt("LOAD_ID");
                                 statement = conn.prepareStatement(a);
                                 statement.executeUpdate();
                             } else {
                                 listofModel.add(invoiceModel);
                             }
                             
                             
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
    Fruit.Common.CommonMethods cm = new CommonMethods();
    
    
    public LinkedList<InvoiceModel> getProductQntyByP_Id(int productcat, String dt){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                LinkedList<InvoiceModel> listofModel = new LinkedList();
                int tId = 0;
		String query = "select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"WHERE ld.`P_ID`="+productcat+" AND inv.INV_DATE<= '"+dt+"' AND ld.`ACTIVE`=true";
                
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
                                     resultado.getString("INV_DATE"),
                                     resultado.getInt("LOAD_ID"), 
                                     resultado.getInt("SUPPLIER_ID"), 
                                     resultado.getString("TRUCK_NO")+" : "+cm.dateviewformat(resultado.getDate("INV_DATE")),
                                     resultado.getString("TRANSPORT"),
                                     true
                             );
                             
                             if(resultado.getInt("QNTY")==resultado.getInt("qntysold")){
                                 
                                 String a = "UPDATE load_details SET ACTIVE = false WHERE LOAD_ID="+resultado.getInt("LOAD_ID");
                                 statement = conn.prepareStatement(a);
                                 statement.executeUpdate();
                             } else {
                                 listofModel.add(invoiceModel);
                             }
                             
                             
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
    public List<InvoiceModel> getProductQntyByP_Id_Date(int productcat,String dt){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                List<InvoiceModel> listofModel = new ArrayList();
                int tId = 0;
		String query = "select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"WHERE ld.`P_ID`="+productcat+" AND inv.INV_DATE<= '"+dt+"' AND ld.`ACTIVE`=true";
                
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
                                     resultado.getString("INV_DATE"),
                                     resultado.getInt("LOAD_ID"), 
                                     resultado.getInt("SUPPLIER_ID"), 
                                     resultado.getString("TRUCK_NO")+" : "+cm.dateviewformat(resultado.getDate("INV_DATE")),
                                     resultado.getString("TRANSPORT"),
                                     true
                             );
                             
                             if(resultado.getInt("QNTY")==resultado.getInt("qntysold")){
                                 
                                 String a = "UPDATE load_details SET ACTIVE = false WHERE LOAD_ID="+resultado.getInt("LOAD_ID");
                                 statement = conn.prepareStatement(a);
                                 statement.executeUpdate();
                             } else {
                                 listofModel.add(invoiceModel);
                             }
                             
                             
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
    public int getProductStockByP_Id(int productcat, int ldid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int listofModel = 0;
                int tId = 0;
		String query = "select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"WHERE ld.`P_ID`="+productcat+" AND ld.INVOICE_ID="+ldid+" AND ld.`ACTIVE`=true";
                
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
                                     resultado.getString("INV_DATE"),
                                     resultado.getInt("LOAD_ID"), 
                                     resultado.getInt("SUPPLIER_ID"), 
                                     resultado.getString("TRUCK_NO")+" : "+cm.dateviewformat(resultado.getDate("INV_DATE")),
                                     resultado.getString("TRANSPORT"),
                                     true
                             );
                             
                             listofModel = resultado.getInt("stock");
                             if(resultado.getInt("QNTY")==resultado.getInt("qntysold")){
                                 
                                 String a = "UPDATE load_details SET ACTIVE = false WHERE LOAD_ID="+resultado.getInt("LOAD_ID");
                                 statement = conn.prepareStatement(a);
                                 statement.executeUpdate();
                             } else {
                                 
                             }
                             
                             
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
    
    
     public int getStockByLoadID(int productcat, int ldid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int listofModel = 0;
                int tId = 0;
		String query = "select (ld.`QNTY`-\n" +
"    IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"    WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"    (SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"    WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock from invoice inv\n" +
"    INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"    WHERE ld.`LOAD_ID`="+ldid;

                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         while(resultado.next()){
//                             InvoiceModel invoiceModel = new InvoiceModel(
//                                     resultado.getInt("INVOICE_ID"), 
//                                     resultado.getInt("QNTY"), 
//                                     resultado.getInt("stock"), 
//                                     resultado.getInt("qntysold"), 
//                                     resultado.getString("INV_DATE"),
//                                     resultado.getInt("LOAD_ID"), 
//                                     resultado.getInt("SUPPLIER_ID"), 
//                                     resultado.getString("TRUCK_NO")+" : "+cm.dateviewformat(resultado.getDate("INV_DATE")),
//                                     resultado.getString("TRANSPORT"),
//                                     true
//                             );
                             
                             listofModel = resultado.getInt("stock");
                             
                             
                             
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
