<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
body {
	background: url(images/bg2.jpg);
	background-size: cover;
}

table {
	margin: auto;
}

button {
	background: #FE5A3A;
	border: 1px solid #FEAF50;
}

#content {
    position: relative;
    top: 50%;
    height: 1000px;
    margin-top: -1200px; /* negative half of the height */
}
</style>
</head>
<body>
	<%-- 
	<%
		request.getRequestDispatcher("house?method=gotoHomePage").forward(request, response);
	%>
--%>
	<div class="content">
		<table>
		<tr>
		<td></td>
		</tr>
		<tr>
		<td></td>
		</tr>
		<tr>
		<td></td>
		</tr>
		<tr>
		<td></td>
		</tr>
			<tr>
				<td><label class="ui-green">
						<button
							onclick='document.location="/HouseSystem/house?method=gotoHomePage"'><p style="color:white">&nbsp;进&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;入&nbsp;</p></button>
				</label></td>
			</tr>
		</table>
	</div>
</body>
</html>