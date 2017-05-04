package com.hungteshun.shop.admin.action;

import java.util.List;

import com.hungteshun.shop.orders.service.OrderService;
import com.hungteshun.shop.orders.vo.OrderItem;
import com.hungteshun.shop.orders.vo.Orders;
import com.hungteshun.shop.utils.pageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminOrdersAction extends ActionSupport implements ModelDriven<Orders> {

	Orders orders = new Orders();
	private Integer currentPage;
	private OrderService orderService;
	
	public Orders getModel() {
		return orders;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	/**
	 * 分页查询所有的订单
	 * @return
	 */
	public String findAllOrdersWithPage(){
		pageBean<Orders> Orders_pageBean = orderService.findAllOrdersWithPage(currentPage);
		ActionContext.getContext().getValueStack().set("Orders_pageBean", Orders_pageBean);
		return "findAllOrders";
	}
	
	/**
	 * 异步加载订单项
	 * @return
	 */
	public String viewOrderItem(){
		List<OrderItem> orderItemList = orderService.findOrderItemsByOid(orders.getOid());
		ActionContext.getContext().getValueStack().set("orderItemList", orderItemList);
		return "viewOrderItem";
	}
	
	/**
	 * 修改订单状态
	 * @return
	 */
	public String updateStatus(){
		Orders existOrders = orderService.findOrderByOid(orders.getOid());
		//修改订单状态为运输中，未确定收货
		existOrders.setState(2);
		orderService.update(existOrders);
		return "updateStatus";
	}
	
	public String statisticsPage(){
		return "statisticsPage";
	}
}
