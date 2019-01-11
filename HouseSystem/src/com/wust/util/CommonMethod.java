package com.wust.util;

import java.util.List;

import com.wust.dao.DicDao;
import com.wust.entity.Dic;

/**
 * ���������� -->�����̬���������ֵ����Ϣ
 */
public class CommonMethod {
	private static List<Dic> priceList;
	private static List<Dic> houseTypeList;
	private static List<Dic> areaList;
	
	/**ˢ���б�*/
	public static void initDicList(){
		initPriceList();
		initHouseTypeList();
		initAreaList();
	}
	
	/**��ȡ�۸��б�*/
	public static List<Dic> getPriceList(){
		if(priceList==null){
			//��ʼ���۸��б�
			initPriceList();
		}
		return priceList;
	}
	
	/**��ȡ�����б�*/
	public static List<Dic> getHouseList(){
		if(houseTypeList==null){
			//��ʼ���۸��б�
			initHouseTypeList();
		}
		return houseTypeList;
	}

	/**��ȡ����б�*/
	public static List<Dic> getAreaList(){
		if(areaList==null){
			//��ʼ���۸��б�
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
