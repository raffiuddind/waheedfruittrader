/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.DAL;

import Fruit.Common.CommonMethods;
import Fruit.Common.DbConnection;
import Fruit.Model.InvoiceReportModel;
import com.User.SaleBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Raffiuddin
 */
public class InvoiceReportDAL {

    public InvoiceReportDAL() {
    }
    
    Fruit.Common.CommonMethods cm= new CommonMethods();
    
    public List<InvoiceReportModel> getTotalInvoice(String dt1,String dt2, String st){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<InvoiceReportModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = null;
                if(dt1==null && dt2!=null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '' AND '"+dt2+"' ORDER BY inv.`INV_DATE` desc";
                if(dt1!=null && dt2!=null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '"+dt1+"' AND '"+dt2+"'  ORDER BY inv.`INV_DATE` desc";
                if(dt1!=null && dt2==null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '"+dt1+"' AND NOW()  ORDER BY inv.`INV_DATE` desc";
                if(dt1==null && dt2==null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '' AND now()  ORDER BY inv.`INV_DATE` desc";
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            InvoiceReportModel bm = new InvoiceReportModel(
                                    i,
                                    resultado.getInt("LOAD_ID"),
                                    cm.dateviewformat(resultado.getDate("INV_DATE")), 
                                    resultado.getString("SUPPLIER_NAME"), 
                                    resultado.getString("TRUCK_NO"), 
                                    resultado.getString("QNTY"),
                                    resultado.getString("qntysold"),
                                    resultado.getString("stock")
                                    );bm.setInvoiceID(resultado.getString("INVOICE_ID"));
                            i++;
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
    
    public List<InvoiceReportModel> getTotalInvoiceSupplerWise(String dt1,String dt2, String st, int sid){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<InvoiceReportModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = null;
                if(dt1==null && dt2!=null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '' AND '"+dt2+"' AND ld.`SUPPLIER_ID`="+sid+" ORDER BY inv.`INV_DATE` desc";
                if(dt1!=null && dt2!=null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '"+dt1+"' AND '"+dt2+"' AND ld.`SUPPLIER_ID`="+sid+" ORDER BY inv.`INV_DATE` desc";
                if(dt1!=null && dt2==null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '"+dt1+"' AND NOW() AND ld.`SUPPLIER_ID`="+sid+" ORDER BY inv.`INV_DATE` desc";
                if(dt1==null && dt2==null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '' AND now() AND ld.`SUPPLIER_ID`="+sid+" ORDER BY inv.`INV_DATE` desc";
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            InvoiceReportModel bm = new InvoiceReportModel(
                                    i,
                                    resultado.getInt("LOAD_ID"),
                                    cm.dateviewformat(resultado.getDate("INV_DATE")), 
                                    resultado.getString("SUPPLIER_NAME"), 
                                    resultado.getString("TRUCK_NO"), 
                                    resultado.getString("QNTY"),
                                    resultado.getString("qntysold"),
                                    resultado.getString("stock")
                                    );
                            i++;
                            bm.setInvoiceID(resultado.getString("INVOICE_ID"));
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
    
    
    public List<InvoiceReportModel> getTotalInvoiceSupplerWise(String dt1,String dt2, String st, String trkNo){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<InvoiceReportModel>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = null;
                if(dt1==null && dt2!=null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '' AND '"+dt2+"' AND inv.`TRUCK_NO`='"+trkNo+"' ORDER BY inv.`INV_DATE` desc";
                if(dt1!=null && dt2!=null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '"+dt1+"' AND '"+dt2+"' AND inv.`TRUCK_NO`='"+trkNo+"' ORDER BY inv.`INV_DATE` desc";
                if(dt1!=null && dt2==null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '"+dt1+"' AND NOW() AND inv.`TRUCK_NO`='"+trkNo+"' ORDER BY inv.`INV_DATE` desc";
                if(dt1==null && dt2==null)
                query ="select inv.`INVOICE_ID`,ld.`QNTY`,(ld.`QNTY`-\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"wHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) )) as stock,\n" +
"IF((SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) is null, 0,\n" +
"(SELECT SUM(ttrx.`QNTY`) FROM ttl_transaction ttrx \n" +
"WHERE ttrx.`LOAD_ID` = ld.`LOAD_ID`) ) as qntysold,inv.`INV_DATE`\n" +
",ld.`LOAD_ID`,s.`SUPPLIER_NAME`,ld.`SUPPLIER_ID`,inv.`TRUCK_NO`,inv.`TRANSPORT` from invoice inv\n" +
"INNER JOIN load_details ld ON ld.`INVOICE_ID`=inv.`INVOICE_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.ACTIVE="+st+" AND inv.`INV_DATE` BETWEEN '' AND now() AND inv.`TRUCK_NO`='"+trkNo+"' ORDER BY inv.`INV_DATE` desc";
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            InvoiceReportModel bm = new InvoiceReportModel(
                                    i,
                                    resultado.getInt("LOAD_ID"),
                                    cm.dateviewformat(resultado.getDate("INV_DATE")), 
                                    resultado.getString("SUPPLIER_NAME"), 
                                    resultado.getString("TRUCK_NO"), 
                                    resultado.getString("QNTY"),
                                    resultado.getString("qntysold"),
                                    resultado.getString("stock")
                                    );
                            i++;
                            bm.setInvoiceID(resultado.getString("INVOICE_ID"));
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
    
    public List<SaleBean> getTrkDetails(InvoiceReportModel irm){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                int tId = 0;
                List<SaleBean>  entryTrxModels = new ArrayList<>();
//                List<SaleCatelogModel>  transactionModels = new ArrayList<>();
                
		String query = null;
               
                query ="SELECT c.`NAME`,p.`PRODUCT_NAME`,cat.`CATEGORY_TYPE`,cat.`CAT_ID`,(ttrx.`QNTY`)\n" +
",ttrx.`UNIT_COST`,(ttrx.`TOTAL`),inv.`TRUCK_NO`,inv.INV_DATE,ld.`QNTY`,\n" +
"(SELECT tr.`T_DATE`\n" +
"ORDER BY tr.`T_DATE` desc LIMIT 1) as edate\n" +
" FROM ttl_transaction ttrx\n" +
"INNER JOIN `transaction` tr ON tr.`T_ID`=ttrx.`T_ID`\n" +
"INNER JOIN customer c ON c.`CUSTOMER_ID`=tr.`CUSTOMER_ID`\n" +
"INNER JOIN load_details ld ON ld.`LOAD_ID`=ttrx.`LOAD_ID`\n" +
"INNER JOIN invoice inv ON inv.`INVOICE_ID`=ld.`INVOICE_ID`\n" +
"INNER JOIN product_category cat ON cat.`CAT_ID`=ttrx.`CAT_ID`\n" +
"INNER JOIN product p ON p.`P_ID`=cat.`P_ID`\n" +
"INNER JOIN supplier s ON s.`SUPPLIER_ID`=ld.`SUPPLIER_ID`\n" +
"WHERE ld.`LOAD_ID`="+irm.getLoadID()+" \n" +
"GROUP BY ttrx.`TT_ID`  order by tr.`T_DATE`,c.`NAME` ";
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         int i=1;
                        while(resultado.next()){
                            SaleBean bm = new SaleBean(
                                    i,
                                    resultado.getString("NAME"), 
                                    cm.dateviewformat(resultado.getDate("edate")), 
                                    resultado.getString("PRODUCT_NAME")+" "+resultado.getString("CATEGORY_TYPE"),  
                                    resultado.getString("CATEGORY_TYPE"), 
                                    resultado.getString("QNTY"),
                                    cm.rupee(resultado.getString("UNIT_COST")),
                                    cm.rupee(resultado.getString("TOTAL"))
                                    );
                            i++;
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
}
