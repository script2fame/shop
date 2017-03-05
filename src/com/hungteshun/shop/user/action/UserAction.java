package com.hungteshun.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hungteshun.shop.user.service.UserService;
import com.hungteshun.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author hungteshun黄调聪
 * 
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();

	public User getModel() {
		return user;
	}

	// 注入UserService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 跳转到注册页面
	 */
	public String registePage() {
		return "registePage";
	}

	/**
	 * ajax异步检测是否存在用户名
	 * 
	 * @throws IOException
	 */
	public String checkUsername() throws IOException {
		User existUser = userService.findByUsername(user.getUsername());
		// 获得response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if (existUser != null) {
			// 用户名已经存在
			response.getWriter().print("<font color='red'>用户名已经存在</font>");
		} else {
			// 用户名不存在
			response.getWriter().print("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	/*
	 * 用户注册
	 */
	public String regist() {
		userService.save(user);
		return NONE;
	}
}
