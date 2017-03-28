package com.hungteshun.shop.admin.vo;

/**
 * 后台管理员信息表
 * @author hungteshun黄调聪
 *
 */
public class Admin {

	private Integer aid;
	private String username;
	private String password;
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
