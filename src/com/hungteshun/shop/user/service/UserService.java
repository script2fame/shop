package com.hungteshun.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.hungteshun.shop.user.dao.UserDao;
import com.hungteshun.shop.user.vo.User;
import com.hungteshun.shop.utils.UUIDUtils;

/**
 * 用户模块的service层
 * @author hungteshun黄调聪
 *
 */
@Transactional
public class UserService {

	//注入UserDao
	private UserDao userDao;

	public void setUserdao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//按用户名查询用户
	public User findByUsername(String username){
		
		return userDao.findByUsername(username);
	}

	//完善用户信息
	public void save(User user) {
		//保存用户信息到数据库
		user.setState(0);//0表示未激活，1表示已激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
	}
	
}
