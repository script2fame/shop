package com.hungteshun.shop.admin.service;

import org.springframework.transaction.annotation.Transactional;

import com.hungteshun.shop.admin.dao.AdminDao;
import com.hungteshun.shop.admin.vo.Admin;

@Transactional
public class AdminService {

	//注入AdminDao
	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	/**
	 * 后台管理员登录
	 * @param username
	 * @param password
	 * @return
	 */
	public Admin login(String username, String password) {
		return adminDao.login(username,password);
	}

}
