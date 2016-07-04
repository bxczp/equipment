package com.bx.dao;

import java.util.List;

import com.bx.model.Equipment;
import com.bx.model.EquipmentType;
import com.bx.model.PageBean;
import com.bx.model.Repair;

/**
 *@date 2016年3月26日
 * Equipment.java
 *@author CZP
 *@parameter
 */
public interface EquipmentDao {
	
	public List<Equipment> getEquipmentList(Equipment s_equipment,PageBean pageBean);
	
	public int getListCount(Equipment s_equipment);
	
	public void add(Equipment equipment);
	
	public void update(Equipment equipment);
	
	public void delete(int id);
	
	public Equipment getEquipmentById(int id);
	
	public boolean existEquipmentWithTypeId(int typeId);
	
	
}
