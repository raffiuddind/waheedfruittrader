/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fruit.Common;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author raffiuddin
 */
public class DbConnection {
    static Connection con =null;
  
    public static Connection getConnection() throws Exception
    {
        
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = config.Config.getInstancce().getProperty("db_name");
        String driver = "com.mysql.jdbc.Driver";
        String userName = config.Config.getInstancce().getProperty("bd_user");;
        String password = config.Config.getInstancce().getProperty("db_pswd");;

        Class.forName(driver).newInstance();
//        System.out.println("user "+userName+" pswd "+password);
        con = DriverManager.getConnection(url + dbName, userName,password);
        
        return con;
    }

    public static void closeConnection(Connection conn)
    {
	    try
	    {
	    	if(conn != null) {
	    		conn.close();
	    	}
	    }
	    catch (SQLException e)
	    {
                e.printStackTrace();
	    }
	}
    public static void closeConnection(Connection conn, PreparedStatement preparedStatement, ResultSet rs)
    {
	    try
	    {
	    	if(conn != null) {
	    		conn.close();
	    	}
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(rs !=null){
                    rs.close();
                }
	    }
	    catch (SQLException e)
	    {
                e.printStackTrace();
	    }
	}
    
}
