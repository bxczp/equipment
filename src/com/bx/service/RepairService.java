package com.bx.service;

import java.util.List;

import com.bx.model.PageBean;
import com.bx.model.Repair;

/**
 * @date 2016年3月26日 RepairService.java
 * @author CZP
 * @parameter
 */
public interface RepairService {

	public List<Repair> getRepairList(Repair s_repair, PageBean pageBean);

	public int getRepairListCount(Repair s_repair);
	
	public void updateRepair(int repairId,int equipmentId,String repairMan,boolean success);
	
	public Repair getRepairById(int id);
	
}
