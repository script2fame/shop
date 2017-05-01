package com.hungteshun.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hungteshun.shop.categorysecond.dao.CategorySecondDao;
import com.hungteshun.shop.categorysecond.vo.CategorySecond;
import com.hungteshun.shop.product.vo.Product;
import com.hungteshun.shop.utils.pageBean;

@Transactional
public class CategorySecondService {

	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	public pageBean<CategorySecond> findCategorySecondWithPage(
			Integer currentPage) {
		pageBean<CategorySecond> pageBeanCategorySecond = new pageBean<CategorySecond>();
		//设置当前页
		if(currentPage == null){
			currentPage = 1;
		}
		pageBeanCategorySecond.setCurrentPage(currentPage);
		//设置每页的记录数
		int limit = 8;
		pageBeanCategorySecond.setLimit(limit);
		//设置总的记录数
		int totalCount = 0;
		totalCount = categorySecondDao.findCountByCid();
		pageBeanCategorySecond.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		//totalPage = (int) Math.ceil(totalCount/limit);
		if(totalCount%limit==0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBeanCategorySecond.setTotalPage(totalPage);
		//显示的数据的集合
		int begin =(currentPage - 1)*limit;
		List<CategorySecond> categorySecondList = categorySecondDao.findByPageCid(begin,limit);
		pageBeanCategorySecond.setList(categorySecondList);
		return pageBeanCategorySecond;
	}
	
	/**
	 * 添加二级分类
	 * @param categorySecond
	 */
	public void addCategorySecond(CategorySecond categorySecond) {
		categorySecondDao.addCategorySecond(categorySecond);
	}
	
	/**
	 * 根据csid查找二级分类
	 * @param categorySecond
	 */
	public void deleteCategorySecond(CategorySecond categorySecond) {
		categorySecondDao.deleteCategorySecond(categorySecond);
	}

	public CategorySecond findCategorySecondByCsid(Integer csid) {
		return categorySecondDao.findCategorySecondByCsid(csid);
	}
	/**
	 * 修改二级分类
	 * @param categorySecond
	 */
	public void updateCategorySecond(CategorySecond categorySecond) {
		categorySecondDao.updateCategorySecond(categorySecond);
	}

	/**
	 * 查询所有的二级分类
	 * @return 
	 */
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
}
