package com.hungteshun.shop.product.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.hungteshun.shop.product.dao.ProductDao;
import com.hungteshun.shop.product.vo.Product;
import com.hungteshun.shop.utils.FormatDate;
import com.hungteshun.shop.utils.pageBean;

@Transactional
public class ProductService {

	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * 查找热门商品
	 * @return
	 */
	public List<Product> findHot() {
		return productDao.findHot();
	}
	/**
	 * 查找最新商品
	 * @return
	 */
	public List<Product> findNewest() {
		return productDao.findNewest();
	}

	/**
	 * 根据pid查找商品信息
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	/**
	 * 根据一级分类的cid查询对应的商品
	 * @param cid
	 * @param currentPage
	 * @return
	 */
	public pageBean<Product> findProductByPageCid(Integer cid, int currentPage) {
		pageBean<Product> pageBean = new pageBean<Product>();
		//设置当前页:
		pageBean.setCurrentPage(currentPage);
		//设置每页的记录数
		int limit = 12;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount = 0;
		totalCount = productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		//totalPage = (int) Math.ceil(totalCount/limit);
		if(totalCount%limit==0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//显示的数据的集合
		int begin =(currentPage - 1)*limit;
		List<Product> pList = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(pList);
		return pageBean;
	}

	/**
	 * 根据二级分类的csid查询商品带分页
	 * @param csid
	 * @param currentPage
	 * @return
	 */
	public pageBean<Product> findProductByPageCsid(Integer csid, int currentPage) {
		pageBean<Product> pageBean = new pageBean<Product>();
		//设置当前页:
		pageBean.setCurrentPage(currentPage);
		//设置每页的记录数
		int limit = 12;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount = 0;
		totalCount = productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		//totalPage = (int) Math.ceil(totalCount/limit);
		if(totalCount%limit==0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//显示的数据的集合
		int begin =(currentPage - 1)*limit;
		List<Product> pList = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(pList);
		return pageBean;
	}
	/**
	 * 查询所有商品带分页
	 * @param currentPage
	 * @return
	 */
	public pageBean<Product> findAllProductWithPage(Integer currentPage) {
		pageBean<Product> pageBean = new pageBean<Product>();
		//设置当前页:
		pageBean.setCurrentPage(currentPage);
		//设置每页的记录数
		int limit = 12;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount = 0;
		totalCount = productDao.findCountAll();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		//totalPage = (int) Math.ceil(totalCount/limit);
		if(totalCount%limit==0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//显示的数据的集合
		int begin =(currentPage - 1)*limit;
		List<Product> pList = productDao.findAll(begin,limit);
		pageBean.setList(pList);
 		return pageBean;
	}
	
	/**
	 * 保存图片
	 * @param product
	 * @param upload
	 * @param uploadFileName 
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void save(Product product, File upload, String uploadFileName) throws IOException, ParseException {
		Date date = FormatDate.formatDate(new Date());
		product.setPdate(date);
		if(upload != null){
			//获得文件上传后要保存到服务器的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile = new File(realPath + "//" +uploadFileName);
			//拷贝文件，完成保存到数据库
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
			productDao.save(product);
		}
		
	}

	/**
	 * 删除商品，同时删除商品的图片
	 * @param product
	 */
	public void delete(Product product) {
		String path = product.getImage();
		if(path != null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(realPath);
			file.delete();
		}
		productDao.delete(product);
	}

	public void update(Product product, File upload, String uploadFileName) throws ParseException, IOException {
		Date date = FormatDate.formatDate(new Date());
		product.setPdate(date);
		if(upload != null){
			//获得文件上传后要保存到服务器的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile = new File(realPath + "//" +uploadFileName);
			//拷贝文件，完成保存到数据库
			FileUtils.copyFile(upload, diskFile);
			//删除原来的图片
			String path = product.getImage();
			String old_realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(old_realPath);
			if(file.exists()){
				file.delete();
			}
			product.setImage("products/"+uploadFileName);
		}
		productDao.update(product);
	}
	
}
