package com.bx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @date 2016年3月25日 PropertiesUtil.java
 * @author CZP
 * @parameter
 */
public class PropertiesUtil {

	public static String getValue(String key){
		Properties properties = new Properties();
		InputStream inputStream = new PropertiesUtil().getClass().getResourceAsStream("/equipment.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(PropertiesUtil.getValue("pageSize"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
