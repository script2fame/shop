<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<br>
<form id="admin_allProductList_form" name="admin_allProductList_form" action="" method="post">
	<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
		<tbody>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3"><strong>所有商品</strong>
				</TD>
			</tr>
			<tr>
				<td class="ta_01" align="right">
					<div class="span9">
						<div class="headerAd">
							<input id="admin_inp_search_product" class="form-control" type="text" placeholder="商品搜索"/>
							<button type="button" id="admin_btn_search_product" class="btn btn-primary" onclick="admin_search_product(1)">search</button>
						</div>
					</div>
					<button type="button" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload',plain:true"
						onclick="admin_switchToProduct(1)">刷新</button>
					<button type="button" class="easyui-linkbutton"
						data-options="iconCls:'icon-add',plain:true"
						onclick="admin_productAddPage()">添加</button>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1" id="DataGrid1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

							<td align="center" width="10%">序号</td>
							<td align="center" width="17%">商品名称</td>
							<td align="center" width="17%">商品图片</td>
							<td align="center" width="17%">商品价格</td>
							<td align="center" width="17%">是否热门</td>
							<td width="7%" align="center">编辑</td>
							<td width="7%" align="center">删除</td>
						</tr>
						<s:iterator var="product" value="product_pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%"><s:property value="#status.index+1" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%"><s:property value="#product.pname" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%"><s:property value="#product.shop_price"/></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%"><img alt="商品图片" width="40" height="45" src="${pageContext.request.contextPath}/<s:property value='#product.image'/>"></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
										<s:if test="#product.is_hot == 1">热门商品</s:if>
										<s:else>普通商品</s:else>
									</td>
									<td align="center" style="HEIGHT: 22px">
										<button type="button" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="adminproduct_edit_page(<s:property value='#product.pid' />)">修改</button>
									</td>
									<td align="center" style="HEIGHT: 22px">
										<button type="button" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="adminproduct_remove_do(<s:property value='#product.pid' />)">删除</button>	
									</td>
								</tr>
						</s:iterator>
						<s:if test="productType != null">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td colspan="7" align="center" width="18%">
								第<s:property value="product_pageBean.currentPage"/>/<s:property value="product_pageBean.totalPage"/>页
								<s:if test="product_pageBean.currentPage != 1">
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_search_product_productType(1,'<s:property value='productType'/>')">首页</button>
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_search_product_productType(<s:property value='product_pageBean.currentPage'/> -1,'<s:property value='productType'/>')">上一页</button>
								</s:if>
								<s:if test="product_pageBean.currentPage != product_pageBean.totalPage">
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_search_product_productType(<s:property value='product_pageBean.currentPage'/> +1,'<s:property value='productType'/>')">下一页</button>
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_search_product_productType(<s:property value='product_pageBean.totalPage'/>,'<s:property value='productType'/>')">尾页</button>									
								</s:if>
							</td>
						</tr>	
						</s:if>
						<s:else>
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td colspan="7" align="center" width="18%">
								第<s:property value="product_pageBean.currentPage"/>/<s:property value="product_pageBean.totalPage"/>页
								<s:if test="product_pageBean.currentPage != 1">
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToProduct(1)">首页</button>
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToProduct(<s:property value='product_pageBean.currentPage'/> -1)">上一页</button>
								</s:if>
								<s:if test="product_pageBean.currentPage != product_pageBean.totalPage">
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToProduct(<s:property value='product_pageBean.currentPage'/> +1)">下一页</button>
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToProduct(<s:property value='product_pageBean.totalPage'/> )">尾页</button>									
								</s:if>
							</td>
						</tr>
						</s:else>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</form>