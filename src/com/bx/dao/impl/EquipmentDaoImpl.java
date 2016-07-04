package com.bx.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.bx.dao.EquipmentDao;
import com.bx.model.Equipment;
import com.bx.model.PageBean;
import com.bx.util.StringUtil;

/**
 * @date 2016年3月26日 EquipmentdaoImpl.java
 * @author CZP
 * @parameter
 */
@Repository("equipmentDao")
public class EquipmentDaoImpl implements EquipmentDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Equipment> getEquipmentList(Equipment s_equipment, PageBean pageBean) {
		StringBuffer sql = new StringBuffer("select * from t_equipment t1,t_equipmentType t2  where t1.typeId=t2.id ");
		final List<Equipment> equipmentList = new ArrayList<>();
		if (s_equipment != null) {
			if (StringUtil.isNotEmpty(s_equipment.getName())) {
				sql.append("and t1.name like '%" + s_equipment.getName() + "%'");
			}
		}
		if (pageBean != null) {
			sql.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		jdbcTemplate.query(sql.toString(), new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Equipment equipment = new Equipment();
				equipment.setId(rs.getInt("id"));
				equipment.setName(rs.getString("name"));
				equipment.setNo(rs.getString("no"));
				equipment.setRemark(rs.getString("remark"));
				equipment.setState(rs.getInt("state"));
				equipment.setTypeId(rs.getInt("typeId"));
				equipment.setTypeName(rs.getString("typeName"));
				equipmentList.add(equipment);
			}
		});
		return equipmentList;
	}

	@Override
	public int getListCount(Equipment s_equipment) {
		StringBuffer sql = new StringBuffer("select count(*) from t_equipment ");
		if (s_equipment != null) {
			if (StringUtil.isNotEmpty(s_equipment.getName())) {
				sql.append("and name like '%" + s_equipment.getName() + "%'");
			}
		}
		return jdbcTemplate.queryForObject(sql.toString().replaceFirst("and", "where"), Integer.class);
	}

	@Override
	public void add(Equipment equipment) {
		String sql = "insert into t_equipment values(null,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { equipment.getName(), equipment.getNo(), equipment.getTypeId(),
				equipment.getState(), equipment.getRemark() });
	}

	@Override
	public void update(Equipment equipment) {
		String sql = "update t_equipment set name=?,no=?,typeId=?,state=?,remark=? where id=?";
		jdbcTemplate.update(sql, new Object[] { equipment.getName(), equipment.getNo(), equipment.getTypeId(),
				equipment.getState(), equipment.getRemark(), equipment.getId() });
	}

	@Override
	public void delete(int id) {
		String sql = "delete from t_equipment where id=?";
		jdbcTemplate.update(sql, new Object[] { id });
	}

	@Override
	public Equipment getEquipmentById(int id) {
		String sql = "select * from t_equipment t1,t_equipmentType t2 where t1.typeId=t2.id and t1.id=" + id;
		final Equipment equipment = new Equipment();
		jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				equipment.setId(rs.getInt("id"));
				equipment.setName(rs.getString("name"));
				equipment.setNo(rs.getString("no"));
				equipment.setRemark(rs.getString("remark"));
				equipment.setState(rs.getInt("state"));
				equipment.setTypeId(rs.getInt("typeId"));
				equipment.setTypeName(rs.getString("typeName"));
			}
		});
		return equipment;
	}

	@Override
	public boolean existEquipmentWithTypeId(int typeId) {
		String sql="select count(*) from t_equipment where typeId=?";
		int count=jdbcTemplate.queryForObject(sql,new Object[]{typeId}, Integer.class);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

}
