package com.bx.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @date 2016年3月25日 ResponseUtil.java
 * @author CZP
 * @parameter
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response, Object o) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(o.toString());
		writer.flush();
		writer.close();
	}

}
