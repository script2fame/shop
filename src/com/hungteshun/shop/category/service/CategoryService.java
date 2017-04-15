package com.hungteshun.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hungteshun.shop.category.dao.CategoryDao;
import com.hungteshun.shop.category.vo.Category;

@Transactional
public class CategoryService {

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public List<Category> findAllCategory(){
		return categoryDao.findAllCategory();
	}

	/**
	 * 添加一级分类的方法
	 * @param category
	 */
	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}

	/**
	 * 根据一级分类id查找一级分类
	 * @param cid
	 */

	public Category findCategoryByCid(Integer cid) {
		return categoryDao.findCategoryByCid(cid);
		
	}
	/**
	 * 删除一级分类
	 * @param category
	 */
	public void removeCategory(Category category) {
		categoryDao.removeCategory(category);
	}
	/**
	 * 修改一级分类
	 * @param category
	 */
	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);
	}
}
