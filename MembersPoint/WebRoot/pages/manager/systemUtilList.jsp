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
<title>系统配置变量列表</title>
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
			<li><ul class="dashboard-menu nav nav-list collapse">
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
			<div class="btn-toolbar list-toolbar">
				<button class="btn btn-primary" onclick="addcfm()">
					<i class="fa fa-plus"></i> 添加系统变量
				</button>
				<div class="btn-group"></div>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th style="min-width:120px;">名称</th>
						<th style="min-width:120px;">库留存</th>
						<th style="min-width:120px;">内容</th>
						<th style="width: 10em;text-align:center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator var="util" value="utilList" status="ser">
						<tr>
							<td><s:property value="#ser.count+(pageIndex-1)*pageSize" /></td>
							<td><s:property value="#util.showName" /></td>
							<td><s:property value="#util.name" /></td>
							<td><s:property value="#util.content" /></td>
							<td style="text-align:center;"><a
								onclick="updatecfm('<s:property value="#util.id"/>','<s:property value="#util.showName" />','<s:property value="#util.name" />','<s:property value="#util.content" />')"
								role="button"> <i class="fa fa-pencil"></i>修改
							</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			
			<ul class="pagination">
				<li><a href="utilList.action?pageIndex=1">&laquo;</a></li>
				<s:iterator value="new int[pageCount]" status="i">
					<s:if test="#i.index+1==pageIndex">
						<li class="active"><a href="#"><s:property value="#i.index+1"/></a></li>
					</s:if>
					<s:else>
						<li><a href="utilList.action?pageIndex=<s:property value="#i.index+1"/>"><s:property value="#i.index+1"/></a></li>
					</s:else>
				</s:iterator>
				<li><a href="utilList.action?pageIndex=<s:property value="pageCount"/>">&raquo;</a></li>
			</ul>

			<div class="modal small fade" id="updateModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h3 id="myModalLabel">修改系統配置</h3>
						</div>
						<div class="modal-body">
							<form id="updateForm" action="updateUtil.action" method="post">
								<input type="hidden" id="utilId" name="util.id">
								<div class="form-group">
									<label>展示名称</label> <input type="text" id="showName"
										name="util.showName" class="form-control span12">
								</div>
								<div class="form-group">
									<label>名称</label> <input type="text" id="name" readonly="readonly"
										name="util.name" class="form-control span12">
								</div>
								<div class="form-group">
									<label>内容</label>
									<textarea value="Smith" rows="3" id="content" name="util.content" class="form-control"></textarea>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-default" data-dismiss="modal"
								aria-hidden="true">返回</button>
							<button onclick="updateSub()" class="btn btn-danger"
								data-dismiss="modal">确定</button>
						</div>
					</div>
				</div>
			</div>

			<div class="modal small fade" id="addModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h3 id="myModalLabel">添加系統配置</h3>
						</div>
						<div class="modal-body">
							<form id="addForm" action="addUtil.action" method="post">
								<div class="form-group">
									<label>展示名称</label> <input type="text"
										name="util.showName" class="form-control span12">
								</div>
								<div class="form-group">
									<label>名称</label> <input type="text"
										name="util.name" class="form-control span12">
								</div>
								<div class="form-group">
									<label>内容</label>
									<textarea value="Smith" rows="3" name="util.content" class="form-control"></textarea>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-default" data-dismiss="modal"
								aria-hidden="true">返回</button>
							<button onclick="addSub()" class="btn btn-danger"
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
		function addcfm() {
			$('#addModel').modal();
		}
		function addSub() {
			$('#addForm').submit();
		}
		function updatecfm(id, showName,name,content) {
			$('#utilId').val(id);
			$('#showName').val(showName);
			$('#name').val(name);
			$('#content').val(content);
			$('#updateModel').modal();
		}
		function updateSub() {
			$('#updateForm').submit();
		}
	</script>


</body>
</html>