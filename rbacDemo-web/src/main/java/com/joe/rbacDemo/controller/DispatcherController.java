package com.joe.rbacDemo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joe.rbacDemo.bean.AJAXResult;
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
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:login";
	}
	
	@RequestMapping("/main")
	public String main(){
		return "main";
	}
	
	@ResponseBody
	@RequestMapping("/doAJAXLogin")
	public Object doAJAXLogin(User user, HttpSession session){
		
		AJAXResult result = new AJAXResult();
		User dbUser = userService.queryForLogin(user);
		
		if(dbUser != null){
			session.setAttribute("loginUser", dbUser);
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/doLogin")
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
