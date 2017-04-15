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
	
	/**
	 * 根据一级分类id查询一级分类
	 * @param cid
	 * @return
	 */
	public Category findCategoryByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	/**
	 * 删除一级分类
	 * @param category
	 */
	public void removeCategory(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	/**
	 * 修改一级分类
	 * @param category
	 */
	public void updateCategory(Category category) {
		this.getHibernateTemplate().update(category);
	}
}
