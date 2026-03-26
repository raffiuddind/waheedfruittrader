/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.DAL;

import Fruit.Common.DbConnection;
import Fruit.Model.ProductCategoryModel;
import Fruit.Model.ProductEntryModel;
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
public class ProductDAL {

    public ProductDAL() {
    }
    
    public String addProduct(ProductEntryModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "INSERT INTO product(PRODUCT_NAME, SHORT_CUT, CREATED, ACTIVE, UPDATED) VALUES('"
				+ productEntryModel.getProductName()
				+ "', '"
				+ productEntryModel.getProductNameShort()
                                + "', '"
                                + productEntryModel.getCreted()
                                + "', "
                                + productEntryModel.isActive()
				+ ", NOW())";
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product added";
                        JOptionPane.showMessageDialog(null, "Product name is inserted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in ProductDAL's add method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
    public int getProductByName(ProductEntryModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
                int result = 0;
		String resultMsg = "";
		String query = "select * from product where PRODUCT_NAME='"+productEntryModel.getProductName()+"' "
                        + "AND ACTIVE=true";
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         if(resultado.next())
                             result = 1;
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Product name is inserted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in ProductDAL's add method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return result;

        
        
    }
    
    public String updateProduct(ProductEntryModel productEntryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE product SET PRODUCT_NAME = '"
				+ productEntryModel.getProductName()
				+ "', SHORT_CUT = '"
				+ productEntryModel.getProductNameShort()
                                + "', CREATED = '"
                                + productEntryModel.getCreted()
                                + "', ACTIVE = "
                                + productEntryModel.isActive()
				+ ", UPDATED = NOW() WHERE P_ID ="+productEntryModel.getpId();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product updated";
                        JOptionPane.showMessageDialog(null, "Product name is updated successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in ProductDAL's update method : "
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
		String query = "UPDATE product SET ACTIVE =false WHERE P_ID ="+productEntryModel.getpId();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product deleted";
                        JOptionPane.showMessageDialog(null, productEntryModel.getProductName()+" deleted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in ProductDAL's delete method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
    public List<ProductEntryModel> getProductModelList() {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		List<ProductEntryModel> lstOfProducts = new ArrayList<ProductEntryModel>();
		String query = "select * from product where ACTIVE =true ORDER BY PRODUCT_NAME ";
		try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();
			while (results.next()) {
				ProductEntryModel mediumObj = new ProductEntryModel(
                                                results.getString("PRODUCT_NAME"),
                                                results.getString("SHORT_CUT"),
                                                results.getBoolean("ACTIVE"),
                                                results.getString("CREATED"),
                                                results.getInt("P_ID")
                                                );
						

				lstOfProducts.add(mediumObj);
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
    
    public String addProductCat(ProductCategoryModel productCategoryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "INSERT INTO product_category(CATEGORY_TYPE, CAT_SHORT, P_ID, CREATED, ACTIVE, UPDATED) VALUES('"
				+ productCategoryModel.getCategoryName()
				+ "', '"
				+ productCategoryModel.getCategoryNameShort()
                                + "',"
                                + productCategoryModel.getProductEntryModel().getpId()
                                + ", '"
                                + productCategoryModel.getCreted()
                                + "', "
                                + productCategoryModel.isActive()
				+ ", NOW())";
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product added";
                        JOptionPane.showMessageDialog(null, "Category is inserted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in ProductDAL's addProductCat method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
    public int getProductCatByName(ProductCategoryModel productCategoryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;int result=0;
		String resultMsg = "";
		String query = "SELECT * FROM product_category where CATEGORY_TYPE ='"+productCategoryModel.getCategoryName()+"' AND "
                + " ACTIVE=true AND P_ID="+productCategoryModel.getProductEntryModel().getpId();
                
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado= statement.executeQuery();
                         if(resultado.next())
                             result=1;
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Category is inserted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in ProductDAL's addProductCat method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return result;

        
        
    }
    
    public String updateProductCat(ProductCategoryModel productCategoryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE product_category SET CATEGORY_TYPE = '"
				+ productCategoryModel.getCategoryName()
				+ "', CAT_SHORT = '"
				+ productCategoryModel.getCategoryNameShort()
                                + "', CREATED = '"
                                + productCategoryModel.getCreted()
                                + "', ACTIVE = "
                                + productCategoryModel.isActive()
                                + ", P_ID = "
                                + productCategoryModel.getProductEntryModel().getpId()
				+ ", UPDATED = NOW() WHERE CAT_ID ="+productCategoryModel.getCatId();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product updated";
                        JOptionPane.showMessageDialog(null, "Category is updated successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in ProductDAL's updateProductCat method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
    public String deleteProductCat(ProductCategoryModel productCategoryModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "UPDATE product_category SET ACTIVE = false WHERE CAT_ID ="+productCategoryModel.getCatId();
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product deleted";
                        JOptionPane.showMessageDialog(null, productCategoryModel.getCategoryName()+" category deleted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in ProductDAL's deleteProductCat method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
    public List<ProductCategoryModel> getProductCatModelList() {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		List<ProductCategoryModel> lstOfProducts = new ArrayList<ProductCategoryModel>();
		String query = "select * from product_category cat\n" +
" INNER JOIN product pro ON pro.`P_ID` = cat.`P_ID` where cat.ACTIVE=true AND pro.ACTIVE = true ORDER BY pro.PRODUCT_NAME,cat.CATEGORY_TYPE";
		try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();
			while (results.next()) {
                                ProductEntryModel entryModel = new ProductEntryModel(
                                        results.getString("PRODUCT_NAME"),
                                                results.getString("SHORT_CUT"),
                                                results.getBoolean("ACTIVE"),
                                                results.getString("CREATED"),
                                                results.getInt("P_ID")
                                );
                                        
				ProductCategoryModel mediumObj = new ProductCategoryModel(
                                                results.getString("CATEGORY_TYPE"),
                                                results.getString("CAT_SHORT"),
                                                results.getBoolean("ACTIVE"),
                                                results.getString("CREATED"),
                                                results.getInt("CAT_ID"),
                                                results.getInt("P_ID"),
                                                
                                                entryModel
                                                
                                                );
						

				lstOfProducts.add(mediumObj);
			}
		} catch (Exception e) {
			System.err
					.println("Exception occured in ProductDAL's getProductCatModelList method : "
							+ e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}

		return lstOfProducts;
	}
    
    public List<ProductCategoryModel> getProductCatModelList(int proId) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		List<ProductCategoryModel> lstOfProducts = new ArrayList<ProductCategoryModel>();
		String query = "select * from product_category cat\n" +
" INNER JOIN product pro ON pro.`P_ID` = cat.`P_ID` WHERE pro.P_ID = "+ proId +" "
                        + "AND cat.ACTIVE=true AND pro.ACTIVE = true ORDER BY pro.PRODUCT_NAME,cat.CATEGORY_TYPE";
                        
		try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			results = statement.executeQuery();
			while (results.next()) {
                                ProductEntryModel entryModel = new ProductEntryModel(
                                        results.getString("PRODUCT_NAME"),
                                                results.getString("SHORT_CUT"),
                                                results.getBoolean("ACTIVE"),
                                                results.getString("CREATED"),
                                                results.getInt("P_ID")
                                );
                                        
				ProductCategoryModel mediumObj = new ProductCategoryModel(
                                                results.getString("CATEGORY_TYPE"),
                                                results.getString("CAT_SHORT"),
                                                results.getBoolean("ACTIVE"),
                                                results.getString("CREATED"),
                                                results.getInt("CAT_ID"),
                                                results.getInt("P_ID"),
                                                
                                                entryModel
                                                
                                                );
						

				lstOfProducts.add(mediumObj);
			}
		} catch (Exception e) {
			System.err
					.println("Exception occured in ProductDAL's getProductCatModelList method : "
							+ e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}

		return lstOfProducts;
	}

    public boolean getProdcutStatus(int pId) {
        try {
            Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;int result=0;
		String resultMsg = "";
                
		String query = "SELECT * FROM load_details WHERE `P_ID` = "+pId+" and `ACTIVE`=true";
                System.out.println(query);
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado= statement.executeQuery();
                         if(resultado.next()){
                             result=1;
                             return true;
                         }
                             
			resultMsg = "Product added";
//                        JOptionPane.showMessageDialog(null, "Category is inserted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in ProductDAL's checkstatus method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
