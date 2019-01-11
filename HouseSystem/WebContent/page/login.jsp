<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>易租房 - 用户登录</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<style type="text/css">    
     body{    
        background: url(images/bg.jpg);    
        background-size:cover;  
     }    
</style> 
<script type="text/javascript">
function checkForm() {
	var username = document.getElementsByName('username')[0].value;
	var password = document.getElementsByName('password')[0].value;
	var veryCode = document.getElementById('veryCode').value;
	if (username == "") {
		document.getElementById("ckMsg").innerHTML = "请输入用户名！";
		return false;
	} else if (password == "") {
		document.getElementById("ckMsg").innerHTML = "请输入密码！";
		return false;
	} else if (veryCode == "") {
		document.getElementById("ckMsg").innerHTML = "请输入验证码！";
		return false;
	} else {
		return true;
	}
}
</script>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">
			<a href="/HouseSystem"><img src="images/logo.gif" /></a>
		</div>
	</div>
	<div id="regLogin" class="wrap">
		<div class="dialog">
			<div class="box">
				<h4>用户登录</h4>
				<form action="user?method=login" method="post" name="loginForm"
					onsubmit="return checkForm()">
					<div class="infos">
						<table class="field" style="align: center; width: 100%">
							<tr>
								<td colspan="2" style="color: red; text-align: center;"><p id="ckMsg"> ${msg} </p></td>
							</tr>
							<tr>
								<td class="field">用 户 名：</td>
								<td><input type="text" class="text" name="username" /></td>
							</tr>
							<tr>
								<td class="field">密 码：</td>
								<td><input type="password" class="text" name="password" /></td>
							</tr>
							<tr>
								<td class="field">验 证 码：</td>
								<td><input type="text" class="text verycode"
									name="veryCode" id="veryCode"  autocomplete="off"/><img id="codeImg"
									src="page/code.jsp"
									onclick="this.src='page/code.jsp?id='+ Math.random();"
									title="单击图片刷新" 
									style="vertical-align: middle; position: relative; left: 10%" /></td>
							</tr>
						</table>
						<div class="buttons">
							<input type='submit' value="立即登录" /> <input type='button'
								value='注册'
								onclick='document.location="/HouseSystem/page/register.jsp"' />
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


