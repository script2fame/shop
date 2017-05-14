package com.hungteshun.shop.admin.action;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.hungteshun.shop.categorysecond.service.CategorySecondService;
import com.hungteshun.shop.categorysecond.vo.CategorySecond;
import com.hungteshun.shop.product.service.ProductService;
import com.hungteshun.shop.product.vo.Product;
import com.hungteshun.shop.utils.pageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理Action
 * 
 * @author hungteshun黄调聪
 * 
 */
public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {

	Product product = new Product();

	private ProductService productService;
	
	private Integer currentPage;

	private CategorySecondService categorySecondService;
	
	private File upload;//上传的文件
	private String uploadFileName;//上传的文件名
	private String uploadContextType;//上传的文件的MIME的类型
	private String productType;
	
	public Product getModel() {
		return product;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	/**
	 * 生产setter方法，在前台取出来判断是否有传入查询条件
	 * @param productType
	 */
	
	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductType() {
		return productType;
	}

	public String findAllProduct() {
		pageBean<Product> product_pageBean = productService.findAllProductWithPage(currentPage);
		ActionContext.getContext().getValueStack().set("product_pageBean", product_pageBean);
		return "findAllProduct";
	}
	/**
	 * 跳转到添加商品的页面
	 * @return
	 */
	public String addProduct_page(){
		/**
		 * 查询所有的二级分类，然后跳转页面
		 */
		List<CategorySecond> categorySecondList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("categorySecondList", categorySecondList);
		return "addProduct_page";
	}
	/**
	 * 添加商品信息
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public String addProduct_do() throws IOException, ParseException{
		productService.save(product,upload,uploadFileName);
		return "addProduct_do";
	}
	public String remove_do(){
		product = productService.findByPid(product.getPid());
		//删除商品，同时删除图片
		productService.delete(product);
		return "remove_do";
	}
	
	/**
	 * 跳转到修改商品的页面
	 * @return
	 */
	public String edit_page(){
		product = productService.findByPid(product.getPid());
		//找到所有的二级分类
		List<CategorySecond> categorySecondList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("categorySecondList", categorySecondList);
		return "edit_page";
	}
	/**
	 * 修改商品信息
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public String updateProduct_do() throws ParseException, IOException{
		productService.update(product,upload,uploadFileName);
		return "updateProduct_do";
	}
	/**
	 * 条件查询
	 * @return
	 */
	public String findBySearch(){
		if(productType != null){
			pageBean<Product> product_pageBean = productService.findProduceBySearch(currentPage, productType);
			ActionContext.getContext().getValueStack().set("product_pageBean", product_pageBean);
		}else{
			pageBean<Product> product_pageBean = productService.findAllProductWithPage(currentPage);
			ActionContext.getContext().getValueStack().set("product_pageBean", product_pageBean);
		}
		return "findAllProduct";
	}
}
