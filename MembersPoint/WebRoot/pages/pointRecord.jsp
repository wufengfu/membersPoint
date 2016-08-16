<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">

<title>积分明细</title>
<meta charset="utf-8">
    <title>Bootstrap Admin</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="pages/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="pages/lib/font-awesome/css/font-awesome.css">
<script src="pages/lib/jquery-1.11.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="pages/stylesheets/theme.css">
<link rel="stylesheet" type="text/css"
	href="pages/stylesheets/premium.css">
</head>

<body  class=" theme-blue" style="background:white;">


	<script type="text/javascript">
		$(function() {
			var match = document.cookie.match(new RegExp('color=([^;]+)'));
			if (match)
				var color = match[1];
			if (color) {
				$('body').removeClass(function(index, css) {
					return (css.match(/\btheme-\S+/g) || []).join(' ')
				})
				$('body').addClass('theme-' + color);
			}

			$('[data-popover="true"]').popover({
				html : true
			});

		});
	</script>
	<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
	color: #fff;
}
</style>

	<script type="text/javascript">
		$(function() {
			var uls = $('.sidebar-nav > ul > *').clone();
			uls.addClass('visible-xs');
			$('#main-menu').append(uls.clone());
		});
		
	</script>

<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="" href="#"><span class="navbar-brand"><span
					class="fa fa-paper-plane"></span>飞行积分</span></a>
		</div>

		<div class="navbar-collapse collapse" style="height: 1px;"></div>
	</div>

	<div class="main-content" style="margin-top:50px;height:100%;">

		<table class="table">
			<!-- <thead>
				<tr>
					<th>序号</th>
					<th>交易日期</th>
					<th>交易时间</th>
					<th>交易流水号</th>
					<th>交易类型</th>
					<th>交易积分</th>
					<th>合作机构简称</th>
					<th>在途状态</th>
					<th>交易状态</th>
				</tr>
			</thead> -->
			<tbody>
			<s:iterator value="records" var="record" status="ser">
				<tr>
				<td colspan="4"><s:property value="#ser.count" /></td>
				</tr>
				<tr>
					<th>交易流水号</th>
					<td style="font-size:12px;"><s:property value="#record.serialNum" /></td>
					<th>交易日期</th>
					<td><s:date name="#record.dealTime" format="yyyy/MM/dd" /></td>
				</tr>
				<tr>
					<th>交易类型</th>
					<s:if test="{#record.dealType == 1}"><td>汇入</td></s:if>
					<s:else><td>汇出</td></s:else>
					<th>交易时间</th>
					<td><s:date name="#record.dealTime" format="HH:mm:ss" /></td>
				</tr>
				<tr>
					<th>交易积分</th>
					<td><s:property value="#record.dealPoint" /></td>
					<th>在途状态</th>
					<s:if test="{#record.comStatus==1}"><td>已生效</td></s:if>
					<s:else><td>失败</td></s:else>
				</tr>
				<tr>
					<th>合作机构简称</th>
					<td><s:property value="#record.airlinecompany.companyName" /></td>
					<th>交易状态</th>
					<s:if test="{#record.dealStatus==1}"><td>成功</td></s:if>
					<s:else><td>失败</td></s:else>
				</tr>
			</s:iterator>
			</tbody>
		</table>
	</div>
	<script src="pages/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
	</script>

</body>
</html>
