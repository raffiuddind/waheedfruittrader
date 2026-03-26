/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.DAL;

import Fruit.Common.CommonMethods;
import Fruit.Common.DbConnection;
import Fruit.Model.RegistrationModel;
import com.User.StatementModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import practise.WhAPIMessageSender;

/**
 *
 * @author raffiuddin
 */
public class CustomerDAL {
    public String addCustomer(RegistrationModel registrationModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "INSERT INTO customer(NAME, CREATED, MOBILE, MOBILE2, PHOTO, CITY, PIN, STATE, NATIV, PLOT_NO, ACTIVE) VALUES('"
				+ registrationModel.getName()
				+ "', NOW(),'"
				+ registrationModel.getMobile()
                                + "', '"
                                + registrationModel.getMobile2()
                                + "', NULL,'"
                                + registrationModel.getCity()
                                + "', "
                                + registrationModel.getPinNo()
                                + ", '"
                                + registrationModel.getState()
                                + "', '"
                                + registrationModel.getNativ()
                                + "', '"
                                + registrationModel.getPlotNo()
                                + "', "
                                + true
				+ ")";
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Su added";
                        JOptionPane.showMessageDialog(null, "Customer inserted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in CustomerRegistrationDAL's add method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
    public int getCustomerByName(RegistrationModel registrationModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int result = 0;
		String query = "select * from customer WHERE NAME='"+registrationModel.getName()+"' AND ACTIVE =true ORDER BY NAME";
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         if(resultado.next()){
                             result = 1;
                         }
			resultMsg = "Su added";
//                        JOptionPane.showMessageDialog(null, "Customer inserted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in CustomerRegistrationDAL's add method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return result;

        
        
    }
    
    public String updateCustomer(RegistrationModel registrationModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE customer SET NAME = '"
				+ registrationModel.getName()
				+ "', MOBILE = '"
				+ registrationModel.getMobile()
                                + "', MOBILE2 = '"
                                + registrationModel.getMobile2()
                                + "', PHOTO ="
                                +registrationModel.getPhoto()
                                + ",CITY ='"
                                +registrationModel.getCity()
                                +"',PIN ="
                                +registrationModel.getPinNo()
                                +",STATE='"
                                +registrationModel.getState()
                                +"',PLOT_NO='"
                                +registrationModel.getPlotNo()
                                +"',NATIV='"+registrationModel.getNativ()+"', ACTIVE="+registrationModel.isActive()+""
                        + " WHERE CUSTOMER_ID = "+registrationModel.getCsID();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product updated";
                        JOptionPane.showMessageDialog(null, "Updated successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in CustomerDAL's update method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
    public String deleteCustomer(RegistrationModel registrationModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE customer SET  ACTIVE = "
                                + registrationModel.isActive()
				+ " WHERE CUSTOMER_ID = "+registrationModel.getCsID();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product deleted";
                        JOptionPane.showMessageDialog(null, "Customer deleted successfully..!");
		} catch (Exception e) {
			System.err.println("Exception occured in CustomerDAL's delete method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
    public ArrayList<StatementModel> getCustomerBalance(String l, String custId){
        Fruit.Common.CommonMethods cm = new CommonMethods();
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                ArrayList<StatementModel> lstOfProducts = new ArrayList<StatementModel>();
		String query = null;
                if(l.equalsIgnoreCase("all")){
                    if(custId==null)
                    query = "select c.`CUSTOMER_ID`,COUNT(tr.`T_ID`) as cnt,c.`NAME`,c.`CITY`,c.`MOBILE`,IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total,\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`)) as pd,\n" +
"(IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) -\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`))\n" +
") as balance,(SELECT T_DATE FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID` ORDER BY t.T_ID desc LIMIT 1) as ddate,"
                            + "(SELECT SUM(ttt.`TOTAL`) FROM ttl_transaction ttt \n" +
"INNER JOIN `transaction` t1 ON t1.`T_ID`=ttt.`T_ID` WHERE t1.`CUSTOMER_ID`=tr.`CUSTOMER_ID` AND\n" +
" t1.`DETAILS`='Pre. Balance') as pre from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE c.`ACTIVE`=true GROUP BY c.`CUSTOMER_ID` ORDER BY c.`NAME`";
                    else{
                        query = "select c.`CUSTOMER_ID`,COUNT(tr.`T_ID`) as cnt,c.`NAME`,c.`CITY`,c.`MOBILE`,IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total,\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`)) as pd,\n" +
"(IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) -\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`))\n" +
") as balance,(SELECT T_DATE FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID` ORDER BY t.T_ID desc LIMIT 1) as ddate,"
                            + "(SELECT SUM(ttt.`TOTAL`) FROM ttl_transaction ttt \n" +
"INNER JOIN `transaction` t1 ON t1.`T_ID`=ttt.`T_ID` WHERE t1.`CUSTOMER_ID`=tr.`CUSTOMER_ID` AND\n" +
" t1.`DETAILS`='Pre. Balance') as pre from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE c.`ACTIVE`=true AND c.`CUSTOMER_ID`="+custId+" GROUP BY c.`CUSTOMER_ID` ORDER BY c.`NAME`"; }
                } else {
                    if(null == custId)
                    query = "select c.`CUSTOMER_ID`,COUNT(tr.`T_ID`) as cnt,c.`NAME`,c.`CITY`,c.`MOBILE`,IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total,\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`)) as pd,\n" +
"(IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) -\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`))\n" +
") as balance,(SELECT T_DATE FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID` ORDER BY t.T_ID desc LIMIT 1) as ddate,"
                            + "(SELECT SUM(ttt.`TOTAL`) FROM ttl_transaction ttt \n" +
"INNER JOIN `transaction` t1 ON t1.`T_ID`=ttt.`T_ID` WHERE t1.`CUSTOMER_ID`=tr.`CUSTOMER_ID` AND\n" +
" t1.`DETAILS`='Pre. Balance') as pre from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE c.`ACTIVE`=true AND c.NATIV = '"+l+"' GROUP BY c.`CUSTOMER_ID` ORDER BY c.`NAME`";
                    else {
                        query = "select c.`CUSTOMER_ID`,COUNT(tr.`T_ID`) as cnt,c.`NAME`,c.`CITY`,c.`MOBILE`,IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total,\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`)) as pd,\n" +
"(IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) -\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`))\n" +
") as balance,(SELECT T_DATE FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID` ORDER BY t.T_ID desc LIMIT 1) as ddate,"
                            + "(SELECT SUM(ttt.`TOTAL`) FROM ttl_transaction ttt \n" +
"INNER JOIN `transaction` t1 ON t1.`T_ID`=ttt.`T_ID` WHERE t1.`CUSTOMER_ID`=tr.`CUSTOMER_ID` AND\n" +
" t1.`DETAILS`='Pre. Balance') as pre from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE c.`ACTIVE`=true AND c.NATIV = '"+l+"' AND c.`CUSTOMER_ID`="+custId+" GROUP BY c.`CUSTOMER_ID` ORDER BY c.`NAME`";
                    }
                }
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
                        int r=1;
                        System.out.println("sql "+query);
                        while(resultado.next()){
                            if(resultado.getInt("balance")>0){
                            StatementModel sm = new StatementModel(
                                    resultado.getInt("CUSTOMER_ID"),
                                    
                                    cm.rupee(resultado.getString("pre")),
                                    resultado.getString("NAME"),
                                    cm.dateviewformat(resultado.getDate("ddate")),
                                    resultado.getString("MOBILE"),
                                    
                                    cm.rupee(resultado.getString("balance"))
                                    );
                            r++;
                            lstOfProducts.add(sm);
                            }
                        }
			resultMsg = "Product deleted";
//                        JOptionPane.showMessageDialog(null, "Customer deleted successfully..!");
		} catch (Exception e) {
			System.err.println("Exception occured in CustomerDAL's getCustomerBalance method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn, statement, resultado);
		}
        return lstOfProducts;

        
        
    }
    public List<StatementModel> getDeadCustomerBalance(String l){
        Fruit.Common.CommonMethods cm = new CommonMethods();
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                List<StatementModel> lstOfProducts = new ArrayList<StatementModel>();
		String query = null;
                if(l.equalsIgnoreCase("all")){
                    query = "select COUNT(tr.`T_ID`) as cnt,c.`NAME`,c.`CITY`,c.`MOBILE`,IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total,\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`)) as pd,\n" +
"(IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) -\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`))\n" +
") as balance,(SELECT T_DATE FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID` ORDER BY t.T_ID desc LIMIT 1) as ddate from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE c.`ACTIVE`=false GROUP BY c.`CUSTOMER_ID` ORDER BY c.`NAME`";
                } else {
                    query = "select COUNT(tr.`T_ID`) as cnt,c.`NAME`,c.`CITY`,c.`MOBILE`,IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total,\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`)) as pd,\n" +
"(IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) -\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`))\n" +
") as balance,(SELECT T_DATE FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID` ORDER BY t.T_ID desc LIMIT 1) as ddate from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE c.`ACTIVE`=false AND c.NATIV = '"+l+"' GROUP BY c.`CUSTOMER_ID` ORDER BY c.`NAME`";
                }
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
                        int r=1;
                        while(resultado.next()){
                            if(resultado.getInt("balance")>0){
                            StatementModel sm = new StatementModel(
                                    r,
                                    resultado.getString("CITY"),
                                    resultado.getString("NAME"),
                                    cm.dateviewformat(resultado.getDate("ddate")),
                                    resultado.getString("MOBILE"),
                                    
                                    cm.rupee(resultado.getString("balance"))
                                    );
                            r++;
                            lstOfProducts.add(sm);
                            }
                        }
			resultMsg = "Product deleted";
//                        JOptionPane.showMessageDialog(null, "Customer deleted successfully..!");
		} catch (Exception e) {
			System.err.println("Exception occured in CustomerDAL's getCustomerBalance method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return lstOfProducts;

        
        
    }
    
    public String getTotalBalance(String s){
        Fruit.Common.CommonMethods cm = new CommonMethods();
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String result = "0";
                List<StatementModel> lstOfProducts = new ArrayList<StatementModel>();
		String query = null;
                if(s.equalsIgnoreCase("all")){
                    query = "select IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total,\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  ) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  )) as pd,\n" +
"((IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) -\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  ) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  ))\n" +
")) as balance from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE c.`ACTIVE`=true ";
                } else{
                    query = "select IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) as total,\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  ) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  )) as pd,\n" +
"((IF(SUM(ttrx.`TOTAL`) is null,0,SUM(ttrx.`TOTAL`)) -\n" +
"IF((SELECT SUM(t.`PAID`) FROM `transaction` t  ) is null,0,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  ))\n" +
")) as balance from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE c.`ACTIVE`=true AND c.NATIV='"+s+"' ";
                }
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
                        int r=1;
                        while(resultado.next()){
                            result = cm.rupee(resultado.getString("balance"));
                        }
			resultMsg = "Product deleted";
//                        JOptionPane.showMessageDialog(null, "Customer deleted successfully..!");
		} catch (Exception e) {
			System.err.println("Exception occured in CustomerDAL's delete method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return result;

        
        
    }
    
    public List<RegistrationModel> getCustomerList() {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
                List<RegistrationModel> lstOfProducts = new ArrayList<RegistrationModel>();
		String query = "select * from customer WHERE ACTIVE =true ORDER BY NAME ";
		try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();
			while (results.next()) {
				RegistrationModel mediumObj = new RegistrationModel(
                                                results.getInt("CUSTOMER_ID"),
                                                results.getString("NAME"),
                                                results.getString("MOBILE"),
                                                results.getString("MOBILE2"),
                                                results.getBytes("PHOTO"),
                                                results.getString("CITY"),
                                                results.getInt("PIN"),
                                                results.getString("STATE"),
                                                results.getString("PLOT_NO"),
                                                results.getBoolean("ACTIVE"),
                                                results.getString("NATIV")
                                                
                                                );
				
//                            System.out.println("state : "+results.getBoolean("ACTIVE"));
				lstOfProducts.add(mediumObj);
			}
		} catch (Exception e) {
			System.err
					.println("Exception occured in CustomerDAL's getCustomers method : "
							+ e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}

		return lstOfProducts;
	}
    public List<RegistrationModel> getDeletedCustomerList() {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		List<RegistrationModel> lstOfProducts = new ArrayList<RegistrationModel>();
		String query = "select * from customer WHERE ACTIVE =false ORDER BY NAME ";
		try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();
			while (results.next()) {
				RegistrationModel mediumObj = new RegistrationModel(
                                                results.getInt("CUSTOMER_ID"),
                                                results.getString("NAME"),
                                                results.getString("MOBILE"),
                                                results.getString("MOBILE2"),
                                                results.getBytes("PHOTO"),
                                                results.getString("CITY"),
                                                results.getInt("PIN"),
                                                results.getString("STATE"),
                                                results.getString("PLOT_NO"),
                                                results.getBoolean("ACTIVE"),
                                                results.getString("NATIV")
                                                
                                                );
						

				lstOfProducts.add(mediumObj);
			}
		} catch (Exception e) {
			System.err
					.println("Exception occured in CustomerDAL's getCustomers method : "
							+ e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}

		return lstOfProducts;
	}
    public List<RegistrationModel> getCustomerListByID(int id) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		List<RegistrationModel> lstOfProducts = new ArrayList<RegistrationModel>();
		String query = "select * from customer WHERE CUSTOMER_ID="+id+" AND ACTIVE =true ORDER BY NAME ";
		try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();
			while (results.next()) {
				RegistrationModel mediumObj = new RegistrationModel(
                                                results.getInt("CUSTOMER_ID"),
                                                results.getString("NAME"),
                                                results.getString("MOBILE"),
                                                results.getString("MOBILE2"),
                                                results.getBytes("PHOTO"),
                                                results.getString("CITY"),
                                                results.getInt("PIN"),
                                                results.getString("STATE"),
                                                results.getString("PLOT_NO"),
                                                results.getBoolean("ACTIVE"),
                                                results.getString("NATIV")
                                                
                                                );
						

				lstOfProducts.add(mediumObj);
			}
		} catch (Exception e) {
			System.err
					.println("Exception occured in CustomerDAL's getCustomers method : "
							+ e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}

		return lstOfProducts;
	}
    public List<RegistrationModel> getDeletedCustomerListByID(int id) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		List<RegistrationModel> lstOfProducts = new ArrayList<RegistrationModel>();
		String query = "select * from customer WHERE CUSTOMER_ID="+id+" AND ACTIVE =false ORDER BY NAME ";
		try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();
			while (results.next()) {
				RegistrationModel mediumObj = new RegistrationModel(
                                                results.getInt("CUSTOMER_ID"),
                                                results.getString("NAME"),
                                                results.getString("MOBILE"),
                                                results.getString("MOBILE2"),
                                                results.getBytes("PHOTO"),
                                                results.getString("CITY"),
                                                results.getInt("PIN"),
                                                results.getString("STATE"),
                                                results.getString("PLOT_NO"),
                                                results.getBoolean("ACTIVE"),
                                                results.getString("NATIV")
                                                
                                                );
						

				lstOfProducts.add(mediumObj);
			}
		} catch (Exception e) {
			System.err
					.println("Exception occured in CustomerDAL's getCustomers method : "
							+ e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}

		return lstOfProducts;
	}
}
