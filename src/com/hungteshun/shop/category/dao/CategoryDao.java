package com.hungteshun.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hungteshun.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{
	
	public List<Category> findAllCategory(){
		String hql = "from Category";
		List<Category> categoryList = this.getHibernateTemplate().find(hql);
		if(categoryList != null){
			return categoryList;
		}else{
			return null;
		}
	}

	/**
	 * 添加一级分类
	 * @param category
	 */
	public void addCategory(Category category) {
		this.getHibernateTemplate().save(category);
	}
}
