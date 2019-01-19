package com.wust.util;

import java.sql.*;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
//	private static Connection conn = null;
	private static ComboPooledDataSource cpds = null;

	static {
		cpds = new ComboPooledDataSource("mysql/house_system");
	}

//	private static void init() {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		String url = "jdbc:mysql://localhost:3306/house_system";
//		String user = "root";
//		String password = "root";
//		try {
//			conn = DriverManager.getConnection(url, user, password);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public static Connection getConnection() {
//		if (conn == null) {
//			init();
//		}
//		return conn;
		try {
			return cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void closeAll(ResultSet rs, Statement stmt, PreparedStatement pstmt, Connection conn) {
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
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		if (getConnection() != null) {
//			System.out.println("连接成功!!!!");
//		} else
//			System.out.println("连接不成功");
//	}
}
