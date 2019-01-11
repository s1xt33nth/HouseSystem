package com.wust.util;

import java.sql.*;

public class DBUtil {
	private static Connection conn = null;

	private static void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/house_system";
		String user = "root";
		String password = "root";
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (conn == null) {
			init();
		}
		return conn;
	}

	public static void closeAll(ResultSet rs, Statement stmt, PreparedStatement pstmt) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
