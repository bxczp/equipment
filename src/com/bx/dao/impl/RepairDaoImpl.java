package com.bx.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.bx.dao.RepairDao;
import com.bx.model.PageBean;
import com.bx.model.Repair;
import com.bx.util.DateUtil;
import com.bx.util.StringUtil;

/**
 * @date 2016年3月26日 RepairDaoImpl.java
 * @author CZP
 * @parameter
 */

@Repository("repairDao")
public class RepairDaoImpl implements RepairDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(Repair repair) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_repair values(null,?,?,null,now(),null,0,1)");
		jdbcTemplate.update(sql.toString(), new Object[] { repair.getEquipmentId(), repair.getUserMan() });

	}

	@Override
	public List<Repair> getRepairList(Repair s_repair, PageBean pageBean) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_repair r,t_equipment e where r.equipmentId=e.id ");
		if (s_repair != null) {
			if (StringUtil.isNotEmpty(s_repair.getEquipmentName())) {
				sql.append(" and name like '%" + s_repair.getEquipmentName() + "%'");
			}
			if (s_repair.getFinishState() != null) {
				sql.append(" and r.finishState = " + s_repair.getFinishState());
			}
		}
		if (pageBean != null) {
			sql.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		final List<Repair> repairList = new ArrayList<>();
		jdbcTemplate.query(sql.toString(), new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Repair repair = new Repair();
				repair.setEquipmentId(rs.getInt("equipmentId"));
				repair.setEquipmentName(rs.getString("name"));
				repair.setEquipmentNo(rs.getInt("no"));
				repair.setFinishState(rs.getInt("finishState"));
				repair.setId(rs.getInt("id"));
				repair.setRepairMan(rs.getString("repairMan"));
				repair.setState(rs.getInt("state"));
				repair.setUserMan(rs.getString("userMan"));
				/**
				 * G 年代标志符 y 年 M 月 d 日 h 时 在上午或下午 (1~12) H 时 在一天中 (0~23) m 分 s 秒
				 * S 毫秒 E 星期 D 一年中的第几天 F 一月中第几个星期几 w 一年中第几个星期 W 一月中第几个星期 a 上午 /
				 * 下午 标记符 k 时 在一天中 (1~24) K 时 在上午或下午 (0~11) z 时区
				 */

				repair.setRepairTime(DateUtil.formatString(rs.getString("repairTime"), "yyyy-MM-dd HH:mm:ss"));
				repair.setFinishTime(DateUtil.formatString(rs.getString("finishTime"), "yyyy-MM-dd HH:mm:ss"));
				repairList.add(repair);
			}

		});
		return repairList;
	}

	@Override
	public int getRepairListCount(Repair s_repair) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from t_repair r,t_equipment e where r.equipmentId=e.id ");
		if (s_repair != null) {
			if (StringUtil.isNotEmpty(s_repair.getEquipmentName())) {
				sql.append(" and name like '%" + s_repair.getEquipmentName() + "%'");
			}
			if (s_repair.getFinishState() != null) {
				sql.append(" and r.finishState = " + s_repair.getFinishState());
			}
		}
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}

	@Override
	public void update(Repair repair) {
		String sql = "update t_repair set repairMan =? ,state=?,finishTime=now(),finishState=2 where id=?";
		jdbcTemplate.update(sql, new Object[] { repair.getRepairMan(), repair.getState(), repair.getId() });
	}

	@Override
	public Repair getRepairById(int id) {
		String sql = "select * from t_repair r,t_equipment e where r.equipmentId=e.id and r.id= " + id;
		final Repair repair=new Repair();
		jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				repair.setEquipmentId(rs.getInt("equipmentId"));
				repair.setEquipmentName(rs.getString("name"));
				repair.setEquipmentNo(rs.getInt("no"));
				repair.setFinishState(rs.getInt("finishState"));
				repair.setId(rs.getInt("id"));
				repair.setRepairMan(rs.getString("repairMan"));
				repair.setState(rs.getInt("state"));
				repair.setUserMan(rs.getString("userMan"));
				repair.setRepairTime(DateUtil.formatString(rs.getString("repairTime"), "yyyy-MM-dd HH:mm:ss"));
				repair.setFinishTime(DateUtil.formatString(rs.getString("finishTime"), "yyyy-MM-dd HH:mm:ss"));
			}
		});
		
		return repair;
	}

}
