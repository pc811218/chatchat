package test.config.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=Chat", "sa", "sa123456");
		System.out.println("--connect successfully--");
	}

}
