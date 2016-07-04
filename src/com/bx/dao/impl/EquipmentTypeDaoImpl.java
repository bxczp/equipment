package com.bx.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.bx.dao.EquipmentTypeDao;
import com.bx.model.EquipmentType;
import com.bx.model.PageBean;
import com.bx.util.StringUtil;

/**
 * @date 2016年3月25日 EquipmentTypeDaoImpl.java
 * @author CZP
 * @parameter
 */
@Repository("equipmentTypeDao")
public class EquipmentTypeDaoImpl implements EquipmentTypeDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<EquipmentType> getEquipmentTypeList(EquipmentType s_EquipmentType, PageBean pageBean) {
		StringBuffer sql = new StringBuffer("select * from t_equipmentType ");
		final List<EquipmentType> EquipmentTypeList = new ArrayList<>();
		if (s_EquipmentType != null) {
			if (StringUtil.isNotEmpty(s_EquipmentType.getTypeName())) {
				sql.append(" and typeName like '%" + s_EquipmentType.getTypeName() + "%'");
			}
		}
		if (pageBean != null) {
			sql.append("limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		jdbcTemplate.query(sql.toString().replaceFirst("and", "where"), new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				EquipmentType EquipmentType = new EquipmentType();
				EquipmentType.setTypeName(rs.getString("typeName"));
				EquipmentType.setId(rs.getInt("id"));
				EquipmentType.setRemark(rs.getString("remark"));
				EquipmentTypeList.add(EquipmentType);
			}
		});

		return EquipmentTypeList;
	}

	@Override
	public int EquipmentTypeListCount(EquipmentType s_EquipmentType) {
		StringBuffer sql = new StringBuffer("select count(*) as total from t_equipmentType");
		if (s_EquipmentType != null) {
			if (StringUtil.isNotEmpty(s_EquipmentType.getTypeName())) {
				sql.append(" and typeName like '%" + s_EquipmentType.getTypeName() + "%'");
			}
		}
		// 两个使用的方法 不同
		return jdbcTemplate.queryForObject(sql.toString().replaceFirst("and", "where"), Integer.class);
	}

	@Override
	public void add(EquipmentType EquipmentType) {
		String sql = "insert into t_equipmentType values(null,?,?)";
		jdbcTemplate.update(sql, new Object[] { EquipmentType.getTypeName(), EquipmentType.getRemark() });

	}

	@Override
	public void update(EquipmentType EquipmentType) {
		String sql = "update t_equipmentType set typeName=?, remark=? where id=?";
		jdbcTemplate.update(sql,
				new Object[] { EquipmentType.getTypeName(), EquipmentType.getRemark(), EquipmentType.getId() });
	}

	@Override
	public void delete(int id) {
		String sql = "delete from t_equipmentType where id=?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public EquipmentType getEquipmentTypeById(int id) {
		String sql = "select * from t_equipmentType where id=?";
		final EquipmentType EquipmentType = new EquipmentType();
		jdbcTemplate.query(sql, new Object[] { id }, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				EquipmentType.setTypeName(rs.getString("typeName"));
				EquipmentType.setId(rs.getInt("id"));
				EquipmentType.setRemark(rs.getString("remark"));
			}
		});
		return EquipmentType;
	}

}
