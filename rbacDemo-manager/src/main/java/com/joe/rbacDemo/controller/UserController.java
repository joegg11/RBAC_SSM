package com.joe.rbacDemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joe.rbacDemo.bean.User;
import com.joe.rbacDemo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	/**
	 * 用户管理界面的首页展示返回
	 * @return
	 */
	@RequestMapping("/index")
	public String index(@RequestParam(required=false, defaultValue="1")Integer pageno, @RequestParam(required=false, defaultValue="5")Integer pagesize, Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (pageno-1)*pagesize);
		map.put("size", pagesize);
		
		List<User> users = userService.pageQueryData(map);
		model.addAttribute("users", users);
		
		//当前页码
		model.addAttribute("pageno", pageno);
		//总数据条数
		int totalsize = userService.pageQueryCount(map);
		//最大页码（总页码）
		int totalno = 0;
		if(totalsize%pagesize==0){
			totalno = totalsize/pagesize;
		}else{
			totalno = totalsize/pagesize+1;
		}
		model.addAttribute("totalno", totalno);
		
		return "user/index";
	}
}
