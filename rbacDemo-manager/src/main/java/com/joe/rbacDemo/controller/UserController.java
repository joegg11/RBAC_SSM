package com.joe.rbacDemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joe.rbacDemo.bean.AJAXResult;
import com.joe.rbacDemo.bean.Page;
import com.joe.rbacDemo.bean.User;
import com.joe.rbacDemo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(Integer pageno, Integer pagesize){
		AJAXResult result = new AJAXResult();
		
		try{
			
			//分页查询
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageno-1)*pagesize);
			map.put("size", pagesize);
			
			List<User> users = userService.pageQueryData(map);
			
			//当前页码	
			//总数据条数
			int totalsize = userService.pageQueryCount(map);
			//最大页码（总页码）
			int totalno = 0;
			if(totalsize%pagesize==0){
				totalno = totalsize/pagesize;
			}else{
				totalno = totalsize/pagesize+1;
			}
			
			//分页中的对象
			Page<User> userPage = new Page<User>();
			userPage.setDatas(users);
			userPage.setTotalno(totalno);
			userPage.setTotalsize(totalsize);
			userPage.setPageno(pageno);
			
			result.setData(userPage);
			result.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@RequestMapping("/index")
	public String index(){
		return "user/index";
	}
	
	/**
	 * 用户管理界面的首页展示返回
	 * @return
	 */
	@RequestMapping("/index1")
	public String index1(@RequestParam(required=false, defaultValue="1")Integer pageno, @RequestParam(required=false, defaultValue="5")Integer pagesize, Model model){
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
