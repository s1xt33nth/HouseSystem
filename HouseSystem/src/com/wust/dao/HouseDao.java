package com.wust.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wust.entity.House;
import com.wust.entity.PageData;
import com.wust.entity.UserInfo;
import com.wust.util.DBUtil;
import com.wust.util.DateUtil;

public class HouseDao extends BaseDao {

	public boolean addHouse(House house) {
		try {
			String sql = "INSERT INTO houseinfo(user_id, house_title, house_type, house_floorage, house_price, house_date, district, street, contact, description, pub_date, filepath) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, house.getUserId());
			pstmt.setString(2, house.getHouseTitle());
			pstmt.setString(3, house.getHouseType());
			pstmt.setDouble(4, house.getHouseFloorage());
			pstmt.setDouble(5, house.getHousePrice());
			pstmt.setDate(6, DateUtil.toSqlDate(house.getHouseDate()));
			pstmt.setString(7, house.getDistrict());
			pstmt.setString(8, house.getStreet());
			pstmt.setString(9, house.getContact());
			pstmt.setString(10, house.getDescription());
			pstmt.setString(11, house.getPubDate());
			pstmt.setString(12, house.getFilePath());
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

	public List<House> queryHouseListByUserID(int userId) {
		List<House> houseList = new ArrayList<House>();
		try {
			String sql = "SELECT * FROM houseinfo WHERE user_id =" + userId + " ORDER BY pub_date DESC ";
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				House house = new House();
				house.setHouseId(rs.getInt(1));
				house.setUserId(rs.getInt(2));
				house.setHouseTitle(rs.getString(3));
				house.setHouseType(rs.getString(4));
				house.setHouseFloorage(rs.getDouble(5));
				house.setHousePrice(rs.getDouble(6));
				house.setHouseDate(rs.getDate(7));
				house.setDistrict(rs.getString(8));
				house.setStreet(rs.getString(9));
				house.setContact(rs.getString(10));
				house.setDescription(rs.getString(11));
				house.setPubDate(DateUtil.getTimeStampString(rs.getTimestamp(12)));
				house.setFilePath(rs.getString(13));

				houseList.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, null, pstmt);
		}
		return houseList;
	}

	public List<House> queryHouseListByUserID(int userId, PageData pageData) {
		List<House> houseList = new ArrayList<House>();
		try {
			String sql = "SELECT * FROM houseinfo WHERE user_id =? ORDER BY pub_date DESC limit ?,?";
			// 确定两个参数
			int startIndex = pageData.getPageSize() * (pageData.getCurrPage() - 1);
			int pageSize = pageData.getPageSize();
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, userId);
			pstmt.setInt(2, startIndex);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				House house = new House();
				house.setHouseId(rs.getInt(1));
				house.setUserId(rs.getInt(2));
				house.setHouseTitle(rs.getString(3));
				house.setHouseType(rs.getString(4));
				house.setHouseFloorage(rs.getDouble(5));
				house.setHousePrice(rs.getDouble(6));
				house.setHouseDate(rs.getDate(7));
				house.setDistrict(rs.getString(8));
				house.setStreet(rs.getString(9));
				house.setContact(rs.getString(10));
				house.setDescription(rs.getString(11));
				house.setPubDate(DateUtil.getTimeStampString(rs.getTimestamp(12)));
				house.setFilePath(rs.getString(13));

				houseList.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, null, pstmt);
		}
		return houseList;
	}

	public List<House> queryHouseListAll(PageData pageData) {
		List<House> houseList = new ArrayList<House>();
		try {
			String sql = "SELECT * FROM houseinfo ORDER BY pub_date DESC limit ?,?";
			// 确定两个参数
			int startIndex = pageData.getPageSize() * (pageData.getCurrPage() - 1);
			int pageSize = pageData.getPageSize();
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, startIndex);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				House house = new House();
				house.setHouseId(rs.getInt(1));
				house.setUserId(rs.getInt(2));
				house.setHouseTitle(rs.getString(3));
				house.setHouseType(rs.getString(4));
				house.setHouseFloorage(rs.getDouble(5));
				house.setHousePrice(rs.getDouble(6));
				house.setHouseDate(rs.getDate(7));
				house.setDistrict(rs.getString(8));
				house.setStreet(rs.getString(9));
				house.setContact(rs.getString(10));
				house.setDescription(rs.getString(11));
				house.setPubDate(DateUtil.getTimeStampString(rs.getTimestamp(12)));
				house.setFilePath(rs.getString(13));

				houseList.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, null, pstmt);
		}
		return houseList;
	}

	public boolean updateHouse(House house) {
		try {
			String sql = "UPDATE houseinfo set house_title = ?, house_type = ?, house_floorage = ?, house_price = ?, house_date = ?, district = ?, street = ?, contact = ?, description = ?, pub_date = ?, filepath = ? WHERE house_id = ?";
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, house.getHouseTitle());
			pstmt.setString(2, house.getHouseType());
			pstmt.setDouble(3, house.getHouseFloorage());
			pstmt.setDouble(4, house.getHousePrice());
			pstmt.setDate(5, DateUtil.toSqlDate(house.getHouseDate()));
			pstmt.setString(6, house.getDistrict());
			pstmt.setString(7, house.getStreet());
			pstmt.setString(8, house.getContact());
			pstmt.setString(9, house.getDescription());
			pstmt.setString(10, house.getPubDate());
			pstmt.setString(11, house.getFilePath());
			pstmt.setInt(12, house.getHouseId());
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
			DBUtil.closeAll(rs, stmt, pstmt);
		}
	}

	public boolean deleteHouse(int houseId) {
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "DELETE FROM houseinfo WHERE house_id =" + houseId;
			int rows = stmt.executeUpdate(sql);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.closeAll(rs, stmt, pstmt);
		}
	}

	public House queryHouseByHouseId(int houseId) {
		House house = null;
		try {
			String sql = "SELECT * FROM houseinfo WHERE house_id =" + houseId;
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				house = new House();
				house.setHouseId(rs.getInt(1));
				house.setUserId(rs.getInt(2));
				house.setHouseTitle(rs.getString(3));
				house.setHouseType(rs.getString(4));
				house.setHouseFloorage(rs.getDouble(5));
				house.setHousePrice(rs.getDouble(6));
				house.setHouseDate(rs.getDate(7));
				house.setDistrict(rs.getString(8));
				house.setStreet(rs.getString(9));
				house.setContact(rs.getString(10));
				house.setDescription(rs.getString(11));
				house.setPubDate(DateUtil.getTimeStampString(rs.getTimestamp(12)));
				house.setFilePath(rs.getString(13));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, null, pstmt);
		}
		return house;
	}

	public int getMaxCount(int userId) {
		try {
			String sql = "SELECT count(*) FROM houseInfo WHERE user_id=" + userId;
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, null);
		}
		return 0;
	}

	public int getMaxCount() {
		try {
			String sql = "SELECT count(*) FROM houseInfo";
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, null);
		}
		return 0;
	}

	public UserInfo queryUserInfoByHouseId(int houseId) {
		UserInfo userInfo = null;
		try {
			String sql = "SELECT * FROM userinfo WHERE user_Id = ( SELECT user_Id FROM houseinfo WHERE house_id ="
					+ houseId + ")";
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

	public int getMaxCountByConditon(String houseTitle, String housePrice, String district, String houseType,
			String houseFloorage, String timeRange) {
		try {
			StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM houseInfo where 1=1");
			if (houseTitle != null || !houseTitle.equals("")) {
				sb.append(" AND house_title like'%" + houseTitle + "%'");
			}
			if (housePrice != null || !housePrice.equals("")) {
				switch (housePrice) {
				case "0":
					break;
				case "0-1000":
					sb.append(" AND house_price BETWEEN 0 AND 1000");
					break;
				case "1000-2000":
					sb.append(" AND house_price BETWEEN 1000 AND 2000");
					break;
				case "2000-1000000":
					sb.append(" AND house_price BETWEEN 2000 AND 1000000");
					break;
				}
			}
			if (district != null || !district.equals("")) {
				switch (district) {
				case "0":
					break;
				case "1000":
					sb.append(" AND district = '江岸区' ");
					break;
				case "1001":
					sb.append(" AND district = '江汉区' ");
					break;
				case "1002":
					sb.append(" AND district = '硚口区' ");
					break;
				case "1003":
					sb.append(" AND district = '汉阳区' ");
					break;
				case "1004":
					sb.append(" AND district = '武昌区' ");
					break;
				case "1005":
					sb.append(" AND district = '青山区' ");
					break;
				case "1006":
					sb.append(" AND district = '洪山区' ");
					break;
				}
			}
			if (houseType != null || !houseType.equals("")) {
				switch (houseType) {
				case "0":
					break;
				case "1000":
					sb.append(" AND house_type = 'FX001' ");
					break;
				case "1001":
					sb.append(" AND house_type = 'FX002' ");
					break;
				case "1002":
					sb.append(" AND house_type = 'FX003' ");
					break;
				case "1003":
					sb.append(" AND house_type = 'FX004' ");
					break;
				}
			}
			if (houseFloorage != null || !houseFloorage.equals("")) {
				switch (houseFloorage) {
				case "0":
					break;
				case "0-50":
					sb.append(" AND house_floorage BETWEEN 0 AND 50 ");
					break;
				case "50-100":
					sb.append(" AND house_floorage BETWEEN 50 AND 100 ");
					break;
				case "100-1000000":
					sb.append(" AND house_floorage BETWEEN 100 AND 1000000 ");
					break;
				}
			}
			if (timeRange != null || !timeRange.equals("")) {
				switch (timeRange) {
				case "00":
					break;
				case "01":
					sb.append(" AND to_days(pub_date) = to_days(now()) ");
					break;
				case "02":
					sb.append(" AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(pub_date) ");
					break;
				case "03":
					sb.append(" AND DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(pub_date) ");
					break;
				}
			}
			String sql = sb.toString();
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, null);
		}
		return 0;
	}

	public List<House> queryHouseListByCondition(String houseTitle, String housePrice, String district,
			String houseType, String houseFloorage, String timeRange, PageData pageData) {
		List<House> houseList = new ArrayList<House>();
		try {
			StringBuffer sb = new StringBuffer("SELECT * FROM houseInfo where 1=1");

			if (houseTitle != null || !houseTitle.equals("")) {
				sb.append(" AND house_title like'%" + houseTitle + "%'");
			}
			if (housePrice != null || !housePrice.equals("")) {
				switch (housePrice) {
				case "0":
					break;
				case "0-1000":
					sb.append(" AND house_price BETWEEN 0 AND 1000");
					break;
				case "1000-2000":
					sb.append(" AND house_price BETWEEN 1000 AND 2000");
					break;
				case "2000-1000000":
					sb.append(" AND house_price BETWEEN 2000 AND 1000000");
					break;
				}
			}
			if (district != null || !district.equals("")) {
				switch (district) {
				case "0":
					break;
				case "1000":
					sb.append(" AND district = '江岸区' ");
					break;
				case "1001":
					sb.append(" AND district = '江汉区' ");
					break;
				case "1002":
					sb.append(" AND district = '硚口区' ");
					break;
				case "1003":
					sb.append(" AND district = '汉阳区' ");
					break;
				case "1004":
					sb.append(" AND district = '武昌区' ");
					break;
				case "1005":
					sb.append(" AND district = '青山区' ");
					break;
				case "1006":
					sb.append(" AND district = '洪山区' ");
					break;
				}
			}
			if (houseType != null || !houseType.equals("")) {
				switch (houseType) {
				case "0":
					break;
				case "1000":
					sb.append(" AND house_type = 'FX001' ");
					break;
				case "1001":
					sb.append(" AND house_type = 'FX002' ");
					break;
				case "1002":
					sb.append(" AND house_type = 'FX003' ");
					break;
				case "1003":
					sb.append(" AND house_type = 'FX004' ");
					break;
				}
			}
			if (houseFloorage != null || !houseFloorage.equals("")) {
				switch (houseFloorage) {
				case "0":
					break;
				case "0-50":
					sb.append(" AND house_floorage BETWEEN 0 AND 50 ");
					break;
				case "50-100":
					sb.append(" AND house_floorage BETWEEN 50 AND 100 ");
					break;
				case "100-1000000":
					sb.append(" AND house_floorage BETWEEN 100 AND 1000000 ");
					break;
				}
			}
			if (timeRange != null || !timeRange.equals("")) {
				switch (timeRange) {
				case "00":
					break;
				case "01":
					sb.append(" AND to_days(pub_date) = to_days(now()) ");
					break;
				case "02":
					sb.append(" AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(pub_date) ");
					break;
				case "03":
					sb.append(" AND DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(pub_date) ");
					break;
				}
			}
			String sql = sb.toString() + " ORDER BY pub_date DESC limit ?,?";
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 确定两个参数
			int startIndex = pageData.getPageSize() * (pageData.getCurrPage() - 1);
			int pageSize = pageData.getPageSize();
			// 设置参数
			pstmt.setInt(1, startIndex);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				House house = new House();
				house.setHouseId(rs.getInt(1));
				house.setUserId(rs.getInt(2));
				house.setHouseTitle(rs.getString(3));
				house.setHouseType(rs.getString(4));
				house.setHouseFloorage(rs.getDouble(5));
				house.setHousePrice(rs.getDouble(6));
				house.setHouseDate(rs.getDate(7));
				house.setDistrict(rs.getString(8));
				house.setStreet(rs.getString(9));
				house.setContact(rs.getString(10));
				house.setDescription(rs.getString(11));
				house.setPubDate(DateUtil.getTimeStampString(rs.getTimestamp(12)));
				house.setFilePath(rs.getString(13));

				houseList.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, null, pstmt);
		}
		return houseList;
	}

	public int getMaxCountByConditonWithKeyword(String keyword, String housePrice, String district, String houseType,
			String houseFloorage, String timeRange) {
		try {
			StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM houseInfo where 1=1");
			if (keyword != null || !keyword.equals("")) {
				sb.append(" AND ( house_title like'%" + keyword + "%' OR description like'%" + keyword + "%' OR district like'%" + keyword + "%' OR street like'%" + keyword + "%' )");
			}
			if (housePrice != null || !housePrice.equals("")) {
				switch (housePrice) {
				case "0":
					break;
				case "0-1000":
					sb.append(" AND house_price BETWEEN 0 AND 1000");
					break;
				case "1000-2000":
					sb.append(" AND house_price BETWEEN 1000 AND 2000");
					break;
				case "2000-1000000":
					sb.append(" AND house_price BETWEEN 2000 AND 1000000");
					break;
				}
			}
			if (district != null || !district.equals("")) {
				switch (district) {
				case "0":
					break;
				case "1000":
					sb.append(" AND district = '江岸区' ");
					break;
				case "1001":
					sb.append(" AND district = '江汉区' ");
					break;
				case "1002":
					sb.append(" AND district = '硚口区' ");
					break;
				case "1003":
					sb.append(" AND district = '汉阳区' ");
					break;
				case "1004":
					sb.append(" AND district = '武昌区' ");
					break;
				case "1005":
					sb.append(" AND district = '青山区' ");
					break;
				case "1006":
					sb.append(" AND district = '洪山区' ");
					break;
				}
			}
			if (houseType != null || !houseType.equals("")) {
				switch (houseType) {
				case "0":
					break;
				case "1000":
					sb.append(" AND house_type = 'FX001' ");
					break;
				case "1001":
					sb.append(" AND house_type = 'FX002' ");
					break;
				case "1002":
					sb.append(" AND house_type = 'FX003' ");
					break;
				case "1003":
					sb.append(" AND house_type = 'FX004' ");
					break;
				}
			}
			if (houseFloorage != null || !houseFloorage.equals("")) {
				switch (houseFloorage) {
				case "0":
					break;
				case "0-50":
					sb.append(" AND house_floorage BETWEEN 0 AND 50 ");
					break;
				case "50-100":
					sb.append(" AND house_floorage BETWEEN 50 AND 100 ");
					break;
				case "100-1000000":
					sb.append(" AND house_floorage BETWEEN 100 AND 1000000 ");
					break;
				}
			}
			if (timeRange != null || !timeRange.equals("")) {
				switch (timeRange) {
				case "00":
					break;
				case "01":
					sb.append(" AND to_days(pub_date) = to_days(now()) ");
					break;
				case "02":
					sb.append(" AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(pub_date) ");
					break;
				case "03":
					sb.append(" AND DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(pub_date) ");
					break;
				}
			}
			String sql = sb.toString();
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, null);
		}
		return 0;
	}

	public List<House> queryHouseListByConditionWithKeyword(String keyword, String housePrice, String district,
			String houseType, String houseFloorage, String timeRange, PageData pageData) {
		List<House> houseList = new ArrayList<House>();
		try {
			StringBuffer sb = new StringBuffer("SELECT * FROM houseInfo where 1=1");
			if (keyword != null || !keyword.equals("")) {
				sb.append(" AND ( house_title like'%" + keyword + "%' OR description like'%" + keyword + "%' OR district like'%" + keyword + "%' OR street like'%" + keyword + "%' )");
			}
			if (housePrice != null || !housePrice.equals("")) {
				switch (housePrice) {
				case "0":
					break;
				case "0-1000":
					sb.append(" AND house_price BETWEEN 0 AND 1000");
					break;
				case "1000-2000":
					sb.append(" AND house_price BETWEEN 1000 AND 2000");
					break;
				case "2000-1000000":
					sb.append(" AND house_price BETWEEN 2000 AND 1000000");
					break;
				}
			}
			if (district != null || !district.equals("")) {
				switch (district) {
				case "0":
					break;
				case "1000":
					sb.append(" AND district = '江岸区' ");
					break;
				case "1001":
					sb.append(" AND district = '江汉区' ");
					break;
				case "1002":
					sb.append(" AND district = '硚口区' ");
					break;
				case "1003":
					sb.append(" AND district = '汉阳区' ");
					break;
				case "1004":
					sb.append(" AND district = '武昌区' ");
					break;
				case "1005":
					sb.append(" AND district = '青山区' ");
					break;
				case "1006":
					sb.append(" AND district = '洪山区' ");
					break;
				}
			}
			if (houseType != null || !houseType.equals("")) {
				switch (houseType) {
				case "0":
					break;
				case "1000":
					sb.append(" AND house_type = 'FX001' ");
					break;
				case "1001":
					sb.append(" AND house_type = 'FX002' ");
					break;
				case "1002":
					sb.append(" AND house_type = 'FX003' ");
					break;
				case "1003":
					sb.append(" AND house_type = 'FX004' ");
					break;
				}
			}
			if (houseFloorage != null || !houseFloorage.equals("")) {
				switch (houseFloorage) {
				case "0":
					break;
				case "0-50":
					sb.append(" AND house_floorage BETWEEN 0 AND 50 ");
					break;
				case "50-100":
					sb.append(" AND house_floorage BETWEEN 50 AND 100 ");
					break;
				case "100-1000000":
					sb.append(" AND house_floorage BETWEEN 100 AND 1000000 ");
					break;
				}
			}
			if (timeRange != null || !timeRange.equals("")) {
				switch (timeRange) {
				case "00":
					break;
				case "01":
					sb.append(" AND to_days(pub_date) = to_days(now()) ");
					break;
				case "02":
					sb.append(" AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(pub_date) ");
					break;
				case "03":
					sb.append(" AND DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(pub_date) ");
					break;
				}
			}
			String sql = sb.toString() + " ORDER BY pub_date DESC limit ?,?";
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 确定两个参数
			int startIndex = pageData.getPageSize() * (pageData.getCurrPage() - 1);
			int pageSize = pageData.getPageSize();
			// 设置参数
			pstmt.setInt(1, startIndex);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				House house = new House();
				house.setHouseId(rs.getInt(1));
				house.setUserId(rs.getInt(2));
				house.setHouseTitle(rs.getString(3));
				house.setHouseType(rs.getString(4));
				house.setHouseFloorage(rs.getDouble(5));
				house.setHousePrice(rs.getDouble(6));
				house.setHouseDate(rs.getDate(7));
				house.setDistrict(rs.getString(8));
				house.setStreet(rs.getString(9));
				house.setContact(rs.getString(10));
				house.setDescription(rs.getString(11));
				house.setPubDate(DateUtil.getTimeStampString(rs.getTimestamp(12)));
				house.setFilePath(rs.getString(13));

				houseList.add(house);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, null, pstmt);
		}
		return houseList;
	}

}
