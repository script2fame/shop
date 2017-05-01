package com.hungteshun.shop.admin.action.interceptor;

import org.apache.struts2.ServletActionContext;

import com.hungteshun.shop.admin.vo.Admin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class AdminInterceptor extends MethodFilterInterceptor{

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//判断session中是否存在后台用户的信息
		Admin existAdmin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("existAdmin");
		if(existAdmin == null){
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("您还没有登录，请先登录在访问");
			return "adminNotExist";
		}else{
			return actionInvocation.invoke();
		}
	}

}
