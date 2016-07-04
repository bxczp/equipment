package com.bx.model;
/**
 *@date 2016年3月24日
 * EquipmentType.java
 *@author CZP
 *@parameter
 */
public class EquipmentType {
	
	private Integer id;
	private String typeName;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	

}
