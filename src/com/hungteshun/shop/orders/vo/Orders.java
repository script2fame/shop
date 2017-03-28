package com.hungteshun.shop.orders.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.hungteshun.shop.user.vo.User;

/**
 * 订单对象
 * 
 * @author hungteshun黄调聪
 * 
 */

public class Orders {

	private Integer oid;
	private Double totalMoney;// 订单总金额
	private Date ordertime;
	private Integer state;// 0：未付款 1:已付款但是未发货 2：已发货但是未确认收货 3：已确认收货
	private String username;// 收货人姓名
	private String userphone;
	private String useraddr;
	private User user;
	private Set<OrderItem> orderItemSet = new HashSet<OrderItem>();

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUseraddr() {
		return useraddr;
	}

	public void setUseraddr(String useraddr) {
		this.useraddr = useraddr;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderItem> getOrderItemSet() {
		return orderItemSet;
	}

	public void setOrderItemSet(Set<OrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
	}

}
