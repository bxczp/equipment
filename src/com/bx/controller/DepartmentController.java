package com.bx.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bx.model.Department;
import com.bx.model.PageBean;
import com.bx.service.DepartmentService;
import com.bx.service.UserService;
import com.bx.util.PageUtil;
import com.bx.util.PropertiesUtil;
import com.bx.util.ResponseUtil;
import com.bx.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * @date 2016年3月25日 DepartmentController.java
 * @author CZP
 * @parameter
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Resource
	private DepartmentService departmentService;
	@Resource
	private UserService userService;

	// @RenderParam是修饰参数的 repired默认是true
	// 在SpringMVC后台控制层获取参数的方式主要有两种，
	// 一种是request.getParameter("name")，另外一种是用注解@RequestParam直接获取
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", required = false) String page, Department s_department,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("s_department", s_department);
		} else {
			s_department = (Department) session.getAttribute("s_department");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		int count = departmentService.departmentListCount(s_department);
		List<Department> departmentList = departmentService.getDepartmentList(s_department, pageBean);
		String pageCode = PageUtil.getPagation(request.getContextPath() + "/department/list.do", count,
				Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		// 把要传递的参数 加到modelAndView
		modelAndView.addObject("pageCode", pageCode);
		modelAndView.addObject("departmentList", departmentList);
		modelAndView.addObject("modeName", "部门管理");
		modelAndView.addObject("mainPage", "department/list.jsp");
		// 设置 所需要 跳转的页面
		modelAndView.setViewName("main");
		return modelAndView;
	}

	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value = "id", required = false) String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("mainPage", "department/save.jsp");
		modelAndView.setViewName("main");
		modelAndView.addObject("modeName", "部门管理");
		if (StringUtil.isEmpty(id)) {
			modelAndView.addObject("actionName", "部门添加");
		} else {
			modelAndView.addObject("actionName", "部门更新");
			Department department = departmentService.getDepartmentById(Integer.parseInt(id));
			modelAndView.addObject("department", department);
		}
		return modelAndView;
	}

	@RequestMapping("/save")
	public String save(Department department) {
		if (department.getId() == null) {
			departmentService.add(department);
		} else {
			departmentService.update(department);
		}
		// 重定向到 mvc请求
		return "redirect:/department/list.do";
	}

	@RequestMapping("/delete")
	public void delete(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		if (userService.existUserWithDeptId(Integer.parseInt(id))) {
			jsonObject.put("error", "该部门下存在用户，不能删除");
		} else {
			departmentService.delete(Integer.parseInt(id));
			jsonObject.put("success", true);
		}
		try {
			ResponseUtil.write(response, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
