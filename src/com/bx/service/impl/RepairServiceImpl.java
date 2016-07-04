package com.bx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bx.dao.EquipmentDao;
import com.bx.dao.RepairDao;
import com.bx.model.Equipment;
import com.bx.model.PageBean;
import com.bx.model.Repair;
import com.bx.service.RepairService;

/**
 * @date 2016年3月26日 RepairSerivceImpl.java
 * @author CZP
 * @parameter
 */

@Service("repairService")
public class RepairServiceImpl implements RepairService {

	@Resource
	private RepairDao repairDao;
	
	@Resource
	protected EquipmentDao equipmentDao;
	

	@Override
	public List<Repair> getRepairList(Repair s_repair, PageBean pageBean) {
		return repairDao.getRepairList(s_repair, pageBean);
	}

	@Override
	public int getRepairListCount(Repair s_repair) {
		return repairDao.getRepairListCount(s_repair);
	}

	@Override
	public void updateRepair(int repairId, int equipmentId, String repairMan, boolean success) {
		Repair repair = new Repair();
		repair.setRepairMan(repairMan);
		Equipment equipment=equipmentDao.getEquipmentById(equipmentId);
		repair.setId(repairId);
		// 修理成功
		if (success) {
			repair.setState(1);
			equipment.setState(1);
		} else {//报废
			repair.setState(2);
			equipment.setState(3);
		}
		equipmentDao.update(equipment);
		repairDao.update(repair);
	}

	@Override
	public Repair getRepairById(int id) {
		return repairDao.getRepairById(id);
	}

}
