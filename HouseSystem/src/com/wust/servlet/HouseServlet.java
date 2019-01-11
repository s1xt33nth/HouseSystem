package com.wust.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.wust.dao.HouseDao;
import com.wust.entity.Dic;
import com.wust.entity.House;
import com.wust.entity.PageData;
import com.wust.entity.UserInfo;
import com.wust.util.CommonMethod;
import com.wust.util.DateUtil;

public class HouseServlet extends HttpServlet {
	private HouseDao houseDao = new HouseDao();
	private PageData pageData = new PageData();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HouseServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String method = request.getParameter("method");

		if (method.equals("gotoHomePage")) {
			gotoHomePage(request, response);
		} else if (method.equals("gotoHouseAdd")) {
			gotoHouseAdd(request, response);
		} else if (method.equals("houseAdd")) {
			houseAdd(request, response);
		} else if (method.equals("gotoHouseManage")) {
			gotoHouseManage(request, response);
		} else if (method.equals("gotoHouseModify")) {
			gotoHouseModify(request, response);
		} else if (method.equals("houseDelete")) {
			houseDelete(request, response);
		} else if (method.equals("houseModify")) {
			houseModify(request, response);
		} else if (method.equals("gotoShow")) {
			gotoShow(request, response);
		} else if (method.equals("searchHouseList")) {
			searchHouseList(request, response);
		} else if (method.equals("searchHouse")) {
			searchHouse(request, response);
		}
	}

	private void searchHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");

		String keyword = request.getParameter("keyword");
		String housePrice = request.getParameter("price");
		String district = request.getParameter("street_id");
		String houseType = request.getParameter("type_id");
		String houseFloorage = request.getParameter("floorage");
		String timeRange = request.getParameter("time_range");

		// ��ʼ��pageData
		int maxCount = houseDao.getMaxCountByConditonWithKeyword(keyword, housePrice, district, houseType, houseFloorage, timeRange);
		pageData.setMaxCount(maxCount);
		if (pageSize != null && pageSize.matches("\\d{1,2}") && Integer.parseInt(pageSize) > 0) {
			pageData.setPageSize(Integer.parseInt(pageSize));
			pageData.setCurrPage(1);
		} else {
			pageData.setPageSize(10);
		}
		if (currPage != null) {
			pageData.setCurrPage(Integer.parseInt(currPage));
		}
		// ��ѯ�����б�
		List<Dic> houseTypeList = CommonMethod.getHouseList();
		// ��������������
		request.setAttribute("houseTypeList", houseTypeList);
		request.setAttribute("pageData", pageData);

		List<House> houseList = houseDao.queryHouseListByConditionWithKeyword(keyword, housePrice, district, houseType,
				houseFloorage, timeRange, pageData);
		if (houseList.size() == 0) {
			request.setAttribute("msg", "��Ǹ�����޴��෿��");
			request.getRequestDispatcher("/page/house_list.jsp").forward(request, response);
			return;
		}
		// ����������
		request.setAttribute("keyword", keyword);
		request.setAttribute("houseList", houseList);
		request.getRequestDispatcher("/page/house_list.jsp").forward(request, response);
		
	}

	private void searchHouseList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");

		String houseTitle = request.getParameter("title");
		String housePrice = request.getParameter("price");
		String district = request.getParameter("street_id");
		String houseType = request.getParameter("type_id");
		String houseFloorage = request.getParameter("floorage");
		String timeRange = request.getParameter("time_range");

		// ��ʼ��pageData
		int maxCount = houseDao.getMaxCountByConditon(houseTitle, housePrice, district, houseType, houseFloorage, timeRange);
		pageData.setMaxCount(maxCount);
		if (pageSize != null && pageSize.matches("\\d{1,2}") && Integer.parseInt(pageSize) > 0) {
			pageData.setPageSize(Integer.parseInt(pageSize));
			pageData.setCurrPage(1);
		} else {
			pageData.setPageSize(10);
		}
		if (currPage != null) {
			pageData.setCurrPage(Integer.parseInt(currPage));
		}
		// ��ѯ�����б�
		List<Dic> houseTypeList = CommonMethod.getHouseList();
		// ��������������
		request.setAttribute("houseTypeList", houseTypeList);
		request.setAttribute("pageData", pageData);

		List<House> houseList = houseDao.queryHouseListByCondition(houseTitle, housePrice, district, houseType,
				houseFloorage, timeRange, pageData);
		if (houseList.size() == 0) {
			request.setAttribute("msg", "��Ǹ�����޴��෿��");
			request.getRequestDispatcher("/page/house_list.jsp").forward(request, response);
			return;
		}
		// ����������
		request.setAttribute("houseTitle", houseTitle);
		request.setAttribute("houseList", houseList);
		request.getRequestDispatcher("/page/house_list.jsp").forward(request, response);

	}

	private void gotoShow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡҪչʾ��houseId
		int houseId = Integer.parseInt(request.getParameter("houseId"));
		// ��ѯҪչʾ��House����
		House house = houseDao.queryHouseByHouseId(houseId);
		// ��ѯ��Ӧ��UserId
		UserInfo houseUserInfo = houseDao.queryUserInfoByHouseId(houseId);
		if (house == null) {
			request.setAttribute("msg", "������Ϣ������");
			gotoHomePage(request, response);
		} else {
			request.setAttribute("house", house);
			// ��ѯ�����б�
			List<Dic> houseTypeList = CommonMethod.getHouseList();
			// ��������������
			request.setAttribute("houseTypeList", houseTypeList);
			request.setAttribute("houseUserInfo", houseUserInfo);
			request.getRequestDispatcher("/page/show.jsp").forward(request, response);
		}
	}

	private void gotoHomePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");

		// ��ʼ��pageData
		int maxCount = houseDao.getMaxCount();
		pageData.setMaxCount(maxCount);

		if (pageSize != null && pageSize.matches("\\d{1,2}") && Integer.parseInt(pageSize) > 0) {
			pageData.setPageSize(Integer.parseInt(pageSize));
			pageData.setCurrPage(1);
		} else {
			pageData.setPageSize(10);
		}

		if (currPage != null) {
			pageData.setCurrPage(Integer.parseInt(currPage));
		}

		List<House> houseList = houseDao.queryHouseListAll(pageData);
		// ����������
		request.setAttribute("houseList", houseList);
		request.setAttribute("pageData", pageData);
		// ��ѯ�����б�
		List<Dic> houseTypeList = CommonMethod.getHouseList();
		// ��������������
		request.setAttribute("houseTypeList", houseTypeList);
		request.getRequestDispatcher("/page/house_list.jsp").forward(request, response);
	}

	private void houseDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡҪɾ����houseId
		int houseId = Integer.parseInt(request.getParameter("houseId"));

		boolean flag = houseDao.deleteHouse(houseId);

		if (!flag) {
			request.setAttribute("msg", "ɾ��ʧ�ܣ�");
			gotoHouseManage(request, response);
		} else {
			gotoHouseManage(request, response);
		}
	}

	private void gotoHouseModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡҪ�޸ĵ�houseId
		int houseId = Integer.parseInt(request.getParameter("houseId"));
		// ��ѯҪ�޸ĵ�House����
		House house = houseDao.queryHouseByHouseId(houseId);
		if (house == null) {
			request.setAttribute("msg", "������Ϣ������");
			gotoHouseManage(request, response);
		} else {
			request.setAttribute("house", house);
			// ��ѯ�����б�
			List<Dic> houseTypeList = CommonMethod.getHouseList();
			// ��������������
			request.setAttribute("houseTypeList", houseTypeList);
			request.getRequestDispatcher("/page/modify.jsp").forward(request, response);
		}
	}

	private void houseModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String token = String.valueOf(request.getSession().getAttribute("token"));
