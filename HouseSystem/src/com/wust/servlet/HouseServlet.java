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

		// 初始化pageData
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
		// 查询房型列表
		List<Dic> houseTypeList = CommonMethod.getHouseList();
		// 存入请求作用域
		request.setAttribute("houseTypeList", houseTypeList);
		request.setAttribute("pageData", pageData);

		List<House> houseList = houseDao.queryHouseListByConditionWithKeyword(keyword, housePrice, district, houseType,
				houseFloorage, timeRange, pageData);
		if (houseList.size() == 0) {
			request.setAttribute("msg", "抱歉，暂无此类房屋");
			request.getRequestDispatcher("/page/house_list.jsp").forward(request, response);
			return;
		}
		// 存入作用域
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

		// 初始化pageData
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
		// 查询房型列表
		List<Dic> houseTypeList = CommonMethod.getHouseList();
		// 存入请求作用域
		request.setAttribute("houseTypeList", houseTypeList);
		request.setAttribute("pageData", pageData);

		List<House> houseList = houseDao.queryHouseListByCondition(houseTitle, housePrice, district, houseType,
				houseFloorage, timeRange, pageData);
		if (houseList.size() == 0) {
			request.setAttribute("msg", "抱歉，暂无此类房屋");
			request.getRequestDispatcher("/page/house_list.jsp").forward(request, response);
			return;
		}
		// 存入作用域
		request.setAttribute("houseTitle", houseTitle);
		request.setAttribute("houseList", houseList);
		request.getRequestDispatcher("/page/house_list.jsp").forward(request, response);

	}

	private void gotoShow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取要展示的houseId
		int houseId = Integer.parseInt(request.getParameter("houseId"));
		// 查询要展示的House对象
		House house = houseDao.queryHouseByHouseId(houseId);
		// 查询对应的UserId
		UserInfo houseUserInfo = houseDao.queryUserInfoByHouseId(houseId);
		if (house == null) {
			request.setAttribute("msg", "房屋信息不存在");
			gotoHomePage(request, response);
		} else {
			request.setAttribute("house", house);
			// 查询房型列表
			List<Dic> houseTypeList = CommonMethod.getHouseList();
			// 存入请求作用域
			request.setAttribute("houseTypeList", houseTypeList);
			request.setAttribute("houseUserInfo", houseUserInfo);
			request.getRequestDispatcher("/page/show.jsp").forward(request, response);
		}
	}

	private void gotoHomePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");

		// 初始化pageData
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
		// 存入作用域
		request.setAttribute("houseList", houseList);
		request.setAttribute("pageData", pageData);
		// 查询房型列表
		List<Dic> houseTypeList = CommonMethod.getHouseList();
		// 存入请求作用域
		request.setAttribute("houseTypeList", houseTypeList);
		request.getRequestDispatcher("/page/house_list.jsp").forward(request, response);
	}

	private void houseDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取要删除的houseId
		int houseId = Integer.parseInt(request.getParameter("houseId"));

		boolean flag = houseDao.deleteHouse(houseId);

		if (!flag) {
			request.setAttribute("msg", "删除失败！");
			gotoHouseManage(request, response);
		} else {
			gotoHouseManage(request, response);
		}
	}

	private void gotoHouseModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取要修改的houseId
		int houseId = Integer.parseInt(request.getParameter("houseId"));
		// 查询要修改的House对象
		House house = houseDao.queryHouseByHouseId(houseId);
		if (house == null) {
			request.setAttribute("msg", "房屋信息不存在");
			gotoHouseManage(request, response);
		} else {
			request.setAttribute("house", house);
			// 查询房型列表
			List<Dic> houseTypeList = CommonMethod.getHouseList();
			// 存入请求作用域
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
		// 初始化SmartUpload对象
		su.initialize(this.getServletConfig(), request, response);
		com.jspsmart.upload.File file = null;
		// 允许上传类型
		String allowed = "gif,jpg,jpeg,png";
		// 不许上传类型
		String denied = "jsp,asp,php,aspx,html,htm,exe,bat";
		com.jspsmart.upload.Request req = null;
		// 设置上传文件大小
		int file_size = 2 * 1024 * 1024; // 2mb
		int i = 0;
		try {
			// 定义允许上传文件类型
			su.setAllowedFilesList(allowed);
			// 不允许上传文件类型
			su.setDeniedFilesList(denied);
			// 单个文件最大限制
			su.setMaxFileSize(file_size);
			su.setCharset("utf-8");
			// 执行上传
			su.upload();
			// 得到单个上传文件的信息
			file = su.getFiles().getFile(0);

			// 获取smartupload封装的request
			req = su.getRequest();

			String filename = "";
			// 绝对路径
			String filepath = null;
			// 相对路径
			String filePath = null;
			if (!file.isMissing()) {
				// 设置文件在服务器的保存位置
				filepath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\wtpwebapps\\HouseSystem\\upload\\";
//				filepath = "upload\\";
				String tmpFront = "" + (int) (Math.random() * 100000);
				filename = file.getFileName();
				String tmpBack = "." + filename.substring(filename.lastIndexOf('.') + 1);
				filePath = tmpFront + tmpBack;
				filepath += tmpFront + tmpBack;
//				System.out.println("filepath_modify: " + filepath);
//				System.out.println("filePath_modify: " + filePath);
				// 文件另存为
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
			request.setAttribute("msg", "房屋修改失败！");
			gotoHouseAdd(request, response);
		}

	}

	private void gotoHouseAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查询房型列表
		List<Dic> houseTypeList = CommonMethod.getHouseList();
		// 存入请求作用域
		request.setAttribute("houseTypeList", houseTypeList);

		request.getRequestDispatcher("/page/add.jsp").forward(request, response);

	}

	private void houseAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;

		String filename = "";
		SmartUpload su = new SmartUpload();
		// 初始化SmartUpload对象
		su.initialize(this.getServletConfig(), request, response);
		com.jspsmart.upload.File file = null;
		// 允许上传类型
		String allowed = "gif,jpg,jpeg,png";
		// 不许上传类型
		String denied = "jsp,asp,php,aspx,html,htm,exe,bat";
		com.jspsmart.upload.Request req = null;
		// 设置上传文件大小
		int file_size = 2 * 1024 * 1024; // 2mb
		int i = 0;
		try {
			// 定义允许上传文件类型
			su.setAllowedFilesList(allowed);
			// 不允许上传文件类型
			su.setDeniedFilesList(denied);
			// 单个文件最大限制
			su.setMaxFileSize(file_size);
			su.setCharset("utf-8");
			// 执行上传
			su.upload();
			// 得到单个上传文件的信息
			file = su.getFiles().getFile(0);
			// 绝对路径
			String filepath = null;
			// 相对路径
			String filePath = null;
			if (!file.isMissing()) {
				// 设置文件在服务器的保存位置
				filepath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\wtpwebapps\\HouseSystem\\upload\\";
//				filepath = "upload\\";
				String tmpFront = "" + (int) (Math.random() * 100000);
				filename = file.getFileName();
				String tmpBack = "." + filename.substring(filename.lastIndexOf('.') + 1);
				filePath = tmpFront + tmpBack;
				filepath += tmpFront + tmpBack;
//				System.out.println("filepath_Add: " + filepath);
//				System.out.println("filePath_Add: " + filePath);
//				// 文件另存为
				file.setCharset("utf-8");
				file.saveAs(filepath, SmartUpload.SAVE_PHYSICAL);
			}

			// 获取smartupload封装的request
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
			request.setAttribute("msg", "房屋发布失败！");
			gotoHouseAdd(request, response);
		}
	}

	private void gotoHouseManage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String currPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");

		UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");

		// 初始化pageData
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
		// 存入作用域
		request.setAttribute("houseList", houseList);
		request.setAttribute("pageData", pageData);

		request.getRequestDispatcher("/page/manage.jsp").forward(request, response);
	}

}
