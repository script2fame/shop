package com.hungteshun.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hungteshun.shop.user.vo.User;

/**
 * 用户模块持久层代码
 * 
 * @author hungteshun黄调聪 
 * 继承HibernateDaoSupport，会为我们提供一个hibernate模板，
 *         我们需要将sessionFactory注入到Dao中，就可以使用hibernate模板
 */
public class UserDao extends HibernateDaoSupport {

	/**
	 * 按名称查询是否存在用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		String hql = "from User where username = ?";
		List<User> userLists = this.getHibernateTemplate().find(hql, username);
		if (userLists != null && userLists.size() > 0) {
			return userLists.get(0);
		} else {
			return null;
		}
	}

	// 保存用户信息到数据库
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 根据激活码查找用户
	 * 
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		String sql = "from User where code = ?";
		List<User> userLists = this.getHibernateTemplate().find(sql, code);
		if (userLists != null && userLists.size() > 0) {
			return userLists.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 更新用户信息
	 * 
	 * @param existUser
	 */
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 */
	public User login(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> userlists = this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(userlists != null && userlists.size()>0){
			return userlists.get(0);
		}else{
			return null;
		}
	}
}
