package com.hungteshun.shop.orders.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hungteshun.shop.cart.vo.Cart;
import com.hungteshun.shop.cart.vo.CartItem;
import com.hungteshun.shop.orders.dao.OrderDao;
import com.hungteshun.shop.orders.vo.OrderItem;
import com.hungteshun.shop.orders.vo.Orders;
import com.hungteshun.shop.user.vo.User;
import com.hungteshun.shop.utils.FormatDate;
import com.hungteshun.shop.utils.pageBean;

@Transactional
public class OrderService {

	//注入OrderDao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}



	/**
	 * 将订单信息保存到数据库
	 * @param orders
	 * @param existcart
	 * @param existUser
	 * @throws ParseException
	 */
	public void savaOrders(Orders orders,Cart existcart, User existUser) throws ParseException {
		orders.setTotalMoney(existcart.getTotalMoney());
		Date date = FormatDate.formatDate(new Date());
		orders.setOrdertime(date);
		orders.setState(0);
		orders.setUser(existUser);
		for(CartItem cartItem : existcart.getCartItems()){
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubTotal());
			orderItem.setOrders(orders);
			orders.getOrderItemSet().add(orderItem);
		}
		orderDao.saveOrders(orders);
		//将购物车信息清空
		existcart.clearCart();
	}


	/**
	 * 通过用户ID查询用户订单
	 * @param uid
	 * @param currentPage
	 * @return
	 */
	public pageBean<Orders> findOrderByUid(Integer uid, Integer currentPage) {
		pageBean<Orders> orderPageBean = new pageBean<Orders>();
		//设置当前页
		orderPageBean.setCurrentPage(currentPage);
		//每一页显示的记录数
		Integer limit = 4;
		orderPageBean.setLimit(limit);
		//设置总的记录数
		Integer totalCount = 0;
		totalCount = orderDao.findTotalcountByUid(uid);
		orderPageBean.setTotalCount(totalCount);
		//设置总页数
		Integer totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		orderPageBean.setTotalPage(totalPage);
		//设置每一页要显示的数据集合
		Integer begin = (currentPage - 1) * limit;
		List<Orders> orderList = orderDao.findOrdersByUidWithPage(uid,begin,limit);
		orderPageBean.setList(orderList);
		return orderPageBean;
	}



	/**
	 * 根据订单编号查询订单
	 * @param oid
	 * @return
	 */
	public Orders findOrderByOid(Integer oid) {
		return orderDao.findOrderByOid(oid);
	}


	/**
	 * 修改订单
	 * @param currentOrder
	 */
	public void update(Orders currentOrder) {
		orderDao.update(currentOrder);
	}


	/**
	 * 分页查询所有的订单
	 * @param currentPage
	 * @return
	 */
	public pageBean<Orders> findAllOrdersWithPage(Integer currentPage) {
		pageBean<Orders> orderPageBean = new pageBean<Orders>();
		//设置当前页
		orderPageBean.setCurrentPage(currentPage);
		//每一页显示的记录数
		Integer limit = 10;
		orderPageBean.setLimit(limit);
		//设置总的记录数
		Integer totalCount = 0;
		totalCount = orderDao.findTotalcount();
		orderPageBean.setTotalCount(totalCount);
		//设置总页数
		Integer totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		orderPageBean.setTotalPage(totalPage);
		//设置每一页要显示的数据集合
		Integer begin = (currentPage - 1) * limit;
		List<Orders> orderList = orderDao.findAllOrdersWithPage(begin,limit);
		orderPageBean.setList(orderList);
		return orderPageBean;
	}


	/**
	 * 根据订单查询订单明细
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOrderItemsByOid(Integer oid) {
		return orderDao.findOrderItemsByOid(oid);
	}



}