//		String formToken = request.getParameter("formToken");
//
//		if (token == null || !token.equals(formToken)) {
//			System.out.println("token not equals!!!");
//			gotoHouseManage(request, response);
//			return;
//		} else {
//			System.out.println("token equals");
//			request.getSession().removeAttribute("token");
//		}

		boolean flag = false;

		SmartUpload su = new SmartUpload();
		// ��ʼ��SmartUpload����
		su.initialize(this.getServletConfig(), request, response);
		com.jspsmart.upload.File file = null;
		// �����ϴ�����
		String allowed = "gif,jpg,jpeg,png";
		// �����ϴ�����
		String denied = "jsp,asp,php,aspx,html,htm,exe,bat";
		com.jspsmart.upload.Request req = null;
		// �����ϴ��ļ���С
		int file_size = 2 * 1024 * 1024; // 2mb
		int i = 0;
		try {
			// ���������ϴ��ļ�����
			su.setAllowedFilesList(allowed);
			// �������ϴ��ļ�����
			su.setDeniedFilesList(denied);
			// �����ļ��������
			su.setMaxFileSize(file_size);
			su.setCharset("utf-8");
			// ִ���ϴ�
			su.upload();
			// �õ������ϴ��ļ�����Ϣ
			file = su.getFiles().getFile(0);

			// ��ȡsmartupload��װ��request
			req = su.getRequest();

			String filename = "";
			// ����·��
			String filepath = null;
			// ���·��
			String filePath = null;
			if (!file.isMissing()) {
				// �����ļ��ڷ������ı���λ��
				filepath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\wtpwebapps\\HouseSystem\\upload\\";
//				filepath = "upload\\";
				String tmpFront = "" + (int) (Math.random() * 100000);
				filename = file.getFileName();
				String tmpBack = "." + filename.substring(filename.lastIndexOf('.') + 1);
				filePath = tmpFront + tmpBack;
				filepath += tmpFront + tmpBack;
//				System.out.println("filepath_modify: " + filepath);
//				System.out.println("filePath_modify: " + filePath);
				// �ļ����Ϊ
				file.setCharset("utf-8");
				file.saveAs(filepath, SmartUpload.SAVE_PHYSICAL);
			} else {
				filePath = req.getParameter("filePath");
			}

			int houseId = Integer.parseInt(req.getParameter("houseId"));
			int userId = Integer.parseInt(req.getParameter("userId"));
			String houseTitle = req.getParameter("houseTitle");
			String houseType = req.getParameter("houseType");
			double houseFloorage = Double.parseDouble(req.getParameter("houseFloorage"));
			double housePrice = Double.parseDouble(req.getParameter("housePrice"));
			Date houseDate = DateUtil.getBirthDate(req.getParameter("houseDate"));
			String district = req.getParameter("district");
			String street = req.getParameter("street");
			String contact = req.getParameter("contact");
			String description = req.getParameter("description");
			Date now = new Date();
			String pubDate = DateUtil.getTimeSampleString(now);

			House house = new House(houseId, userId, houseTitle, houseType, houseFloorage, housePrice, houseDate,
					district, street, contact, description, pubDate, filePath);
			flag = houseDao.updateHouse(house);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (flag) {
			gotoHouseManage(request, response);
		} else {
			request.setAttribute("msg", "�����޸�ʧ�ܣ�");
			gotoHouseAdd(request, response);
		}

	}

	private void gotoHouseAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ѯ�����б�
		List<Dic> houseTypeList = CommonMethod.getHouseList();
		// ��������������
		request.setAttribute("houseTypeList", houseTypeList);

		request.getRequestDispatcher("/page/add.jsp").forward(request, response);

	}

	private void houseAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;

		String filename = "";
		SmartUpload su = new SmartUpload();
		// ��ʼ��SmartUpload����
		su.initialize(this.getServletConfig(), request, response);
		com.jspsmart.upload.File file = null;
		// �����ϴ�����
		String allowed = "gif,jpg,jpeg,png";
		// �����ϴ�����
		String denied = "jsp,asp,php,aspx,html,htm,exe,bat";
		com.jspsmart.upload.Request req = null;
		// �����ϴ��ļ���С
		int file_size = 2 * 1024 * 1024; // 2mb
		int i = 0;
		try {
			// ���������ϴ��ļ�����
			su.setAllowedFilesList(allowed);
			// �������ϴ��ļ�����
			su.setDeniedFilesList(denied);
			// �����ļ��������
			su.setMaxFileSize(file_size);
			su.setCharset("utf-8");
			// ִ���ϴ�
			su.upload();
			// �õ������ϴ��ļ�����Ϣ
			file = su.getFiles().getFile(0);
			// ����·��
			String filepath = null;
			// ���·��
			String filePath = null;
			if (!file.isMissing()) {
				// �����ļ��ڷ������ı���λ��
				filepath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\wtpwebapps\\HouseSystem\\upload\\";
//				filepath = "upload\\";
				String tmpFront = "" + (int) (Math.random() * 100000);
				filename = file.getFileName();
				String tmpBack = "." + filename.substring(filename.lastIndexOf('.') + 1);
				filePath = tmpFront + tmpBack;
				filepath += tmpFront + tmpBack;
//				System.out.println("filepath_Add: " + filepath);
//				System.out.println("filePath_Add: " + filePath);
//				// �ļ����Ϊ
				file.setCharset("utf-8");
				file.saveAs(filepath, SmartUpload.SAVE_PHYSICAL);
			}

			// ��ȡsmartupload��װ��request
			req = su.getRequest();
			
			String token = String.valueOf(request.getSession().getAttribute("token"));
//			System.out.println("token:" + token);
			String formToken = req.getParameter("formToken");
//			System.out.println("formToken:" + formToken);
			if (token == null || !token.equals(formToken)) {
				gotoHouseManage(request, response);
				return;
			} else {
				request.getSession().removeAttribute("token");
			}
			
			int userId = Integer.parseInt(req.getParameter("userId"));
			String houseTitle = req.getParameter("houseTitle");
			String houseType = req.getParameter("houseType");
			double houseFloorage = Double.parseDouble(req.getParameter("houseFloorage"));
			double housePrice = Double.parseDouble(req.getParameter("housePrice"));
			Date houseDate = DateUtil.getBirthDate(req.getParameter("houseDate"));
			String district = req.getParameter("district");
			String street = req.getParameter("street");
			String contact = req.getParameter("contact");
			String description = req.getParameter("description");
			Date now = new Date();
			String pubDate = DateUtil.getTimeSampleString(now);

			House house = new House(userId, houseTitle, houseType, houseFloorage, housePrice, houseDate, district,
					street, contact, description, pubDate, filePath);
			flag = houseDao.addHouse(house);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (flag) {
			gotoHouseManage(request, response);
		} else {
			request.setAttribute("msg", "���ݷ���ʧ�ܣ�");
			gotoHouseAdd(request, response);
		}
	}

	private void gotoHouseManage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String currPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");

		// ��ʼ��pageData
		int maxCount = houseDao.getMaxCount(loginUser.getUserId());
		pageData.setMaxCount(maxCount);

		if (pageSize != null && pageSize.matches("\\d{1,2}") && Integer.parseInt(pageSize) > 0) {
			pageData.setPageSize(Integer.parseInt(pageSize));
			pageData.setCurrPage(1);
		} else {
			pageData.setPageSize(5);
		}

		if (currPage != null) {
			pageData.setCurrPage(Integer.parseInt(currPage));
		}

		List<House> houseList = houseDao.queryHouseListByUserID(loginUser.getUserId(), pageData);
		// ����������
		request.setAttribute("houseList", houseList);
		request.setAttribute("pageData", pageData);

		request.getRequestDispatcher("/page/manage.jsp").forward(request, response);
	}

}
