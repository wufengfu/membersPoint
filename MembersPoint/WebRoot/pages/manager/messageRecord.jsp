<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
	if (session.getAttribute("manager") == null) {
		response.sendRedirect("login.jsp");
		//request.getRequestDispatcher("login.jsp").forward(request,response);
	}
%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>短信发送记录列表</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="${basePath}pages/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="${basePath}pages/lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}pages/stylesheets/jquery.datetimepicker.css" />
<script src="${basePath}pages/lib/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="${basePath}pages/lib/jquery.datetimepicker.full.js" type="text/javascript">
</script>

<script src="${basePath}pages/lib/jQuery-Knob/js/jquery.knob.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$(".knob").knob();
	});
</script>


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
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="" href="index.html"><span class="navbar-brand"><span
					class="fa fa-paper-plane"></span> 飞行积分</span></a>
		</div>

		<div class="navbar-collapse collapse" style="height: 1px;">
			<ul id="main-menu" class="nav navbar-nav navbar-right">
				<li class="dropdown hidden-xs"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <span
						class="glyphicon glyphicon-user padding-right-small"
						style="position:relative;top: 3px;"></span> <s:property
							value="#session.manager.userName" /> <i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu">
						<li class="divider"></li>
						<li><a href="logOut.action">退出登录</a></li>
						<li class="divider"></li>
					</ul></li>
			</ul>
		</div>
	</div>


	<div class="sidebar-nav">
		<ul>
			<li><a href="#" data-target=".dashboard-menu" class="nav-header"
				data-toggle="collapse"><i class="fa fa-fw fa-dashboard"></i>基本信息<i
					class="fa fa-collapse"></i></a></li>
			<li><ul class="dashboard-menu nav nav-list collapse">
					<li><a href="getCompanyList.action"><span class="fa fa-caret-right"></span>
							航司信息 </a></li>
					<li><a href="userList.action"><span class="fa fa-caret-right"></span>
							用户信息</a></li>
				</ul></li>

			<li><a href="#" data-target=".premium-menu"
				class="nav-header collapsed" data-toggle="collapse"> <i
					class="fa fa-fw fa-fighter-jet"></i> 积分申请<i class="fa fa-collapse"></i></a></li>
			<li><ul class="premium-menu nav nav-list collapse">
					<li><a href="pointAboList.action"><span
							class="fa fa-caret-right"></span> 汇入申请</a></li>
					<li><a href="pointRemitList.action"><span
							class="fa fa-caret-right"></span> 汇出申请</a></li>
					<li><a href="pointRecordList.action"><span
							class="fa fa-caret-right"></span> 积分记录</a></li>
				</ul></li>

			<li><a href="#" data-target=".legal-menu"
				class="nav-header collapsed" data-toggle="collapse"><i
					class="fa fa-fw fa-legal"></i> 系统设置<i class="fa fa-collapse"></i></a></li>
			<li><ul class="legal-menu nav nav-list collapse in">
					<li><a href="utilList.action"><span
							class="fa fa-caret-right"></span> 系统变量设置</a></li>
					<li><a href="formulaList.action"><span
							class="fa fa-caret-right"></span> 计算公式设置</a></li>
					<li><a href="messageRecordList.action"><span
							class="fa fa-caret-right"></span> 短信发送记录</a></li>
					<li><a href="#"><span
							class="fa fa-caret-right"></span> 财务报表</a></li>
				</ul></li>

		</ul>
	</div>

	<div class="content">
		<div class="main-content">
		<div class="header">
				<form id="searchForm" action="messageRecordList.action" method="post">
					<div class="input-group search pull-right">
					<span class="input-group-btn"> </span>
						<input type="text" name="startTime"
						value="开始时间" id="startTimeDatePicker" class="form-control"/>
						<span class="input-group-btn"> </span>
						<input type="text" name="endTime"
						value="结束时间" id="endTimeDatePicker" class="form-control"/>
						<span class="input-group-btn"> </span>
						<div class="input-group">
							<input type="text" class="form-control" id="phoneNum"
								name="phoneNum" value="目的号码"> <span
								class="input-group-btn">
								<button class="btn btn-primary" type="button" id="BtnSearch">
									<i class="fa fa-search "></i>
								</button>
							</span>
						</div>
					</div>
				</form>

				<div class="btn-toolbar list-toolbar">
					<!-- <div class="btn-group">
				<label>用户状态：</label>
			</div> -->
				</div>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th style="min-width:120px;">用户名</th>
						<th style="min-width:120px;">目的号码</th>
						<th>短信内容</th>
						<th>扩展</th>
						<th>引用</th>
						<th>发送时间</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator var="record" value="records" status="ser">
					<tr>
						<td><s:property value="#ser.count+(pageIndex-1)*pageSize" /></td>
						<td><s:property value="#record.user.familyName" /><s:property value="#record.user.realName" /></td>
						<td><s:property value="#record.phoneNum"/></td>
						<td><s:property value="#record.content" /></td>
						<td><s:property value="#record.ext" /></td>
						<td><s:property value="#record.reference" /></td>
						<td><s:date name="#record.addTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</s:iterator>
				</tbody>
			</table>

			<ul class="pagination">
				<li><a href="messageRecordList.action?pageIndex=1">&laquo;</a></li>
				<s:iterator value="new int[pageCount]" status="i">
					<s:if test="#i.index+1==pageIndex">
						<li class="active"><a href="#"><s:property value="#i.index+1"/></a></li>
					</s:if>
					<s:else>
						<li><a href="messageRecordList.action?pageIndex=<s:property value="#i.index+1"/>"><s:property value="#i.index+1"/></a></li>
					</s:else>
				</s:iterator>
				<li><a href="messageRecordList.action?pageIndex=<s:property value="pageCount"/>">&raquo;</a></li>
			</ul>
			<div class="modal small fade" id="passModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h3 id="myModalLabel">通过汇入申请</h3>
						</div>
						<div class="modal-body">
						<input type="hidden" id="passUrl"/>  
							<p class="error-text">
								<i class="fa fa-warning modal-icon"></i>确定已汇出吗？
							</p>
						</div>
						<div class="modal-footer">
							<button class="btn btn-default" data-dismiss="modal"
								aria-hidden="true">返回</button>
							<button onclick="passSub()" class="btn btn-danger" data-dismiss="modal">确定</button>
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal small fade" id="effectModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h3 id="myModalLabel">生效确认</h3>
						</div>
						<div class="modal-body">
						<input type="hidden" id="effectUrl"/>  
							<p class="error-text">
								<i class="fa fa-warning modal-icon"></i>确定已生效吗？
							</p>
						</div>
						<div class="modal-footer">
							<button class="btn btn-default" data-dismiss="modal"
								aria-hidden="true">返回</button>
							<button onclick="passSub()" class="btn btn-danger" data-dismiss="modal">确定</button>
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal small fade" id="backModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h3 id="myModalLabel">驳回汇入申请</h3>
						</div>
						<div class="modal-body">
						<input type="hidden" id="backUrl"/>
							<p class="error-text">
								<i class="fa fa-warning modal-icon"></i>确定驳回这条申请吗？
							</p>
						</div>
						<div class="modal-footer">
							<button class="btn btn-default" data-dismiss="modal"
								aria-hidden="true">返回</button>
							<button onclick="backSub()" class="btn btn-danger" data-dismiss="modal">确定</button>
						</div>
					</div>
				</div>
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
		function passcfm(url){
			$('#passUrl').val(url);//给会话中的隐藏属性URL赋值  
	        $('#passModel').modal(); 
		}
		function passSub(){
			var url=$.trim($("#passUrl").val());//获取会话中的隐藏属性URL  
	        window.location.href=url;   
		}
		function effectcfm(url){
			$('#passUrl').val(url);//给会话中的隐藏属性URL赋值  
	        $('#effectModel').modal(); 
		}
		function effectSub(){
			var url=$.trim($("#passUrl").val());//获取会话中的隐藏属性URL  
	        window.location.href=url;   
		}
		function backcfm(url){
			$('#backUrl').val(url);//给会话中的隐藏属性URL赋值  
	        $('#backModel').modal(); 
		}
		function backSub(){
			var url=$.trim($("#backUrl").val());//获取会话中的隐藏属性URL  
	        window.location.href=url;   
		}
		$('#startTimeDatePicker').datetimepicker({
			lang : "ch", //语言选择中文
			format : "Y-m-d H:m:s", //格式化日期
			timepicker : true, //关闭时间选项      
			autoclose:true,
			todayButton : true
		//关闭选择今天按钮
		});
		$('#endTimeDatePicker').datetimepicker({
			lang : "ch", //语言选择中文
			format : "Y-m-d H:m:s", //格式化日期
			timepicker : true, //关闭时间选项  
			autoclose:true,    
			todayButton : false
		//关闭选择今天按钮
		});
		$('#phoneNum').focus(function(){
			if($('#phoneNum').val()=="目的号码"){
				$('#phoneNum').val("");	
			}
		});
		$('#phoneNum').blur(function(){
			if($('#phoneNum').val()==""){
				$('#phoneNum').val("目的号码");	
			}
		});
		/* $('#startTimeDatePicker').focus(function(){
			if($('#phoneNum').val()=="开始时间"){
				$('#phoneNum').val("");	
			}
		});
		$('#startTimeDatePicker').blur(function(){
			if($('#phoneNum').val()==""){
				$('#phoneNum').val("开始时间");	
			}
		});
		$('#endTimeDatePicker').focus(function(){
			if($('#phoneNum').val()=="结束时间"){
				$('#phoneNum').val("");	
			}
		});
		$('#endTimeDatePicker').blur(function(){
			if($('#phoneNum').val()==""){
				$('#phoneNum').val("结束时间");	
			}
		}); */
		$('#BtnSearch').click(function(){
			if($('#phoneNum').val()=="目的号码"){
				$('#phoneNum').val("");	
			}
			if($('#startTimeDatePicker').val()=="开始时间"){
				$('#startTimeDatePicker').val("");	
			}
			if($('#endTimeDatePicker').val()=="结束时间"){
				$('#endTimeDatePicker').val("");	
			}
			$('#searchForm').submit();
		});
	</script>


</body>
</html>