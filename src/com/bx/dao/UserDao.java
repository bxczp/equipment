package com.bx.dao;
import java.util.List;

import com.bx.model.Department;
import com.bx.model.PageBean;
/**
 *@date 2016年3月24日
 * UserDao.java
 *@author CZP
 *@parameter
 */
import com.bx.model.User;
public interface UserDao {
	
	public User login(User user);

	public List<User> getUserList(User s_user,PageBean pageBean);
	
	public int getUserListCount(User s_user);
	
	public void add(User user);
	
	public void update(User user);
	
	public void delete(int id);
	
	public User getUserById(int id);
	
	public boolean existUserWithDeptId(int deptId);
	
	
}
