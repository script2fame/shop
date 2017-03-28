package com.hungteshun.shop.orders.vo;

import com.hungteshun.shop.product.vo.Product;

/**
 * 订单的每一项内容
 * @author hungteshun黄调聪
 *
 */
public class OrderItem {

	private Integer itemid;
	private Integer count;
	private Double subtotal;
	private Product product;
	private Orders orders;
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
}
