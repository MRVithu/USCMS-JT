package com.vithu.uscms.others;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.stereotype.Service;
/**
 * @author S.Sugashan
 * @CreatedOn 21th December 2017
 * @Purpose Establishing connection & Return Connection
 */
@Service
public class DBConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/uscms";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "root";
		   
	private static Connection conn = DBConnection.startConnection();
	
	//Initialize a connectionss
	public static Connection startConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	//return a connection
	public Connection getCon() throws SQLException {
		conn = DBConnection.startConnection();
		return conn;  
	}

	public void closeCon() throws SQLException {
		conn.close();
	}
}
