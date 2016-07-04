package com.bx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bx.dao.DepartmentDao;
import com.bx.model.Department;
import com.bx.model.PageBean;
import com.bx.service.DepartmentService;

/**
 *@date 2016年3月25日
 * DepartmentServiceImpl.java
 *@author CZP
 *@parameter
 */

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	
	@Resource
	private DepartmentDao departmentDao;

	@Override
	public List<Department> getDepartmentList(Department s_department, PageBean pageBean) {
		return departmentDao.getDepartmentList(s_department, pageBean);
	}

	@Override
	public int departmentListCount(Department s_department) {
		return departmentDao.departmentListCount(s_department);
	}

	@Override
	public void add(Department department) {
		departmentDao.add(department);
	}

	@Override
	public void update(Department department) {
		departmentDao.update(department);
	}

	@Override
	public void delete(int id) {
		departmentDao.delete(id);
	}

	@Override
	public Department getDepartmentById(int id) {
		return departmentDao.getDepartmentById(id);
	}

}
