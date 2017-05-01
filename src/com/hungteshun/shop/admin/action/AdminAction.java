package com.hungteshun.shop.admin.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.hungteshun.shop.admin.service.AdminService;
import com.hungteshun.shop.admin.vo.Admin;
import com.hungteshun.shop.category.service.CategoryService;
import com.hungteshun.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台管理员登录
 * @author hungteshun黄调聪
 *
 */
public class AdminAction extends ActionSupport implements ModelDriven<Admin>{

	Admin admin = new Admin();
	
	public Admin getModel() {
		return admin;
	}
	
	//注入AdminService
	private AdminService adminService;
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * 后台管理员登录
	 * @return
	 */
	public String login(){
		String username = admin.getUsername();
		String password = admin.getPassword();
		System.out.println("username="+username);
		Admin existAdmin = adminService.login(username,password);
		if(existAdmin != null){
			//登录成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdmin", existAdmin);
			return "loginSuccess";
		}else{
			//登录失败
			this.addActionError("用户名或密码错误,请重新输入");
			return "loginFail";
		}
	}
	
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
}
