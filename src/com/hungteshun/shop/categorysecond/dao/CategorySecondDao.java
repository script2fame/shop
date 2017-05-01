package com.hungteshun.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hungteshun.shop.categorysecond.vo.CategorySecond;
import com.hungteshun.shop.utils.PageHibernateCallback;
import com.hungteshun.shop.utils.pageBean;

public class CategorySecondDao extends HibernateDaoSupport {

	public pageBean<CategorySecond> findCategorySecondWithPage(
			Integer currentPage) {
		return null;
	}
	//查询所有的二级分类的数量
	public int findCountByCid() {
		String hql = "select count(*) from CategorySecond";
		List<Long> countList = this.getHibernateTemplate().find(hql);
		if(countList!= null && countList.size()>0){
			return countList.get(0).intValue();
		}
		return 0;
	}
	//按分页要求查询所需的数据
	public List<CategorySecond> findByPageCid(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> categorySecondList = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		if(categorySecondList != null && categorySecondList.size() > 0){
			return categorySecondList;
		}
		return null;
	}
	//添加二级分类
	public void addCategorySecond(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}
	
	/**
	 * 删除二级分类
	 * @param categorySecond
	 */
	public void deleteCategorySecond(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}
	/**
	 * 根据二级分类查找对应的二级分类
	 * @param csid
	 * @return
	 */
	public CategorySecond findCategorySecondByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}
	/**
	 * 修改二级分类
	 * @param categorySecond
	 * @return
	 */
	public void updateCategorySecond(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}
	/**
	 * 查询所有的二级分类
	 * @return
	 */
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		return this.getHibernateTemplate().find(hql);
		
	}	

}
