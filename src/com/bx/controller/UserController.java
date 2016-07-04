package com.bx.controller;

import java.util.ArrayList;
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
import com.bx.model.User;
import com.bx.service.DepartmentService;
import com.bx.service.UserService;
import com.bx.util.PageUtil;
import com.bx.util.PropertiesUtil;
import com.bx.util.ResponseUtil;
import com.bx.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * @date 2016年3月24日 UserController.java
 * @author CZP
 * @parameter
 */

@Controller
// springMVC 的请求 地址方式 对应的 整个controller类
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private DepartmentService departmentService;

	// 对应的是这个方法的请求地址
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request) {
		User currentUser = userService.login(user);
		if (currentUser.getId() == null) {
			request.setAttribute("user", user);
			request.setAttribute("errorMsg", "用户名或密码错误");
			// return到spring-Mvc的视图解析器
			// 返回的字符串 代表一个 视图名称
			// 与modelAndView.setViewName("...")功能相同
			// 不要 加 .jsp 因为视图解析器 中 配置 有后缀
			return "login";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", currentUser);
			// 重定向 到jsp页面
			// 这里一定要加 .jsp
			/**
			 * 请求路径前的 / 表示 服务器的根路径
			 * 只要 在路径前 加 / 就会从根路径 开始 查找匹配
			 *  推荐 加/
			 */
			return "redirect:/main.jsp";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:/login.jsp";
	}

//	@RequestParam 相当于req.getParamnet() 必须是 字符串的类型的参数
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			User s_user) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("s_user", s_user);
		}else{
			s_user=(User) session.getAttribute("s_user");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		List<User> userList = userService.getUserList(s_user, pageBean);
		int total = userService.getUserListCount(s_user);
		String pageCode = PageUtil.getPagation("", total, Integer.parseInt(page),
				Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		modelAndView.addObject("pageCode", pageCode);
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("modeName", "用户管理");
		modelAndView.addObject("mainPage", "user/list.jsp");
		// 设置 所需要 跳转的页面
		modelAndView.setViewName("main");
		return modelAndView;
	}
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="id")String id,HttpServletResponse response){
		JSONObject jsonObject=new JSONObject();
		userService.delete(Integer.parseInt(id));
		jsonObject.put("success", true);
		try {
			ResponseUtil.write(response, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value="id",required=false)String id){
		ModelAndView modelAndView=new ModelAndView();
		if(StringUtil.isNotEmpty(id)){
			User u=new User();
			u=userService.getUserById(Integer.parseInt(id));
			modelAndView.addObject("actionName", "用户更新");
			modelAndView.addObject("user", u);
		}else{
			modelAndView.addObject("actionName", "用户添加");
		}
		List<Department> departmentList=new ArrayList<>();
		departmentList=departmentService.getDepartmentList(null, null);
		modelAndView.addObject("departmentList", departmentList);
		modelAndView.addObject("mainPage", "user/save.jsp");
		modelAndView.addObject("modeName", "用户管理");
		modelAndView.setViewName("main");
		return modelAndView;
	}
	
	@RequestMapping("/save")
	public String save(User user){
		if(user.getId()!=null){
			userService.update(user);
		}else{
			userService.add(user);
		}
		// 设置 所需要 跳转的页面
		// 不能 设置 要请求的 地址
		// modelAndView.setViewName("/user/list.do");
		// 重定向 到 list.do 请求
		return "redirect:/user/list.do";
	}

}
