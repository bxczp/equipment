package com.bx.service;

import java.util.List;

import com.bx.model.PageBean;
import com.bx.model.User;

/**
 * @date 2016年3月24日 UserService.java
 * @author CZP
 * @parameter
 */
public interface UserService {

	public User login(User user);

	public List<User> getUserList(User s_user, PageBean pageBean);

	public int getUserListCount(User s_user);

	public void add(User user);

	public void update(User user);

	public void delete(int id);

	public User getUserById(int id);
	
	public boolean existUserWithDeptId(int deptId);

}
