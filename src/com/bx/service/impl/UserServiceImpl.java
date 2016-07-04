package com.bx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bx.dao.UserDao;
import com.bx.model.PageBean;
import com.bx.model.User;
import com.bx.service.UserService;

/**
 *@date 2016年3月24日
 * UserServiceImpl.java
 *@author CZP
 *@parameter
 */

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserDao userDao;

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public List<User> getUserList(User s_user, PageBean pageBean) {
		return userDao.getUserList(s_user, pageBean);
	}

	@Override
	public int getUserListCount(User s_user) {
		return userDao.getUserListCount(s_user);
	}

	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public boolean existUserWithDeptId(int deptId) {
		return userDao.existUserWithDeptId(deptId);
	}

}
