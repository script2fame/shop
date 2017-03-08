<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="span10 last">
	<div class="topNav clearfix">
		<ul>
		<s:if test="#session.existUser!=null">
		<li ><s:property value="#session.existUser.name"></s:property></li>
			<li ><a href="#">我的订单</a></li>
			<li ><a href="${pageContext.request.contextPath}/user_loginOut.action">退出</a>|</li>
		</s:if>
		<s:else>
			<li id="headerLogin" class="headerLogin" style="display: list-item;"><a
				href="${pageContext.request.contextPath}/user_loginPage.action">登录</a>|</li>
			<li id="headerRegister" class="headerRegister"
				style="display: list-item;"><a href="${pageContext.request.contextPath}/user_registePage.action">注册</a>|</li>
		</s:else>
			<li><a>会员中心</a> |</li>
			<li><a>购物指南</a> |</li>
			<li><a>关于我们</a></li>
		</ul>
	</div>
	<div class="cart">
		<a href="./购物车.htm">购物车</a>
	</div>
	<div class="phone">
		客服热线: <strong>18379170048</strong>
	</div>
</div>
<div class="span24">
	<ul class="mainNav">
		<li><a href="./index.htm">首页</a> |</li>
		<li><a href="./蔬菜分类.htm">定制套餐</a> |</li>
		<li><a>安全频道</a> |</li>
		<li><a>商城卡</a> |</li>
		<li><a>蔬菜基地</a> |</li>
		<li><a>节气养生</a> |</li>
		<li><a>便民服务</a> |</li>

	</ul>
</div>