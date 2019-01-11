package com.wust.dao;

import java.sql.SQLException;

import com.wust.entity.UserInfo;
import com.wust.util.DBUtil;

public class UserDao extends BaseDao{
	
	public String queryPassword(String username) {
		String password = null;
		try {
			String sql = "SELECT password FROM userinfo WHERE username =" + "'" + username + "'";
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				password = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, null);
		}
		return password;
	}

	public boolean insertInfo(UserInfo userInfo) {
		try {
			String sql = "INSERT INTO userinfo(username,password,telephone,realname) VALUES(?, ?, ?, ?)";
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userInfo.getUsername());
			pstmt.setString(2, userInfo.getPassword());
			pstmt.setString(3, userInfo.getTelephone());
			pstmt.setString(4, userInfo.getRealname());
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.closeAll(null, null, pstmt);
		}
	}

	public UserInfo queryUserByName(String username) {
		UserInfo userInfo = null;
		try {
			String sql = "SELECT * FROM userinfo WHERE username =" + "'" + username + "'";
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				userInfo = new UserInfo();
				userInfo.setUserId(rs.getInt(1));
				userInfo.setUsername(rs.getString(2));
				userInfo.setPassword(rs.getString(3));
				userInfo.setTelephone(rs.getString(4));
				userInfo.setRealname(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, null);
		}
		return userInfo;
	}
		
}
