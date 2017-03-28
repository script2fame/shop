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

	private String securitycode;
	
	public void setSecuritycode(String securitycode) {
		this.securitycode = securitycode;
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
		if(securitycode != null){
			String existsecuritycode = (String)ServletActionContext.getRequest().getSession().getAttribute("securitycode");
			if(securitycode.equalsIgnoreCase(existsecuritycode)){
				userService.save(user);
				this.addActionMessage("请前往邮箱激活...");
				return "registeSuccess";
			}else{
				this.addActionError("验证码输入错误");
				return "registFail";
			}
		}else{
			this.addActionError("服务器出现错误，请等待管理员修复...");
			return "registFail";
		}
		
	}

	/**
	 * 激活用户
	 */
	public String active() {
		boolean flag = userService.findByCode(user.getCode());
		if (flag == true) {
			// 用户存在
			this.addActionMessage("激活成功");
		} else {
			// 用户不存在，激活码失效
			this.addActionError("激活失败");
		}
		return "message";
	}

	/**
	 * 跳转到用户登录页面
	 */
	public String loginPage() {
		return "loginPage";
	}

	/**
	 * 用户登录
	 */
	public String login() {
		User existUser = userService.login(user);
		if(existUser == null){
			this.addActionError("登录失败");
			System.out.println("用户不存在");
			return "login";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}
	/**
	 * 退出系统，销毁session
	 */
	public String loginOut(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "loginOut";
	}
}
