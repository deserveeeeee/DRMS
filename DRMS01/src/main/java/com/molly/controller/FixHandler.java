package com.molly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.molly.bean.FixBean;
import com.molly.bean.RoomBean;
import com.molly.service.IFixService;

@Controller
@RequestMapping("/fix")
public class FixHandler {
	
	
	@Autowired
	private IFixService fixService;
	
	
//	插入某条信息
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	@ResponseBody
	public String insertFix(@Validated FixBean fixBean,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return list.toString();
		}
		return fixService.insertFix(fixBean);
	}
//	处理某条信息
	@RequestMapping(value="/handle",method=RequestMethod.POST)
	@ResponseBody
	public String handleFix(FixBean fixBean){
		return fixService.handleFix(fixBean);
	}
//	查看某个房间的所有的信息
	@RequestMapping(value="/show")
	@ResponseBody
	public List<FixBean> showAll(RoomBean roomBean){
		return fixService.showAll(roomBean);
	}
}
