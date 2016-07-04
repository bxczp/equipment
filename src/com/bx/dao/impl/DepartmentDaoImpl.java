package com.bx.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.bx.dao.DepartmentDao;
import com.bx.model.Department;
import com.bx.model.PageBean;
import com.bx.util.StringUtil;

/**
 * @date 2016年3月25日 DepartmentDaoImpl.java
 * @author CZP
 * @parameter
 */
@Repository("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Department> getDepartmentList(Department s_department, PageBean pageBean) {
		StringBuffer sql = new StringBuffer("select * from t_department ");
		final List<Department> departmentList = new ArrayList<>();
		if (s_department != null) {
			if (StringUtil.isNotEmpty(s_department.getDeptName())) {
				sql.append(" and deptName like '%" + s_department.getDeptName() + "%'");
			}
		}
		if (pageBean != null) {
			sql.append("limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		jdbcTemplate.query(sql.toString().replaceFirst("and", "where"), new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Department department = new Department();
				department.setDeptName(rs.getString("deptName"));
				department.setId(rs.getInt("id"));
				department.setRemark(rs.getString("remark"));
				departmentList.add(department);
			}
		});

		return departmentList;
	}

	@Override
	public int departmentListCount(Department s_department) {
		StringBuffer sql = new StringBuffer("select count(*) as total from t_department");
		if (s_department != null) {
			if (StringUtil.isNotEmpty(s_department.getDeptName())) {
				sql.append(" and deptName like '%" + s_department.getDeptName() + "%'");
			}
		}
		// 两个使用的方法 不同
		return jdbcTemplate.queryForObject(sql.toString().replaceFirst("and", "where"), Integer.class);
	}

	@Override
	public void add(Department department) {
		String sql = "insert into t_department values(null,?,?)";
		jdbcTemplate.update(sql, new Object[] { department.getDeptName(), department.getRemark() });

	}

	@Override
	public void update(Department department) {
		String sql = "update t_department set deptName=?, remark=? where id=?";
		jdbcTemplate.update(sql, new Object[] { department.getDeptName(), department.getRemark(),department.getId() });
	}

	@Override
	public void delete(int id) {
		String sql="delete from t_department where id=?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public Department getDepartmentById(int id) {
		String sql="select * from t_department where id=?";
		final Department department=new Department();
		jdbcTemplate.query(sql, new Object[]{id},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				department.setDeptName(rs.getString("deptName"));
				department.setId(rs.getInt("id"));
				department.setRemark(rs.getString("remark"));
			}
		} );
		return department;
	}

	
}
