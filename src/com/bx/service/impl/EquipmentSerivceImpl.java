package com.bx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bx.dao.EquipmentDao;
import com.bx.dao.RepairDao;
import com.bx.model.Equipment;
import com.bx.model.PageBean;
import com.bx.model.Repair;
import com.bx.service.EquipmentService;

/**
 *@date 2016年3月26日
 * EquipmentSerivceImpl.java
 *@author CZP
 *@parameter
 */

@Service("equipmentService")
public class EquipmentSerivceImpl implements EquipmentService {
	
	@Resource
	private EquipmentDao equipmentDao;
	
	@Resource
	private RepairDao repairDao;

	@Override
	public List<Equipment> getEquipmentList(Equipment s_equipment, PageBean pageBean) {
		return equipmentDao.getEquipmentList(s_equipment, pageBean);
	}

	@Override
	public int getListCount(Equipment s_equipment) {
		return equipmentDao.getListCount(s_equipment);
	}

	@Override
	public void add(Equipment equipment) {
		equipmentDao.add(equipment);
	}

	@Override
	public void update(Equipment equipment) {
		equipmentDao.update(equipment);
	}
	

	@Override
	public void addRepair(int equipmentId, String userMan) {
		Repair repair=new Repair();
		repair.setEquipmentId(equipmentId);
		repair.setUserMan(userMan);
		repairDao.add(repair);
		Equipment equipment=equipmentDao.getEquipmentById(equipmentId);
		//维修状态
		equipment.setState(2);
		equipmentDao.update(equipment);
	}
	
	@Override
	public void delete(int id) {
		equipmentDao.delete(id);
	}

	@Override
	public Equipment getEquipmentById(int id) {
		return equipmentDao.getEquipmentById(id);
	}

	@Override
	public boolean existEquipmentWithTypeId(int typeId) {
		return equipmentDao.existEquipmentWithTypeId(typeId);
	}

}
