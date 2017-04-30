package com.hungteshun.shop.admin.action;

import java.util.List;

import com.hungteshun.shop.category.service.CategoryService;
import com.hungteshun.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements
		ModelDriven<Category> {

	Category category = new Category();

	public Category getModel() {
		return category;
	}

	// 注入CategoryService
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 查找所有的一级分类
	 * 
	 * @return
	 */
	public String findAllCategory() {
		List<Category> categoryList = categoryService.findAllCategory();
		// 将集合放到值栈中
		ActionContext.getContext().getValueStack()
				.set("categoryList", categoryList);
		return "findAllCategory";
	}

	/**
	 * 跳转到添加一级分类页面
	 * 
	 * @return
	 */
	public String categoryAddPage() {
		return "categoryAddPage";
	}

	/**
	 * 添加一级分类
	 * 
	 * @return
	 */
	public String addCategory() {
		categoryService.addCategory(category);
		return "addCategory";
	}

	/**
	 * 删除一级分类
	 * 
	 * @return
	 */
	public String removeCategory() {
		// 先查找出该一级分类，然后删除，如果不级联则不删除该一级分类下的所有的二级分类
		category = categoryService.findCategoryByCid(category.getCid());
		categoryService.removeCategory(category);
		return "removeCategory";
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @return
	 */
	public String editCategory() {
		category = categoryService.findCategoryByCid(category.getCid());
		return "editCategory";
	}

	/**
	 * 修改一级分类
	 * 
	 * @return
	 */
	public String updateCategory() {
		// 先查找出该一级分类
		categoryService.updateCategory(category);
		return "updateCategory";
	}
}
