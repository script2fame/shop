package com.hungteshun.shop.orders.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hungteshun.shop.orders.vo.Orders;
import com.hungteshun.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{

	/**
	 * 将订单信息保存到购物车
	 * @param orders
	 */
	public void saveOrders(Orders orders) {
		this.getHibernateTemplate().save(orders);
	}
	
	/**
	 * 通过用户id查询用户的订单数量
	 * @param uid
	 * @return
	 */
	public Integer findTotalcountByUid(Integer uid) {
		String hql = "select count(*) from Orders o where o.user.uid = ?";
		List<Long> countList = this.getHibernateTemplate().find(hql,uid);
		if(countList != null && countList.size() > 0){
			return countList.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 根据用户id查询该用户所有的订单带分页
	 * @param uid
	 * @param begin
	 * @param limit
	 * @return
	 */
	
	public List<Orders> findOrdersByUidWithPage(Integer uid, Integer begin,
			Integer limit) {
		
		String hql = "from Orders o where o.user.uid = ? order by o.ordertime desc";
		List<Orders> orderList = this.getHibernateTemplate().execute(new PageHibernateCallback<Orders>(hql, new Object[]{uid}, begin, limit));
		if(orderList != null && orderList.size() > 0){
			return orderList;
		}
		return null;
	}

	/**
	 * 根据商品ID查询商品
	 * @param oid
	 * @return
	 */
	public Orders findOrderByOid(Integer oid) {
		return this.getHibernateTemplate().get(Orders.class, oid);
	}

	/**
	 * 修改订单状态
	 * @param currentOrder
	 */
	public void update(Orders currentOrder) {
		this.getHibernateTemplate().update(currentOrder);
	}
}
