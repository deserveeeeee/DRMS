package com.molly.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.molly.bean.UserBean;
import com.molly.service.IUserService;
import com.molly.util.PageUtil;

@Controller
@RequestMapping("/um")
public class UMHandler {
	
	@Autowired
	private IUserService userService;
	
	
//	分页显示所有的users
	@RequestMapping(value="/showThis")
	@ResponseBody
	public Map<String, Object> showThisUsers(@Validated PageUtil inUtil,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return null;
		}
		return userService.showThisUsers(inUtil);
	}
//	展示某个用户信息
	@RequestMapping(value="/showUser")
	@ResponseBody
	public UserBean showUser(UserBean bean){
		return userService.showUser(bean);
	}
//	改变某个用户的权限
	@RequestMapping(value="/set",method=RequestMethod.POST)
	@ResponseBody
	public String setAuth(UserBean bean){
		return userService.setAuth(bean);
	}
//	删除某个用户
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String deleteUser(UserBean bean){
		return userService.deleteUser(bean);
	}
//  查询某个用户
	@RequestMapping(value="/search",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> search(UserBean bean,@Validated PageUtil inUtil,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return null;
		}
		return userService.searchUsers(bean, inUtil);
	}
}
