package com.wust.util;

import java.util.List;

import com.wust.dao.DicDao;
import com.wust.entity.Dic;

/**
 * 公共方法类 -->提代静态方法访问字典表信息
 */
public class CommonMethod {
	private static List<Dic> priceList;
	private static List<Dic> houseTypeList;
	private static List<Dic> areaList;
	
	/**刷新列表*/
	public static void initDicList(){
		initPriceList();
		initHouseTypeList();
		initAreaList();
	}
	
	/**获取价格列表*/
	public static List<Dic> getPriceList(){
		if(priceList==null){
			//初始化价格列表
			initPriceList();
		}
		return priceList;
	}
	
	/**获取房型列表*/
	public static List<Dic> getHouseList(){
		if(houseTypeList==null){
			//初始化价格列表
			initHouseTypeList();
		}
		return houseTypeList;
	}

	/**获取面积列表*/
	public static List<Dic> getAreaList(){
		if(areaList==null){
			//初始化价格列表
			initAreaList();
		}
		return areaList;
	}

	private static void initPriceList() {
		DicDao dicDao = new DicDao();
		priceList = dicDao.queryDicList("PRICE");
	}
	

	private static void initHouseTypeList() {
		DicDao dicDao = new DicDao();
		houseTypeList = dicDao.queryDicList("HOUSETYPE");
	}
	
	private static void initAreaList() {
		DicDao dicDao = new DicDao();
		areaList = dicDao.queryDicList("AREA");
	}
}
