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
<title>用户列表</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="${basePath}pages/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="${basePath}pages/lib/font-awesome/css/font-awesome.css">

<script src="${basePath}pages/lib/jquery-1.11.1.min.js"
	type="text/javascript"></script>

<script src="${basePath}pages/lib/jQuery-Knob/js/jquery.knob.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$(".knob").knob();
	});
</script>


<link rel="stylesheet" type="text/css"
	href="${basePath}pages/stylesheets/theme.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}pages/stylesheets/premium.css">

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
			<li><ul class="dashboard-menu nav nav-list collapse in">
					<li><a href="getCompanyList.action"><span
							class="fa fa-caret-right"></span> 航司信息 </a></li>
					<li><a href="userList.action"><span
							class="fa fa-caret-right"></span> 用户信息</a></li>
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
			<li><ul class="legal-menu nav nav-list collapse">
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
			<div class="input-group search pull-right hidden-sm hidden-xs">
				<form id="searchForm" action="userList.action" method="post">
				<div class="input-group">
					<input type="text" class="form-control" id="searchRealName" name="fullName" value="真实姓名"> <span
						class="input-group-btn">
					</span>
					<div class="input-group">
					<input type="text" class="form-control" id="searchPhoneNum" name="phoneNum" value="电话号码"> <span
						class="input-group-btn">
						<button class="btn btn-primary" type="button" id="BtnSearch">
							<i class="fa fa-search "></i>
						</button>
					</span>
				</div>
				</div>
				</form>
			</div>

			<div class="btn-toolbar list-toolbar">
				<!-- <div class="btn-group">
				<label>用户状态：</label>
			</div> -->
				<div class="btn-group">
					<button type="button" class="btn btn-normal">
						<s:if test="statues==-2">
						 所有用户
						</s:if>
						<s:elseif test="statues==-1">
							黑名单
						</s:elseif>
						<s:elseif test="statues==0">
							未审核用户
						</s:elseif>
						<s:elseif test="statues==1">
							普通用户
						</s:elseif>
						<s:elseif test="statues==2">
							优惠用户
						</s:elseif>

					</button>
					<button type="button" class="btn btn-normal dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">点击下拉</span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="userList.action?statues=0">未审核用户</a></li>
						<li><a href="userList.action?statues=1">普通用户</a></li>
						<li><a href="userList.action?statues=2">优惠用户</a></li>
						<li><a href="userList.action?statues=-1">黑名单</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="userList.action?statues=-2">所有用户</a></li>
					</ul>
				</div>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>微信昵称</th>
					<th>性别</th>
					<th>省份城市</th>
					<th>备注名</th>
					<th>真实姓名</th>
					<th>电话号码</th>
					<th>积分余额</th>
					<th>状态</th>
					<th style="width: 10em;text-align:center;">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator var="user" value="users" status="ser">
					<tr>
						<td><s:property value="#ser.count+(pageIndex-1)*pageSize" /></td>
						<td><s:property value="#user.nickname" /></td>
						<td><s:property value="#user.sex" /></td>
						<td><s:property value="#user.province" /> <s:property
								value="#user.city" /></td>
						<td><s:property value="#user.remark" /></td>
						<td><s:property value="#user.fullName" /></td>
						<td><s:property value="#user.phoneNum" /></td>
						<td><s:property value="#user.pointBalance" /></td>
						<td><s:if test="#user.statues==0">
								未审核
							</s:if> <s:if test="#user.statues==1">
								已审核
							</s:if> <s:if test="#user.statues==2">
								优惠用户
							</s:if> <s:if test="#user.statues==-1">
								已拉黑
							</s:if>
						<td style="text-align:center;"><s:if test="#user.statues==0">
								<a
									onclick="passcfm('passVerify.action?user.id=<s:property value="#user.id"/>')"
									role="button"> <i class="fa fa-pencil"></i>通过
								</a>
								<a
									onclick="backcfm('backVerify.action?user.id=<s:property value="#user.id"/>')"
									role="button"> <i class="fa fa-trash-o"></i>拉黑
								</a>
							</s:if> <s:if test="#user.statues==1">
								<a
									onclick="favcfm('favUser.action?user.id=<s:property value="#user.id"/>')"
									role="button"> <i class="fa fa-pencil"></i>优惠
								</a>
								<a
									onclick="backcfm('backVerify.action?user.id=<s:property value="#user.id"/>')"
									role="button"> <i class="fa fa-trash-o"></i>拉黑
								</a>
							</s:if> <s:if test="#user.statues==2">
								<a
									onclick="backcfm('backVerify.action?user.id=<s:property value="#user.id"/>')"
									role="button"> <i class="fa fa-trash-o"></i>拉黑
								</a>
							</s:if> <s:if test="#user.statues==-1">
								<a
									onclick="reBackcfm('passVerify.action?user.id=<s:property value="#user.id"/>')"
									role="button"> <i class="fa fa-trash-o"></i>还原
								</a>
							</s:if></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>

		<ul class="pagination">
				<li><a href="userList?pageIndex=1">&laquo;</a></li>
				<s:iterator value="new int[pageCount]" status="i">
					<s:if test="#i.index+1==pageIndex">
						<li class="active"><a href="#"><s:property value="#i.index+1"/></a></li>
					</s:if>
					<s:else>
						<li><a href="userList?pageIndex=<s:property value="#i.index+1"/>"><s:property value="#i.index+1"/></a></li>
					</s:else>
				</s:iterator>
				<li><a href="userList?pageIndex=<s:property value="pageCount"/>">&raquo;</a></li>
			</ul>
		<div class="modal small fade" id="passModel" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">通过用户申请</h3>
					</div>
					<div class="modal-body">
						<input type="hidden" id="passUrl" />
						<p class="error-text">
							<i class="fa fa-warning modal-icon"></i>确定通过审核这位用户吗？
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal"
							aria-hidden="true">返回</button>
						<button onclick="passSub()" class="btn btn-danger"
							data-dismiss="modal">确定</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal small fade" id="favModel" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">优惠提示</h3>
					</div>
					<div class="modal-body">
						<input type="hidden" id="favUrl" />
						<p class="error-text">
							<i class="fa fa-warning modal-icon"></i>确定把该成员设置为优惠用户吗？
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal"
							aria-hidden="true">返回</button>
						<button onclick="backSub()" class="btn btn-danger"
							data-dismiss="modal">确定</button>
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
						<h3 id="myModalLabel">拉黑提示</h3>
					</div>
					<div class="modal-body">
						<input type="hidden" id="backUrl" />
						<p class="error-text">
							<i class="fa fa-warning modal-icon"></i>确定把该成员拉黑吗？
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal"
							aria-hidden="true">返回</button>
						<button onclick="backSub()" class="btn btn-danger"
							data-dismiss="modal">确定</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal small fade" id="reBackModel" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">恢复提示</h3>
					</div>
					<div class="modal-body">
						<input type="hidden" id="backUrl" />
						<p class="error-text">
							<i class="fa fa-warning modal-icon"></i>确定把该成员移除黑名单吗？
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal"
							aria-hidden="true">返回</button>
						<button onclick="backSub()" class="btn btn-danger"
							data-dismiss="modal">确定</button>
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
		$('#searchRealName').focus(function(){
			if($('#searchRealName').val()=="真实姓名"){
				$('#searchRealName').val("");	
			}else{
			}
		});
		$('#searchRealName').blur(function(){
			if($('#searchRealName').val()==""){
				$('#searchRealName').val("真实姓名");	
			}else{
			}
		});
		$('#searchPhoneNum').focus(function(){
			if($('#searchPhoneNum').val()=="电话号码"){
				$('#searchPhoneNum').val("");	
			}else{
			}
		});
		$('#searchPhoneNum').blur(function(){
			if($('#searchPhoneNum').val()==""){
				$('#searchPhoneNum').val("电话号码");	
			}else{
			}
		});
		$('#BtnSearch').click(function(){
		if($('#searchPhoneNum').val()=="电话号码"){
			$('#searchPhoneNum').val()==""
		}
		if($('#searchRealName').val()=="真实姓名"){
			$('#searchRealName').val()==""
		}
			$('#searchForm').submit();
		});
		function passcfm(url) {
			$('#passUrl').val(url);//给会话中的隐藏属性URL赋值  
			$('#passModel').modal();
		}
		function passSub() {
			var url = $.trim($("#passUrl").val());//获取会话中的隐藏属性URL  
			window.location.href = url;
		}
		function backcfm(url) {
			$('#backUrl').val(url);//给会话中的隐藏属性URL赋值  
			$('#backModel').modal();
		}
		function backSub() {
			var url = $.trim($("#backUrl").val());//获取会话中的隐藏属性URL  
			window.location.href = url;
		}
		function favcfm(url) {
			$('#backUrl').val(url);//给会话中的隐藏属性URL赋值  
			$('#favModel').modal();
		}
		function favSub() {
			var url = $.trim($("#favUrl").val());//获取会话中的隐藏属性URL  
			window.location.href = url;
		}
		function reBackcfm(url) {
			$('#backUrl').val(url);//给会话中的隐藏属性URL赋值  
			$('#reBackModel').modal();
		}
		function reBackSub() {
			var url = $.trim($("#reBackUrl").val());//获取会话中的隐藏属性URL  
			window.location.href = url;
		}
	</script>


</body>
</html>