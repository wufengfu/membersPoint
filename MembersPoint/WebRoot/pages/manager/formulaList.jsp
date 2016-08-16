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

				<!-- Split button -->
				<div class="btn-group">
					<button class="btn btn-primary" onclick="addcfm()">
						<i class="fa fa-plus"></i> 添加阶梯公式
					</button>
					<div class="btn-group" style="margin-left:20px;">
						<button type="button" class="btn btn-normal">
							<s:if test="company==null">
						 全部
					</s:if>
							<s:else>
								<s:property value="company.companyName" />
							</s:else>

						</button>
						<button type="button" class="btn btn-normal dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="caret"></span> <span class="sr-only">点击下拉</span>
						</button>
						<ul class="dropdown-menu">
							<s:iterator var="c" value="companys" status="ser">
								<li><a
									href="formulaList?companyId=<s:property value="#c.id" />"><s:property
											value="#c.companyName" /></a></li>
							</s:iterator>
							<li role="separator" class="divider"></li>
							<li><a href="formulaList?companyId=0">所有</a></li>
						</ul>
					</div>
				</div>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>航司名称</th>
						<th>阶梯值</th>
						<th>比例值</th>
						<th style="width: 10em;text-align:center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator var="formula" value="formulas" status="ser">
						<tr>
							<td><s:property value="#ser.count+(pageIndex-1)*pageSize" /></td>
							<td><s:property value="#formula.airlinecompany.companyName" /></td>
							<td><s:property value="#formula.levelValue" /></td>
							<td><s:property value="#formula.levelRatio" /></td>
							<td style="text-align:center;"><a
								onclick="updatecfm('<s:property value="#formula.id"/>','<s:property value="#formula.airlinecompany.id" />','<s:property value="#formula.levelValue" />','<s:property value="#formula.levelRatio" />')"
								role="button"> <i class="fa fa-pencil"></i>修改
							</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>

			<ul class="pagination">
				<li><a href="formulaList.action?pageIndex=1">&laquo;</a></li>
				<s:iterator value="new int[pageCount]" status="i">
					<s:if test="#i.index+1==pageIndex">
						<li class="active"><a href="#"><s:property value="#i.index+1"/></a></li>
					</s:if>
					<s:else>
						<li><a href="formulaList.action?pageIndex=<s:property value="#i.index+1"/>"><s:property value="#i.index+1"/></a></li>
					</s:else>
				</s:iterator>
				<li><a href="formulaList.action?pageIndex=<s:property value="pageCount"/>">&raquo;</a></li>
			</ul>

			<div class="modal small fade" id="updateModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h3 id="myModalLabel">修改阶梯公式</h3>
						</div>
						<div class="modal-body">
							<form id="updateForm" action="udpateFormula.action" method="post">
								<input type="hidden" id="formulaIdHidden" name="formula.id">
								<input type="hidden" id="updateCompanyIdHidden"
									name="formula.airlinecompany.id">
								<div class="form-group">
									<label>阶梯值</label> <input type="number" id="formulaValue"
										name="formula.levelValue" class="form-control span12">
								</div>
								<div class="form-group">
									<label>比例值（分/万里程）</label> <input type="number"
										id="formulaRatio" name="formula.levelRatio"
										class="form-control span12">
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
							<h3 id="myModalLabel">添加阶梯公式</h3>
						</div>
						<div class="modal-body">
							<form id="addForm" action="addFormula.action" method="post">
								<div class="form-group">
									<div class="btn-group">
										<button type="button" id="btnCompanyName"
											class="btn btn-normal">
											<s:if test="company==null">
												 国航
											</s:if>
											<s:else>
												<s:property value="company.companyName" />
											</s:else>
										</button>
										<button type="button" class="btn btn-normal dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											<span class="caret"></span> <span class="sr-only">Toggle
												Dropdown</span>
										</button>
										<ul class="dropdown-menu">
											<s:iterator var="c" value="companys" status="ser">
												<li><a href="javascript:void(0)"
													onclick="changeCompanyId('<s:property
														value="#c.id" />','<s:property
														value="#c.companyName" />')"><s:property
															value="#c.companyName" /></a></li>
											</s:iterator>
										</ul>
									</div>
								</div>
								<input type="hidden" id="companyIdHidden"
									name="formula.airlinecompany.id"
									value="<s:property value="company==null?1:company.id" />">
								<div class="form-group">
									<label>阶梯值</label> <input type="number"
										name="formula.levelValue" class="form-control span12">
								</div>
								<div class="form-group">
									<label>比例值（分/万里程）</label> <input type="number"
										name="formula.levelRatio" class="form-control span12">
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
		function changeCompanyId(id,companyName){
			$('#companyIdHidden').val(id);
			$('#btnCompanyName').html(companyName);
		}
		function addcfm() {
			$('#addModel').modal();
		}
		function addSub() {
			$('#addForm').submit();
		}
		function updatecfm(id, companyId, levelValue, levelRatio) {
			$('#formulaIdHidden').val(id);
			$('#updateCompanyIdHidden').val(companyId);
			$('#formulaValue').val(levelValue);
			$('#formulaRatio').val(levelRatio);
			$('#updateModel').modal();
		}
		function updateSub() {
			$('#updateForm').submit();
		}
	</script>


</body>
</html>