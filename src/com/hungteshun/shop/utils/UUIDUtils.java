package com.hungteshun.shop.utils;

import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * @author hungteshun黄调聪
 *
 */
public class UUIDUtils {

	//生产随机的字符串
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
