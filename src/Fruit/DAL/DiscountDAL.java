/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.DAL;

import Fruit.Common.CommonMethods;
import Fruit.Common.DbConnection;
import Fruit.Model.DiscountModel;
import groovyjarjarantlr.CommonAST;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Raffiuddin
 */
public class DiscountDAL {
    
    CommonMethods methods = new CommonMethods();
    public int addDiscount_Damage(DiscountModel dm){
        int result = 0;
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg ="";
                String query ="";
                try {
                if(dm.getMsg().equalsIgnoreCase("Pre. Balance")){
                    
                     query = "INSERT INTO `transaction`(T_DATE,BALANCE, CUSTOMER_ID, DETAILS) VALUES('"
				+ dm.getDt()
                                + "', "
                                + dm.getBal()
                                + ", "
                                + dm.getcId()
                                + ", '"
                                + dm.getMsg()
                                + "')";
                     conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			 result = statement.executeUpdate();
                    ResultSet r = statement.getGeneratedKeys();
                    if(r.next()){
                        System.out.println("pre bal");
                        r.getInt(1);
                        String q = "INSERT INTO `ttl_transaction`(TOTAL, T_ID) VALUES("
                                + dm.getAmount()
                                +","
                                +r.getInt(1)+")";
                        statement = null;
                        statement = conn.prepareStatement(q);
                        statement.executeUpdate();
                        
                    }
                } else {
		 query = "INSERT INTO `transaction`(PAID, BALANCE, T_DATE, CUSTOMER_ID, DETAILS) VALUES("
				+ dm.getAmount()
				+ ","
				+ dm.getBal()
                                + ", '"
                                + dm.getDt()
                                + "', "
                                + dm.getcId()
                                + ", '"
                                + dm.getMsg()
                                + "')";
                 conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 result = statement.executeUpdate();
                }
                
//			conn = DbConnection.getConnection();
//			statement = conn.prepareStatement(query);
//			int result = statement.executeUpdate();
			resultMsg = "Su added";
                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's addDiscount_Damage method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        
        return result;

        
        
    }
    public int updateDiscount_Damage(DiscountModel dm){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE `transaction` SET "
                        + "PAID="+ dm.getAmount()
                        + ", BALANCE="+ dm.getBal()
                        + ", T_DATE='"+ dm.getDt()
                        + "', CUSTOMER_ID="+ dm.getcId()
                        + ", DETAILS='"+ dm.getMsg()+"' WHERE T_ID="+dm.getId();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Su added";
                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's updateDiscount_Damage method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return 0;

        
        
    }
    public int updatePre_Balance(DiscountModel dm){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE `transaction` SET "
//                        + "PAID="+ dm.getAmount()
//                        + ", BALANCE="+ dm.getBal()
                        + " T_DATE='"+ dm.getDt()
//                        + "', CUSTOMER_ID="+ dm.getcId()
//                        + ", DETAILS='"+ dm.getMsg()+"' "
                        + "' WHERE T_ID="+dm.getId();
		String query1 = "UPDATE `ttl_transaction` SET "
                        + "TOTAL="+ dm.getAmount()
//                        + ", BALANCE="+ dm.getBal()
//                        + ", T_DATE='"+ dm.getDt()
//                        + "', CUSTOMER_ID="+ dm.getcId()
//                        + ", DETAILS='"+ dm.getMsg()+"' "
                        + " WHERE T_ID="+dm.getId();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			statement = conn.prepareStatement(query1);
			int result1 = statement.executeUpdate();
			resultMsg = "Su added";
                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's updateDiscount_Damage method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return 0;

        
        
    }
    public int updatePaid(DiscountModel dm){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE `transaction` SET "
                        + "PAID="+ dm.getAmount()
//                        + ", BALANCE="+ dm.getBal()
                        + ", T_DATE='"+ dm.getDt()
//                        + "', CUSTOMER_ID="+ dm.getcId()
//                        + ", DETAILS='"+ dm.getMsg()+"'"
                        + "' WHERE T_ID="+dm.getId();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Su added";
                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's updatePaid method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return 0;

        
        
    }
    public int deleteDiscount_Damage(DiscountModel dm){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "DELETE FROM `transaction` WHERE T_ID="+dm.getId();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Su added";
                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's deleteDiscount_Damage method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return 0;

        
        
    }
    public int deletePre_Balance(DiscountModel dm){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "DELETE FROM `transaction` WHERE T_ID="+dm.getId();
		String query1 = "DELETE FROM `ttl_transaction` WHERE T_ID="+dm.getId();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query1);
			int result = statement.executeUpdate();
			statement = conn.prepareStatement(query);
			int result1 = statement.executeUpdate();
			resultMsg = "Su added";
                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's deleteDiscount_Damage method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return 0;

        
        
    }
    public int updateAfterDiscount_Damage(int amt, int cid, int tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE `transaction` SET BALANCE=BALANCE+"+amt+" WHERE CUSTOMER_ID="+cid+" AND T_ID>"+tid;
                try {
                        System.out.println(query);
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Su added";
//                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's addDiscount_Damage method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return 0;

        
        
    }
    public int updateAfterPrev_Balance(int amt, int cid, int tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE `transaction` SET BALANCE=BALANCE-"+amt+" WHERE CUSTOMER_ID="+cid+" AND T_ID>"+tid;
                try {
                        System.out.println(query);
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Su added";
//                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's addDiscount_Damage method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return 0;

        
        
    }
    public int updateAfterPaid(int amt, int cid, int tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE `transaction` SET BALANCE=BALANCE+"+amt+" WHERE CUSTOMER_ID="+cid+" AND T_ID>="+tid;
                try {
                        System.out.println(query);
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Su added";
//                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's addDiscount_Damage method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return 0;

        
        
    }
    public List<DiscountModel> getDiscount_Damage(int cid, String dt1, String dt2){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                List<DiscountModel> discountModels = new ArrayList<>();
                System.out.println("Customer id : "+cid);
                String custidLogic = " AND tr.CUSTOMER_ID="+cid;
		String query = null;
                if(dt1 == null && dt2 == null){
                    query = "SELECT tr.CUSTOMER_ID,tr.T_ID,tr.BALANCE,c.NAME,tr.T_DATE,tr.DETAILS,tr.PAID FROM business_db.transaction tr inner join customer c on c.CUSTOMER_ID=tr.CUSTOMER_ID\n" +
" where (tr.DETAILS='Damage' or tr.DETAILS='Discount') \n" +
"and tr.T_DATE between '' and now()";
                    if(cid!=0)
                        query = query + custidLogic;
                   
                }
                if(dt1 == null && dt2 != null){
                    query = "SELECT tr.CUSTOMER_ID,tr.T_ID,tr.BALANCE,c.NAME,tr.T_DATE,tr.DETAILS,tr.PAID FROM business_db.transaction tr inner join customer c on c.CUSTOMER_ID=tr.CUSTOMER_ID\n" +
" where (tr.DETAILS='Damage' or tr.DETAILS='Discount') \n" +
"and tr.T_DATE between '' and '"+dt2+"'";
                    if(cid!=0)
                        query = query + custidLogic;
                }
                if(dt1 != null && dt2 == null){
                    query = "SELECT tr.CUSTOMER_ID,tr.T_ID,tr.BALANCE,c.NAME,tr.T_DATE,tr.DETAILS,tr.PAID FROM business_db.transaction tr inner join customer c on c.CUSTOMER_ID=tr.CUSTOMER_ID\n" +
" where (tr.DETAILS='Damage' or tr.DETAILS='Discount') \n" +
"and tr.T_DATE between '"+dt1+"' and NOW()";
                    if(cid!=0)
                        query = query + custidLogic;
                    
                    
                }    
                if(dt1 != null && dt2 != null){
                    query = "SELECT tr.CUSTOMER_ID,tr.T_ID,tr.BALANCE,c.NAME,tr.T_DATE,tr.DETAILS,tr.PAID FROM business_db.transaction tr inner join customer c on c.CUSTOMER_ID=tr.CUSTOMER_ID\n" +
" where (tr.DETAILS='Damage' or tr.DETAILS='Discount') \n" +
"and tr.T_DATE between '"+dt1+"' AND '"+dt2+"'";
                    if(cid!=0)
                        query = query + custidLogic;
                    

                }
                    
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
			resultMsg = "Su added";
                        System.out.println(query);
                        while(resultado.next()){
                            DiscountModel dm = new DiscountModel(
                                    resultado.getInt("T_ID"), 
                                    resultado.getInt("PAID"), 
                                    resultado.getInt("BALANCE"), 
                                    resultado.getInt("CUSTOMER_ID"), 
                                    resultado.getString("DETAILS"), 
                                    (resultado.getString("T_DATE")),
                                    resultado.getString("NAME")
                                    );
                            discountModels.add(dm);
                        }
//                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's addDiscount_Damage method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return discountModels;

        
        
    }
    public List<DiscountModel> getPrev_Balance(int cid, String dt1, String dt2){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                List<DiscountModel> discountModels = new ArrayList<>();
                
		String query = null;
                if(dt1 == null && dt2 == null)
                query = "SELECT tr.`T_ID`,tr.`T_DATE`,ttr.`TOTAL`,ttr.`TT_ID`,tr.DETAILS FROM `transaction`tr\n" +
"INNER JOIN ttl_transaction ttr ON ttr.`T_ID`=tr.`T_ID` WHERE tr.TRX_TOTAL is null \n" +
"AND tr.DETAILS='Pre. Balance' \n" +
" AND tr.CUSTOMER_ID="+cid;
                if(dt1 == null && dt2 != null)
                query = "SELECT tr.`T_ID`,tr.`T_DATE`,ttr.`TOTAL`,ttr.`TT_ID`,tr.DETAILS FROM `transaction`tr\n" +
"INNER JOIN ttl_transaction ttr ON ttr.`T_ID`=tr.`T_ID` WHERE tr.TRX_TOTAL is null \n" +
"AND tr.DETAILS='Pre. Balance' AND tr.`T_DATE` BETWEEN '' AND '"+dt2+"'\n" +
" AND tr.CUSTOMER_ID="+cid;
                if(dt1 != null && dt2 == null)
                query = "SELECT tr.`T_ID`,tr.`T_DATE`,ttr.`TOTAL`,ttr.`TT_ID`,tr.DETAILS FROM `transaction`tr\n" +
"INNER JOIN ttl_transaction ttr ON ttr.`T_ID`=tr.`T_ID` WHERE tr.TRX_TOTAL is null \n" +
"AND tr.DETAILS='Pre. Balance' AND tr.`T_DATE` BETWEEN '"+dt1+"' AND ''\n" +
" AND tr.CUSTOMER_ID="+cid;
                if(dt1 != null && dt2 != null)
                query = "SELECT tr.`T_ID`,tr.`T_DATE`,ttr.`TOTAL`,ttr.`TT_ID`,tr.DETAILS FROM `transaction`tr\n" +
"INNER JOIN ttl_transaction ttr ON ttr.`T_ID`=tr.`T_ID` WHERE tr.TRX_TOTAL is null \n" +
"AND tr.DETAILS='Pre. Balance' AND tr.`T_DATE` BETWEEN '"+dt1+"' AND '"+dt2+"'\n" +
" AND tr.CUSTOMER_ID="+cid;
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
			resultMsg = "Su added";
                        System.out.println(query);
                        while(resultado.next()){
                            DiscountModel dm = new DiscountModel(
                                    resultado.getInt("T_ID"), 
                                    resultado.getInt("TOTAL"), 
                                    0, 
                                    0, 
                                    resultado.getString("DETAILS"), 
                                    resultado.getString("T_DATE"),
                                    null
                                    );
                            discountModels.add(dm);
                        }
//                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's addDiscount_Damage method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return discountModels;

        
        
    }
    public List<DiscountModel> getPaidDetails(int cid, String dt1, String dt2){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                List<DiscountModel> discountModels = new ArrayList<>();
                
		String query = null;
                if(dt1 == null && dt2 == null)
                query = "select `PAID`,`T_DATE`,`T_ID`,`DETAILS` from `transaction` WHERE \n" +
"(`DETAILS` is null OR `DETAILS`='Bank') AND `PAID`>0 AND `CUSTOMER_ID`="+cid;
                if(dt1 == null && dt2 != null)
                query = "select `PAID`,`T_DATE`,`T_ID`,`DETAILS` from `transaction` WHERE \n" +
"(`DETAILS` is null OR `DETAILS`='Bank') AND `PAID`>0 AND `CUSTOMER_ID`="+cid+" AND  T_DATE BETWEEN '' AND '"+dt2+"'";
                if(dt1 != null && dt2 == null)
                query = "select `PAID`,`T_DATE`,`T_ID`,`DETAILS` from `transaction` WHERE \n" +
"(`DETAILS` is null OR `DETAILS`='Bank') AND `PAID`>0 AND `CUSTOMER_ID`="+cid+" AND T_DATE BETWEEN '"+dt1+"' AND NOW() ";
                if(dt1 != null && dt2 != null)
                query = "select `PAID`,`T_DATE`,`T_ID`,`DETAILS` from `transaction` WHERE \n" +
"(`DETAILS` is null OR `DETAILS`='Bank') AND `PAID`>0 AND `CUSTOMER_ID`="+cid+" AND T_DATE BETWEEN '"+dt1+"' AND '"+dt2+"' ";
                try {
                    System.out.println(query);
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
			resultMsg = "Su added";
                        System.out.println(query);
                        while(resultado.next()){
                            DiscountModel dm = new DiscountModel(
                                    resultado.getInt("T_ID"), 
                                    resultado.getInt("PAID"), 0,0,resultado.getString("DETAILS"),
//                                    resultado.getInt("BALANCE"), 
//                                    resultado.getInt("CUSTOMER_ID"), 
//                                    resultado.getString("DETAILS"), 
                                    resultado.getString("T_DATE"),null
                                    );
                            discountModels.add(dm);
                        }
//                        JOptionPane.showMessageDialog(null, "Transaction  successfull..!");
		} catch (Exception e) {
			System.err.println("Exception occured in DiscountDAL's getPaidDetails method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return discountModels;

        
        
    }
    
}
