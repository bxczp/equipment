package com.bx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bx.dao.EquipmentDao;
import com.bx.dao.EquipmentTypeDao;
import com.bx.dao.RepairDao;
import com.bx.model.Equipment;
import com.bx.model.EquipmentType;
import com.bx.model.PageBean;
import com.bx.model.Repair;
import com.bx.service.EquipmentTypeSerice;

/**
 *@date 2016年3月26日
 * EquipmentSerivceImpl.java
 *@author CZP
 *@parameter
 */
@Service("equipmentTypeService")
public class EquipmentTypeSerivceImpl implements EquipmentTypeSerice{
	
	@Resource
	private EquipmentTypeDao equipmentTypeDao;
	
	@Resource
	private RepairDao repairDao;


	@Override
	public List<EquipmentType> getEquipmentTypeList(EquipmentType s_equipmentType, PageBean pageBean) {
		return equipmentTypeDao.getEquipmentTypeList(s_equipmentType, pageBean);
	}

	@Override
	public int EquipmentTypeListCount(EquipmentType s_equipmentType) {
		return equipmentTypeDao.EquipmentTypeListCount(s_equipmentType);
	}

	@Override
	public void add(EquipmentType equipmentType) {
		equipmentTypeDao.add(equipmentType);
	}

	@Override
	public void update(EquipmentType equipmentType) {
		equipmentTypeDao.update(equipmentType);
	}

	@Override
	public void delete(int id) {
		equipmentTypeDao.delete(id);
	}

	@Override
	public EquipmentType getEquipmentTypeById(int id) {
		return equipmentTypeDao.getEquipmentTypeById(id);
	}


}
