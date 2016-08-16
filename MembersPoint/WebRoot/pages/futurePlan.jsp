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

<title>未来愿景</title>
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
					class="fa fa-paper-plane"></span>弗恩天下</span></a>
		</div>

		<div class="navbar-collapse collapse" style="height: 1px;"></div>
	</div>


	<div class="main">
		<div class="main-content">
			<div class="panel panel-default">
	            <a href="#widget1container" class="panel-heading" data-toggle="collapse">未来愿景 </a>
	            <div id="widget1container" class="panel-body collapse in">
	                <s:property value="systemUtil.content" escapeHtml="false"/>
	            </div>
	        </div>
        </div>
	</div>
	 <script src="pages/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
</body>
</html>
