package com.hungteshun.shop.index.action;

import java.util.List;

import com.hungteshun.shop.category.service.CategoryService;
import com.hungteshun.shop.category.vo.Category;
import com.hungteshun.shop.product.service.ProductService;
import com.hungteshun.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	//注入一级分类的service
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//注入商品模块的service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public String execute(){
		//查找一级分类
		List<Category> categoryList = categoryService.findAllCategory();
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		//查找热门商品
		List<Product> productList = productService.findHot();
		//将数据放入值栈中
		ActionContext.getContext().getValueStack().set("productList", productList);
		//查询最新商品
		List<Product> newestProductList = productService.findNewest(); 
		//将数据存入值栈
		ActionContext.getContext().getValueStack().set("newestProductList", newestProductList);
		return "index";
	}
}
