/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.DAL;

import Fruit.Common.CommonMethods;
import Fruit.Common.DbConnection;
import Fruit.Model.CashOnDateModel;
import Fruit.ReportModel.BalanceModel;
import com.User.StatementModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author Raffiuddin
 */
public class ReportDAL {

    Logger log = Logger.getLogger(ReportDAL.class);

    Fruit.Common.CommonMethods cm= new CommonMethods();
    public List<BalanceModel> getCustomerBalance(String l){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<BalanceModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = null;
                if(l.equalsIgnoreCase("all")){
                    
                query = "select c.`NAME`,c.`CITY`,c.`MOBILE`,SUM(ttrx.`TOTAL`) as total,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`)as paid,\n" +
"SUM(ttrx.`TOTAL`)-(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) as balance from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE c.`ACTIVE`=true GROUP BY c.`CUSTOMER_ID` ORDER BY c.`NAME`";
                
                } else {
                    
                query = "select c.`NAME`,c.`CITY`,c.`MOBILE`,SUM(ttrx.`TOTAL`) as total,\n" +
"(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`)as paid,\n" +
"SUM(ttrx.`TOTAL`)-(SELECT SUM(t.`PAID`) FROM `transaction` t  WHERE t.`CUSTOMER_ID`=c.`CUSTOMER_ID`) as balance from ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"WHERE c.`ACTIVE`=true AND c.NATIV='"+l+"' GROUP BY c.`CUSTOMER_ID` ORDER BY c.`NAME`";
                    
                }
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            BalanceModel bm = new BalanceModel(
                                    i, 
                                    resultado.getString("NAME"), 
                                    resultado.getString("CITY"), 
                                    resultado.getString("MOBILE"), 
                                    cm.rupee(resultado.getString("balance")) 
                                    );
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerBalance method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    public List<BalanceModel> getTaxes(){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<BalanceModel>  entryTrxModels = new ArrayList<BalanceModel>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = "select * from taxes";
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            BalanceModel bm = new BalanceModel(
                                    resultado.getInt("TAX_ID"), 
                                    resultado.getString("TAX_NAME"), 
                                    null, 
                                    null, 
                                    null); 
                            entryTrxModels.add(bm);
                                    
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerBalance method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    
    public int addLoadTax(int ldID,int taxID, String dt, int amt){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "INSERT INTO tax_to_load (LOAD_ID, TAX_ID, DT, Amount) VALUES("
				+ ldID
				+ ", "
				+ taxID
                                + ", '"
                                + dt
                                + "', "
                                + amt
                                + ")";
                
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
			System.err.println("Exception occured in InvoiceDAL's addSupplierTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public int deleteLoadTax(int ID){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
		String query = "DELETE FROM tax_to_load where ID="+ID;
				
				
                
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
			System.err.println("Exception occured in InvoiceDAL's addSupplierTrx method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return tId;

        
        
    }
    public List<BalanceModel> getLoadReportPricewise(String trkNo,String dt1){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<BalanceModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
//                System.out.println("trk : "+trkNo);
		String query = null;
               

                
                    query = "SELECT c.`NAME`,p.`PRODUCT_NAME`,cat.`CATEGORY_TYPE`,cat.`CAT_ID`,SUM(ttrx.`QNTY`) as qnty\n" +
",ttrx.`UNIT_COST` ,SUM(ttrx.`TOTAL`) as tot,inv.`TRUCK_NO`,inv.INV_DATE,ld.LOAD_ID,ld.`QNTY`,tr.`T_DATE`\n" +
" FROM ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"INNER JOIN load_details ld ON ld.`LOAD_ID`=ttrx.`LOAD_ID`\n" +
"INNER JOIN invoice inv ON inv.`INVOICE_ID`=ld.`INVOICE_ID`\n" +
"INNER JOIN product_category cat ON cat.`CAT_ID`=ttrx.`CAT_ID`\n" +
"INNER JOIN product p ON p.`P_ID`=cat.`P_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE inv.`TRUCK_NO`='"+trkNo+"' AND inv.INV_DATE ='"+dt1+"' \n" +
"GROUP BY ttrx.`CAT_ID`,ttrx.UNIT_COST";

                try {
//                    System.out.println(query);
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            BalanceModel bm = new BalanceModel(
                                    resultado.getInt("LOAD_ID"), 
                                    resultado.getString("PRODUCT_NAME")+" "+resultado.getString("CATEGORY_TYPE"), 
                                    resultado.getString("qnty"), 
                                    cm.rupee(resultado.getString("UNIT_COST")), 
                                    cm.rupee(resultado.getString("tot")) 
                                    );
                            entryTrxModels.add(bm);
                            
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerBalance method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    public List<BalanceModel> getLoadTaxes(int loadId){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<BalanceModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = "select ldTx.`ID`,ldTx.`LOAD_ID`,ldTx.`DT`,ldTx.`Amount`,tx.`TAX_NAME` from tax_to_load ldTx \n" +
"INNER JOIN taxes tx ON tx.`TAX_ID`=ldTx.`TAX_ID` \n" +
"WHERE ldTx.`LOAD_ID`="+loadId+" order by tx.TAX_ID";
//                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            BalanceModel bm = new BalanceModel(
                                    resultado.getInt("ID"), 
                                    resultado.getString("TAX_NAME"), 
                                    cm.rupee(resultado.getString("Amount")), 
                                    "", 
                                    "" 
                                    );
                            entryTrxModels.add(bm);
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerBalance method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    public List<BalanceModel> getInvDtls(int loadId){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<BalanceModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = "SELECT sp.`SUPPLIER_NAME`,sp.`MOBILE`,ld.`QNTY`,SUM(ttr.`QNTY`) as sold,inv.`INV_DATE`\n" +
" FROM load_details ld\n" +
"INNER JOIN invoice inv ON inv.`INVOICE_ID`=ld.`INVOICE_ID`\n" +
"INNER JOIN supplier sp ON sp.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"INNER JOIN ttl_transaction ttr ON ttr.`LOAD_ID`=ld.`LOAD_ID`\n" +
"WHERE ld.`LOAD_ID`="+loadId;
//                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            BalanceModel bm = new BalanceModel(
                                    resultado.getInt("QNTY"), 
                                    resultado.getString("SUPPLIER_NAME"), 
                                    (resultado.getString("sold")), 
                                    (resultado.getString("INV_DATE")), 
                                    
                                    "" 
                                    );
                            entryTrxModels.add(bm);
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerBalance method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    public List<StatementModel> getCollection(int cId,String dt1,String nativ){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<StatementModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = null;
                if(nativ.equalsIgnoreCase("all")){
                    if(cId==0){
                query = "SELECT c.`NAME`,c.`CITY`,(tr.`PAID`),c.`MOBILE`,c.`NATIV` FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE`='"+dt1+"' AND tr.`DETAILS` is null  \n" +
"AND tr.`PAID`>0 \n" +
"group by tr.`CUSTOMER_ID`";
                    } else {
                         query = "SELECT c.`NAME`,c.`CITY`,(tr.`PAID`),c.`MOBILE`,c.`NATIV` FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE`='"+dt1+"' AND tr.`DETAILS` is null  \n" +
"AND tr.`PAID`>0 AND tr.`CUSTOMER_ID`="+cId+" \n" +
"group by tr.`CUSTOMER_ID`";
                
                    }
                } else {
                    if(cId==0){
                query = "SELECT c.`NAME`,c.`CITY`,(tr.`PAID`),c.`MOBILE`,c.`NATIV` FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE`='"+dt1+"' AND tr.`DETAILS` is null  \n" +
"AND tr.`PAID`>0 \n" +
"AND c.`NATIV`='"+nativ+"' \n" +
"group by tr.`CUSTOMER_ID`";
                    } else {
                         query = "SELECT c.`NAME`,c.`CITY`,(tr.`PAID`),c.`MOBILE`,c.`NATIV` FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE`='"+dt1+"' AND tr.`DETAILS` is null  \n" +
"AND tr.`PAID`>0 AND tr.`CUSTOMER_ID`="+cId+" \n" +
"AND c.`NATIV`='"+nativ+"' \n" +
"group by tr.`CUSTOMER_ID`";
                
                }
                }
  
                try {
                    System.out.println(query);
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            StatementModel bm = new StatementModel(
                                    i, 
                                    resultado.getString("NAME"),
                                    resultado.getString("CITY"),
                                    null,
                                    cm.rupee(resultado.getString("PAID")),
                                    resultado.getString("MOBILE"));
                            entryTrxModels.add(bm);
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerBalance method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    public List<CashOnDateModel> getCollectionByDates(int cId,String dt1,String dt2,String nativ){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<CashOnDateModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                String discountORDamage = "tr.`DETAILS`='Discount' OR tr.`DETAILS`='Damage'";
		String query = null;
                if(nativ.equalsIgnoreCase("all")){
                    if(cId==0){
//                query = "SELECT c.`NAME`,c.`CITY`,(tr.`PAID`),c.`MOBILE`,c.`NATIV` FROM `transaction` tr \n" +
//"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
//"WHERE tr.`T_DATE`='"+dt1+"' AND tr.`DETAILS` is null  \n" +
//"AND tr.`PAID`>0 \n" +
//"group by tr.`CUSTOMER_ID`";
                query = "SELECT c.`NAME`,c.`CITY`,tr.`T_DATE`,SUM(tr.`PAID`) as paid,c.`MOBILE`,c.`NATIV`,tr.`DETAILS`,"
                        + "(SELECT SUM(tr.`PAID`)  FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE` between '"+dt1+"' and '"+dt2+"' AND (tr.`DETAILS` is null   OR IF(tr.`DETAILS`='bank',true,false))\n" +
"AND tr.`PAID` > 0) as tot FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE` between '"+dt1+"' and '"+dt2+"' AND (tr.`DETAILS` is null OR "+discountORDamage+" OR IF(tr.`DETAILS`='bank',true,false))\n" +
"AND tr.`PAID` > 0 \n" +
"group by tr.T_ID,tr.`CUSTOMER_ID` ORDER BY tr.`T_DATE`,c.`NAME`";
                    } else {
//                         query = "SELECT c.`NAME`,c.`CITY`,(tr.`PAID`),c.`MOBILE`,c.`NATIV` FROM `transaction` tr \n" +
//"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
//"WHERE tr.`T_DATE`='"+dt1+"' AND tr.`DETAILS` is null  \n" +
//"AND tr.`PAID`>0 AND tr.`CUSTOMER_ID`="+cId+" \n" +
//"group by tr.`CUSTOMER_ID`";
                         query = "SELECT c.`NAME`,c.`CITY`,tr.`T_DATE`,SUM(tr.`PAID`) as paid,c.`MOBILE`,c.`NATIV`,tr.`DETAILS`,"
                                 + "(SELECT SUM(tr.`PAID`) FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE` between '"+dt1+"' and '"+dt2+"' AND (tr.`DETAILS` is null OR IF(tr.`DETAILS`='bank',true,false))\n" +
"AND tr.`PAID` > 0 AND tr.`CUSTOMER_ID`="+cId+") as tot FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE` between '"+dt1+"' and '"+dt2+"' AND (tr.`DETAILS` is null OR "+discountORDamage+" OR IF(tr.`DETAILS`='bank',true,false))\n" +
"AND tr.`PAID` > 0 AND tr.`CUSTOMER_ID`="+cId+" \n" +
"group by tr.T_ID,tr.`CUSTOMER_ID` ORDER BY tr.`T_DATE`,c.`NAME`";
                
                    }
                } else {
                    if(cId==0){
//                query = "SELECT c.`NAME`,c.`CITY`,(tr.`PAID`),c.`MOBILE`,c.`NATIV` FROM `transaction` tr \n" +
//"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
//"WHERE tr.`T_DATE`='"+dt1+"' AND tr.`DETAILS` is null  \n" +
//"AND tr.`PAID`>0 \n" +
//"AND c.`NATIV`='"+nativ+"' \n" +
//"group by tr.`CUSTOMER_ID`";
                query = "SELECT c.`NAME`,c.`CITY`,tr.`T_DATE`,SUM(tr.`PAID`) as paid,c.`MOBILE`,c.`NATIV`,tr.`DETAILS`,"
                        + "(SELECT SUM(tr.`PAID`) FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE` between '"+dt1+"' and '"+dt2+"' AND (tr.`DETAILS` is null OR IF(tr.`DETAILS`='bank',true,false))\n" +
"AND tr.`PAID` > 0  \n" +
"AND c.`NATIV`='"+nativ+"' ) as tot FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE` between '"+dt1+"' and '"+dt2+"' AND (tr.`DETAILS` is null OR "+discountORDamage+" OR IF(tr.`DETAILS`='bank',true,false))\n" +
"AND tr.`PAID` > 0  \n" +
"AND c.`NATIV`='"+nativ+"' \n" +                     
"group by tr.T_ID,tr.`CUSTOMER_ID` ORDER BY tr.`T_DATE`,c.`NAME`";
                    } else {
                         query = "SELECT c.`NAME`,c.`CITY`,tr.`T_DATE`,SUM(tr.`PAID`) as paid,c.`MOBILE`,c.`NATIV`,tr.`DETAILS`,"
                                 + "(SELECT SUM(tr.`PAID`) FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE` between '"+dt1+"' and '"+dt2+"' AND (tr.`DETAILS` is null OR IF(tr.`DETAILS`='bank',true,false))\n" +
"AND tr.`PAID` > 0  \n" +
"AND c.`NATIV`='"+nativ+"' AND tr.`CUSTOMER_ID`="+cId+") as tot FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE` between '"+dt1+"' and '"+dt2+"' AND (tr.`DETAILS` is null OR "+discountORDamage+" OR IF(tr.`DETAILS`='bank',true,false))\n" +
"AND tr.`PAID` > 0  \n" +
"AND c.`NATIV`='"+nativ+"' AND tr.`CUSTOMER_ID`="+cId+" \n" +                     
"group by tr.T_ID,tr.`CUSTOMER_ID` ORDER BY tr.`T_DATE`,c.`NAME`";
                
                }
                }
  
                try {
                    System.out.println(query);
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            System.out.println(resultado.getString("DETAILS"));
                            CashOnDateModel bm = new CashOnDateModel(
                                    i++, 
                                    resultado.getString("NAME"),
                                    resultado.getString("CITY"),
                                    (null == resultado.getString("DETAILS"))?
                                    cm.rupee(resultado.getString("PAID")):null,
                                    cm.dateviewformat(resultado.getDate("T_DATE")),
                                    resultado.getString("DETAILS"),
                                    ((null != resultado.getString("DETAILS")) && (resultado.getString("DETAILS").equalsIgnoreCase("discount") || resultado.getString("DETAILS").equalsIgnoreCase("damage")))?
                                    cm.rupee(resultado.getString("PAID")): null);
                            bm.setTotalCollection(resultado.getString("tot"));
                            entryTrxModels.add(bm);
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerBalance method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    public List<StatementModel> getTotalCollection(String dt1,String nativ){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<StatementModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = null;
                if(nativ.equalsIgnoreCase("all")){
                    
                query = "SELECT tr.`T_DATE`,SUM(tr.`PAID`) as pd,c.`NATIV` FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE`='"+dt1+"' AND tr.`DETAILS` is null  \n" +
"AND tr.`PAID`>0";
                                    
                    
                } else {
                    
                query = "SELECT tr.`T_DATE`,SUM(tr.`PAID`) as pd,c.`NATIV` FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE`='"+dt1+"' AND tr.`DETAILS` is null  \n" +
"AND c.`NATIV`='"+nativ+"'\n" +
"AND tr.`PAID`>0 ";
                    
                
                }
                
  
                try {
                    System.out.println(query);
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            StatementModel bm = new StatementModel(
                                    i, 
                                    resultado.getString("T_DATE"),
                                    resultado.getString("NATIV"),
                                    null,
                                    cm.rupee(resultado.getString("pd")),
                                    null);
                            entryTrxModels.add(bm);
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerBalance method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    
    public List<StatementModel> getTotalCollections(String dt1, String nativ){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<StatementModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = null;
                if(nativ.equalsIgnoreCase("all")){
                    
                query = "SELECT tr.`T_DATE`,SUM(tr.`PAID`) as pd,c.`NATIV` FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE`='"+dt1+"' AND tr.`DETAILS` is null  \n" +
"AND tr.`PAID`>0";
                                    
                    
                } else {
                    
                query = "SELECT tr.`T_DATE`,SUM(tr.`PAID`) as pd,c.`NATIV` FROM `transaction` tr \n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID` \n" +
"WHERE tr.`T_DATE`='"+dt1+"' AND tr.`DETAILS` is null  \n" +
"AND c.`NATIV`='"+nativ+"'\n" +
"AND tr.`PAID`>0 ";
                    
                
                }
                
  
                try {
                    System.out.println(query);
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            StatementModel bm = new StatementModel(
                                    i, 
                                    resultado.getString("T_DATE"),
                                    resultado.getString("NATIV"),
                                    null,
                                    cm.rupee(resultado.getString("pd")),
                                    null);
                            entryTrxModels.add(bm);
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerBalance method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }

    TrxDAL trxDAL = new TrxDAL();
    
    
    
    public List<StatementModel> getCustomerT_Id(int cid,String dt1,String dt2){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<StatementModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                String query;
		if(!dt1.isEmpty() && !dt2.isEmpty())
                query = "SELECT c.`CUSTOMER_ID`,c.`NAME`,c.`MOBILE` \n" +
",c.`CITY`,c.`NATIV`,tr.`T_ID`,tr.`PAID`,tr.`BALANCE`,\n" +
"tr.`T_DATE`,tr.`TRX_TOTAL`\n" +
" FROM customer c\n" +
"INNER JOIN `transaction` tr ON tr.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"WHERE c.`CUSTOMER_ID`="+cid+" AND tr.`T_DATE` BETWEEN '"+dt1+"' AND '"+dt2+"'\n" +
"ORDER BY tr.T_DATE,tr.T_ID";
                else if(!dt1.isEmpty() && dt2.isEmpty())
                query = "SELECT c.`CUSTOMER_ID`,c.`NAME`,c.`MOBILE` \n" +
",c.`CITY`,c.`NATIV`,tr.`T_ID`,tr.`PAID`,tr.`BALANCE`,\n" +
"tr.`T_DATE`,tr.`TRX_TOTAL`\n" +
" FROM customer c\n" +
"INNER JOIN `transaction` tr ON tr.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"WHERE c.`CUSTOMER_ID`="+cid+" AND tr.`T_DATE` BETWEEN '"+dt1+"' AND NOW() \n" +
"ORDER BY tr.T_DATE,tr.T_ID";
                else
		 query = "SELECT c.`CUSTOMER_ID`,c.`NAME`,c.`MOBILE` \n" +
",c.`CITY`,c.`NATIV`,tr.`T_ID`,tr.`PAID`,tr.`BALANCE`,\n" +
"tr.`T_DATE`,tr.`TRX_TOTAL`\n" +
" FROM customer c\n" +
"INNER JOIN `transaction` tr ON tr.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"WHERE c.`CUSTOMER_ID`="+cid+" AND tr.`T_DATE` BETWEEN '' AND '"+dt2+"' \n" +
"ORDER BY tr.T_DATE,tr.T_ID";
                
                System.out.println(query);
                int fbal = trxDAL.getCustomerBalanceByDate(cid, dt2);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            StatementModel bm = new StatementModel(
                                    resultado.getInt("T_ID"),
                                    resultado.getString("T_DATE"),
                                    null,
                                    resultado.getString("TRX_TOTAL"),
                                    resultado.getString("PAID"),
                                    cm.rupee(String.valueOf(trxDAL.getCustomerBalanceByDate1(cid, resultado.getString("T_DATE"), resultado.getInt("T_ID"))))
                                    
                                    );
                            entryTrxModels.add(bm);
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerT_Id method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    
    
    
    
    
    public List<StatementModel> getCustomerT_IdByLimit(int cid,String dt1,String dt2){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<StatementModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                String query;
        String lastTrxLimit = config.Config.getInstancce().getProperty("lastTranxLimit");
        System.out.println("Limit "+lastTrxLimit);
		if(!dt1.isEmpty() && !dt2.isEmpty())
                query = "SELECT c.`CUSTOMER_ID`,c.`NAME`,c.`MOBILE` \n" +
",c.`CITY`,c.`NATIV`,tr.`T_ID`,tr.`PAID`,tr.`BALANCE`,\n" +
"tr.`T_DATE`,tr.`TRX_TOTAL`\n" +
" FROM customer c\n" +
"INNER JOIN `transaction` tr ON tr.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"WHERE c.`CUSTOMER_ID`="+cid+" AND tr.`T_DATE` BETWEEN '"+dt1+"' AND '"+dt2+"'\n" +
"ORDER BY tr.T_DATE,tr.T_ID LIMIT "+lastTrxLimit ;
                else if(!dt1.isEmpty() && dt2.isEmpty())
                query = "SELECT c.`CUSTOMER_ID`,c.`NAME`,c.`MOBILE` \n" +
",c.`CITY`,c.`NATIV`,tr.`T_ID`,tr.`PAID`,tr.`BALANCE`,\n" +
"tr.`T_DATE`,tr.`TRX_TOTAL`\n" +
" FROM customer c\n" +
"INNER JOIN `transaction` tr ON tr.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"WHERE c.`CUSTOMER_ID`="+cid+" AND tr.`T_DATE` BETWEEN '"+dt1+"' AND NOW() \n" +
"ORDER BY tr.T_DATE,tr.T_ID LIMIT "+lastTrxLimit ;
                else
		 
                query = "(SELECT c.`CUSTOMER_ID`,c.`NAME`,c.`MOBILE` \n" +
",c.`CITY`,c.`NATIV`,tr.`T_ID`,tr.`PAID`,tr.`BALANCE`,\n" +
"tr.`T_DATE`,tr.`TRX_TOTAL`\n" +
" FROM customer c\n" +
" INNER JOIN `transaction` tr ON tr.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
" WHERE c.`CUSTOMER_ID`="+cid+" AND tr.`T_DATE` BETWEEN '' AND '"+dt2+"' \n" +
" ORDER BY tr.T_DATE desc,tr.T_ID desc LIMIT "+lastTrxLimit+")  ORDER BY T_DATE,T_ID " ;
                
                System.out.println("getCustomerT_IdByLimit : "+query);
                log.info("getCustomerT_IdByLimit : "+query);
                int fbal = trxDAL.getCustomerBalanceByDate(cid, dt2);
                log.info("trxDAL.getCustomerBalanceByDate(cid, dt2); "+fbal);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            
                            StatementModel bm = new StatementModel(
                                    resultado.getInt("T_ID"),
                                    resultado.getString("T_DATE"),
                                    null,
                                    resultado.getString("TRX_TOTAL"),
                                    resultado.getString("PAID"),
                                    cm.rupee(String.valueOf(trxDAL.getCustomerBalanceByDate1(cid, resultado.getString("T_DATE"), resultado.getInt("T_ID"))))
                                    
                                    );
                            System.out.println(i+ " bm "+bm.toString());
                            i++;
                            entryTrxModels.add(bm);
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerT_Id method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    public List<StatementModel> getCustomerTtlTrxBy_Tid(int tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<StatementModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                String query;
		
                query = "select c.`NAME`,trx.`PAID`,\n" +
"(\n" +
"SELECT ((SELECT SUM(ttrx.`TOTAL`) FROM \n" +
"ttl_transaction ttrx  INNER JOIN `transaction` t ON\n" +
"t.`T_ID`=ttrx.`T_ID` WHERE t.`CUSTOMER_ID`=tr.`CUSTOMER_ID` AND \n" +
"(t.`T_DATE` BETWEEN '' AND trx.`T_DATE` and t.`T_ID`<="+tid+" ))-SUM(tr.`PAID`)) as dtot FROM `transaction` tr \n" +
"WHERE tr.`CUSTOMER_ID`=trx.`CUSTOMER_ID` AND (tr.`T_DATE` BETWEEN '' AND trx.`T_DATE`\n" +
" and tr.`T_ID`<="+tid+")) as bal\n" +
",trx.`BALANCE`,trx.`T_DATE`,trx.`TRX_TOTAL`,ttrx.`TT_ID`,\n" +
"CONCAT(p.`PRODUCT_NAME`,'  ',cat.`CATEGORY_TYPE`) as item\n" +
",ttrx.`QNTY`,ttrx.`UNIT_COST`,ttrx.`TOTAL`,(\n" +
"SELECT SUM(ttrx1.`TOTAL`) FROM ttl_transaction ttrx1 WHERE ttrx1.`T_ID`=ttrx.`T_ID`\n" +
") as trxTotal,ttrx.`T_ID`\n" +
",ttrx.`CAT_ID` FROM customer c\n" +
"INNER JOIN `transaction` trx ON trx.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"INNER JOIN ttl_transaction ttrx ON ttrx.`T_ID`=trx.`T_ID`\n" +
"INNER JOIN product_category cat ON cat.`CAT_ID`=ttrx.`CAT_ID`\n" +
"INNER JOIN product p ON p.`P_ID`=cat.`P_ID`\n" +
" WHERE trx.`T_ID`="+tid+"\n" +
"ORDER BY trx.T_DATE,trx.`T_ID`";
                
		 
                
//                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            StatementModel bm = new StatementModel(
                                    resultado.getInt("T_ID"),
                                    resultado.getString("T_DATE"),
                                    resultado.getString("item"),
                                    resultado.getString("QNTY")+"X"+resultado.getString("UNIT_COST")+"=",
                                    resultado.getString("TOTAL"),
                                    (resultado.getString("trxTotal")),
                                    (resultado.getString("PAID")),
                                    (resultado.getString("bal"))
                                    
                                    );
                            log.info(query);
                            entryTrxModels.add(bm);
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerTtlTrxBy_Tid method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    public List<StatementModel> getCustomerTtlTrxBy_Tid(List<Integer> tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<StatementModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                String query;
                try {
                conn = DbConnection.getConnection();
        Iterator<Integer> it = tid.iterator();
		      while (it.hasNext()) {
                        query = "select c.`NAME`,trx.`PAID`,\n" +
"(\n" +
"SELECT ((SELECT SUM(ttrx.`TOTAL`) FROM \n" +
"ttl_transaction ttrx  INNER JOIN `transaction` t ON\n" +
"t.`T_ID`=ttrx.`T_ID` WHERE t.`CUSTOMER_ID`=tr.`CUSTOMER_ID` AND \n" +
"(t.`T_DATE` BETWEEN '' AND trx.`T_DATE` and t.`T_ID`<="+it.next()+" ))-SUM(tr.`PAID`)) as dtot FROM `transaction` tr \n" +
"WHERE tr.`CUSTOMER_ID`=trx.`CUSTOMER_ID` AND (tr.`T_DATE` BETWEEN '' AND trx.`T_DATE`\n" +
" and tr.`T_ID`<="+it.next()+")) as bal\n" +
",trx.`BALANCE`,trx.`T_DATE`,trx.`TRX_TOTAL`,ttrx.`TT_ID`,\n" +
"CONCAT(p.`PRODUCT_NAME`,'  ',cat.`CATEGORY_TYPE`) as item\n" +
",ttrx.`QNTY`,ttrx.`UNIT_COST`,ttrx.`TOTAL`,(\n" +
"SELECT SUM(ttrx1.`TOTAL`) FROM ttl_transaction ttrx1 WHERE ttrx1.`T_ID`=ttrx.`T_ID`\n" +
") as trxTotal,ttrx.`T_ID`\n" +
",ttrx.`CAT_ID` FROM customer c\n" +
"INNER JOIN `transaction` trx ON trx.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"INNER JOIN ttl_transaction ttrx ON ttrx.`T_ID`=trx.`T_ID`\n" +
"INNER JOIN product_category cat ON cat.`CAT_ID`=ttrx.`CAT_ID`\n" +
"INNER JOIN product p ON p.`P_ID`=cat.`P_ID`\n" +
" WHERE trx.`T_ID`="+it.next()+"\n" +
"ORDER BY trx.T_DATE,trx.`T_ID`";
                          
                                
                       statement = conn.prepareStatement(query);
                       statement.addBatch();
			 System.out.println(query);
                          
            
        }
                      } catch (Exception e) {
                              e.printStackTrace();
                          }
//                query = "select c.`NAME`,trx.`PAID`,\n" +
//"(\n" +
//"SELECT ((SELECT SUM(ttrx.`TOTAL`) FROM \n" +
//"ttl_transaction ttrx  INNER JOIN `transaction` t ON\n" +
//"t.`T_ID`=ttrx.`T_ID` WHERE t.`CUSTOMER_ID`=tr.`CUSTOMER_ID` AND \n" +
//"(t.`T_DATE` BETWEEN '' AND trx.`T_DATE` and t.`T_ID`<="+tid+" ))-SUM(tr.`PAID`)) as dtot FROM `transaction` tr \n" +
//"WHERE tr.`CUSTOMER_ID`=trx.`CUSTOMER_ID` AND (tr.`T_DATE` BETWEEN '' AND trx.`T_DATE`\n" +
//" and tr.`T_ID`<="+tid+")) as bal\n" +
//",trx.`BALANCE`,trx.`T_DATE`,trx.`TRX_TOTAL`,ttrx.`TT_ID`,\n" +
//"CONCAT(p.`PRODUCT_NAME`,'  ',cat.`CATEGORY_TYPE`) as item\n" +
//",ttrx.`QNTY`,ttrx.`UNIT_COST`,ttrx.`TOTAL`,(\n" +
//"SELECT SUM(ttrx1.`TOTAL`) FROM ttl_transaction ttrx1 WHERE ttrx1.`T_ID`=ttrx.`T_ID`\n" +
//") as trxTotal,ttrx.`T_ID`\n" +
//",ttrx.`CAT_ID` FROM customer c\n" +
//"INNER JOIN `transaction` trx ON trx.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
//"INNER JOIN ttl_transaction ttrx ON ttrx.`T_ID`=trx.`T_ID`\n" +
//"INNER JOIN product_category cat ON cat.`CAT_ID`=ttrx.`CAT_ID`\n" +
//"INNER JOIN product p ON p.`P_ID`=cat.`P_ID`\n" +
//" WHERE trx.`T_ID`="+tid+"\n" +
//"ORDER BY trx.T_DATE,trx.`T_ID`";
                
		 
                
//                System.out.println(query);
                try {
            int[] rs = statement.executeBatch();
                         int i=1;
//                        while(resultado.next()){
//                            StatementModel bm = new StatementModel(
//                                    resultado.getInt("T_ID"),
//                                    resultado.getString("T_DATE"),
//                                    resultado.getString("item"),
//                                    resultado.getString("QNTY")+"X"+resultado.getString("UNIT_COST")+"=",
//                                    resultado.getString("TOTAL"),
//                                    (resultado.getString("trxTotal")),
//                                    (resultado.getString("PAID")),
//                                    (resultado.getString("bal"))
//                                    
//                                    );
////                            log.info(query);
//                            entryTrxModels.add(bm);
//                         }
                        

                        System.out.println(i);
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerTtlTrxBy_Tid method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    public String getCustomerBallanceByDate(int cid,String dt){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                String entryTrxModels = "";
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                String query;
		
                query = "SELECT ((SELECT SUM(ttrx.`TOTAL`) FROM \n" +
"ttl_transaction ttrx  INNER JOIN `transaction` t ON\n" +
"t.`T_ID`=ttrx.`T_ID` WHERE t.`CUSTOMER_ID`=tr.`CUSTOMER_ID` AND \n" +
"t.`T_DATE` < '"+dt+"')-SUM(tr.`PAID`)) as dtot FROM `transaction` tr \n" +
"WHERE tr.`CUSTOMER_ID`="+cid+" AND tr.`T_DATE` < '"+dt+"'";
                
		 
                
                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
//                            StatementModel bm = new StatementModel(
//                                    resultado.getInt("T_ID"),
//                                    resultado.getString("T_DATE"),
//                                    resultado.getString("item")+" "+resultado.getString("QNTY")+"X"+resultado.getString("UNIT_COST")+"="+resultado.getString("TOTAL"),
//                                    resultado.getString("trxTotal"),
//                                    resultado.getString("PAID"),
//                                    resultado.getString("BALANCE")
//                                    
//                                    );
                            entryTrxModels = resultado.getString("dtot");
                            
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerTtlTrxBy_Tid method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    
    public String getCustomerBallanceByDateAndTID(int cid,String dt, int T_ID){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                String entryTrxModels = "";
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                String query;
		
                query = "SELECT ((SELECT SUM(ttrx.`TOTAL`) FROM \n" +
"ttl_transaction ttrx  INNER JOIN `transaction` t ON\n" +
"t.`T_ID`=ttrx.`T_ID` WHERE t.`CUSTOMER_ID`=tr.`CUSTOMER_ID` AND \n" +
"t.`T_DATE` BETWEEN '' AND '"+dt+"'  AND  ( IF (t.`T_DATE` = '"+dt+"', t.T_ID<"+T_ID+", true)))-SUM(tr.`PAID`)) as dtot FROM `transaction` tr \n" +
"WHERE tr.`CUSTOMER_ID`="+cid+" AND tr.`T_DATE` BETWEEN '' AND '"+dt+"' AND  ( IF (tr.`T_DATE` = '"+dt+"', tr.T_ID<"+T_ID+", true))";
                
		 
                
                System.out.println("getCustomerBallanceByDateAndTID "+query);
                log.info("getCustomerBallanceByDateAndTID "+query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
//                            StatementModel bm = new StatementModel(
//                                    resultado.getInt("T_ID"),
//                                    resultado.getString("T_DATE"),
//                                    resultado.getString("item")+" "+resultado.getString("QNTY")+"X"+resultado.getString("UNIT_COST")+"="+resultado.getString("TOTAL"),
//                                    resultado.getString("trxTotal"),
//                                    resultado.getString("PAID"),
//                                    resultado.getString("BALANCE")
//                                    
//                                    );
                            entryTrxModels = resultado.getString("dtot");
                            
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerTtlTrxBy_Tid method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return entryTrxModels;

        
        
    }
    public StatementModel getCustomerPreBal(int cid,int tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                String entryTrxModels = "";
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                StatementModel statementModel=null;
                String query;
		
                query = "SELECT c.`CUSTOMER_ID`,c.`NAME`,c.`MOBILE` \n" +
",c.`CITY`,c.`NATIV`,tr.`T_ID`,tr.`T_DATE`,trx.`TOTAL`\n" +
" FROM customer c\n" +
"INNER JOIN `transaction` tr ON tr.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"INNER JOIN ttl_transaction trx ON trx.`T_ID` = tr.`T_ID`\n" +
"WHERE c.`CUSTOMER_ID`="+cid+" AND trx.`T_ID`="+tid;
                
		 
                
                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            statementModel = new StatementModel(
                                    resultado.getInt("T_ID"),
                                    resultado.getString("T_DATE"),
                                    null,
                                    null,
                                    "Previous balance",
                                    resultado.getString("TOTAL")
                                    
                                    );
//                            entryTrxModels = resultado.getString("dtot");
                            
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerTtlTrxBy_Tid method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return statementModel;

        
        
    }
    
    public StatementModel getCustomerDamOrDiscount(int cid,int tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                String entryTrxModels = "";
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                StatementModel statementModel=null;
                String query;
		
                query = "SELECT c.`CUSTOMER_ID`,c.`NAME`,c.`MOBILE` \n" +
",c.`CITY`,c.`NATIV`,tr.`T_ID`,tr.`T_DATE`,tr.`PAID`,tr.BALANCE,tr.`DETAILS`\n" +
" FROM customer c\n" +
"INNER JOIN `transaction` tr ON tr.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"WHERE c.`CUSTOMER_ID`="+cid+" AND tr.`T_ID`="+tid;
                
		 
                
                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            statementModel = new StatementModel(
                                    resultado.getInt("T_ID"),
                                    resultado.getString("T_DATE"),
                                    null,
                                    resultado.getString("DETAILS"),
                                    cm.rupee(resultado.getString("PAID")),
                                    //cm.rupee(resultado.getString("BALANCE"))
                                    cm.rupee(String.valueOf(trxDAL.getCustomerBalanceByDate(cid, resultado.getString("T_DATE"))))
                                    
                                    );
//                            entryTrxModels = resultado.getString("dtot");
                            
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerTtlTrxBy_Tid method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return statementModel;

        
        
    }
    public StatementModel getCustomerDamOrDiscount1(int cid,int tid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                String entryTrxModels = "";
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                StatementModel statementModel=null;
                String query;
		
                query = "SELECT c.`CUSTOMER_ID`,c.`NAME`,c.`MOBILE` \n" +
",c.`CITY`,c.`NATIV`,tr.`T_ID`,tr.`T_DATE`,tr.`PAID`,tr.BALANCE,tr.`DETAILS`\n" +
" FROM customer c\n" +
"INNER JOIN `transaction` tr ON tr.`CUSTOMER_ID`=c.`CUSTOMER_ID`\n" +
"WHERE c.`CUSTOMER_ID`="+cid+" AND tr.`T_ID`="+tid;
                
		 
                
                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            statementModel = new StatementModel(
                                    resultado.getInt("T_ID"),
                                    resultado.getString("T_DATE"),
                                    null,
                                    resultado.getString("DETAILS"),
                                    cm.rupee(resultado.getString("PAID")),
                                    //cm.rupee(resultado.getString("BALANCE"))
                                    cm.rupee(String.valueOf(trxDAL.getCustomerBalanceByDate1(cid, resultado.getString("T_DATE"), resultado.getInt("T_ID"))))
                                    
                                    );
//                            entryTrxModels = resultado.getString("dtot");
                            
                         }
                        

                        
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Transaction successful..!");
		} catch (Exception e) {
			System.err.println("Exception occured in ReportDAL's getCustomerTtlTrxBy_Tid method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return statementModel;

        
        
    }
}
