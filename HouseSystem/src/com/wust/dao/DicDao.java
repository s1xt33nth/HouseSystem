package com.wust.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wust.entity.Dic;
import com.wust.util.DBUtil;

public class DicDao extends BaseDao{
	
	public List<Dic> queryDicList(String type) {
		List<Dic> dicList = new ArrayList<Dic>();
		try {
			String sql = "SELECT * FROM dic WHERE type = ? ";
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			Dic dic = null;
			while(rs.next()){
				dic = new Dic();
				dic.setCode(rs.getString(1));
				dic.setText(rs.getString(2));
				dic.setType(rs.getString(3));
				//Ìí¼Óµ½list
				dicList.add(dic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			DBUtil.closeAll(rs, null, pstmt);
		}
		return dicList;
	}
	
}
