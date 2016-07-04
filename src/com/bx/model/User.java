package com.bx.model;
/**
 *@date 2016年3月24日
 * User.java
 *@author CZP
 *@parameter
 */
public class User {
	
//	主键值 类型推荐 为 Integer （默认值为null）
//	而 int 的默认值为 0 
	private Integer id;
	private String userName;
	private String password;
	private String trueName;
	//角色名 （管理员1 使用者2   维修者3 ）
	private String roleName;
	private Integer deptId;
	private String deptName;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
	
	
	
}
