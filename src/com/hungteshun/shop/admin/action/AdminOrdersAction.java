package com.hungteshun.shop.admin.action;

import java.util.ArrayList;
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
	//条件查询订单
	private String orders_search_news;
	
	public Orders getModel() {
		return orders;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setOrders_search_news(String orders_search_news) {
		this.orders_search_news = orders_search_news;
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
	/**
	 * 统计分析商品信息
	 * @return
	 */
	public String statisticsPage(){
		return "statisticsPage";
	}
	/**
	 * 条件查询订单信息
	 * @return
	 */
	public String findOrdersBySearch(){
		System.out.println("orders_search_news="+orders_search_news);
		if(orders_search_news.length()>0){
			try {
				int orders_search_news_id = Integer.parseInt(orders_search_news);
				Orders existOrders = orderService.findOrderByOid(orders_search_news_id);
				//将得到的数据封装到一个pageBean中，方便显示
				pageBean<Orders> orderPageBean = new pageBean<Orders>();
				orderPageBean.setCurrentPage(1);
				//设置每一页显示的记录数
				Integer limit = 1;
				orderPageBean.setLimit(limit);
				//设置总的记录数为1
				Integer totalCount = 1;
				orderPageBean.setTotalCount(totalCount);
				Integer totalPage = 1;
				orderPageBean.setTotalPage(totalPage);
				//设置每一页要显示的数据集合
				List<Orders> orderList = new ArrayList<Orders>();
				orderList.add(existOrders);
				orderPageBean.setList(orderList);
				ActionContext.getContext().getValueStack().set("Orders_pageBean", orderPageBean);
			} catch (Exception e) {
				ActionContext.getContext().getValueStack().set("Orders_pageBean", null);
			}
		}else{
			pageBean<Orders> Orders_pageBean = orderService.findAllOrdersWithPage(currentPage);
			ActionContext.getContext().getValueStack().set("Orders_pageBean", Orders_pageBean);
		}
		return "findAllOrders";
	}
}
