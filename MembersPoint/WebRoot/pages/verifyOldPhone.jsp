<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html lang="en">
<head>
<head>
<base href="<%=basePath%>">

<meta charset="utf-8">
<title>验证手机号</title>
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
	<div class="main-content" style="backgorund:white;">
		<div class="navbar navbar-default" role="navigation">
			<div class="navbar-header">
				<a class="" href="#"><span class="navbar-brand"><span
						class="fa fa-paper-plane"></span>飞行积分</span></a>
			</div>

			<div class="navbar-collapse collapse" style="height: 1px;"></div>
		</div>
		<div class="header">
			<h1 class="page-title" style="margin-top:20px;">验证手机号码</h1>
		</div>
		<div class="main-content"  style="margin-left:20px;margin-right:20px;">
			<div class="row">
				<div class="col-md-4">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form id="tab" action="verifyCode.action" method="post">
								<div class="form-group">
									<label>电话</label> <input type="text" id="phoneNum" readonly="readonly" name="oldPhoneNum" value="<s:property value="oldPhoneNum"/>"
										class="form-control">
								</div>
								<div id="verifyDiv" class="form-group">
								<table>
									<tr>
										<td><a id="getVerifyCode" href="javascript:void(0)" onclick="getVerify()" data-toggle="modal" class="btn btn-primary">获取验证码</a></td>
										<td><input type="text" name="vertifyCode" class="form-control" style="margin-left:20px;width:100%;"></td>
									</tr>
								</table>
								</div>
							</form>
						</div>

						<div class="btn-toolbar list-toolbar">
							<a href="#myModal" data-toggle="modal" class="btn btn-danger">提交</a>
						</div>
					</div>
				</div>

				<div class="modal small fade" id="myModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h3 id="myModalLabel">手机验证提交</h3>
							</div>
							<div class="modal-body">

								<p class="error-text">
									<i class="fa fa-warning modal-icon"></i>确定提交验证码吗？
								</p>
							</div>
							<div class="modal-footer">
								<button class="btn btn-default" data-dismiss="modal"
									aria-hidden="true">取消</button>
								<button class="btn btn-danger" data-dismiss="modal" id="sureSub">确定</button>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

	</div>
	<script src="pages/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
		$("#sureSub").click(function(){
			//TODO 做一些验证
			$("#tab").submit();
		});
		var countdown = 60;
		function settime(){
			if (countdown == 0) { 
				$('#getVerifyCode').removeAttr("disabled");    
				$('#getVerifyCode').html("获取验证码"); 
				countdown = 60; 
				return;
			} else { 
				$('#getVerifyCode').attr("disabled", true); 
				$('#getVerifyCode').html("重新发送(" + countdown + ")"); 
				countdown--; 
			} 
			setTimeout(function(){settime() },1000);
		}
		function getVerify(){
			if($('#phoneNum').val()=="" || !$('#phoneNum').val().match(/^1[3|4|5|8][0-9]\d{4,8}$/)){
				alert("请联系我们的工作人员更换！");
			}else{
			$.ajax({
				url:"sendVertify.action",
				dataType:"json",
				async:true,//是否异步
				data:{'phoneNum':$("#phoneNum").val()},
				type:"POST",
				beforeSend: function() {
			        //请求前的处理
			    },
			    success: function(data) {
			    if(data=="success"){
					    setTimeout(function(){settime() },1000);
				    }else{
				    	alert("程序异常，请联系我们！");
				    }
			    },
			    complete: function() {
			        //请求完成的处理
			    },
			    error: function() {
			        //请求出错处理
			    }
			});
				
			}
		}
	</script>
</body>
</html>
