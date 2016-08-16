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
<title>账号设置</title>
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
			<h1 class="page-title" style="margin-top:20px;">账号设置</h1>
		</div>
		<div class="main-content"  style="margin-left:20px;margin-right:20px;">
			<div class="row">
				<div class="col-md-4">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form id="tab" action="perfectInfo.action" method="post">
								<div class="form-group">
									<table>
										<tr>
											<td><label>姓</label></td>
											<td><label style="margin-left:20px;">姓拼音</label></td>
										</tr>
										<tr>
											<td><input type="text" name="user.familyName" value="<s:property value="user.familyName" />"
												class="form-control"></td>
											<td><input type="text" name="user.familyNameSpell" value="<s:property value="user.familyNameSpell" />"
												class="form-control"  style="margin-left:20px;"></td>
										</tr>
									</table>
								</div>
								<div class="form-group">
									<table>
										<tr>
											<td><label>名</label></td>
											<td><label style="margin-left:20px;">名拼音</label></td>
										</tr>
										<tr>
											<td><input type="text" name="user.realName" value="<s:property value="user.realName" />"
												class="form-control"></td>
											<td><input type="text" name="user.realNameSpell" value="<s:property value="user.realNameSpell" />"
												class="form-control"  style="margin-left:20px;"></td>
										</tr>
									</table>
								</div>
								<div class="form-group">
									<label>积分余额</label> <input type="text" name="user.pointBalance" value="<s:property  value="user.pointBalance" />"
										readonly="readonly" class="form-control">
								</div>
								<div class="form-group">
									<label>电话</label> <input type="text" id="phoneNum" name="user.phoneNum" value="<s:property  value="user.phoneNum" />"
										class="form-control" onchange="isViewVerify()">
										<input type="hidden" id="hiddenPhoneNum" name="oldPhoneNum" value="<s:property  value="user.phoneNum" />">
								</div>
								<div id="phoneNumHint" class="form-group" style="display:none;">
									<label style="color:red">请输入正确的手机号码</label>
								</div>
								<div id="verifyHrefDiv" class="form-group" style="display:none;">
									<a href="verifyOldPhone" class="btn btn-primary">更换手机号</a>
								</div>
								<div id="verifyDiv" class="form-group" style="display:none;">
								<table>
									<tr>
										<td><a id="getVerifyCodeRe" href="javascript:void(0)" onclick="getVerify(this)" data-toggle="modal" class="btn btn-primary">获取验证码</a></td>
										<td><input type="text" name="vertifyCode" class="form-control" style="margin-left:20px;width:100%;"></td>
									</tr>
								</table>
								</div>
								<div class="form-group">
									<label>交易密码</label> <input type="password" id="transPass" name="user.transactionPassword"
										value="<s:property value="user.transactionPassword"/>" class="form-control" onchange="isViewPassVerify()">
										<input type="hidden" id="hiddenTransPass" name="oldTransPass" value="<s:property  value="user.transactionPassword" />">
								</div>
								<div id="passVerifyDiv" class="form-group" style="display:none;">
									<table>
										<tr>
											<td><a id="getVerifyCode" href="javascript:void(0)" onclick="getTransVerify(this)" data-toggle="modal" class="btn btn-primary">获取验证码</a></td>
											<td><input type="text" name="passVerifyCode" class="form-control" value="" style="margin-left:20px;"></td>
										</tr>
									</table>
								</div>
							</form>
						</div>

						<div class="btn-toolbar list-toolbar">
							<a href="#myModal" data-toggle="modal" class="btn btn-danger">更新</a>
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
								<h3 id="myModalLabel">账号信息提交</h3>
							</div>
							<div class="modal-body">

								<p class="error-text">
									<i class="fa fa-warning modal-icon"></i>确定更改账号信息吗？
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
			$("#tab").submit();
		});
		function isViewVerify(){
			if($("#phoneNum").val()!=$("#hiddenPhoneNum").val()){
				if($("#hiddenPhoneNum").val()!=""){
					$("#verifyHrefDiv").show();
				}else{
					$("#verifyDiv").show();
				}
			}else{
				if($("#hiddenPhoneNum").val()!=""){
					$("#verifyHrefDiv").hide();
				}else{
					$("#verifyDiv").hide();
				}
			}
			isViewPassVerify();
			$('#phoneNumHint').hide();
		}
		function isViewPassVerify(){
			if($("#verifyDiv").is(":hidden")){
				if($("#transPass").val()!=$("#hiddenTransPass").val()){
					$("#passVerifyDiv").show();
				}else{
					$("#passVerifyDiv").hide();
				}
			}else{
				$("#passVerifyDiv").hide();
			}
		}
		var countdown = 60;
		function settime(){
			if (countdown == 0) { 
				$('#getVerifyCode').removeAttr("disabled");    
				$('#getVerifyCode').html("获取验证码"); 
				$('#getVerifyCodeRe').removeAttr("disabled");    
				$('#getVerifyCodeRe').html("获取验证码"); 
				countdown = 60; 
				return;
			} else { 
				$('#getVerifyCode').attr("disabled", true); 
				$('#getVerifyCode').html("重新发送(" + countdown + ")"); 
				$('#getVerifyCodeRe').attr("disabled", true); 
				$('#getVerifyCodeRe').html("重新发送(" + countdown + ")"); 
				countdown--; 
			} 
			setTimeout(function(){settime() },1000);
		}
		function getTransVerify(obj){
			if($('#hiddenPhoneNum').val()=="" || !$('#hiddenPhoneNum').val().match(/^((13[0-9])|(15[^4,\\D])|(18[0,0-9]))\d{8}$/)){
				alert("手机号码格式不正确，请先更换手机号码！");
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
			    	$('#transPass').attr("readonly",true);
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
		function getVerify(obj){
			if($('#phoneNum').val()=="" || !$('#phoneNum').val().match(/^((13[0-9])|(15[^4,\\D])|(18[0,0-9]))\d{8}$/)){
			alert($('#phoneNum').val());
				$('#phoneNumHint').show();
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
			    	$('#phoneNum').attr("readonly", true);
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
