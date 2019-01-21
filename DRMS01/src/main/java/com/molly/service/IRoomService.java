package com.molly.service;

import java.util.Map;

import com.molly.bean.RoomBean;
import com.molly.util.PageUtil;

public interface IRoomService {
	
	
	public Map<String, Object> showThisRooms(PageUtil inUtil);
	public String addRoom(RoomBean roomBean);
	public String deleteRoom(RoomBean roomBean);
	public RoomBean findRoom(RoomBean roomBean);
	public Map<String, Object> searchRooms(RoomBean roomBean,String available,PageUtil inUtil);
}
