package com.hungteshun.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.hungteshun.shop.cart.vo.Cart;
import com.hungteshun.shop.cart.vo.CartItem;
import com.hungteshun.shop.product.service.ProductService;
import com.hungteshun.shop.product.vo.Product;
import com.hungteshun.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 购物车模块的action
 * @author hungteshun黄调聪
 *
 */
public class CartAction extends ActionSupport{

	//属性驱动接收pid
	private Integer pid;
	//属性驱动接收商品购买数量
	private Integer count;
	//注入ProductService
	private ProductService productService;
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	/**
	 * 添加商品到购物车
	 * @return
	 */
	public String addToCart(){
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart != null){
			cart.addCartItem(cartItem);
		}else{
			cart = new Cart();
			cart.addCartItem(cartItem);
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return "addToCart";
	}
	/**
	 * 从购物车移除商品
	 * @return
	 */
	public String removeFromCart(){
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart != null){
			cart.removeFromCart(pid);
		}
		return "removeFromCart";
	}
	/**
	 * 清空购物车
	 * @return
	 */
	public String clearCart(){
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart != null){
			cart.clearCart();
		}
		return "clearCart";
	}
	/**
	 * 跳转到我的购物车页面
	 * @return
	 */
	public String toMyCart(){
		return "toMyCart";
	}
}
