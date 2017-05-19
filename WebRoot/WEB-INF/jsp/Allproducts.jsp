<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大葱哥网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="http://localhost:8080/mango/">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.jpg" alt="大葱哥">
			</a>
		</div>
	</div>
	<div class="span9">
			<div class="headerAd">
				<input id="inp_search_product" class="form-control" type="text" placeholder="商品搜索"/>
				<button id="btn_search_product" class="btn btn-primary" onclick="search_product(1)">search</button>
			</div>
	</div>
	<%@ include file="menu.jsp" %>
</div>	
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator var="category" value="#session.categoryList">
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/product_findCategoryByCid.action?cid=<s:property value='#category.cid'/>&currentPage=1"><s:property value="#category.cname"/></a>
							</dt>
								<s:iterator var="categorysecond" value="#category.categoryseconds">
									<dd>
										<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value='#categorysecond.csid'/>&currentPage=1"><s:property value="#categorysecond.csname"/></a>
									</dd>
								</s:iterator>
						</dl>
						</s:iterator>
			</div>
		</div>
		<div class="span18 last">
			<form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm" method="get">
				<div id="result" class="result table clearfix">
						<ul>
						<s:iterator var="p" value="pageBeanProduct.list">
						<li>
							<a href="${pageContext.request.contextPath}/product_findByPid.action?pid=<s:property value='#p.pid'/>">
							<img src="${pageContext.request.contextPath}/<s:property value='#p.image'/>" width="170" height="170"  style="display: inline-block;">
							<span style='color:green'><s:property value="#p.pname"/></span>
							<span class="price">商城价： ￥<s:property value="#p.shop_price"/>元</span>
							</a>
							</li>
							</s:iterator>
						</ul>
				</div>
	<div class="pagination">
	<!-- 判断是否是根据一级分类查询到的商品 -->
	<s:if test="cid != null">
			<span>第<s:property value="pageBeanProduct.currentPage"/>/<s:property value="pageBeanProduct.totalPage"/>页</span>
			<s:if test="pageBeanProduct.currentPage!=1">
			<a href="${pageContext.request.contextPath}/product_findCategoryByCid.action?cid=<s:property value="cid"/>&currentPage=1" class="firstPage">&nbsp;</a>
			<a href="${pageContext.request.contextPath}/product_findCategoryByCid.action?cid=<s:property value="cid"/>&currentPage=<s:property value='pageBeanProduct.currentPage-1'/>" class="previousPage">&nbsp;</a>
			</s:if>
			<s:iterator var="i" begin="1" end="pageBeanProduct.totalPage">
				<s:if test="pageBeanProduct.currentPage!=#i">
					<a href="${pageContext.request.contextPath}/product_findCategoryByCid.action?cid=<s:property value="cid"/>&currentPage=<s:property value='#i'/>"><s:property value='#i'/></a>
				</s:if>
				<s:else>
					<span class="currentPage"><s:property value='#i'/></span>
				</s:else>
			</s:iterator>
				
			<s:if test="pageBeanProduct.currentPage!=pageBeanProduct.totalPage">
			<a class="nextPage" href="${pageContext.request.contextPath}/product_findCategoryByCid.action?cid=<s:property value="cid"/>&currentPage=<s:property value='pageBeanProduct.currentPage+1'/>">&nbsp;</a>
			<a class="lastPage" href="${pageContext.request.contextPath}/product_findCategoryByCid.action?cid=<s:property value="cid"/>&currentPage=<s:property value='pageBeanProduct.totalPage'/>">&nbsp;</a>
			</s:if>
		</s:if>
		<!-- 根据二级分类查询得到的商品 -->
		<s:elseif test="csid != null">
			<span>第<s:property value="pageBeanProduct.currentPage"/>/<s:property value="pageBeanProduct.totalPage"/>页</span>
			<s:if test="pageBeanProduct.currentPage!=1">
			<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=1" class="firstPage">&nbsp;</a>
			<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=<s:property value='pageBeanProduct.currentPage-1'/>" class="previousPage">&nbsp;</a>
			</s:if>
			<s:iterator var="i" begin="1" end="pageBeanProduct.totalPage">
				<s:if test="pageBeanProduct.currentPage!=#i">
					<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=<s:property value='#i'/>"><s:property value='#i'/></a>
				</s:if>
				<s:else>
					<span class="currentPage"><s:property value='#i'/></span>
				</s:else>
			</s:iterator>
				
			<s:if test="pageBeanProduct.currentPage!=pageBeanProduct.totalPage">
			<a class="nextPage" href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=<s:property value='pageBeanProduct.currentPage+1'/>">&nbsp;</a>
			<a class="lastPage" href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=<s:property value='pageBeanProduct.totalPage'/>">&nbsp;</a>
			</s:if>
		</s:elseif>
		<!-- 条件或者非条件查询 -->
		<s:elseif test="productType != null">
			<span>第<s:property value="pageBeanProduct.currentPage"/>/<s:property value="pageBeanProduct.totalPage"/>页</span>
			<s:if test="pageBeanProduct.currentPage!=1">
			<a href="${pageContext.request.contextPath}/product_findBySearch.action?currentPage=1&productType=<s:property value='productType'/>" class="firstPage">&nbsp;</a>
			<a href="${pageContext.request.contextPath}/product_findBySearch.action?currentPage=<s:property value='pageBeanProduct.currentPage-1'/>&productType=<s:property value='productType'/>" class="previousPage">&nbsp;</a>
			</s:if>
			<s:iterator var="i" begin="1" end="pageBeanProduct.totalPage">
				<s:if test="pageBeanProduct.currentPage!=#i">
					<a href="${pageContext.request.contextPath}/product_findBySearch.action?currentPage=<s:property value='#i'/>&productType=<s:property value='productType'/>"><s:property value='#i'/></a>
				</s:if>
				<s:else>
					<span class="currentPage"><s:property value='#i'/></span>
				</s:else>
			</s:iterator>
				
			<s:if test="pageBeanProduct.currentPage!=pageBeanProduct.totalPage">
			<a class="nextPage" href="${pageContext.request.contextPath}/product_findBySearch.action?currentPage=<s:property value='pageBeanProduct.currentPage+1'/>&productType=<s:property value='productType'/>">&nbsp;</a>
			<a class="lastPage" href="${pageContext.request.contextPath}/product_findBySearch.action?currentPage=<s:property value='pageBeanProduct.totalPage'/>&productType=<s:property value='productType'/>">&nbsp;</a>
			</s:if>
		</s:elseif>
		<s:else >
			<span>第<s:property value="pageBeanProduct.currentPage"/>/<s:property value="pageBeanProduct.totalPage"/>页</span>
			<s:if test="pageBeanProduct.currentPage!=1">
			<a href="${pageContext.request.contextPath}/product_findBySearch.action?currentPage=1" class="firstPage">&nbsp;</a>
			<a href="${pageContext.request.contextPath}/product_findBySearch.action?currentPage=<s:property value='pageBeanProduct.currentPage-1'/>" class="previousPage">&nbsp;</a>
			</s:if>
			<s:iterator var="i" begin="1" end="pageBeanProduct.totalPage">
				<s:if test="pageBeanProduct.currentPage!=#i">
					<a href="${pageContext.request.contextPath}/product_findBySearch.action?currentPage=<s:property value='#i'/>"><s:property value='#i'/></a>
				</s:if>
				<s:else>
					<span class="currentPage"><s:property value='#i'/></span>
				</s:else>
			</s:iterator>
				
			<s:if test="pageBeanProduct.currentPage!=pageBeanProduct.totalPage">
			<a class="nextPage" href="${pageContext.request.contextPath}/product_findBySearch.action?currentPage=<s:property value='pageBeanProduct.currentPage+1'/>">&nbsp;</a>
			<a class="lastPage" href="${pageContext.request.contextPath}/product_findBySearch.action?currentPage=<s:property value='pageBeanProduct.totalPage'/>">&nbsp;</a>
			</s:if>
		</s:else>
	</div>
			</form>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
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
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >官网</a>
						|
					</li>
					<li>
						<a >论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</html>