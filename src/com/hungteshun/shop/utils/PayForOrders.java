package com.hungteshun.shop.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PayForOrders {

	private static String encodingCharset = "UTF-8";
	
	/**
	 * 使用易宝的算法和秘钥生成hmac
	 * @param p0_Cmd
	 * @param p1_MerId
	 * @param p2_Order
	 * @param p3_Amt
	 * @param p4_Cur
	 * @param p5_Pid
	 * @param p6_Pcat
	 * @param p7_Pdesc
	 * @param p8_Url
	 * @param p9_SAF
	 * @param pa_MP
	 * @param pd_FrpId
	 * @param pr_NeedResponse
	 * @param keyValue
	 * @return
	 */
	public static String generateHmac(String p0_Cmd,String p1_MerId,
			String p2_Order, String p3_Amt, String p4_Cur,String p5_Pid, String p6_Pcat,
			String p7_Pdesc,String p8_Url, String p9_SAF,String pa_MP,String pd_FrpId,
			String pr_NeedResponse,String keyValue){
		StringBuilder sb= new StringBuilder();
		sb.append(p0_Cmd);
		sb.append(p1_MerId);
		sb.append(p2_Order);
		sb.append(p3_Amt);
		sb.append(p4_Cur);
		sb.append(p5_Pid);
		sb.append(p6_Pcat);
		sb.append(p7_Pdesc);
		sb.append(p8_Url);
		sb.append(p9_SAF);
		sb.append(pa_MP);
		sb.append(pd_FrpId);
		sb.append(pr_NeedResponse);
		return PayForOrders.hmacSign(sb.toString(), keyValue);
	}
	public static String hmacSign(String aValue, String aKey) {
		byte k_ipad[] = new byte[64];
		byte k_opad[] = new byte[64];
		byte keyb[];
		byte value[];
		try {
			keyb = aKey.getBytes(encodingCharset);
			value = aValue.getBytes(encodingCharset);
		} catch (UnsupportedEncodingException e) {
			keyb = aKey.getBytes();
			value = aValue.getBytes();
		}

		Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
		Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
		for (int i = 0; i < keyb.length; i++) {
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);
			k_opad[i] = (byte) (keyb[i] ^ 0x5c);
		}

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {

			return null;
		}
		md.update(k_ipad);
		md.update(value);
		byte dg[] = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 16);
		dg = md.digest();
		return toHex(dg);
	}
	public static String toHex(byte input[]) {
		if (input == null)
			return null;
		StringBuffer output = new StringBuffer(input.length * 2);
		for (int i = 0; i < input.length; i++) {
			int current = input[i] & 0xff;
			if (current < 16)
				output.append("0");
			output.append(Integer.toString(current, 16));
		}

		return output.toString();
	}
}
