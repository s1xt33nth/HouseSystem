package com.wust.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wust.dao.UserDao;
import com.wust.entity.UserInfo;

public class UserServlet extends HttpServlet {
	private UserDao userDao = new UserDao();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
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
		if (method.equals("login")) {
			login(request, response);
		} else if (method.equals("register")) {
			register(request, response);
		} else if (method.equals("logout")) {
			logout(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rand = (String) request.getSession().getAttribute("sRand");
		String input = request.getParameter("veryCode");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserInfo userInfo = userDao.queryUserByName(username);

		if (!rand.equals(input)) {
			request.setAttribute("msg", "��������ȷ����֤�룡");
			request.getRequestDispatcher("/page/login.jsp").forward(request, response);
		} else if (userInfo == null) {
			request.setAttribute("msg", "�û��������ڣ�");
			request.getRequestDispatcher("/page/login.jsp").forward(request, response);
		} else if (!userInfo.getPassword().equals(password)) {
			request.setAttribute("msg", "�������");
			request.getRequestDispatcher("/page/login.jsp").forward(request, response);
		} else if (userInfo.getPassword().equals(password)) {
			HttpSession ses = request.getSession();
			ses.setAttribute("loginUser", userInfo);
			request.getRequestDispatcher("/house?method=gotoHouseManage").forward(request, response);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String telephone = request.getParameter("telephone");
		String realname = request.getParameter("realname");

		if (username == "" || password == "" || telephone == "" || realname == "") {
			request.setAttribute("msg", "����д��Ϣ��");
			request.getRequestDispatcher("/page/register.jsp").forward(request, response);
			return;
		}

		UserDao userDao = new UserDao();
		String pwd = userDao.queryPassword(username);
		if (pwd != null) {
			request.setAttribute("msg", "�û����ѱ�ע�ᣬ����ģ�");
			request.getRequestDispatcher("/page/register.jsp").forward(request, response);
		} else {
			UserInfo userInfo = new UserInfo(username, password, telephone, realname);
			boolean flag = userDao.insertInfo(userInfo);

			if (!flag) {
				request.setAttribute("msg", "ע��ʧ�ܣ������ԣ�");
				request.getRequestDispatcher("/page/register.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "ע��ɹ������¼��");
				request.getRequestDispatcher("/page/login.jsp").forward(request, response);
			}
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}
}
