package com.hungteshun.shop.admin.action;

import java.util.List;

import com.hungteshun.shop.category.service.CategoryService;
import com.hungteshun.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	Category category = new Category();
	public Category getModel() {
		return category;
	}
	
	//注入CategoryService
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/**
	 * 查找所有的一级分类
	 * @return
	 */
	public String findAllCategory(){
		List<Category> categoryList = categoryService.findAllCategory();
		//将集合放到值栈中
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		return "findAllCategory";
	}

}
