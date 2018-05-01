package com.hm.utils;

import java.math.BigDecimal;
import java.util.Arrays;


public class BaseUtil {
	
	/**
	 * 判断数组中是否含有该字符串
	 * @param arr
	 * @param str
	 * @return
	 */
	public static boolean useList(String[] arr, String str) {
	    return Arrays.asList(arr).contains(str);
	}
	
	/**
	 * 判断数组arrA中是否包含数组arrB中的所有元素(如果包含返回非公共数据)
	 * @param arr
	 * @param arrB
	 * @return
	 */
	public static boolean contains(String[] arrA, String[] arrB) {
		boolean obj = true;
		for (String str : arrB) {
			if(!Arrays.asList(arrA).contains(str)){
				obj = false;
			}
		}
	    return obj;
	}
	
	/**
	 * 判断数组arrA中是否包含数组arrB中的所有元素(如果包含返回非公共数据)
	 * @param arr
	 * @param arrB
	 * @return
	 */
	public static String[] difference(String[] arrA, String[] arrB) {
		if(contains(arrA, arrB)){
			String data = "";
			for (String str : arrA) {
				if(!Arrays.asList(arrB).contains(str)){
					data += str+",";
				}
			}
			if(!StringUtil.isEmpty(data))
			return data.substring(0, data.length()-1).split(",");
		}
		return null;
	}
	
	public static String[] defferenceArray(String[] arrA, String[] arrB) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arrA.length;i++) {
			boolean flag = false;
			for(int n=0;n<arrB.length;n++) {
				if(arrA[i].equals(arrB[n])) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				sb.append(arrA[i]+",");
			}
		}
		String str = sb.toString().substring(0, sb.length()-1);
		return str.split(",");
	}
	
	public static String ArryToString(String[] arrA) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arrA.length;i++) {
			if(StringUtil.isEmpty(arrA[i])) {
				sb.append(",");
			}else {
				sb.append(arrA[i]+",");
			}
			
		}
		return sb.toString().substring(0, sb.length()-1);
	}
	
	public static Double round(double d) {
		BigDecimal b = new BigDecimal(new Double(d).toString());
		double f = b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f;
	}
	
	public static String getPrintSize(long size) {
		//如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
		if (size < 1024) {
			return String.valueOf(size) + "B";
		}else {
		    size = size / 1024;
		}
		//如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
		//因为还没有到达要使用另一个单位的时候
		//接下去以此类推
		if(size < 1024) {
		    String str = String.valueOf((double)size/1024);
			str = str.substring(0,str.indexOf(".")+3);
			return str + "MB";
			//return String.valueOf(size) + "KB";
		}else {
		    size = size / 1024;
		}
		if(size < 1024) {
			//因为如果以MB为单位的话，要保留最后1位小数，
		    //因此，把此数乘以100之后再取余
		    size = size * 100;
		    return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
		}else {
		    //否则如果要以GB为单位的，先除于1024再作同样的处理
		    size = size * 100 / 1024;
		    return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
		}
	}
	
}
