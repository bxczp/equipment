package com.bx.service;

import java.util.List;

import com.bx.model.EquipmentType;
import com.bx.model.PageBean;

/**
 *@date 2016年3月25日
 * EquipmentTypeDao.java
 *@author CZP
 *@parameter
 */
public interface EquipmentTypeSerice {
	
	public List<EquipmentType> getEquipmentTypeList(EquipmentType s_equipmentType,PageBean pageBean);
	
	public int EquipmentTypeListCount(EquipmentType s_equipmentType);
	
	public void add(EquipmentType equipmentType);
	
	public void update(EquipmentType equipmentType);
	
	public void delete(int id);
	
	public EquipmentType getEquipmentTypeById(int id);
	
	
}
