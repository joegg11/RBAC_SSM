package com.joe.rbacDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joe.rbacDemo.bean.User;
import com.joe.rbacDemo.service.UserService;

@Controller
public class DispatcherController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("doLogin")
	public String doLogin(User user, Model model){
		
		User dbUser = userService.queryForLogin(user);
		
		if(dbUser != null){
			return "main";
		}else{
			String errorMsg = "账号或密码错误，请重新输入";
			model.addAttribute("errorMsg", errorMsg);
			return "redirect:login";
		}
	}
}
