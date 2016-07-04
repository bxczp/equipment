package com.bx.util;
/**
 *@date 2016年3月24日
 * StringUtil.java
 *@author CZP
 *@parameter
 */
public class StringUtil {
	
	
	public static boolean isNotEmpty(String str){
		if(str!=null&&!"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	

}
