package com.bx.model;

import java.util.Date;

/**
 *@date 2016年3月24日
 * Repair.java
 *@author CZP
 *@parameter
 */
public class Repair {
	
	private Integer Id;
	private Integer equipmentId;
	private String equipmentName;
	//设备编号
	private Integer equipmentNo;
	//报修人
	private String userMan;
	//维修人
	private String repairMan;
	private Date repairTime;
	private Date finishTime;
	//维修后的状态 1 维修成功 2 报废
	private Integer state;
	//报修时的状态 1 未处理 2 处理完成
	private Integer finishState;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public Integer getEquipmentNo() {
		return equipmentNo;
	}
	public void setEquipmentNo(Integer equipmentNo) {
		this.equipmentNo = equipmentNo;
	}
	public String getUserMan() {
		return userMan;
	}
	public void setUserMan(String userMan) {
		this.userMan = userMan;
	}
	public String getRepairMan() {
		return repairMan;
	}
	public void setRepairMan(String repairMan) {
		this.repairMan = repairMan;
	}
	public Date getRepairTime() {
		return repairTime;
	}
	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getFinishState() {
		return finishState;
	}
	public void setFinishState(Integer finishState) {
		this.finishState = finishState;
	}

}
