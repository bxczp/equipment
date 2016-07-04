package com.bx.dao;

import java.util.List;

import com.bx.model.PageBean;
import com.bx.model.Repair;

/**
 *@date 2016年3月26日
 * RepairDao.java
 *@author CZP
 *@parameter
 */
public interface RepairDao {
	
	public void add(Repair repair);
	
	public List<Repair> getRepairList(Repair s_repair,PageBean pageBean);
	
	public int getRepairListCount(Repair s_repair);
	
	public void update(Repair repair);
	
	public Repair getRepairById(int id);
	
}
