package com.joe.rbacDemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.joe.rbacDemo.bean.User;

public interface UserDao {

	@Select("select * from t_user")
	List<User> queryAll();

	@Select("select * from t_user where loginacct = #{loginacct} and userpswd = #{userpswd}")
	User queryForLogin(User user);

}
