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
<title>易租房 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript">
	function toUrl(url) {
		window.location.href = url;
		return;
	}
	function doSearch() {
		var f = document.getElementById('sform');
		f.submit();
	}
	function gotoPage(currPage){
		var pageSize = document.getElementById("pageSize").value;
		window.location.href = "<%=basePath%>house?method=gotoHomePage&pageSize="+pageSize+"&currPage="+currPage;
	}
	function gotoShow(houseId){
		window.location.href = '<%=basePath%>house?method=gotoShow&houseId='+houseId;
	}
</script>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">
			<!--  <a href="/HouseSystem"><img src="images/logo.gif" /></a> -->
			<a href="javascript:void(0);" onclick="toUrl('house?method=gotoHomePage')"><img src="images/logo.gif" /></a>
		</div>
		<div class="search">
			<c:if test="${ sessionScope.loginUser.username == null}">
				<label class="ui-green"><input type="button" name="search"
					value="登       录" onclick="toUrl('/HouseSystem/page/login.jsp')" /></label>
			</c:if>
			<c:if test="${ sessionScope.loginUser.username != null}">
				<label class="ui-red"><input type="button"
					disabled="disabled" name="search"
					value="${sessionScope.loginUser.username}" /></label>
			    <label class="ui-green"><input type="button" name="search"
					value="首       页" onclick="toUrl('house?method=gotoHomePage')" /></label>
				<label class="ui-green"><input type="button" name="search"
					value="管理房屋信息" onclick="toUrl('house?method=gotoHouseManage')" /></label>
				<label class="ui-green"><input type="button" name="search"
					value="发布房屋信息" onclick="toUrl('house?method=gotoHouseAdd')" /></label>
				<label class="ui-green"><input type="button" name="search"
					value="退       出" onclick="toUrl('user?method=logout')" /></label>
			</c:if>
		</div>
	</div>

	<div id="navbar" class="wrap">
		<dl class="search clearfix">
			<form method="post" action="house?method=searchHouseList" id='sform'>
				<dt>
					<ul>
						<li class="bold">房屋信息</li>
						<li>标题：<c:if test="${ houseTitle != null}">
						    <input type="text" class="text" value='${ houseTitle }' name="title" autocomplete="off"/>
						</c:if>
						<c:if test="${ houseTitle == null}">
						    <input type="text" class="text" value='' name="title" autocomplete="off"/>
						</c:if>
							<label class="ui-blue"> 
							<input type="button" onclick='doSearch()' name="search" value="搜索房屋" /></label>
						</li>
					</ul>
				</dt>
				<dd>
					<ul>
						<li class="first">价格</li>
						<li><select name='price' style="width: 100px">
								<option value='0'>不限</option>
								<option value='0-1000'>1000元以下</option>
								<option value='1000-2000'>1000元—2000元</option>
								<option value='2000-1000000'>2000元以上</option>
						</select></li>
					</ul>
				</dd>
				<dd>
					<ul>
						<li class="first">房屋位置</li>
						<li><select name='street_id' style="width: 100px">
								<option value='0'>不限</option>
								<option value='1000'>江岸区</option>
								<option value='1001'>江汉区</option>
								<option value='1002'>硚口区</option>
								<option value='1003'>汉阳区</option>
								<option value='1004'>武昌区</option>
								<option value='1005'>青山区</option>
								<option value='1006'>洪山区</option>
						</select></li>
					</ul>
				</dd>
				<dd>
					<ul>
						<li class="first">房型</li>
						<li><select name='type_id' style="width: 100px">
								<option value='0'>不限</option>
								<option value='1000'>一室一厅</option>
								<option value='1001'>一室两厅</option>
								<option value='1002'>两室一厅</option>
								<option value='1003'>两室两厅</option>
						</select></li>
					</ul>
				</dd>
				<dd>
					<ul>
						<li class="first">面积</li>
						<li><select name='floorage' style="width: 100px">
								<option value='0'>不限</option>
								<option value='0-50'>50以下</option>
								<option value='50-100'>50-100</option>
								<option value='100-1000000'>100以上</option>
						</select></li>
					</ul>
				</dd>
				<dd>
					<ul>
						<li class="first">更新时间</li>
						<li><select name='time_range' style="width: 100px">
								<option value='00'>不限</option>
								<option value='01'>今天</option>
								<option value='02'>一周内</option>
								<option value='03'>一月内</option>
						</select></li>
					</ul>
				</dd>
			</form>
		</dl>
	</div>

	<div class="main wrap">
		<table class="house-list">
			<tr>
				<td colspan="2" style="color: red; text-align: center;">${msg}</td>
			</tr>
			<c:forEach var="house" items="${ requestScope.houseList }"
				varStatus="vs">
				<c:if test="${ vs.count mod 2 eq 0 }">
					<c:set var="className" value="odd" />
				</c:if>
				<c:if test="${ vs.count mod 2 ne 0 }">
					<c:set var="className" value="" />
				</c:if>
				<tr class="${ className }">
					<td class="house-thumb"><span><a
							href="javascript:gotoShow(${house.houseId})"> <c:if
									test="${empty house.filePath }">
									<img src="images/thumb_house.gif" />
								</c:if> <c:if test="${not empty house.filePath }">
									<img src="upload/${house.filePath}" height="90" width="120" />
								</c:if>
						</a> </span></td>
					<td>
						<dl>
							<dt>
								<a href="javascript:gotoShow(${house.houseId})">
									${house.houseTitle} </a>
							</dt>
							<dd>
								${house.district} ${house.street} ${house.houseFloorage}平米 <br />
								联系方式：${house.contact}
							</dd>
						</dl>
					</td>
					<td class="house-type"><c:forEach var="dic"
							items="${ requestScope.houseTypeList }">
							<c:if test="${ house.houseType eq dic.code }">
							${dic.text}
						</c:if>
						</c:forEach></td>
					<td class="house-price"><span>${house.housePrice}</span>元/月</td>
				</tr>
			</c:forEach>
		</table>

		<div class="pager" style="text-align: center;">
			<span>当前第${pageData.currPage}页</span>&nbsp;&nbsp; <span>每页<input
				type="text" id="pageSize" onchange="gotoPage(1)"
				style="width: 20px; height: 10px; text-align: center;"
				value="${pageData.pageSize}" />条记录
			</span>&nbsp;&nbsp; <span>总页数为${pageData.sumPages}页</span>&nbsp;&nbsp; <span
				class="total"> <c:if test="${pageData.currPage eq 1 }">
					<font color='gray'>首页 | 上一页</font>
				</c:if> <c:if test="${pageData.currPage ne 1 }">
					<a href="javascript:gotoPage(1)">首页</a> |
					<a href="javascript:gotoPage(${pageData.currPage-1})">上一页</a> |
				</c:if> <c:if test="${pageData.currPage eq pageData.sumPages }">
					<font color='gray'>下一页 | 末页</font>
				</c:if> <c:if test="${pageData.currPage ne pageData.sumPages }">
					<a href="javascript:gotoPage(${pageData.currPage+1})">下一页</a> |
					<a href="javascript:gotoPage(${pageData.sumPages})">末页</a>
				</c:if>
			</span>
		</div>
	</div>
	<div id="footer" class="wrap">
		<dl>
			<dt>易租房 &copy; 2010易房网 京ICP证1000001号</dt>
			<dd>关于我们 · 联系方式 · 意见反馈 · 帮助中心</dd>
		</dl>
	</div>
</body>
</html>