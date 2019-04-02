package com.joe.rbacDemo.service;

import java.util.List;

import com.joe.rbacDemo.bean.User;

public interface UserService {

	List<User> queryAll();

	User queryForLogin(User user);

}
