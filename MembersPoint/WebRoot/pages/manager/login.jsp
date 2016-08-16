<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>管理员登录</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="${basePath}pages/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="${basePath}pages/lib/font-awesome/css/font-awesome.css">

<script src="${basePath}pages/lib/jquery-1.11.1.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="${basePath}pages/stylesheets/theme.css">
<link rel="stylesheet" type="text/css" href="${basePath}pages/stylesheets/premium.css">

</head>
<body class=" theme-blue">

	<!-- Demo page code -->

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
			<a class="" href="index.html"><span class="navbar-brand"><span
					class="fa fa-paper-plane"></span> 飞行积分</span></a>
		</div>
		<div class="navbar-collapse collapse" style="height: 1px;"></div>
	</div>

	<div class="dialog">
		<div class="panel panel-default">
			<p class="panel-heading no-collapse">登录</p>
			<div class="panel-body">
				<form id="loginForm" action="login.action" method="post">
					<div class="form-group">
						<label>用户名</label> <input type="text" id="userName" name="userName" class="form-control span12">
					</div>
					<div class="form-group">
						<label>密码</label> <input type="text" id="password" name="password" class="form-control span12">
					</div>
					<div class="form-group">
					<label id="messageTip" class="pull-left" style="color:red;"><s:property value="message"/></label>
						<a href="javascript:void(0);" onclick="submit()" class="btn btn-primary pull-right">登录</a>
						<div class="clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="${basePath}pages/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
		$("#userName").focus(function(){
			$("#messageTip").html("");
		});
		$("#password").focus(function(){
			$("#messageTip").html("");
		});
		function submit(){
			var result = 0;
			if($("#userName").val()==""){
				result = 1;
			}
			if($("#password").val()==""){
				result = 1;
			}
			if(result!=1){
				$("#loginForm").submit();
			}else{
				$("#messageTip").html("用户名或密码不能为空！");
			}
		}
	</script>


</body>
</html>

