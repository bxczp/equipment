package com.bx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date 2016年3月26日 DateUtol.java
 * @author CZP
 * @parameter
 */
public class DateUtil {

	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		if (date != null) {
			return dateFormat.format(date);
		} else {
			return "";
		}
	}

	public static Date formatString(String str, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		if (str != null) {
			try {
				return dateFormat.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String getCurrentDate(){
		Date date=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(date);
	}

}
