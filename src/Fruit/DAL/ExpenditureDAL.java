/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.DAL;

import Fruit.Common.DbConnection;
import Fruit.Model.ExpenditureAmountModel;
import Fruit.Model.ExpenditureModel;
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
public class ExpenditureDAL {

    public ExpenditureDAL() {
    }
    
    public int addExpenditureName(ExpenditureModel em){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "INSERT INTO tbl_expenditure(EXPENDITURE,CREATED,ACTIVE) VALUES('"
				+ em.getExpend_Name()
				+ "', NOW(),true)";
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
                        if(resultado != null){
                            resultado.next();
                            tId = resultado.getInt(1);
                            
                                    
                        }
			resultMsg = "Product added";
                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's addCustomerTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    
    public int updateExpenditureName(ExpenditureModel em){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "UPDATE tbl_expenditure SET EXPENDITURE ='"+ em.getExpend_Name()
				+ "' where  idtbl_expenditure="+em.getExpId();
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
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
    public int deleteExpenditureName(ExpenditureModel em){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "DELETE FROM tbl_expenditure where  idtbl_expenditure="+em.getExpId();
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
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
    
    public int deActivateExpenditureName(ExpenditureModel em){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "UPDATE tbl_expenditure SET ACTIVE="+em.isActive()+" where  idtbl_expenditure="+em.getExpId();
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
                        if(resultado != null){
                            resultado.next();
                            tId = resultado.getInt(1);
                            
                                    
                        }
			resultMsg = "Product deActivated..";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's deActivateExpenditureName method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    
    public List<ExpenditureModel> getExpenditureName(){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "SELECT * FROM tbl_expenditure where active=true";
                List<ExpenditureModel> ems = new ArrayList<>();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
                        while(resultado.next()){
                            ExpenditureModel em = new ExpenditureModel(
                                    resultado.getInt("idtbl_expenditure"),
                                    resultado.getString("EXPENDITURE"),
                                    resultado.getString("CREATED"),
                                    query);
                            
                            ems.add(em);
                                    
                        }
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ExpenditureDAL's getExpenditureName method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return ems;

        
        
    }
    
    public int addExpenditureAmount(ExpenditureAmountModel em){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "INSERT INTO exp_amount(EXP_AMOUNT,idtbl_expenditure,exp_date) VALUES('"
				+ em.getAmt()
				+ "', "+em.getExpenditureModel().getExpId()
                                +", '"+em.getExpDate()
                                +"')";
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
                        if(resultado != null){
                            resultado.next();
                            tId = resultado.getInt(1);
                            
                                    
                        }
			resultMsg = "Product added";
                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's addCustomerTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    
    
    public int updateExpenditureAmount(ExpenditureAmountModel em){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "UPDATE exp_amount SET EXP_AMOUNT ='"+ em.getAmt()
				+ "',idtbl_expenditure="+em.getExpenditureModel().getExpId()+","
                        + "exp_date= '"+em.getExpDate()+"' "
                        + "where  idexp_amount="+em.getExpAmtId();
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
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
    public int deleteExpenditureAmount(ExpenditureAmountModel em){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "DELETE FROM exp_amount where  idexp_amount="+em.getExpAmtId();
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
                        if(resultado != null){
                            resultado.next();
                            tId = resultado.getInt(1);
                            
                                    
                        }
			resultMsg = "Product added";
                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in TrxDAL's addCustomerTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    
    public List<ExpenditureAmountModel> getExpenditureAmount(String dt){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "SELECT * FROM exp_amount amt inner join tbl_expenditure exp ON"
                        + " amt.idtbl_expenditure=exp.idtbl_expenditure WHERE amt.exp_date='"+dt+"'";
                List<ExpenditureAmountModel> ems = new ArrayList<>();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
                        while(resultado.next()){
                            ExpenditureAmountModel em = new ExpenditureAmountModel(
                                    resultado.getInt("idexp_amount"),
                                    new ExpenditureModel(resultado.getInt("idtbl_expenditure"), resultado.getString("EXPENDITURE"), query, dt),
                                    resultado.getString("EXP_AMOUNT"),
                                    resultado.getString("exp_date"),
                                    resultado.getString("UPDATED")
                                    );
                            
                            ems.add(em);
                                    
                        }
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ExpenditureDAL's getExpenditureName method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return ems;

        
        
    }
//    Fruit.Common.CommonMethods cm = new CommonMethods();
    public List<ExpenditureAmountModel> getExpenditureAmount(String dt1, String dt2){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "SELECT en.idtbl_expenditure,exp.idexp_amount,en.`EXPENDITURE`,exp.UPDATED,SUM(exp.`EXP_AMOUNT`) amt,exp.exp_date,"
                        + "(SELECT SUM(exp.`EXP_AMOUNT`) FROM exp_amount exp \n" +
"INNER JOIN tbl_expenditure en ON en.idtbl_expenditure=exp.idtbl_expenditure\n" +
" WHERE exp.exp_date between '"+dt1+"' and '"+dt2+"') as tot FROM exp_amount exp \n" +
"INNER JOIN tbl_expenditure en ON en.idtbl_expenditure=exp.idtbl_expenditure\n" +
" WHERE exp.exp_date between '"+dt1+"' and '"+dt2+"' GROUP BY en.idtbl_expenditure,exp.exp_date";
                List<ExpenditureAmountModel> ems = new ArrayList<>();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
                        while(resultado.next()){
                            ExpenditureAmountModel em = new ExpenditureAmountModel(
                                    resultado.getInt("idexp_amount"),
                                    new ExpenditureModel(resultado.getInt("idtbl_expenditure"), resultado.getString("EXPENDITURE"), query, resultado.getString("tot")),
                                    resultado.getString("amt"),
                                    (resultado.getString("exp_date")),
                                    resultado.getString("UPDATED")
                                    );
                            
                            ems.add(em);
                                    
                        }
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ExpenditureDAL's getExpenditureName method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return ems;

        
        
    }
    
    
}
