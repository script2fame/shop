package com.hungteshun.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 * @author DoutzenShum
 *
 */
public class Cart implements Serializable{

	private Map<Integer, CartItem> cartItemMap = new LinkedHashMap<Integer, CartItem>();
	private double totalMoney;
	//原始做法遍历value------在首页遍历entry.然后entry.CartItem.product.pname
	//现在的做法：将map中的value值转成一个单列的集合，即CartItem转成一个集合，遍历方便，且说明该类中有一个属性cartItems
	public Collection<CartItem> getCartItems(){
		return cartItemMap.values();
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	
	//1.添加商品(购物项)到购物车
	public void addCartItem(CartItem cartItem){
		//判断商品(购物项)是否存在
		Integer pid = cartItem.getProduct().getPid();
		if(cartItemMap.containsKey(pid)){
			//商品存在
			CartItem existCartItem = cartItemMap.get(pid);
			existCartItem.setCount(existCartItem.getCount()+cartItem.getCount());
		}else{
			//商品不存在
			cartItemMap.put(pid, cartItem);
		}
		totalMoney += cartItem.getSubTotal();
	}
	//2.从购物车移除商品(购物项)
	public void removeFromCart(Integer pid){
		CartItem existCartItem = cartItemMap.remove(pid);
		totalMoney -= existCartItem.getSubTotal();
	}
	//3.清空购物车
	public void clearCart(){
		cartItemMap.clear();
		totalMoney = 0;
	}
}