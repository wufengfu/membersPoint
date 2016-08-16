<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>积分汇出</title>
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
					return (css.match(/\btheme-\S+/g) || []).join(' ');
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


	<div class="main">
		<div class="panel panel-default">
			<p class="panel-heading no-collapse">积分汇出表格</p>
			<div class="panel-body">
				<form id="pointForm" action="addPointRemit.action" method="post">
				<input type="hidden" id="companyId" name="company.id" value="<s:property value="company.id"/>">
				<input type="hidden" id="userPoints" value="<s:property value="user.pointBalance"/>">
				<div class="form-group">
						<label><s:property value="company.companyName"/>卡号</label> 
						<table style="width:100%;"><tr>
							<td style="text-align:center;"><label><s:property value="company.shortForm"/></label></td>
							<td style="width:90%;"><input type="text" id="cardNum"
							name="pointRemit.cardNum" class="form-control span12"></td>
						</tr></table>
						
							<label id="cardNumTips"
							style="color:red;margin-left:20px;margin-top:5px"></label>
					</div>
					<div class="form-group">
						<table>
							<tr>
								<td><label>姓</label></td>
								<td><label style="margin-left:10px;">姓拼音</label></td>
							</tr>
							<tr>
								<td><input type="text" id="familyName" name="pointRemit.familyName"
									class="form-control"></td>
								<td><input type="text" id="familyNameSpell" name="pointRemit.familyNameSpell"
									class="form-control" style="margin-left:10px;text-transform:uppercase;"></td>
							</tr>
						</table>
					</div>
					<div class="form-group">
						<table>
							<tr>
								<td><label>名</label></td>
								<td><label style="margin-left:10px;">名拼音</label></td>
							</tr>
							<tr>
								<td><input type="text" id="realName" name="pointRemit.realName"
									class="form-control"></td>
								<td><input type="text" id="realNameSpell" name="pointRemit.realNameSpell"
									class="form-control" style="margin-left:10px;text-transform:uppercase;"></td>
							</tr>
							<tr>
								<td colspan="2"><label id="nameTips"
							style="color:red;margin-left:20px;margin-top:5px"></label></td>
							</tr>
						</table>
					</div>
					<div class="form-group">
						<label>里程</label> <input type="text" id="mileage"
							name="pointRemit.mileage" class="form-control span12">
					</div>
					<label id="tips" style="color:red;margin-left:20px;"></label>
					<div class="form-group">
						<label>积分数</label> <input type="text" id="points"
							name="pointRemit.points" readonly="readonly"
							class="form-control span12">
					</div>
					<label id="pointsTips" style="color:red;margin-left:20px;"></label>
					<div id="goForApplyDiv" class="form-group" style="display:none">
						<a class="btn btn-primary pull-right" href="pointApply.jsp">去充值</a>
					</div>
					<div class="form-group">
						<label>电话</label> <input type="text" id="phoneNum"
							name="pointRemit.phoneNum" class="form-control span12"> <label
							id="phoneNumTips"
							style="color:red;margin-left:20px;margin-top:5px"></label>
					</div>
					<div class="form-group">
						<a class="btn btn-primary pull-right" onclick="subForm();">积分汇出</a>
						<label class="remember-me"> <!-- <input type="checkbox"> I agree with the  -->
							<a href="pages/terms-and-conditions.html">里程积分兑换比例说明</a></label>
					</div>
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
		<!-- <p>
			<a href="privacy-policy.html"
				style="font-size: .75em; margin-top: .25em;">Privacy Policy</a>
		</p> -->
	</div>
	<script src="pages/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
		$("#cardNum").focus(function(){
			$("#cardNumTips").html("");
		});
		$("#familyName").focus(function(){
			$("#nameTips").html("");
		});
		$("#familyNameSpell").focus(function(){
			$("#nameTips").html("");
		});
		$("#realName").focus(function(){
			$("#nameTips").html("");
		});
		$("#realNameSpell").focus(function(){
			$("#nameTips").html("");
		});
		$("#mileage").focus(function() {
			$("#tips").html("");
			$("#goForApplyDiv").hide();
			$("#pointsTips").html("");
		});
		//暂时先这么写，后台也需要做验证
		$("#mileage").blur(function() {
			if ($("#mileage").val() < 100) {
				$("#tips").html("里程汇出值请大于100");
			} else {
				$.ajax({
				url:"getPointsByMileage.action",
				dataType:"json",
				async:true,//是否异步
				data:{'company.id':$("#companyId").val(),mileage:$("#mileage").val()},
				type:"GET",
				beforeSend: function(XMLHttpRequest) {
			        //请求前的处理
			    },
			    success: function(data) {
			        //请求成功时处理
			        $("#points").val(data);
			        if(data>$("#userPoints").val()){
			        	$("#pointsTips").html("您的积分余额不足！");
			        	$("#goForApplyDiv").show();
			        }
			    },
			    complete: function(XMLHttpRequest, textStatus) {
			        //请求完成的处理
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
			        //请求出错处理
			        alert("里程积分转换失败，请稍后再试！");
			    }
			});
			}
		});
		$("#phoneNum").focus(function() {
			$("#phoneNumTips").html("联系电话是为了防止积分汇出时的意外情况好方便我们管理员联系亲们！请填写真实的手机号码哦！（建议本人的手机号码）");
		});
		$("#phoneNum").blur(function() {
			$("#phoneNumTips").html("");
		});
		function subForm() {
			var result = 0;
			if($("#cardNum").val()==""){
				$("#cardNumTips").html("请输入国航卡号");
				result = 1;
			}
			if($("#familyName").val()=="" || $("#familyNameSpell").val()=="" || $("#realName").val()=="" || $("#realNameSpell").val()==""){
				$("#nameTips").html("请输入姓、名以及对应的拼音！");
				result = 1;
			}
			if($("#mileage").val()==""){
				$("#tips").html("请输入里程数目！");
				result = 1;
			}
			if($("#mileage").val() < 0){
				$("#tips").html("请输入合理的里程数目！");
				result = 1;
			}
			if($("#points").val()==""){
				$("#pointsTips").html("请等待积分数的计算！");
				result = 1;
			}
			if($("#phoneNum").val()==""){
				$("#phoneNumTips").html("请输入手机号码！");
				result = 1;
			}
			var value = $("#points").val() - $("#userPoints").val();
			if(value > 0){
			    $("#pointsTips").html("您的积分余额不足！");
			    $("#goForApplyDiv").show();
			    result = 1;
			}
			if (result != 1) {
				var cardNum = $("#cardNum").val();
				$("#cardNum").val("CA"+cardNum);
				$("#familyNameSpell").toUpperCase();
				$("#realNameSpell").toUpperCase();
				$("#pointForm").submit();
				//alert("假装提交了")
			}

		}
	</script>
</body>
</html>
