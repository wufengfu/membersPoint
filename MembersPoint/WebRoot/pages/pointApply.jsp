<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>积分充值</title>
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
            if(match) var color = match[1];
            if(color) {
                $('body').removeClass(function (index, css) {
                    return (css.match (/\btheme-\S+/g) || []).join(' ');
                })
                $('body').addClass('theme-' + color);
            }

            $('[data-popover="true"]').popover({html: true});
            
        });
    </script>
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
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
			<p class="panel-heading no-collapse">积分汇入表格</p>
			<div class="panel-body">
				<form id="pointForm" action="pointApply.action" method="post">
					<div class="form-group">
						<label>积分</label> <input type="text" id="points" name="pointAbo.points" class="form-control span12">
					</div>
						<label id="tips" style="color:red;margin-left:20px;"></label>
					<div class="form-group">
						<label>汇入金额</label> <input type="text" id="moneyAmount" readonly="readonly" name="pointAbo.moneyAmount" class="form-control span12">
					</div>
						<label style="color:red;margin-left:20px;"></label>
					<div class="form-group">
						<label>附言</label> <input type="text" id="postscript" name="pointAbo.postscript" class="form-control span12">
						<label id="postscriptTips" style="color:red;margin-left:20px;margin-top:5px"></label>
					</div>
					<div class="form-group">
						<a class="btn btn-primary pull-right" onclick="subForm();">积分汇入</a>
						<label class="remember-me"> <!-- <input type="checkbox"> I agree with the  -->
							<a href="pages/terms-and-conditions.html">积分兑换比例说明</a></label>
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
            $('.demo-cancel-click').click(function(){return false;});
        });
        $("#points").focus(function(){
        	$("#tips").html("");
        	$("#moneyAmount").val("");
        });
        //暂时先这么些，潜在危险：充值时有可能会被人在客户端篡改
        $("#points").blur(function(){
        	if($("#points").val()<1000){
        		$("#tips").html("积分充值请大于1000");
        	}else{
        		//$("#moneyAmount").attr("value",($('#points').val()*0.003).toFixed(2));
        		$("#moneyAmount").val(($('#points').val()*0.003).toFixed(2));
        	}
        });
        $("#postscript").focus(function(){
        	$("#postscriptTips").html("附言是为了管理员进行核对时使用的哦！请填写唯一值（建议正在使用的手机号码）");
        });
        $("#postscript").blur(function(){
        	$("#postscriptTips").html("");
        });
        function subForm(){
        	var result = 0;
        	if($("#points").val() == ""){
        		$("#tips").html("请输入积分值！");
        		result = 1;
        	}
        	if($("#postscript").val() == ""){
        		$("#postscriptTips").html("请输入附言！");
        		result = 1;
        	}
        	if(result != 1){
        		$("#pointForm").submit();
        	}
        	
        }
    </script>
</body>
</html>
