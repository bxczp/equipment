package com.bx.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.bx.dao.UserDao;
import com.bx.model.PageBean;
import com.bx.model.User;
import com.bx.util.StringUtil;

/**
 * @date 2016年3月24日 UserDaoImpl.java
 * @author CZP
 * @parameter
 */
// @Repository对应数据访问层Bean
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	// 来自applicationContext.xml 中的 jdbcTemplate
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public User login(User user) {
		String sql = "select * from t_user where userName = ? and password = ?";
		// 一定要是 final 不然 不能 调用
		final User currentUser = new User();
		// 使用 jdbcTemplate封装的方法
		// 第一个参数 是 要执行的 sql语句 第二个是 要传入的参数 第三个是 回调方法
		jdbcTemplate.query(sql, new Object[] { user.getUserName(), user.getPassword() }, new RowCallbackHandler() {
			// 匿名的回调 函数
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				currentUser.setId(rs.getInt("id"));
				currentUser.setUserName(rs.getString("userName"));
				currentUser.setPassword(rs.getString("password"));
				currentUser.setRoleName(rs.getString("roleName"));
			}
		});
		return currentUser;
	}

	@Override
	public List<User> getUserList(User s_user, PageBean pageBean) {
		StringBuffer sql = new StringBuffer("select * from t_user u,t_department d where u.deptId=d.id");
		final List<User> userList = new ArrayList<>();
		if (s_user != null) {
			if (StringUtil.isNotEmpty(s_user.getUserName())) {
				sql.append(" and u.userName like '%" + s_user.getUserName() + "%'");
			}
		}
		if (pageBean != null) {
			sql.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
		}
		jdbcTemplate.query(sql.toString(), new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				User u = new User();
				u.setDeptId(rs.getInt("deptId"));
				u.setDeptName(rs.getString("deptName"));
				u.setId(rs.getInt("id"));
				u.setPassword(rs.getString("password"));
				u.setRoleName(rs.getString("roleName"));
				u.setTrueName(rs.getString("trueName"));
				u.setUserName(rs.getString("userName"));
				userList.add(u);
			}
		});
		return userList;
	}

	@Override
	public int getUserListCount(User s_user) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from t_user ");
		if (s_user != null) {
			if (StringUtil.isNotEmpty(s_user.getUserName())) {
				sql.append("and userName like '%" + s_user.getUserName() + "%'");
			}
		}
		return jdbcTemplate.queryForObject(sql.toString().replaceFirst("and", "where"), Integer.class);
	}

	@Override
	public void add(User user) {
		String sql = "insert into t_user values(null,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { user.getUserName(), user.getPassword(), user.getTrueName(),
				user.getRoleName(), user.getDeptId() });
	}

	@Override
	public void update(User user) {
		String sql = "update t_user set userName=?,password=?,trueName=?,roleName=?,deptId=? where id=?";
		jdbcTemplate.update(sql, new Object[] { user.getUserName(), user.getPassword(), user.getTrueName(),
				user.getRoleName(), user.getDeptId(), user.getId() });
	}

	@Override
	public void delete(int id) {
		String sql = "delete from t_user where id=?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public User getUserById(int id) {
		String sql = "select * from t_user u,t_department d where u.deptId=d.id and u.id=?";
		final User user = new User();
		jdbcTemplate.query(sql, new Object[] { id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setDeptId(rs.getInt("deptId"));
				user.setDeptName(rs.getString("deptName"));
				user.setId(rs.getInt("id"));
				user.setPassword(rs.getString("password"));
				user.setRoleName(rs.getString("roleName"));
				user.setTrueName(rs.getString("trueName"));
				user.setUserName(rs.getString("userName"));
			}
		});
		return user;
	}

	@Override
	public boolean existUserWithDeptId(int deptId) {
		String sql = "select count(*) from t_user u,t_department d where d.id=u.deptId and d.id=" + deptId;
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

}
