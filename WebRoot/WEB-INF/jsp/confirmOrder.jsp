<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="./网上商城/index.htm">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.jpg" alt="临川大葱哥"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	
</div>
<%@include file="menu.jsp" %>	
</div>	

<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li  class="current"></li>
					<li>确认订单</li>
				</ul>
			</div>
				<table>
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<s:iterator var="cartItem" value="#session.cart.cartItems">
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="<s:property value='#cartItem.product.pid'/>">
								<img src="${pageContext.request.contextPath}/<s:property value='#cartItem.product.image'/>">
							</td>
							<td>
								<a target="_blank"><s:property value='#cartItem.product.pname'/></a>
							</td>
							<td>
								￥<s:property value='#cartItem.product.shop_price'/>
							</td>
							<td class="quantity" width="60">
								<s:property value='#cartItem.count'/>
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value='#cartItem.subTotal'/></span>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/cart_removeFromCart.action?pid=<s:property value='#cartItem.product.pid'/>" class="delete">删除</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
					商品金额: <strong id="effectivePrice">￥<s:property value="#session.cart.totalMoney"/></strong>
				</div>
			<form id="orderForm" action="${pageContext.request.contextPath}/order_submitOrder.action" method="post">
				<div class="span24">
					<p>
							收货地址：<input name="useraddr" id="useraddr" type="text" value="<s:property value='#session.existUser.addr'/>" style="width:350px" />
								<br />
							收货人&nbsp;&nbsp;&nbsp;：<input name="username" id="username" type="text" value="<s:property value='#session.existUser.name'/>" style="width:150px" />
								<br /> 
							联系方式：<input name="userphone" id="userphone" type="text"value="<s:property value='#session.existUser.phone'/>" style="width:150px" />

						</p>
						<hr />
						<p style="text-align:right">
							<a href="javascript:document.getElementById('orderForm').submit();">
								<img src="${pageContext.request.contextPath}/images/finalbutton.gif" width="204" height="51" border="0" />
							</a>
						</p>
				</div>
			</form>
		</div>
		
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>