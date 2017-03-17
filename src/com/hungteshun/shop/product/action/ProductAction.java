package com.hungteshun.shop.product.action;

import com.hungteshun.shop.category.service.CategoryService;
import com.hungteshun.shop.product.service.ProductService;
import com.hungteshun.shop.product.vo.Product;
import com.hungteshun.shop.utils.pageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	//模型驱动使用的model
	private Product product = new Product();
	//属性驱动接收一级分类id
	private Integer cid;
	//属性驱动接收二级分类id
	private Integer csid;
	//属性驱动接收当前页
	private int currentPage;
	//注入categoryService
	private CategoryService categoryService;
	//注入ProductService
	private ProductService productService;
	
	public Product getModel() {
		return product;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	//将cid存入action(值栈)中，使得在jsp中可以取出来
	public Integer getCid() {
		return cid;
	}
	//将csid存入action中(值栈)中，使得在jsp中可以取出来
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public String findByPid(){
		//不用存入值栈，存入model即可，即为栈顶
		product = productService.findByPid(product.getPid());
		return "productDetails";
	}
	
	/**
	 * 通过一级分类id查找一级分类和商品
	 * @return
	 */
	public String findCategoryByCid(){
		//在页面上获取所有一级分类,直接从session中获取
		//根据一级分类查询所有商品带分页
		pageBean<Product> pageBeanProduct = productService.findProductByPageCid(cid,currentPage);
		//将查询到的商品存入值栈中
		ActionContext.getContext().getValueStack().set("pageBeanProduct", pageBeanProduct);
		return "findCategoryByCid";
	}
	
	/**
	 * 通过二级分类的id查找商品带分页
	 * @return
	 */
	public String findByCsid(){
		//根据二级分类查询所有商品带分页
		pageBean<Product> pageBeanProduct = productService.findProductByPageCsid(csid, currentPage);
		//将查询到的商品存入值栈
		ActionContext.getContext().getValueStack().set("pageBeanProduct", pageBeanProduct);
		return "findCategoryByCsid";
	}
}
