package com.joe.rbacDemo.service;

import java.util.List;
import java.util.Map;

import com.joe.rbacDemo.bean.User;

public interface UserService {

	List<User> queryAll();

	User queryForLogin(User user);

	List<User> pageQueryData(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	void insertUser(User user);

}
