<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table border="0" width="100%">
	<tr>
		<td>商品图片</td>
		<td>数量</td>
		<td>小计</td>
	</tr>
	<s:iterator var="orderItem" value="orderItemList">
		<tr>
			<td><img alt="商品图片" width="40" height="45" src="${pageContext.request.contextPath}/<s:property value='#orderItem.product.image'/>"></td>
			<td><s:property value='#orderItem.count'/></td>
			<td><s:property value='#orderItem.subtotal'/></td>
		</tr>
	</s:iterator>
</table>