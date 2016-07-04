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

import com.bx.dao.EquipmentDao;
import com.bx.model.EquipmentType;
import com.bx.model.PageBean;
import com.bx.service.EquipmentService;
import com.bx.service.EquipmentTypeSerice;
import com.bx.util.PageUtil;
import com.bx.util.PropertiesUtil;
import com.bx.util.ResponseUtil;
import com.bx.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * @date 2016年3月26日 EquipmentTypeController.java
 * @author CZP
 * @parameter
 */
@Controller
@RequestMapping("/equipmentType")
public class EquipmentTypeController {

	@Resource
	private EquipmentTypeSerice equipmentTypeService;

	@Resource
	private EquipmentService equipmentService;

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", required = false) String page, EquipmentType s_equipmentType,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("s_equipmentType", s_equipmentType);
		} else {
			s_equipmentType = (EquipmentType) session.getAttribute("s_equipmentType");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		List<EquipmentType> equipmentTypeList = equipmentTypeService.getEquipmentTypeList(s_equipmentType, pageBean);
		int totalNum = equipmentTypeService.EquipmentTypeListCount(s_equipmentType);
		String pageCode = PageUtil.getPagation(request.getContextPath() + "/equipmentType/list.do", totalNum,
				Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		modelAndView.addObject("equipmentTypeList", equipmentTypeList);
		modelAndView.addObject("mainPage", "equipmentType/list.jsp");
		modelAndView.addObject("pageCode", pageCode);
		modelAndView.addObject("modeName", "设备类型管理");
		modelAndView.setViewName("main");
		return modelAndView;
	}

	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value = "id", required = false) String id) {
		ModelAndView modelAndView = new ModelAndView();
		if (StringUtil.isNotEmpty(id)) {
			modelAndView.addObject("actionName", "设备类型添加");
			EquipmentType equipmentType = equipmentTypeService.getEquipmentTypeById(Integer.parseInt(id));
			modelAndView.addObject("equipmentType", equipmentType);
		} else {
			modelAndView.addObject("actionName", "设备类型更新");
		}
		modelAndView.setViewName("main");
		modelAndView.addObject("mainPage", "equipmentType/save.jsp");
		modelAndView.addObject("modeName", "设备类型管理");
		return modelAndView;
	}

	@RequestMapping("/save")
	public String save(EquipmentType equipmentType) {
		if (equipmentType.getId() == null) {
			equipmentTypeService.add(equipmentType);
		} else {
			equipmentTypeService.update(equipmentType);
		}
		return "redirect:/equipmentType/list.do";
	}

	@RequestMapping("/delete")
	public void delete(@RequestParam(value = "id", required = true) String id, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		if (equipmentService.existEquipmentWithTypeId(Integer.parseInt(id))) {
			jsonObject.put("error", "该设备类型下存在设备，不能删除");
		} else {
			equipmentTypeService.delete(Integer.parseInt(id));
			jsonObject.put("success", true);
		}
		try {
			ResponseUtil.write(response, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
