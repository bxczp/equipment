package com.bx.dao;

import java.util.List;

import com.bx.model.EquipmentType;
import com.bx.model.PageBean;

/**
 *@date 2016年3月25日
 * EquipmentTypeDao.java
 *@author CZP
 *@parameter
 */
public interface EquipmentTypeDao {
	
	public List<EquipmentType> getEquipmentTypeList(EquipmentType s_EquipmentType,PageBean pageBean);
	
	public int EquipmentTypeListCount(EquipmentType s_EquipmentType);
	
	public void add(EquipmentType EquipmentType);
	
	public void update(EquipmentType EquipmentType);
	
	public void delete(int id);
	
	public EquipmentType getEquipmentTypeById(int id);
	
}
