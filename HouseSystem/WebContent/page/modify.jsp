<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//设置令牌标记
	session.setAttribute("token", System.currentTimeMillis());
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易租房 -修改房屋信息</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript">
	function checkForm() {
		var houseTitle = document.getElementsByName('houseTitle')[0].value;
		var houseFloorage = document.getElementsByName('houseFloorage')[0].value;
		var housePrice = document.getElementsByName('housePrice')[0].value;
		var houseDate = document.getElementsByName('houseDate')[0].value;
		var district = document.getElementsByName('district')[0].value;
		var street = document.getElementsByName('street')[0].value;
		var contact = document.getElementsByName('contact')[0].value;
		if (houseTitle == "") {
			document.getElementById("ckMsg").innerHTML = "请输入标题！";
			return false;
		} else if (houseFloorage == "") {
			document.getElementById("ckMsg").innerHTML = "请输入面积！";
			return false;
		} else if (housePrice == "") {
			document.getElementById("ckMsg").innerHTML = "请输入价格！";
			return false;
		} else if (houseDate == "") {
			document.getElementById("ckMsg").innerHTML = "请输入房产证日期！";
			return false;
		} else if (district == "") {
			document.getElementById("ckMsg").innerHTML = "请输入区域！";
			return false;
		} else if (street == "") {
			document.getElementById("ckMsg").innerHTML = "请输入街道！";
			return false;
		} else if (contact == "") {
			document.getElementById("ckMsg").innerHTML = "请输入联系方式！";
			return false;
		} else {
			return true;
		}
	}

	var districts = new Array();
	districts['江岸区'] = [ '上海街道', '大智街道', '一元街道', '车站街道', '四唯街道', '永清街道',
			'西马街道', '球场街道', '劳动街道', '二七街道', '新村街道', '丹水池街道', '台北街道', '花桥街道',
			'谌家矶街道', '后湖街道' ];
	districts['江汉区'] = [ '民族街道', '花楼街道', '水塔街道', '民权街道', '满春街道', '民意街道',
			'新华街道', '万松街道', '唐家墩街道', '北湖街道', '前进街道', '常青街道', '汉兴街道' ];
	districts['硚口区'] = [ '易家墩街道', '韩家墩街道', '宗关街道', '汉水桥街道', '宝丰街道', '荣华街道',
			'崇仁街道', '汉中街道', '汉正街街道', '六角亭街道', '长丰街道' ];
	districts['汉阳区'] = [ '翠微街道', '建桥街道', '月湖街道', '晴川街道', '鹦鹉街道', '洲头街道',
			'五里墩街道', '琴断口街道', '江汉二桥街道', '永丰街道', '江堤街道' ];
	districts['武昌区'] = [ '积玉桥街道', '杨园街道', '徐家棚街道', '粮道街街道', '中华路街道', '黄鹤楼街道',
			'紫阳街道', '白沙洲街道', '首义路街道', '中南路街道', '水果湖街道', '珞珈山街道', '石洞街道', '南湖街道' ];
	districts['青山区'] = [ '红卫路街道', '冶金街道', '新沟桥街道', '红钢城街道', '工人村街道', '青山镇街道',
			'厂前街道', '武东街道', '白玉山街道', '钢花村街道' ];
	districts['洪山区'] = [ '珞南街道', '关山街道', '狮子山街道', '张家湾街道', '红旗街道', '葛化街道',
			'洪山街道', '和平街道' ];
	//加载地区
	function loadDistrict() {
		//获取地区下拉框选项集合
		var ops = document.getElementById("districtId").options;
		ops.add(new Option("", ""));
		for ( var i in districts) {
			if ("${requestScope.house.district}" == i) {
				var opt = document.createElement("option");
				opt.value = i;
				opt.innerText = i;
				opt.selected = true; //设置为选中
				document.getElementById("districtId").appendChild(opt);
			} else {
				var opt = document.createElement("option");
				opt.value = i;
				opt.innerText = i;
				document.getElementById("districtId").appendChild(opt);
			}
		}

		//加载街道 列表,并选中当前房屋所属街道 
		for ( var j in districts["${ requestScope.house.district}"]) {
			if (districts["${ requestScope.house.district}"][j] == "${ requestScope.house.street}") {
				var opt = document.createElement("option");
				opt.value = districts["${ requestScope.house.district}"][j];
				opt.innerText = districts["${ requestScope.house.district}"][j];
				opt.selected = true;
			} else {
				var opt = document.createElement("option");
				opt.value = districts["${ requestScope.house.district}"][j];
				opt.innerText = districts["${ requestScope.house.district}"][j];
			}
			document.getElementById("streetId").appendChild(opt);
		}
	}

	//加载街道
	function loadStreet() {
		//获取选中的区
		var selectDistrict = document.getElementById("districtId").value;
		//获取街道下拉框选项集合 
		var ops = document.getElementById("streetId").options;
		//先清空
		ops.length = 0;
		for ( var j in districts[selectDistrict]) {
			ops.add(new Option(districts[selectDistrict][j],
					districts[selectDistrict][j]));
		}
	}
	
