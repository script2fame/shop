package com.hungteshun.shop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {

	public static Date formatDate(Date date) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_str = simpleDateFormat.format(new Date());
		Date date_format = simpleDateFormat.parse(date_str);
		return date_format;
	}
}
