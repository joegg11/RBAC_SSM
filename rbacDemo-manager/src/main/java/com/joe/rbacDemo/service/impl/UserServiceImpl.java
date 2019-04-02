package com.joe.rbacDemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joe.rbacDemo.bean.User;
import com.joe.rbacDemo.dao.UserDao;
import com.joe.rbacDemo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return userDao.queryAll();
	}

	public User queryForLogin(User user) {
		// TODO Auto-generated method stub
		return userDao.queryForLogin(user);
	}
	
}
