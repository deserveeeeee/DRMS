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

import com.molly.bean.RoomBean;
import com.molly.service.IRoomService;
import com.molly.util.PageUtil;

@Controller
@RequestMapping("/room")
public class RoomHandler {
	@Autowired
	private IRoomService roomService;
	
	
//	展示某一页的房间信息
	@RequestMapping("/showThis")
	@ResponseBody
	public Map<String, Object> showStu(@Validated PageUtil inUtil,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return null;
		}
		return roomService.showThisRooms(inUtil);
	}
	
//	增加房间信息
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addRoom(@Validated RoomBean roomBean,BindingResult result){
		System.out.println("我在请求呀");
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return list.toString();
		}
		return roomService.addRoom(roomBean);
	}
	
	
	
//	删除房间信息
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String deleteRoom(RoomBean roomBean){
		return roomService.deleteRoom(roomBean);
	}
	
	
//	查看房间的详细信息，包括房间里面的住的学生的信息
	@RequestMapping("/showRoom")
	@ResponseBody
	public RoomBean findRoom(RoomBean roomBean){
		return roomService.findRoom(roomBean);
	}
	
	
	
//	通过字段查找某一页的房间们信息
	@RequestMapping(value="/search",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchRooms(RoomBean roomBean,String available, PageUtil inUtil){
		return roomService.searchRooms(roomBean, available, inUtil);
	}
}
