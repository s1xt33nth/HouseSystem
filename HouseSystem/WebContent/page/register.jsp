<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易租房 - 用户注册</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<style type="text/css">
body {
	background: url(images/bg.jpg);
	background-size: cover;
}
</style>
<script type="text/javascript">
	function toUrl(url) {
		window.location.href = url;
		return;
	}
	function ckun() {
		var username = document.getElementById("username").value;
		if (username == "") {
			document.getElementById("nameMsg").innerHTML = "<font color='red'>用户名不能为空！</font>";
			//document.getElementById("username").focus();
		} else if (!/^\w{4,16}$/.test(username)) {
			document.getElementById("nameMsg").innerHTML = "<font color='red'>格式不正确！</font>";
			//document.getElementById("username").focus();
		} else {
			document.getElementById("nameMsg").innerHTML = "";
		}
	}

	function ckpw() {
		var password = document.getElementById("password").value;
		if (password == "") {
			document.getElementById("passwordMsg").innerHTML = "<font color='red'>密码不能为空！</font>";
			//document.getElementById("password").focus();
		} else if (!/^\w{6,12}$/.test(password)) {
			document.getElementById("passwordMsg").innerHTML = "<font color='red'>格式不正确！</font>";
			//document.getElementById("password").focus();
		} else {
			document.getElementById("passwordMsg").innerHTML = "";
		}
	}

	function ckrpw() {
		var a = document.getElementById("repassword").value;
		var checkpassword = a.toString();
		var b = document.getElementById("password").value;
		var password = b.toString();
		if (checkpassword == "") {
			document.getElementById("repasswordMsg").innerHTML = "<font color='red'>请再次输入密码！</font>";
			//document.getElementById("repassword").focus();
		} else if (checkpassword != password) {
			document.getElementById("repasswordMsg").innerHTML = "<font color='red'>两次密码不匹配！</font>";
		} else {
			document.getElementById("repasswordMsg").innerHTML = "";
		}
	}

	function cktp() {
		var telephone = document.getElementById("telephone").value;
		if (telephone == "") {
			document.getElementById("telephoneMsg").innerHTML = "<font color='red'>电话不能为空！</font>";
			//document.getElementById("username").focus();
		} else if (!/^\d{11}$/.test(telephone)) {
			document.getElementById("telephoneMsg").innerHTML = "<font color='red'>格式不正确！</font>";
			//document.getElementById("username").focus();
		} else {
			document.getElementById("telephoneMsg").innerHTML = "";
		}
	}

	function ckrn() {
		var realname = document.getElementById("realname").value;
		if (realname == "") {
			document.getElementById("realnameMsg").innerHTML = "<font color='red'>姓名不能为空！</font>";
			//document.getElementById("username").focus();
		} else if (!/^\w{2,5}$/.test(realname)) {
			document.getElementById("realnameMsg").innerHTML = "<font color='red'>格式不正确！</font>";
			//document.getElementById("username").focus();
		} else {
			document.getElementById("realnameMsg").innerHTML = "";
		}
	}
</script>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">
			<a href="javascript:void(0);"
				onclick="toUrl('house?method=gotoHomePage')"><img
				src="images/logo.gif" /></a>
		</div>
	</div>
	<div id="regLogin" class="wrap">
		<div class="dialog">
			<dl class="clearfix">
				<dt>新用户注册</dt>
				<dd class="past">填写个人信息</dd>
			</dl>
			<div class="box">
				<form action="user?method=register" method="post"
					name="registerForm">
					<div class="infos">
						<table class="field" style="align: center; width: 100%">
							<tr>
								<td colspan="2" style="color: blue; text-align: center;">${msg}</td>
							</tr>
							<tr>
								<td class="field">用 户 名：</td>
								<td><input type="text" class="text" name="username"
									onblur="ckun()" id="username" />
									<div style="display: inline; font-size: 14px;" id="nameMsg">长度4-16字符</div>
								</td>
							</tr>
							<tr>
								<td class="field">密 码：</td>
								<td><input type="password" class="text" name="password"
									onblur="ckpw()" id="password" />
									<div style="display: inline; font-size: 14px;" id="passwordMsg">长度6-12位</div>
								</td>
							</tr>
							<tr>
								<td class="field">确认密码：</td>
								<td><input type="password" class="text" name="repassword"
									onblur="ckrpw()" id="repassword" />
									<div style="display: inline; font-size: 14px;"
										id="repasswordMsg"></div></td>
							</tr>
							<tr>
								<td class="field">电 话：</td>
								<td><input type="text" class="text" name="telephone"
									onblur="cktp()" id="telephone" />
									<div style="display: inline; font-size: 14px;"
										id="telephoneMsg">长度11位</div></td>
							</tr>
							<tr>
								<td class="field">用户姓名：</td>
								<td><input type="text" class="text" name="realname"
									onblur="ckrn()" id="realname" />
									<div style="display: inline; font-size: 14px;" id="realnameMsg">长度2-5字符</div></td>
							</tr>
						</table>
						<div class="buttons">
							<input type="submit" value="立即注册" /> <input type="button"
								value="返回登录"
								onclick='document.location="/HouseSystem/page/login.jsp"' />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="footer" class="wrap">
		<dl>
			<dt>易租房 &copy; 2010易房网 京ICP证1000001号</dt>
		</dl>
	</div>
</body>
</html>

