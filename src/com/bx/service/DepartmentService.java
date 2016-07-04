package com.bx.service;

import java.util.List;

import com.bx.model.Department;
import com.bx.model.PageBean;

/**
 * @date 2016年3月25日 DepartmentService.java
 * @author CZP
 * @parameter
 */
public interface DepartmentService {

	public List<Department> getDepartmentList(Department s_department, PageBean pageBean);

	public int departmentListCount(Department s_department);

	public void add(Department department);

	public void update(Department department);

	public void delete(int id);

	public Department getDepartmentById(int id);

}
