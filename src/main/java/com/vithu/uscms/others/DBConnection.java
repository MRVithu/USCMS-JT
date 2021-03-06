package com.vithu.uscms.others;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.stereotype.Service;
/**
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose Establishing connection & Return Connection
 */
@Service
public class DBConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/uscms";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "";
		   
	private static Connection conn = null;
	
	//Initialize a connectionss
	public static Connection startConnection() throws ClassNotFoundException {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	//return a connection
	public Connection getCon() throws SQLException, ClassNotFoundException {
		conn = DBConnection.startConnection();
		return conn;  
	}

	public void closeCon() throws SQLException {
		conn.close();
	}
}
