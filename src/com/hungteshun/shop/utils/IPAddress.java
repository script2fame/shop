package com.hungteshun.shop.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取本机ip地址
 * @author hungteshun黄调聪
 *
 */
public class IPAddress {

	public static String getAddress(){
		String ipAddr = "";
		try {
			InetAddress ia = InetAddress.getLocalHost();
//			System.out.println(ia.getHostName());
			ipAddr = ia.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ipAddr;
	}
	
	public static void main(String[] args) {
		System.out.println(IPAddress.getAddress());
	}
}