</script>
</head>
<body onload="loadDistrict()">
	<div id="header" class="wrap">
		<div id="logo">
			<a href="/HouseSystem"><img src="images/logo.gif" /></a>
		</div>

	</div>
	<div id="regLogin" class="wrap">
		<div class="dialog">
			<dl class="clearfix">
				<dt>房屋信息修改</dt>
				<dd class="past">填写房屋信息</dd>

			</dl>
			<div class="box">
				<form action="house?method=houseModify" method="post" enctype="multipart/form-data" 
					name="houseModifyForm" onsubmit="return checkForm()">
					<!-- 表单隐藏域 -->
					<input type="hidden" name="userId" value="${ house.userId }" /> 
					<input type="hidden" name="houseId" value="${ house.houseId }" /> 
					<input type="hidden" name="formToken" value="${ sessionScope.token }" />
					<div class="infos">
						<table class="field" style="align: center; width: 100%">
							<tr>
								<td colspan="2" style="color: red; text-align: center;"><p
										id="ckMsg">${requestScope.msg}</p></td>
							</tr>
							<tr>
								<td class="field">标 题：</td>
								<td><input type="text" class="text" name="houseTitle"
									value="${ house.houseTitle }" /></td>
							</tr>
							<tr>
								<td class="field">户 型：</td>
								<td><select class="text" name="houseType">
										<c:forEach var="dic" items="${ requestScope.houseTypeList }">
											<c:if test="${ house.houseType eq dic.code }">
												<option value="${ dic.code }" selected>${ dic.text }</option>
											</c:if>
											<c:if test="${ house.houseType ne dic.code }">
												<option value="${ dic.code }">${ dic.text }</option>
											</c:if>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td class="field">面 积：</td>
								<td><input type="text" class="text" name="houseFloorage"
									value="${ house.houseFloorage }" /></td>
							</tr>
							<tr>
								<td class="field">价 格：</td>
								<td><input type="text" class="text" name="housePrice"
									value="${ house.housePrice }" /></td>
							</tr>

							<tr>
								<td class="field">房产证日期：</td>
								<td><input type="date" class="text" name="houseDate"
									value="${ house.houseDate }" /></td>
							</tr>

							<tr>
								<td class="field">位 置：</td>
								<td style="vertical-align: middle;">区：<select class="text"
									name="district" id="districtId" onchange="loadStreet()"
									style="width: 70px; height: 22px; vertical-align: middle;">
								</select> 街：<select class="text" name="street" id="streetId"
									style="width: 100px; height: 22px; vertical-align: middle;">
								</select>
								</td>
							</tr>
							<tr>
								<td class="field">联系方式：</td>
								<td><input type="text" class="text" name="contact"
									value="${ house.contact }" /></td>
							</tr>
							<tr>
								<td class="field">详细信息：</td>
								<td><textarea name="description">${ house.description }</textarea></td>
							</tr>
							<tr>
								<td class="field">上传照片：</td>
								<td><input type="file" class="text" name="file" />
								<!-- 表单隐藏域 -->
					            <input type="hidden" name="filePath" value="${ house.filePath }" /> 
					            </td>
							</tr>
							<tr>
								<td class="field">照片预览 ：</td>
								<td>
								<img src="upload/${house.filePath}" style="max-width: 60%; max-height: 40%;"/>
					            </td>
							</tr>
						</table>
						<div class="buttons">
							<input type="submit" value="立即修改" /> <input type="button"
								value="返       回" onclick="javascript:window.history.go(-1);" /> 
						</div>
					</div>
				</form>
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