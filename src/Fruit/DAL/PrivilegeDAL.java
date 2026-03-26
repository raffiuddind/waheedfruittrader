/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fruit.DAL;

import Fruit.Common.DbConnection;
import Fruit.Model.PrivilegeModel;
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
public class PrivilegeDAL {
    
    public String addPrivilege(PrivilegeModel registrationModel){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "INSERT INTO privilege(P_ID,PANEL, STATUS, UPDATED) VALUES("+registrationModel.getId()+", '"
				+ registrationModel.getPanelName() +"',"
				+ registrationModel.isActive() +","
				+ " NOW())";
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Su added";
//                        JOptionPane.showMessageDialog(null, "Successful...");
		} catch (Exception e) {
			System.err.println("Exception occured in PrivilegeDAL's add method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
    public List<PrivilegeModel> getPrivilegeStatus(){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
                List<PrivilegeModel>  mdl = new ArrayList<PrivilegeModel>();
                int result = 0;
		String query = "select * from privilege";
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			 resultado = statement.executeQuery();
                         while(resultado.next()){
                             PrivilegeModel model = new PrivilegeModel(
                                     result,
                                     resultado.getString("PANEL"),
                                     resultado.getBoolean("STATUS"));
                             mdl.add(model);
                         }
			resultMsg = "Su added";
//                        JOptionPane.showMessageDialog(null, "Customer inserted successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in PrivilegeDAL's get method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return mdl;

        
        
    }
    
    public String deletePrivilege(){
        
        Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		String resultMsg = "";
		String query = "DELETE FROM privilege ";
                try {
			conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			int result = statement.executeUpdate();
			resultMsg = "Product updated";
//                        JOptionPane.showMessageDialog(null, "Updated successfully...");
		} catch (Exception e) {
			System.err.println("Exception occured in PrivilegeDAL's delete method : "
					+ e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			DbConnection.closeConnection(conn);
		}
        return resultMsg;

        
        
    }
    
}
