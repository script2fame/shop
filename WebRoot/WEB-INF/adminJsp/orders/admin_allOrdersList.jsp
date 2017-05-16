<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<br>
<form id="admin_allOrdersList_form" name="admin_allOrdersList_form" action="" method="post">
	<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
		<tbody>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3"><strong>所有订单</strong>
				</TD>
			</tr>
			<tr>
				<td class="ta_01" align="right">
				<div class="span9">
						<div class="headerAd">
							<input id="admin_inp_search_orders" class="form-control" type="text" placeholder="商品搜索"/>
							<button type="button" id="admin_btn_search_orders" class="btn btn-primary" onclick="admin_search_orders()">search</button>
						</div>
						<div>
						<button type="button" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload',plain:true"
						onclick="admin_switchToOrders(1)">刷新</button>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1" id="DataGrid1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

							<td align="center" width="5%">序号</td>
							<td align="center" width="10%">订单编号</td>
							<td align="center" width="10%">订单状态</td>
							<td align="center" width="5%">总金额</td>
							<td align="center" width="12%">下单日期</td>
							<td align="center" width="5%">收货人</td>
							<td align="center" width="10%">联系方式</td>
							<td align="center" width="15%">收货地址</td>
							<td align="center" width="20%">订单详情</td>
						</tr>
						<s:iterator var="Orders" value="Orders_pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 10px" align="center" width="5%"><s:property value="#status.index+1" /></td>
									<td style="CURSOR: hand; HEIGHT: 10px" align="center" width="10%"><s:property value="#Orders.oid" /></td>
									<!-- 0：未付款 1:已付款但是未发货 2：已发货但是未确认收货 3：已确认收货 -->
									<td style="CURSOR: hand; HEIGHT: 10px" align="center" width="10%">
										<s:if test="#Orders.state == 0">
											<span style="color:red">未付款</span>
										</s:if>
										<s:if test="#Orders.state == 1">
											<span style="color:green" onclick="adminorders_updateStatus(<s:property value='#Orders.oid'/>)">去发货</span>
										</s:if>
										<s:if test="#Orders.state == 2">
											<span style="color:blue">已发货，未收货</span>
										</s:if>
										<s:if test="#Orders.state == 3">
											<span style="color:black">交易完成</span>
										</s:if>
									</td>
									<td style="CURSOR: hand; HEIGHT: 10px" align="center" width="5%"><s:property value="#Orders.totalMoney" /></td>
									<td style="CURSOR: hand; HEIGHT: 10px" align="center" width="10%"><s:property value="#Orders.ordertime" /></td>
									<td style="CURSOR: hand; HEIGHT: 10px" align="center" width="5%"><s:property value="#Orders.username"/></td>
									<td style="CURSOR: hand; HEIGHT: 10px" align="center" width="10%"><s:property value="#Orders.userphone"/></td>
									<td style="CURSOR: hand; HEIGHT: 10px" align="center" width="12%"><s:property value="#Orders.useraddr"/></td>
									<td align="center" style="HEIGHT: 10px" width="10%">
										<button id="btn_adminOrders_viewOrderItem" type="button" class="easyui-linkbutton" value="adminOrders_viewOrderItem" onclick="adminOrders_viewOrderItem(<s:property value='#Orders.oid' />)">订单详情</button>
										<div id="div_viewOrderItem_<s:property value='#Orders.oid'/>"></div>
									</td>
								</tr>
						</s:iterator>
						<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							<td colspan="10" align="center" width="10%">
								第<s:property value="Orders_pageBean.currentPage"/>/<s:property value="Orders_pageBean.totalPage"/>页
								<s:if test="Orders_pageBean.currentPage != 1">
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToOrders(1)">首页</button>
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToOrders(<s:property value='Orders_pageBean.currentPage'/> -1)">上一页</button>
								</s:if>
								<s:if test="Orders_pageBean.currentPage != Orders_pageBean.totalPage">
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToOrders(<s:property value='Orders_pageBean.currentPage'/> +1)">下一页</button>
									<button type="button" class="easyui-linkbutton" data-options="plain:true" onclick="admin_switchToOrders(<s:property value='Orders_pageBean.totalPage'/> )">尾页</button>									
								</s:if>
							</td>
						</tr>		
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</form>