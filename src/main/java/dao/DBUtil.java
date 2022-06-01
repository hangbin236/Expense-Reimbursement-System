package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	// singleton design pattern
	static Connection conn;

	static {
		try {
			// step 1
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// connected to AWS Database
	static Connection makeConnection() throws SQLException {
		// step 2
		String connectionUrl = "jdbc:postgresql://my-teamproject1-db.crv9kmc3fltq.us-east-1.rds.amazonaws.com:5432/teamp1";
		String userName = "user1";
		String password = "rootroot";
		if (conn == null) {
			conn = DriverManager.getConnection(connectionUrl, userName, password);
		}
		return conn;
	}

}
