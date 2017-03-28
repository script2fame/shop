package com.hungteshun.shop.admin.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hungteshun.shop.admin.vo.Admin;

public class AdminDao extends HibernateDaoSupport{

	/**
	 * 后台管理员登录
	 * @param username
	 * @param password
	 * @return
	 */
	public Admin login(String username, String password) {
		String hql = "from Admin where username = ? and password = ?";
		List<Admin> adminList = this.getHibernateTemplate().find(hql, username, password);
		if(adminList != null && adminList.size() > 0){
			return adminList.get(0);
		}
		return null;
	}

	
}
