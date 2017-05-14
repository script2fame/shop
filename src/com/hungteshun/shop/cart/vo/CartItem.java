package com.hungteshun.shop.cart.vo;

import com.hungteshun.shop.product.vo.Product;

/**
 * 购物项的对象
 * @author DoutzenShum
 *
 */

public class CartItem {

	private Product product; //商品对象
	private Integer count; //购买的数量
	private double subTotal; //商品小计
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public double getSubTotal() {
		return product.getShop_price() * count;
	}
}
