<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<title>网上商城后台管理登录页面</title>
<meta charset="utf-8">
<script type="text/javascript">
	addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<link href="${pageContext.request.contextPath}/css/styleAdmin.css" rel='stylesheet' type='text/css' />
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
</head>
<body>
	<script>
		$(document).ready(function(c) {
			$('.close').on('click', function(c) {
				$('.login-form').fadeOut('slow', function(c) {
					$('.login-form').remove();
				});
			});
		});
	</script>
	<!--SIGN UP-->
	<h1>后台管理系统登录</h1>
	<div class="login-form">
		<div class="close"></div>
		<div class="head-info">
			<label class="lbl-1"> </label> <label class="lbl-2"> </label> <label
				class="lbl-3"> </label>
		</div>
		<div class="clear"></div>
		<div class="avtar">
			<img src="${pageContext.request.contextPath}/images/avtar.png" />
		</div>
		<span class="signin"><s:actionerror /></span>
		<form id="adminLoginForm" method="post" action="${pageContext.request.contextPath}/admin_login.action" target="_parent" >
			<input type="text" name="username" class="text" value="Username"
				onfocus="this.value = '';"
				onblur="if (this.value == '') {this.value = 'Username';}">
			<div class="key">
				<input type="password" name="password" value="Password" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'Password';}">
			</div>
			<div class="signin">
			<input type="submit" value="Login">
		</div>
		</form>
	</div>
	<div class="copy-rights">
		<p>
			Copyright &copy; 2015.Company name All rights reserved.大葱哥个人网站
			<a href="http://www.hungteshun.com/" target="_blank" title="黄调聪个人网站">大葱哥</a>
		</p>
	</div>

</body>
</html>