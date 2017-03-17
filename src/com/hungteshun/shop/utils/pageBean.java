package com.hungteshun.shop.utils;

import java.util.List;

/**
 * 分页对象的封装
 * @author hungteshun黄调聪
 * @param <T>
 *
 */
public class pageBean<T> {

	private Integer currentPage;//当前页数
	private Integer totalCount;//总的记录数
	private Integer totalPage;//总的页数
	private Integer limit;//每一页显示的记录数
	private List<T> list;//数据的集合
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}

