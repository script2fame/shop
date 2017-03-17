package com.hungteshun.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hungteshun.shop.product.dao.ProductDao;
import com.hungteshun.shop.product.vo.Product;
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
	
}
