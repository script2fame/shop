package com.hungteshun.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hungteshun.shop.product.vo.Product;
import com.hungteshun.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	/**
	 * 查找热门商品
	 * @return
	 */
	public List<Product> findHot() {
		//离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//查询条件
		criteria.add(Restrictions.eq("is_hot", 1));
		criteria.addOrder(Order.desc("pdate"));
		//分页查询
		List<Product> productList = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return productList;
	}

	/**
	 * 查找最新商品
	 * @return
	 */
	public List<Product> findNewest() {
		//离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//条件
		criteria.addOrder(Order.desc("pdate"));
		//分页查询
		List<Product> newestProductList = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return newestProductList;
	}

	/**
	 * 根据pid查找商品信息
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * 根据商品的一级分类ID查询商品个数
	 * @param cid
	 * @return
	 */
	public int findCountByCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> numList= this.getHibernateTemplate().find(hql, cid);
		if(numList!=null&&numList.size()>0){
			return numList.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 根据一级分类的ID查询所有的商品集合
	 * @param cid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		//select p.* from category c,categorysecond cs,product p where c.cid=cs.cid and cs.csid = p.csid and c.cid = 1;
		//select p.* from category c join categorysecond cs on c.cid = cs.cid join product p on p.csid = cs.csid and c.cid=1;
		//hql语句一：select p from Category c,CategorySecond sc,Product p where c.cid =cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
		//hql语句二：
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		//分页查询又一种写法
		List<Product> productList = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(productList!=null&&productList.size()>0){
			return productList;
		}
		return null;
	}
	/**
	 * 根据二级分类的csid查询所有商品个数
	 * @param csid
	 * @return
	 */
	public int findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> numList = this.getHibernateTemplate().find(hql, csid);
		if(numList!=null&&numList.size()>0){
			return numList.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 根据二级分类的csid和起始位置及查询个数去查询所有商品
	 * @param csid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> productList = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if (productList != null && productList.size() > 0) {
			return productList;
		}
		return null;
	}
	
	/**
	 * 查询所有商品数量
	 * @return
	 */
	public int findCountAll() {
		String hql = "select count(*) from Product";
		List<Long> numList = this.getHibernateTemplate().find(hql);
		if (numList != null && numList.size() > 0) {
			return numList.get(0).intValue();
		}
		return 0;
	}
	/**
	 * 查询所有商品
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findAll(int begin, int limit) {
		String hql ="from Product order by pdate desc";
		List<Product> productList = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if (productList != null && productList.size() > 0) {
			return productList;
		}
		return null;
	}

	/**
	 * 保存商品
	 * @param product
	 */
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	/*
	 * 删除商品
	 */
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	/**
	 * 修改商品信息
	 * @param product
	 */
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

	/**
	 * 按查询要求查询商品个数
	 * @param productType
	 * @return
	 */
	public int findCountBySearch(String productType) {
		String hql = "select count(*) from Product p where p.pname like ?";
		List<Long> numList = this.getHibernateTemplate().find(hql,"%"+productType+"%");
		if (numList != null && numList.size() > 0) {
			return numList.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 按查询要求查询所有的商品集合
	 * @param begin
	 * @param limit
	 * @param productType
	 * @return
	 */
	public List<Product> findBySearch(int begin, int limit, String productType) {
		String hql = "from Product p where p.pname like ? order by p.pdate desc";
		List<Product> productList = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{"%"+productType+"%"}, begin, limit));
		if (productList != null && productList.size() > 0) {
			return productList;
		}
		return null;
	}
}
