package com.molly.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.molly.bean.ClaBean;
import com.molly.service.IClassService;
import com.molly.util.PageUtil;

@Controller
@RequestMapping("/cla")
public class ClaHandler {
	@Autowired
	private IClassService claService;
	
	
//	增加班级
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addClass(@Validated ClaBean claBean,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return list.toString();
		}
		return claService.addClass(claBean);
	}
	
//	分页显示班级信息
	@RequestMapping(value="/show")
	@ResponseBody
	public Map<String, Object> showThisClasses(@Validated PageUtil inUtil,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return null;
		}
		return claService.showThisClasses(inUtil);
	}
	
//	删除班级
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(ClaBean claBean){
		return claService.deleteClass(claBean);
	}
}
