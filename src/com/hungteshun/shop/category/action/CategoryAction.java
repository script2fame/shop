package com.hungteshun.shop.category.action;

import java.util.List;

import com.hungteshun.shop.category.service.CategoryService;
import com.hungteshun.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品一级分类action
 * @author hungteshun黄调聪
 *
 */
public class CategoryAction extends ActionSupport implements ModelDriven<Category>{

    Category category = new Category();
    
	public Category getModel() {
		return category;
	}

	//注入CategoryService
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public String findAllCategory(){
		List<Category> categoryList = categoryService.findAllCategory();
		ActionContext.getContext().getSession().put("category", category);
		return "index";
	}
}
