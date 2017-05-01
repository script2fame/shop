package com.hungteshun.shop.orders.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.hungteshun.shop.cart.vo.Cart;
import com.hungteshun.shop.cart.vo.CartItem;
import com.hungteshun.shop.orders.service.OrderService;
import com.hungteshun.shop.orders.vo.OrderItem;
import com.hungteshun.shop.orders.vo.Orders;
import com.hungteshun.shop.user.vo.User;
import com.hungteshun.shop.utils.FormatDate;
import com.hungteshun.shop.utils.PayForOrders;
import com.hungteshun.shop.utils.pageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Orders> {

	Orders orders = new Orders();

	public Orders getModel() {
		return orders;
	}

	// 注入OrderService
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// 属性驱动接收当前页
	private Integer currentPage;

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	// 属性驱动接收支付通道编码
	private String pd_FrpId;

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	// 属性驱动接收付款成功后返回给系统的参数r3_Amt和r6_Order
	private String r3_Amt;// 支付支付成功的金额
	private String r6_Order;// 商户订单号

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	/**
	 * 提交订单
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String submitOrder() throws ParseException {
		// 从session中取出购物车的信息
		Cart existcart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		// 从session中取出用户的信息
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("您好，请先去登录!");
			return "message";
		}
		orderService.savaOrders(orders, existcart, existUser);
		return "submitOrder";
	}

	/**
	 * 购物车结算,确认即将提交的订单信息
	 * 
	 * @return
	 */
	public String confirmOrder() {
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("您好，请先去登录!");
			return "message";
		}
		return "confirmOrder";
	}

	/**
	 * 通过用户id查找用户订单
	 */
	public String findOrderByUid() {
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		Integer uid = existUser.getUid();
		pageBean<Orders> ordersPageBean = orderService.findOrderByUid(uid,
				currentPage);
		// 将pageBean的数据存入值栈
		ActionContext.getContext().getValueStack()
				.set("ordersPageBean", ordersPageBean);
		return "findOrderByUid";
	}

	public String findOrderByOid() {
		orders = orderService.findOrderByOid(orders.getOid());
		return "findOrderByOid";
	}

	/**
	 * 为订单付款
	 * 
	 * @return
	 * @throws IOException
	 */
	public String paymentOfOrder() throws IOException {
		// 付款需要的参数
		// 业务类型
		String p0_Cmd = "Buy";
		// 商户编号
		String p1_MerId = "10001126856";
		// 订单编号
		String p2_Order = orders.getOid().toString();
		// 支付金额
		String p3_Amt = "0.01";
		// 交易币种
		String p4_Cur = "CNY";
		// 商品名称
		String p5_Pid = "";
		// 商品种类
		String p6_Pcat = "";
		// 商品描述
		String p7_Pdesc = "";
		// 商户接收支付成功数据的地址
		String p8_Url = "http://172.20.10.13:8080/shop/order_getBackMessage.action";
		// 送货地址
		String p9_SAF = "";
		// 商户扩展信息
		String pa_MP = "";
		// 支付通道编码
		String pd_FrpId = this.pd_FrpId;
		// 应答机制
		String pr_NeedResponse = "1";
		// 秘钥
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PayForOrders.generateHmac(p0_Cmd, p1_MerId, p2_Order,
				p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF,
				pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		// 拼接支付请求需要的参数
		//这里不能使用stringBuffer会抛出异常Cannot call sendError() after the response has been committed
		StringBuilder sb = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		// 重定向，向易宝发送支付请求
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}

	public String getBackMessage() {
		//根据易宝返回的订单号查询该订单
		Orders currentOrder = orderService.findOrderByOid(Integer.parseInt(r6_Order));
		//将该订单状态修改为2，即已付款
		currentOrder.setState(2);
		orderService.update(currentOrder);
		this.addActionMessage("支付成功!订单编号: "+r6_Order +" 实付款: "+r3_Amt+"元");
		return "message";
	}
	public String updateState(){
		Orders existOrder = orderService.findOrderByOid(orders.getOid());
		existOrder.setState(3);
		orderService.update(existOrder);
		return "updateState";
	}
}
