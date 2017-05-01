<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>所有订单页面</title>
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
					<li  >我的订单</li>
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
					<s:iterator var="orders" value="ordersPageBean.list">
						<tr>
							<th colspan="5">订单状态：
								<s:if test="#orders.state==0">
									<a href="${pageContext.request.contextPath}/order_findOrderByOid.action?oid=<s:property value='#orders.oid'/>" style="color:red">未付款</a>
								</s:if>
								<s:if test="#orders.state==1">
									<span style="color:red">未发货</span>
								</s:if>
								<s:if test="#orders.state==2">
									<a href="${pageContext.request.contextPath}/order_updateState.action?oid=<s:property value='#orders.oid'/>" style="color:blue">确认收货</a>
								</s:if>
								<s:if test="#orders.state==3">
									<span style="color:green">交易完成</span>
								</s:if>
							</th>
						</tr>
						<s:iterator var="orderItem" value="#orders.orderItemSet">
						<tr>
							<td width="60">
								<img src="${pageContext.request.contextPath}/<s:property value='#orderItem.product.image'/>"/>
							</td>
							<td>
								<s:property value="#orderItem.product.pname"/>
							</td>
							<td>
								<s:property value="#orderItem.product.shop_price"/>
							</td>
							<td class="quantity" width="60">
								<s:property value="#orderItem.count"/>
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value="#orderItem.subtotal"/></span>
							</td>
						</tr>
						</s:iterator>
					</s:iterator>
					<tr>
						<td colspan="5">
							<div class="pagination">
							<span>第<s:property value="ordersPageBean.currentPage"/>/<s:property value="ordersPageBean.totalPage"/>页</span>
							<s:if test="ordersPageBean.currentPage!=1">
							<a href="${pageContext.request.contextPath}/order_findOrderByUid.action?&currentPage=1" class="firstPage">&nbsp;</a>
							<a href="${pageContext.request.contextPath}/order_findOrderByUid.action?&currentPage=<s:property value='ordersPageBean.currentPage-1'/>" class="previousPage">&nbsp;</a>
							</s:if>
							<s:iterator var="i" begin="1" end="ordersPageBean.totalPage">
							<s:if test="ordersPageBean.currentPage!=#i">
								<a href="${pageContext.request.contextPath}/order_findOrderByUid.action?&currentPage=<s:property value='#i'/>"><s:property value='#i'/></a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value='#i'/></span>
							</s:else>
							</s:iterator>
							<s:if test="ordersPageBean.currentPage!=ordersPageBean.totalPage">
							<a class="nextPage" href="${pageContext.request.contextPath}/order_findOrderByUid.action?&currentPage=<s:property value='ordersPageBean.currentPage+1'/>">&nbsp;</a>
							<a class="lastPage" href="${pageContext.request.contextPath}/order_findOrderByUid.action?&currentPage=<s:property value='ordersPageBean.totalPage'/>">&nbsp;</a>
							</s:if>
	</div>
						</td>
					</tr>
				</tbody>
			</table>
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