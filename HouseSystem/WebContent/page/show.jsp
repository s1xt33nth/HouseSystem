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
</script>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">
			<a href="/HouseSystem"><img src="images/logo.gif" /></a>
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
					value="首       页" onclick="toUrl('')" /></label>
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
			<form method="post" action="house?method=searchHouse" id='sform'>
				<dt>
					<ul>
						<li class="bold">房屋信息</li>
						<li>包含关键字：
						    <input type="text" class="text" value='' name="keyword" autocomplete="off"/>
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
	<div id="position" class="wrap">当前位置：易租房网 - 浏览房源</div>
	<div id="view" class="main wrap">
		<div class="intro">
			<div class="lefter">
				<h1>${ house.houseTitle }</h1>
				<div class="subinfo">${ house.pubDate }</div>
				<div class="houseinfo">
					<p>
						户 型：<span><c:forEach var="dic"
								items="${ requestScope.houseTypeList }">
								<c:if test="${ house.houseType eq dic.code }">
							${dic.text}
						</c:if>
							</c:forEach></span>
					</p>
					<p>
						面 积：<span>${ house.houseFloorage }m<sup>2</sup></span>
					</p>
					<p>
						位 置：<span>${ house.district }  ${ house.street }</span>
					</p>
					<p>
						联系方式：<span>${ house.contact }</span>
					</p>
				</div>
			</div>
			<div class="side">
				<p>
					<a href="" class="bold">北京易房地产经纪公司</a>
				</p>
				<p>资质证书：有</p>
				<p>内部编号： 00${ houseUserInfo.userId }${ house.houseId }</p>
				<p>联 系 人：${  houseUserInfo.realname } </p>
				<p>
					联系电话：<span>${  houseUserInfo.telephone }</span>
				</p>
				<p>
					手机号码：<span>${  houseUserInfo.telephone }</span>
				</p>
			</div>
			<div class="clear"></div>
			<div class="introduction">
				<h2>
					<span><strong>房源照片</strong></span>
				</h2>
				<div class="content" style="text-align: center; display: table-cell; vertical-align: middle; ">
					 <c:if test="${empty house.filePath }">
							<img src="images/thumb_house.gif" />
					 </c:if>
					 <c:if test="${not empty house.filePath }">
							<img src="upload/${house.filePath}" style="max-width: 100%; max-height: 100%;"/>
					 </c:if>
				</div>
			</div>
			<div class="introduction">
				<h2>
					<span><strong>房源详细信息</strong></span>
				</h2>
				<div class="content">
					<p>${ house.description }</p>
				</div>
			</div>
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

