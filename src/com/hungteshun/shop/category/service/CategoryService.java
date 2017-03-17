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
}
