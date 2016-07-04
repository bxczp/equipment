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

import com.bx.model.Equipment;
import com.bx.model.EquipmentType;
import com.bx.model.PageBean;
import com.bx.model.Repair;
import com.bx.model.User;
import com.bx.service.EquipmentService;
import com.bx.service.EquipmentTypeSerice;
import com.bx.service.RepairService;
import com.bx.util.PageUtil;
import com.bx.util.PropertiesUtil;
import com.bx.util.ResponseUtil;
import com.bx.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * @date 2016年3月26日 EquipmentController.java
 * @author CZP
 * @parameter
 */
@Controller
@RequestMapping("/equipment")
public class EquipmentController {

	@Resource
	private EquipmentService equipmentService;

	@Resource
	private EquipmentTypeSerice equipmentTypeService;

	@Resource
	private RepairService repairService;

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			Equipment s_equipment) {
		ModelAndView modelAndView = new ModelAndView();
		List<Equipment> equipmentList = new ArrayList<>();
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("s_equipment", s_equipment);
		} else {
			s_equipment = (Equipment) session.getAttribute("s_equipment");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		equipmentList = equipmentService.getEquipmentList(s_equipment, pageBean);
		int total = equipmentService.getListCount(s_equipment);
		String pageCode = PageUtil.getPagation(request.getContextPath() + "/equipment/list.do", total,
				Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		modelAndView.addObject("equipmentList", equipmentList);
		modelAndView.addObject("pageCode", pageCode);
		modelAndView.addObject("modeName", "设备管理");
		// 也可以
		// modelAndView.addObject("mainPage", "/equipment/list.jsp");
		modelAndView.addObject("mainPage", "equipment/list.jsp");
		modelAndView.setViewName("main");
		return modelAndView;
	}

	// 使用设备列表
	@RequestMapping("/userList")
	public ModelAndView userList(@RequestParam(value = "page", required = false) String page, Equipment s_equipment,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("s_equipment", s_equipment);
		} else {
			s_equipment = (Equipment) session.getAttribute("s_equipment");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		List<Equipment> equipmentList = new ArrayList<>();
		equipmentList = equipmentService.getEquipmentList(s_equipment, pageBean);
		modelAndView.addObject("equipmentList", equipmentList);
		int total = equipmentService.getListCount(s_equipment);
		String pageCode = PageUtil.getPagation(request.getContextPath() + "/equipment/userList.do", total,
				Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		modelAndView.addObject("mainPage", "equipment/userList.jsp");
		modelAndView.addObject("pageCode", pageCode);
		modelAndView.addObject("modeName", "使用设备管理");
		modelAndView.setViewName("main");
		return modelAndView;
	}

	@RequestMapping("/repair")
	public void repair(@RequestParam(value = "id", required = true) String id, HttpSession session,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		User userMan = (User) session.getAttribute("currentUser");
		equipmentService.addRepair(Integer.parseInt(id), userMan.getUserName());
		jsonObject.put("success", true);
		try {
			ResponseUtil.write(response, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/delete")
	public void delete(@RequestParam(required = true, value = "id") String id, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		equipmentService.delete(Integer.parseInt(id));
		jsonObject.put("success", true);
		try {
			ResponseUtil.write(response, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value = "id", required = false) String id, Equipment equipment) {
		ModelAndView modelAndView = new ModelAndView();
		if (StringUtil.isNotEmpty(id)) {
			modelAndView.addObject("actionName", "设备更新");
			equipment = equipmentService.getEquipmentById(Integer.parseInt(id));
			modelAndView.addObject("equipment", equipment);
		} else {
			modelAndView.addObject("actionName", "设备添加");
		}
		List<EquipmentType> equipmentTypes = equipmentTypeService.getEquipmentTypeList(null, null);
		modelAndView.addObject("equipmentTypeList", equipmentTypes);
		modelAndView.addObject("mainPage", "equipment/save.jsp");
		modelAndView.setViewName("main");
		return modelAndView;
	}

	@RequestMapping("/save")
	public String save(Equipment equipment) {
		if (equipment.getId() != null) {
			equipmentService.update(equipment);
		} else {
			equipmentService.add(equipment);
		}
		return "redirect:/equipment/list.do";
	}

	@RequestMapping("/repairList")
	public ModelAndView repairList(@RequestParam(value = "page", required = false) String page,
			HttpServletRequest request, Repair s_repair) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("s_repair", s_repair);
		} else {
			s_repair = (Repair) session.getAttribute("s_repair");
		}
		// 查询 未维修完的记录
		s_repair.setFinishState(1);
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		List<Repair> repairList = new ArrayList<>();
		repairList = repairService.getRepairList(s_repair, pageBean);
		int total = repairService.getRepairListCount(s_repair);
		String pageCode = PageUtil.getPagation("", total, Integer.parseInt(page),
				Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		modelAndView.addObject("repairList", repairList);
		modelAndView.addObject("mainPage", "equipment/repairList.jsp");
		modelAndView.addObject("modeName", "维修记录");
		modelAndView.addObject("pageCode", pageCode);
		modelAndView.setViewName("main");
		return modelAndView;
	}

	@RequestMapping("/repairHistory")
	public ModelAndView repairHistoryList(@RequestParam(value = "page", required = false) String page,
			HttpServletRequest request, Repair s_repair) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("s_repair", s_repair);
		} else {
			s_repair = (Repair) session.getAttribute("s_repair");
		}
		// 查询 未维修完的记录
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		List<Repair> repairList = new ArrayList<>();
		repairList = repairService.getRepairList(s_repair, pageBean);
		int total = repairService.getRepairListCount(s_repair);
		String pageCode = PageUtil.getPagation("", total, Integer.parseInt(page),
				Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		modelAndView.addObject("repairList", repairList);
		modelAndView.addObject("mainPage", "equipment/repairHistory.jsp");
		modelAndView.addObject("modeName", "维修历史");
		modelAndView.addObject("pageCode", pageCode);
		modelAndView.setViewName("main");
		return modelAndView;
	}

	@RequestMapping("/finishRepair")
	//请求参数可以是boolean类型
	public void finiishRepair(@RequestParam(value = "id") String id, @RequestParam(value = "repairId") String repairId,
			boolean success, HttpServletResponse response,HttpSession session) {
		Repair repair = repairService.getRepairById(Integer.parseInt(repairId));
		repair.setRepairMan(((User)session.getAttribute("currentUser")).getUserName());
		JSONObject jsonObject = new JSONObject();
		repairService.updateRepair(repair.getId(), repair.getEquipmentId(), repair.getRepairMan(), success);
		jsonObject.put("success", true);
		try {
			ResponseUtil.write(response, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
