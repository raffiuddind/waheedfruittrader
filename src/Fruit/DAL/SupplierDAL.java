/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.DAL;

import Fruit.Common.DbConnection;
import Fruit.Model.ProductEntryModel;
import Fruit.Model.RegistrationModel;
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
public class SupplierDAL {

    public SupplierDAL() {
    }
    
    public String addSupplier(RegistrationModel registrationModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "INSERT INTO supplier(SUPPLIER_NAME, CREATED, MOBILE, MOBILE2, PHOTO, CITY, PIN, STATE, PLOT_NO, ACTIVE) VALUES('"
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
    public int getSupplierByName(RegistrationModel registrationModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
                int result =0;
		String resultMsg = "";
		String query = "SELECT * FROM supplier where SUPPLIER_NAME='"+registrationModel.getName()+"' "
                        + "AND ACTIVE=true ";
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			resultado = statement.executeQuery();
                        if(resultado.next())
                            result = 1;
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
    
    public String updateSupplier(RegistrationModel registrationModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE supplier SET SUPPLIER_NAME = '"
				+ registrationModel.getName()
				+ "', MOBILE = '"
				+ registrationModel.getMobile()
                                + "', MOBILE2 = '"
                                + registrationModel.getMobile2()
                                + "', PIN = "
                                + registrationModel.getPinNo()
				+ ", CITY = '"
                                + registrationModel.getCity()
                                + "', STATE = '"
                                + registrationModel.getState()
                                + "', PLOT_NO = '"
                                + registrationModel.getPlotNo()
                                + "' WHERE SUPPLIER_ID ="+registrationModel.getCsID();
                try {
                    System.out.println(query);
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product updated";
                        JOptionPane.showMessageDialog(null, "Updated successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in SupplierDAL's updateSupplier method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn, statement, null);
		}
        return resultMsg;

        
        
    }
    
    public String deleteSupplier(RegistrationModel registrationModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE supplier SET ACTIVE = "
                        + registrationModel.isActive()
                        + " WHERE SUPPLIER_ID ="+registrationModel.getCsID();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product updated";
                        JOptionPane.showMessageDialog(null, "Deleted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in SupplierDAL's deleteSupplier method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
    public String deleteProduct(ProductEntryModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "DELETE FROM product WHERE P_ID ="+productEntryModel.getpId();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product deleted";
                        JOptionPane.showMessageDialog(null, "Product deleted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in ProductDAL's delete method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
    public List<RegistrationModel> getSupplierModelList() {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		List<RegistrationModel> lstOfProducts = new ArrayList<RegistrationModel>();
		String query = "select * from supplier WHERE ACTIVE =true ORDER BY SUPPLIER_NAME ";
		try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();
			while (results.next()) {
				RegistrationModel registrationModel = new RegistrationModel(
                                         results.getInt("SUPPLIER_ID"),
                                         results.getString("SUPPLIER_NAME"),
                                         results.getString("MOBILE"),
                                         results.getString("MOBILE2"),
                                         results.getBytes("PHOTO"),
                                         results.getString("CITY"),
                                         results.getInt("PIN"),
                                         results.getString("STATE"),
                                         results.getString("PLOT_NO"),
                                         true,null);
                                                
                                                
                                               
						

				lstOfProducts.add(registrationModel);
			}
		} catch (Exception e) {
			System.err
					.println("Exception occured in MediumDAL's getMediums method : "
							+ e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}

		return lstOfProducts;
	}
    public List<RegistrationModel> getSupplierModelListById(int id) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		List<RegistrationModel> lstOfProducts = new ArrayList<RegistrationModel>();
		String query = "select * from supplier WHERE SUPPLIER_ID="+id+" AND ACTIVE =true ORDER BY SUPPLIER_NAME ";
		try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();
			while (results.next()) {
				RegistrationModel registrationModel = new RegistrationModel(
                                         results.getInt("SUPPLIER_ID"),
                                         results.getString("SUPPLIER_NAME"),
                                         results.getString("MOBILE"),
                                         results.getString("MOBILE2"),
                                         results.getBytes("PHOTO"),
                                         results.getString("CITY"),
                                         results.getInt("PIN"),
                                         results.getString("STATE"),
                                         results.getString("PLOT_NO"),
                                         true,null);
                                                
                                                
                                               
						

				lstOfProducts.add(registrationModel);
			}
		} catch (Exception e) {
			System.err
					.println("Exception occured in MediumDAL's getMediums method : "
							+ e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}

		return lstOfProducts;
	}
    
}
