package com.hungteshun.shop.admin.action;

import java.util.List;

import com.hungteshun.shop.category.service.CategoryService;
import com.hungteshun.shop.category.vo.Category;
import com.hungteshun.shop.categorysecond.dao.CategorySecondDao;
import com.hungteshun.shop.categorysecond.service.CategorySecondService;
import com.hungteshun.shop.categorysecond.vo.CategorySecond;
import com.hungteshun.shop.utils.pageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author hungteshun黄调聪
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	private CategorySecond categorySecond = new CategorySecond();
	private CategorySecondService categorySecondService = new CategorySecondService();
	private Integer currentPage;
	private CategoryService categoryService;
	//添加二级分类时所属的一级分类的id
	private Integer cs_cid;
	//修改二级分类时所属的一级分类的id
	private Integer cs_id_update;
	
	public CategorySecond getModel() {
		return categorySecond;
	}
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	public void setCs_cid(Integer cs_cid) {
		this.cs_cid = cs_cid;
	}

	public void setCs_id_update(Integer cs_id_update) {
		this.cs_id_update = cs_id_update;
	}

	/**
	 * 根据当前页数currentPage分页查询二级分类
	 * @return
	 */
	public String findAllcategorySecond(){
		pageBean<CategorySecond> pageBeanCategorySecond = categorySecondService.findCategorySecondWithPage(currentPage);
		//将pageBeanCategorySecond保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBeanCategorySecond", pageBeanCategorySecond);;
		System.out.println(pageBeanCategorySecond.getList().size());
		return "findAllcategorySecond";
	}
	
	/**
	 * 查询所有的一级分类然后跳转到二级分类的添加页面
	 * @return
	 */
	public String editCategorySecond(){
		List<Category> categoryList = categoryService.findAllCategory();
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		return "editCategorySecond";
	}
	/**
	 * 添加二级分类
	 * @return
	 */
	public String addCategorySecond(){
		Category category = new Category();
		category.setCid(cs_cid);
		categorySecond.setCategory(category);
		categorySecondService.addCategorySecond(categorySecond);
		return "addCategorySecond";
	}
	
	/**
	 * 删除二级分类
	 * @return
	 */
	public String deleteCategorySecond(){
		categorySecond = categorySecondService.findCategorySecondByCsid(categorySecond.getCsid());
		categorySecondService.deleteCategorySecond(categorySecond);
		return "deleteCategorySecond";
	}
	
	/**
	 * 首先查找二级分类，然后找到所有的一级分类，之后在前台展示出来
	 * @return
	 */
	public String updateCategorySecond_page(){
		categorySecond = categorySecondService.findCategorySecondByCsid(categorySecond.getCsid());
		List<Category> categoryList = categoryService.findAllCategory();
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		return "updateCategorySecond_page";
	}
	/**
	 * 修改二级分类
	 * @return
	 */
	public String updateCategorySecond_do(){
		Category update_category = new Category();
		update_category.setCid(cs_id_update);
		categorySecond.setCategory(update_category);
		categorySecondService.updateCategorySecond(categorySecond);
		return  "updateCategorySecond_do";
	}
}
