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

import com.molly.bean.ClaBean;
import com.molly.bean.RoomBean;
import com.molly.bean.StuBean;
import com.molly.service.IStuService;
import com.molly.util.PageUtil;

@Controller
@RequestMapping("/stu")
public class StuHandler {
	@Autowired
	private IStuService stuService;
	
//	预插入学生信息
	@RequestMapping("/reinsert")
	@ResponseBody
	public Map<String, Object> reInsertStu(){
		return stuService.reInsertStu();
	}
//	插入新的学生信息
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	@ResponseBody
	public String insertStu(@Validated StuBean stuBean,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return list.toString();
		}
		return stuService.insertStu(stuBean);
	}
//	展示某一页的学生信息
	@RequestMapping("/showThis")
	@ResponseBody
	public Map<String, Object> showStu(@Validated PageUtil inUtil,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return null;
		}
		return stuService.showStu(inUtil);
	}
//	删除某一个学生，办理学生退房手续
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteStu(@Validated PageUtil inUtil,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return null;
		}
		return stuService.showStu(inUtil);
	}
//	预改变房间
	@RequestMapping("/rechange")
	@ResponseBody
	public List<RoomBean> reChangeRoom(StuBean stuBean){
		return stuService.reChangeRoom(stuBean);
	}
//	某个学生换房间
	@RequestMapping(value="/change",method=RequestMethod.POST)
	@ResponseBody
	public String changeRoom(StuBean stuBean,RoomBean roomBean) {
		return stuService.changeRoom(stuBean, roomBean);
	}
//	展示某个学生的详细信息
	@RequestMapping("/showstu")
	@ResponseBody
	public StuBean showStu(StuBean stuBean){
		return stuService.showStu(stuBean);
	}
//	通过字段检索某些学生并且分页显示
	@RequestMapping(value="/search",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchStu(StuBean stuBean, PageUtil inUtil){
		return stuService.searchStu(stuBean, inUtil);
	}
}
